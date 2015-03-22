package edu.harvard.cscie99.adam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.harvard.cscie99.adam.service.DataAnalysisService;

/**
 * 
 * @author Gerson
 *
 */
@RestController
@RequestMapping(value = "/")
public class DataAnalysisController {
	
	@Autowired
	private DataAnalysisService dataAnalysisService;
	
	//TODO

}
