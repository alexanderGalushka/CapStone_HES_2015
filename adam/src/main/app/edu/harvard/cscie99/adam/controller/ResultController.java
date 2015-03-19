package edu.harvard.cscie99.adam.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.harvard.cscie99.adam.error.UnauthorizedOperationException;
import edu.harvard.cscie99.adam.model.Plate;
import edu.harvard.cscie99.adam.model.PlateResult;
import edu.harvard.cscie99.adam.model.Project;
import edu.harvard.cscie99.adam.model.WellResult;
import edu.harvard.cscie99.adam.service.AuthenticationService;
import edu.harvard.cscie99.adam.service.ResultService;

@RestController
@RequestMapping(value = "/")
public class ResultController {
	
	@Autowired
	private ResultService resultService;
	
	@Autowired
	private AuthenticationService authService;
	
	@RequestMapping(value = "/project/{projectId}/result/upload_from_file", method = RequestMethod.POST)
	@ResponseBody
	public PlateResult uploadResultFromFile(
			@PathVariable("projectId") int projectId,
			@RequestParam(value="filename", required=true) String filename,
			@RequestParam(value="user", required=true) String user) throws UnauthorizedOperationException{
		
		//TODO
		return null;
	}
	
	@RequestMapping(value = "/project/{projectId}/result/search", method = RequestMethod.POST)
	@ResponseBody
	public List<PlateResult> searchResult(
			@PathVariable("projectId") int projectId,
			@RequestParam(value="id", required=false) int id,
			@RequestParam(value="plate_id", required=false) int plate_id,
			@RequestParam(value="creationDate", required=false) Date creationDate,
			@RequestParam(value="comment", required=false) String comment,
			@RequestParam(value="user", required=true) String user) throws UnauthorizedOperationException{
		
		//TODO
		return null;
	}
	
	@RequestMapping(value = "/project/{projectId}/result/{result_id}/detail", method = RequestMethod.POST)
	@ResponseBody
	public PlateResult getResult(
			@PathVariable("project_id") int projectId,
			@PathVariable("result_id") int resultId,
			@RequestParam(value="user", required=true) String user) throws UnauthorizedOperationException{
		
		//TODO
		return null;
	}
	
	@RequestMapping(value = "/project/{projectId}/result/{result_id}/normalize/{function}", method = RequestMethod.POST)
	@ResponseBody
	public PlateResult normalizeResult(
			@PathVariable("project_id") int projectId,
			@PathVariable("result_id") int resultId,
			@PathVariable("function") String function,
			@RequestParam(value="user", required=true) String user) throws UnauthorizedOperationException{
		
		//TODO
		return null;
	}
	
	@RequestMapping(value = "/project/{projectId}/result/{result_id}/removenan", method = RequestMethod.POST)
	@ResponseBody
	public PlateResult removeNan(
			@PathVariable("project_id") int projectId,
			@PathVariable("result_id") int resultId,
			@RequestParam(value="user", required=true) String user) throws UnauthorizedOperationException{
		
		//TODO
		return null;
	}
	
//	@RequestMapping(value = "/plate/result/{plateId}", method = RequestMethod.GET)
//	@ResponseBody
//	public PlateResult getPlateResult(
//			@PathVariable("plateId") Integer plateId,
//			@RequestParam(value="user", required=true) String user) throws UnauthorizedOperationException{
//		
//		boolean hasAccess = authService.checkUserAccess(user, plateId, "getPlateResult");
//		
//		if (hasAccess){
//			return resultService.getPlateResult(plateId);
//		}
//		else{
//			throw new UnauthorizedOperationException ("User don't have permission", user, "getPlateResult");
//		}
//	}

}
