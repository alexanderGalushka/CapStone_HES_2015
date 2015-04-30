package edu.harvard.cscie99.adam.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.harvard.cscie99.adam.error.UnauthorizedOperationException;
import edu.harvard.cscie99.adam.model.Plate;
import edu.harvard.cscie99.adam.model.ResultSnapshot;
import edu.harvard.cscie99.adam.model.view.mapper.PlateMapper;
import edu.harvard.cscie99.adam.profile.User;
import edu.harvard.cscie99.adam.service.AuthenticationService;
import edu.harvard.cscie99.adam.service.ParserService;
import edu.harvard.cscie99.adam.service.PlateService;
import edu.harvard.cscie99.adam.service.ResultService;

/**
 * PlateController object
 * 
 * Controller that handles request from the frontend to perform 
 * plate related operations (create, remove, update, delete, list)
 * Methods use RESTful calls
 * 
 * @author Gerson
 *
 */
@RestController
@RequestMapping(value = "/")
public class PlateController {
	
	/**
	 * PlateService object
	 * Uses Spring IoC to instantiate PlateService in runtime
	 */
	@Autowired
	private PlateService plateService;

	/**
	 * ResultService object
	 * Uses Spring IoC to instantiate ResultService in runtime
	 */
	@Autowired
	private ResultService resultService;

	/**
	 * ParserService object
	 * Uses Spring IoC to instantiate ParserService in runtime
	 */
	@Autowired
	private ParserService parserService;
	
	/**
	 * ParserService object
	 * Uses Spring IoC to instantiate AuthenticationService in runtime
	 */
	@Autowired
	private AuthenticationService authService;
	
	/**
	 * List Plates method
	 * 
	 * Lists all valid plates previously inserted (via Plate Editor or importing CSV files)
	 * in the application.
	 * 
	 * @return list of Plate objects
	 */
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
	
	/**
	 * getPlate method
	 * 
	 * Retrieve all details from a unique Plate, using the plate Id (key)
	 * 
	 * @param plateId - key of Plate object in DB
	 * @return Plate object in JSON format
	 */
	@RequestMapping(value = "/rest/plate/{plate_id}", method = RequestMethod.GET)
	@ResponseBody
	public edu.harvard.cscie99.adam.model.view.Plate getPlate(
			@PathVariable("plate_id") int plateId){
		
		Plate plate = plateService.retrievePlate(plateId);
		
		return PlateMapper.getViewPlate(plate);
	}
	
	/**
	 * createPlate method
	 * 
	 * Creates a new Plate and persists in the DB.
	 * The method will map the input Plate view object into Plate persistence object using PlateMapper,
	 * persist the object in DB, and returns the generated plate, mapping back to the Plate view
	 * (friendly version of Plate used by the frontend)
	 * 
	 * @param viewPlate - Plate (view) object with all plate attributes
	 * @return Plate object in JSON format
	 */
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
	
	/**
	 * updatePlate method
	 * 
	 * Updates an existing Plate in the DB.
	 * The method will map the input Plate view object into Plate persistence object using PlateMapper,
	 * persist the object in DB, and returns the generated plate, mapping back to the Plate view
	 * (friendly version of Plate used by the frontend)
	 * 
	 * @param viewPlate - Plate (view) object with all plate attributes
	 * @param plateId - plate Key in DB
	 * @return Plate object in JSON format
	 */
	@RequestMapping(value = "/rest/plate/{plate_id}", method = RequestMethod.PUT)
	@ResponseBody
	public edu.harvard.cscie99.adam.model.view.Plate updatePlate(@RequestBody edu.harvard.cscie99.adam.model.view.Plate viewPlate,
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
		currentPlate.setControlTypes(plate.getControlTypes());
		
		return PlateMapper.getViewPlate(plateService.updatePlate(currentPlate));
	}
	
	/**
	 * removePlate method
	 * 
	 * Removes the Plate from Adam's system.
	 * 
	 * This method will logically remove the plate by setting the "valid" attribute to false,
	 * keeping the original information in DB for later reuse. But from the user's perspective,
	 * the plate will no longer appear in the system.
	 * 
	 * @param plateId - plate Key in DB
	 * @return boolean - indication of success or failure
	 */
	@RequestMapping(value = "/rest/plate/{plate_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public boolean removePlate(@PathVariable("plate_id") int plateId){
		
		Plate plate = plateService.retrievePlate(plateId);
		
		return plateService.removePlate(plate);
	}
	
	/**
	 * addResultToPlate method
	 * 
	 * Associates an ResultSnapshot object (refer to the documentation) to a Plate.
	 * 
	 * RESTFul method. Will return a boolean indicating the success of the operation
	 * 
	 * @param plateId - plate Key in DB
	 * @param resultId - result Key in DB
	 * @return boolean - indication of success or failure
	 */
	@RequestMapping(value = "/rest/plate/{plateId}/add_result/{resultId}", method = RequestMethod.POST)
	@ResponseBody
	public boolean addResultToPlate(
			@PathVariable("plateId") int plateId,
			@PathVariable("resultId") int resultId) throws UnauthorizedOperationException{
			
		Plate plate = plateService.retrievePlate(plateId);
		ResultSnapshot result = resultService.retrieveResult(resultId);
		plate.getResults().add(result);
		
		plateService.updatePlate(plate);
		
		return true;
	}
	
	/**
	 * removeResultFromPlate method
	 * 
	 * Removes the association between the ResultSnapshot object (refer to the documentation) and the Plate.
	 * 
	 * RESTFul method. Will return a boolean indicating the success of the operation
	 * 
	 * @param plateId - plate Key in DB
	 * @param resultId - result Key in DB
	 * @return boolean - indication of success or failure
	 */
	@RequestMapping(value = "/rest/plate/{plateId}/remove_result/{resultId}", method = RequestMethod.POST)
	@ResponseBody
	public boolean removeResultFromPlate(
			@PathVariable("plateId") int plateId,
			@PathVariable("resultId") int resultId) throws UnauthorizedOperationException{
			
		Plate plate = plateService.retrievePlate(plateId);
		ResultSnapshot result = resultService.retrieveResult(resultId);
		plate.getResults().remove(result);
		
		plateService.updatePlate(plate);
		
		return true;
		
	}
}
