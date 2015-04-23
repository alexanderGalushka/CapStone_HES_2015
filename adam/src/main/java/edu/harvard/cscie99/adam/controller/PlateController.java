package edu.harvard.cscie99.adam.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.harvard.cscie99.adam.error.ParserException;
import edu.harvard.cscie99.adam.error.UnauthorizedOperationException;
import edu.harvard.cscie99.adam.model.Plate;
import edu.harvard.cscie99.adam.model.ResultSnapshot;
import edu.harvard.cscie99.adam.model.view.mapper.PlateMapper;
import edu.harvard.cscie99.adam.profile.User;
import edu.harvard.cscie99.adam.service.AuthenticationService;
import edu.harvard.cscie99.adam.service.ParserService;
import edu.harvard.cscie99.adam.service.PlateService;
import edu.harvard.cscie99.adam.service.ResultService;

@RestController
@RequestMapping(value = "/")
public class PlateController {
	
	//TODO implement

	@Autowired
	private PlateService plateService;

	@Autowired
	private ResultService resultService;

	@Autowired
	private ParserService parserService;
	
	@Autowired
	private AuthenticationService authService;
	
	public static final String C_PLATE_FILE_PATH = "/home/adam_files/plates/";
//	public static final String C_PLATE_FILE_PATH = "c:/adam_files/plates/";
	
	// Plate CRUD - START
	@RequestMapping(value = "/rest/plate", method = RequestMethod.GET)
	@ResponseBody
	public List<edu.harvard.cscie99.adam.model.view.Plate> listPlates(){
		
		List<Plate> plates = plateService.listPlates();
		
		ArrayList<edu.harvard.cscie99.adam.model.view.Plate> viewPlates = new ArrayList<>();
		
		for (Plate _plate : plates){
			viewPlates.add(PlateMapper.getViewPlate(_plate));
		}
		return viewPlates;
	}
	
	@RequestMapping(value = "/rest/plate/{plate_id}", method = RequestMethod.GET)
	@ResponseBody
	public edu.harvard.cscie99.adam.model.view.Plate getPlate(
			@PathVariable("plate_id") int plateId){
		
		Plate plate = plateService.retrievePlate(plateId);
		
		return PlateMapper.getViewPlate(plate);
	}
	
	@RequestMapping(value = "/rest/plate", method = RequestMethod.POST)
	@ResponseBody
	public edu.harvard.cscie99.adam.model.view.Plate createPlate(@RequestBody edu.harvard.cscie99.adam.model.view.Plate viewPlate,
			HttpServletRequest request){
		
		Plate plate = PlateMapper.getPersistencePlate(viewPlate);
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
		
		User owner = authService.getCurrentUser(request);
		plate.setOwner(owner.getUsername());
		plate.setCreationDate(dateFormat.format(new Date()));
		
		return PlateMapper.getViewPlate(plateService.createPlate(plate));
	}
	
	@RequestMapping(value = "/rest/plate/{plate_id}", method = RequestMethod.PUT)
	@ResponseBody
	public edu.harvard.cscie99.adam.model.view.Plate editPlate(@RequestBody edu.harvard.cscie99.adam.model.view.Plate viewPlate,
			@PathVariable("plate_id") int plateId,
			HttpServletRequest request){
		
		Plate plate = PlateMapper.getPersistencePlate(viewPlate);
		
		Plate currentPlate = plateService.retrievePlate(plateId);
		currentPlate.setBarcode(plate.getBarcode());
		currentPlate.setLabel(plate.getLabel());
		currentPlate.setName(plate.getName());
		currentPlate.setNumberOfColumns(plate.getNumberOfColumns());
		currentPlate.setNumberOfRows(plate.getNumberOfRows());
		currentPlate.setTags(plate.getTags());
		currentPlate.setWellLabels(plate.getWellLabels());
		currentPlate.setProtocolId(plate.getProtocolId());
		currentPlate.setWells(plate.getWells());
		
		return PlateMapper.getViewPlate(plateService.editPlate(currentPlate));
	}
	
	@RequestMapping(value = "/rest/plate/{plate_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public boolean removePlate(@PathVariable("plate_id") int plateId){
		
		Plate plate = plateService.retrievePlate(plateId);
		
		return plateService.removePlate(plate);
	}
	
	//Upload plate
	@RequestMapping(value="/upload_plate", method=RequestMethod.POST)
	public @ResponseBody Plate handlePlateUpload(
			@RequestParam("name") String name,
			@RequestParam("file") MultipartFile file) throws IOException, ParserException{
		
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
	
	//Upload plate
	@RequestMapping(value="/upload_plate_with_result", method=RequestMethod.POST)
	public @ResponseBody Plate handlePlateAndResultUpload(
			@RequestParam("name") String name,
			@RequestParam("file") MultipartFile file) throws IOException, ParserException{
		
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
	
	@RequestMapping(value = "/rest/plate/{plateId}/add_result/{resultId}", method = RequestMethod.POST)
	@ResponseBody
	public boolean addResultToPlate(
			@PathVariable("plateId") int plateId,
			@PathVariable("resultId") int resultId) throws UnauthorizedOperationException{
			
		Plate plate = plateService.retrievePlate(plateId);
		ResultSnapshot result = resultService.retrieveResult(resultId);
		plate.getResults().add(result);
		
		plateService.editPlate(plate);
		
		return true;
	}
	
	@RequestMapping(value = "/rest/plate/{plateId}/remove_result/{resultId}", method = RequestMethod.POST)
	@ResponseBody
	public boolean removeResultFromPlate(
			@PathVariable("plateId") int plateId,
			@PathVariable("resultId") int resultId) throws UnauthorizedOperationException{
			
		Plate plate = plateService.retrievePlate(plateId);
		ResultSnapshot result = resultService.retrieveResult(resultId);
		plate.getResults().remove(result);
		
		plateService.editPlate(plate);
		
		return true;
		
	}
}
