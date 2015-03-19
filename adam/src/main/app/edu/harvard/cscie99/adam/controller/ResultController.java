package edu.harvard.cscie99.adam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.harvard.cscie99.adam.error.UnauthorizedOperationException;
import edu.harvard.cscie99.adam.model.PlateResult;
import edu.harvard.cscie99.adam.service.AuthenticationService;
import edu.harvard.cscie99.adam.service.ResultService;

@RestController
@RequestMapping(value = "/plate/result/{plateId}")
public class PlateResultController {
	
	@Autowired
	private ResultService resultService;
	
	@Autowired
	private AuthenticationService authService;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public PlateResult getPlateResult(
			@PathVariable("plateId") Integer plateId,
			@RequestParam(value="user", required=true) String user) throws UnauthorizedOperationException{
		
		boolean hasAccess = authService.checkUserAccess(user, plateId, "getPlateResult");
		
		if (hasAccess){
			return resultService.getPlateResult(plateId);
		}
		else{
			throw new UnauthorizedOperationException ("User don't have permission", user, "getPlateResult");
		}
	}

}
