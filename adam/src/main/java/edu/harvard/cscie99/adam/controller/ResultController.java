
package edu.harvard.cscie99.adam.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import edu.harvard.cscie99.adam.model.DataSet;
import edu.harvard.cscie99.adam.model.Measurement;
import edu.harvard.cscie99.adam.model.Plate;
import edu.harvard.cscie99.adam.model.Project;
import edu.harvard.cscie99.adam.model.ResultSnapshot;
import edu.harvard.cscie99.adam.model.Well;
import edu.harvard.cscie99.adam.model.WellLabel;
import edu.harvard.cscie99.adam.service.AuthenticationService;
import edu.harvard.cscie99.adam.service.ParserService;
import edu.harvard.cscie99.adam.service.PlateService;
import edu.harvard.cscie99.adam.service.ProfileService;
import edu.harvard.cscie99.adam.service.ProjectService;
import edu.harvard.cscie99.adam.service.QueryService;
import edu.harvard.cscie99.adam.service.ResultService;

/**
 * 
 * @author Gerson
 *
 */
@RestController
@RequestMapping(value = "/")
public class ResultController {

	@Autowired
	private ParserService parserService;

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ResultService resultService;
	
	@Autowired
	private AuthenticationService authService;
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private QueryService queryService;
	
	@Autowired
	private PlateService plateService;
	
	private HashMap<Integer, String> letterMapping = new HashMap<>();
	
//	@RequestMapping(value="/rest/resultsnapshot/{result_id}/prepare", method=RequestMethod.POST)
//	public @ResponseBody boolean prepareResultsData(
//			@PathVariable("result_id") int resultId) throws JsonProcessingException {
//		
//		ResultSnapshot resultSnapshot = resultService.retrieveResult(resultId);
//		
//		return resultService.prepareResultsData(resultSnapshot);
//	}
	
	@RequestMapping(value="/rest/resultsnapshot/{result_id}", method=RequestMethod.GET)
	public @ResponseBody ResultSnapshot getResults(
			@PathVariable("result_id") int resultId) throws JsonProcessingException {
		
		ResultSnapshot resultSnapshot = resultService.retrieveResult(resultId);
		
		return resultSnapshot;
	}
	
	@RequestMapping(value="/rest/resultsnapshot", method=RequestMethod.GET)
	public @ResponseBody List<ResultSnapshot> listResults() throws JsonProcessingException {
		
		List<ResultSnapshot> resultList = resultService.listResults();
		
		return resultList;
	}
	
//	@RequestMapping(value="/rest/getResults", method=RequestMethod.GET)
//	public @ResponseBody DataSet queryResults(
//			@RequestParam(value="projectId", required=false) Integer projectId,
//			@RequestParam(value="plateId", required=false) Integer plateId,
//			@RequestParam(value="labelName", required=false) String labelName,
//			@RequestParam(value="labelValue", required=false) String labelValue,
//			@RequestParam(value="measurementType", required=false) String measurementType,
//			@RequestParam(value="time", required=false) String time
//			) throws JsonProcessingException {
//		
//		return queryService.queryResultsData(projectId, plateId, labelName, labelValue, measurementType, time);
//	}
	
	@RequestMapping(value="/rest/getWells/{project_id}", method=RequestMethod.GET)
	public @ResponseBody ArrayList<HashMap<Object, Object>> getAllResults(
			@PathVariable("project_id") int projectId) throws JsonProcessingException 
	{
		return resultService.getAllWells(projectId);
	}
	
	@RequestMapping(value="/rest/get_all_data", method=RequestMethod.GET)
	public @ResponseBody Map<Object, ArrayList<HashMap<Object, Object>>> getAllPossibleResults() throws JsonProcessingException 
	{
		Map<Object, ArrayList<HashMap<Object, Object>>> result = new HashMap<>();
        List<Project> listOfProjects= projectService.list();
        for (Project project: listOfProjects)
        {
        	result.put(project.getId(), resultService.getAllWells(project.getId()));
        }
        
        return result;
	}
	
}
