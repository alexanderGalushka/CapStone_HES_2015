package edu.harvard.cscie99.adam.controller;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.harvard.cscie99.adam.error.UnauthorizedOperationException;
import edu.harvard.cscie99.adam.model.PlateValidationContainer;
import edu.harvard.cscie99.adam.model.QCplate;
import edu.harvard.cscie99.adam.model.WellValidationContainer;
import edu.harvard.cscie99.adam.service.AuthenticationService;
import edu.harvard.cscie99.adam.service.ParserService;
import edu.harvard.cscie99.adam.service.PlateService;
import edu.harvard.cscie99.adam.service.QualityControlService;

@RestController
@RequestMapping(value = "/")
public class QualityControlController 
{

	@Autowired
	private QualityControlService qualityControlService;

	@Autowired
	private ParserService parserService;
	
	@Autowired
	private AuthenticationService authService;
	
	@Autowired
	private PlateService plateService;
	
	@Autowired
    private SessionFactory sessionFactory;
	
	/**
	 * for specified plate qualifies the data with z and a prime factors 
	 * and normalizes the data if pos and neg controls are present
	 * @param plateId
	 * @return QCplate object
	 * @throws UnauthorizedOperationException
	 */
	
	@RequestMapping(value = "/rest/qc/plate/{plate_id}", method = RequestMethod.GET)
	@ResponseBody
	public QCplate getNormalizedDataPerPlate(
			@PathVariable("plate_id") int plateId) throws  UnauthorizedOperationException{
		
		return qualityControlService.qualifyDataPerPlate(plateId);
	}
	
	/**
	 * for specified project qualifies all per plate data  with z and a prime factors 
	 * and normalizes the data if pos and neg controls are present
	 * @param project_id
	 * @return QCplate object
	 * @throws UnauthorizedOperationException
	 */
	
	@RequestMapping(value = "/rest/qc/project/{project_id}", method = RequestMethod.GET)
	@ResponseBody
	public List<QCplate> getNormalizedDataPerProject(
			@PathVariable("project_id") int project_id) throws  UnauthorizedOperationException{
		
		return qualityControlService.qualifyDataPerProject(project_id);
	}
	
	/**
	 * validates a single Well
	 * @param wellValidator
	 * @return update QCplate object
	 * @throws UnauthorizedOperationException
	 */
	
	@RequestMapping(value = "/rest/qc/single_well_validation", method = RequestMethod.POST)
	@ResponseBody
	public QCplate validateSingleWell(@RequestBody WellValidationContainer wellValidator) 
			                                       throws  UnauthorizedOperationException{
		QCplate result = null;
		try
		{
			qualityControlService.validateSingleWell(wellValidator);
		}
        catch (Exception e)
		{
        	//TODO 
		}
		finally
		{
			result = qualityControlService.qualifyDataPerPlate(wellValidator.getPlateId());
		}
		
		return result;
	}
	
	/**
	 * validates a group of Wells
	 * @param listOfWellValidators
	 * @return update QCplate object
	 * @throws UnauthorizedOperationException
	 */
	
	@RequestMapping(value = "/rest/qc/group_well_validation", method = RequestMethod.POST)
	@ResponseBody
	public List<QCplate> validateGroupOfWells(@RequestBody List<WellValidationContainer> listOfWellValidators)
																throws  UnauthorizedOperationException{
		List<QCplate> result = null;
		try
		{
			qualityControlService.validateGroupOfWell(listOfWellValidators);;
		}	
        catch (Exception e)
		{
        	//TODO 
		}
		finally
		{
			result = qualityControlService.qualifyDataPerProject(listOfWellValidators.get(0).getProjectId());
		}
		return result;
	}
	
	/**
	 * validates a single Plate
	 * @param plateValidator
	 * @return true/false
	 * @throws UnauthorizedOperationException
	 */
	
	@RequestMapping(value = "/rest/qc/single_plate_validation", method = RequestMethod.POST)
	@ResponseBody
	public boolean validateSingleWPlate(@RequestBody PlateValidationContainer plateValidator) 
			                                       throws  UnauthorizedOperationException{
		boolean result = false;
		try
		{
			qualityControlService.validateSinglePlate(plateValidator);
		}
        catch (Exception e)
		{
        	//TODO 
		}
		finally
		{
			result = true;
		}
		
		return result;
	}
	
	/**
	 * validates a group of Plates
	 * @param listOfPlateValidators
	 * @return true/false
	 * @throws UnauthorizedOperationException
	 */
	@RequestMapping(value = "/rest/qc/group_plate_validation", method = RequestMethod.POST)
	@ResponseBody
	public boolean validateGroupOfPlates(@RequestBody List<PlateValidationContainer> listOfPlateValidators)
																throws  UnauthorizedOperationException{
		boolean result = false;
		try
		{
			qualityControlService.validateGroupOfPlates(listOfPlateValidators);
		}	
        catch (Exception e)
		{
        	//TODO 
		}
		finally
		{
			result = true;
		}
		return result;
	}
	
	/**
	 * removes the results data associated with a specified plate
	 * @param plateId
	 * @return true/false
	 * @throws UnauthorizedOperationException
	 */
	
	@RequestMapping(value = "/rest/qc/plate/{plateId}", method = RequestMethod.DELETE)
	@ResponseBody
	public boolean removeResultFromPlate(
			@PathVariable("plateId") int plateId ) throws UnauthorizedOperationException
	{
		return qualityControlService.removeResultsFromPlate (plateId);
	}
	
}
