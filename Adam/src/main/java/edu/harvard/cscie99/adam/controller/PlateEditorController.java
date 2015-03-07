package edu.harvard.cscie99.adam.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.harvard.cscie99.adam.model.Plate;
import edu.harvard.cscie99.adam.service.AuthenticationService;
import edu.harvard.cscie99.adam.service.PlateEditorService;

@RestController
@RequestMapping(value = "/plate/editor/")
public class PlateEditorController {
	
	//TODO implement
	
	@Autowired
	private PlateEditorService plateEditorService;
	
	@Autowired
	private AuthenticationService authService;
	
	/**
	 * Saves the Plate object into the DB. (FR 3.1)
	 * 
	 * @param plate
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public boolean savePlate(
			@RequestParam(value="plate", required=true) Plate plate,
			@RequestParam(value="user", required=true) String user){
		
		//TODO
		return true;
	}
	
	/**
	 * Saves the Plate object into the DB. (FR 3.1)
	 * 
	 * @param plate
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "load/{plateId}", method = RequestMethod.POST)
	@ResponseBody
	public Plate loadPlate(
			@RequestParam(value="user", required=true) String user){
		
		//TODO
		return new Plate();
	}
	
	/**
	 * Searches for plates (FR3.12)
	 * 
	 * @param plate
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "search", method = RequestMethod.POST)
	@ResponseBody
	public List<Plate> searchPlate(
			@RequestParam(value="fromDate", required=true) Date fromDate,
			@RequestParam(value="toDate", required=true) Date toDate,
			@RequestParam(value="params", required=false) HashMap<String, Object> searchParams,
			@RequestParam(value="user", required=true) String user){
		
		//TODO
		ArrayList<Plate> plates = new ArrayList<Plate>();
		return plates;
	}

}
