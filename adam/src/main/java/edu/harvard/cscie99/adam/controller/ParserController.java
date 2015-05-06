package edu.harvard.cscie99.adam.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.harvard.cscie99.adam.error.InvalidPlateFileException;
import edu.harvard.cscie99.adam.error.ParserException;
import edu.harvard.cscie99.adam.error.SessionTimeouException;
import edu.harvard.cscie99.adam.error.UnauthorizedOperationException;
import edu.harvard.cscie99.adam.model.Plate;
import edu.harvard.cscie99.adam.model.QCplate;
import edu.harvard.cscie99.adam.model.ResultSnapshot;
import edu.harvard.cscie99.adam.service.AuthenticationService;
import edu.harvard.cscie99.adam.service.ParserService;
import edu.harvard.cscie99.adam.service.PlateService;
import edu.harvard.cscie99.adam.service.QualityControlService;
import edu.harvard.cscie99.adam.service.ResultService;

/**
 * ParseController class
 * 
 * Intercepts method calls from frontend related to CSV file parsing.
 * Uses REST method calls, serving and retrieving content in JSON format.
 * 
 * @author Gerson
 *
 */
@RestController
@RequestMapping(value = "/")
public class ParserController {

	/**
	 * ParseService reference
	 * Injected by Spring container in runtime (Inversion of Control)
	 */
	@Autowired
	private ParserService parserService;

	/**
	 * PlateService reference
	 */
	@Autowired
	private PlateService plateService;

	/**
	 * AuthenticationService reference (Spring IoC)
	 */
	@Autowired
	private AuthenticationService authService;
	
	/**
	 * QualityControlService reference (Spring IoC)
	 */
	@Autowired
	private QualityControlService qualityControlService;
	
	/**
	 * ResultService reference (Spring IoC)
	 */
	@Autowired
	private ResultService resultService;
	
	public static final String C_RESULT_FILE_PATH = "/home/adam_files/results/";
	public static final String C_PLATE_FILE_PATH = "/home/adam_files/plates/";
//	public static final String C_PLATE_FILE_PATH = "c:/adam_files/plates/";
//	public static final String C_RESULT_FILE_PATH = "c:/adam_files/results/";	

	/**
	 * handlePlateUpload method
	 * 
	 * Method used by frontend to upload a plate CSV file into Adam's DB.
	 * It uses the multi-part encryption type for submitting files. The filesize limit is specified
	 * in the Web Containers configuration file.
	 * 
	 * After upload, the file is saved locally (raw data) as backup, and parsed/cleaned before DB insertion.
	 * 
	 * @param name
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws ParserException
	 * @throws InvalidPlateFileException
	 */
	@RequestMapping(value="/upload_plate", method=RequestMethod.POST)
	public @ResponseBody Plate handlePlateUpload(
			@RequestParam("name") String name,
			@RequestParam("file") MultipartFile file) throws IOException, ParserException, InvalidPlateFileException{
		
		Plate plate = null;
		
		if (file != null && !file.isEmpty()){
		
			byte[] bytes = file.getBytes();
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(C_PLATE_FILE_PATH + name)));
			stream.write(bytes);
			stream.close();
		
			plate = parserService.parsePlateFromFile(C_PLATE_FILE_PATH + name);
		}
		
		return plateService.createPlate(plate);
	}
	
	/**
	 * handleResultUpload method
	 * 
	 * Method used by frontend to upload results CSV file into Adam's DB.
	 * It uses the multi-part encryption type for submitting files. The filesize limit is specified
	 * in the Web Containers configuration file.
	 * 
	 * @param plateId - Plate associated with result
	 * @param file - Multipart file stream
	 * @return resultSnapshot object in JSON format - the persisted result object
	 * @throws IOException
	 * @throws ParserException
	 */
	@RequestMapping(value="/upload_result", method=RequestMethod.POST)
	public @ResponseBody QCplate handleResultUpload(
			@RequestParam(value="plate_id", required=true) int plateId,
			@RequestParam(value="file", required=true) MultipartFile file) throws IOException, ParserException{
		
		ResultSnapshot resultSnapshot = null;
		
		String name = file.getName();
		
		if (file != null && !file.isEmpty()){
		
			byte[] bytes = file.getBytes();
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(C_RESULT_FILE_PATH + name)));
			stream.write(bytes);
			stream.close();
		
			resultSnapshot = parserService.parseResultsFromFile(C_RESULT_FILE_PATH + name);
		}
		
		resultService.saveResultSnapshot(resultSnapshot);
		
		//Associating results to plate
		Plate plate = plateService.retrievePlate(plateId);
		plate.getResults().add(resultSnapshot);
		plateService.updatePlate(plate);
		
		return qualityControlService.qualifyDataPerPlate(plateId);
	}
}
