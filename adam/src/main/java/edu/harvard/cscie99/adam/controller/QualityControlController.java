package edu.harvard.cscie99.adam.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.harvard.cscie99.adam.error.UnauthorizedOperationException;
import edu.harvard.cscie99.adam.model.QCdataTimeWrapper;
import edu.harvard.cscie99.adam.service.AuthenticationService;
import edu.harvard.cscie99.adam.service.ParserService;
import edu.harvard.cscie99.adam.service.QualityControlService;

public class QualityControlController 
{

	@Autowired
	private QualityControlService qualityControlService;

	@Autowired
	private ParserService parserService;
	
	@Autowired
	private AuthenticationService authService;
	
	
	@RequestMapping(value = "/project/{project_id}/qualify_data", method = RequestMethod.GET)
	@ResponseBody
	public Map<Integer, List<QCdataTimeWrapper>> getNormalizedData(
			@PathVariable("project_id") int projectId) throws  UnauthorizedOperationException{
		
		return qualityControlService.qualifyData(projectId);
	}
	
}
