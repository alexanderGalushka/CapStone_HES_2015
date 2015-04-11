package edu.harvard.cscie99.adam.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

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
import edu.harvard.cscie99.adam.model.Plate;
import edu.harvard.cscie99.adam.service.AuthenticationService;
import edu.harvard.cscie99.adam.service.ParserService;
import edu.harvard.cscie99.adam.service.PlateService;

@RestController
@RequestMapping(value = "/")
public class PlateController {
	
	//TODO implement

	@Autowired
	private PlateService plateService;

	@Autowired
	private ParserService parserService;
	
	@Autowired
	private AuthenticationService authService;
	
	public static final String C_PLATE_FILE_PATH = "/home/adam_files/plates/";
//	public static final String C_PLATE_FILE_PATH = "c:/adam_files/plates/";
	
	// Plate CRUD - START
	@RequestMapping(value = "/plate", method = RequestMethod.GET)
	@ResponseBody
	public List<Plate> listPlates(){
		
		return plateService.listPlates();
	}
	
	@RequestMapping(value = "/plate/{plate_id}", method = RequestMethod.GET)
	@ResponseBody
	public Plate getPlate(
			@PathVariable("plate_id") int plateId){
		
		return plateService.retrievePlate(plateId);
	}
	
	@RequestMapping(value = "/plate", method = RequestMethod.POST)
	@ResponseBody
	public Plate createPlate(@RequestBody Plate plate){
		
		return plateService.createPlate(plate);
	}
	
	@RequestMapping(value = "/plate", method = RequestMethod.PUT)
	@ResponseBody
	public Plate editPlate(@RequestBody Plate plate){
		
		return plateService.editPlate(plate);
	}
	
	@RequestMapping(value = "/plate", method = RequestMethod.DELETE)
	@ResponseBody
	public boolean removePlate(@RequestBody Plate plate){
		
		return plateService.removePlate(plate);
	}
	// Plate - END
	
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
}
