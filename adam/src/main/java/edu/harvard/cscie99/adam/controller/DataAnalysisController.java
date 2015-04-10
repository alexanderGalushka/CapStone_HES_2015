package edu.harvard.cscie99.adam.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.harvard.cscie99.adam.model.Curve;
import edu.harvard.cscie99.adam.model.Project;
import edu.harvard.cscie99.adam.service.DataAnalysisService;
import edu.harvard.cscie99.adam.service.ProjectService;

/**
 * 
 * @author Alexander G.
 *
 */
@RestController
@RequestMapping(value = "/")
public class DataAnalysisController 
{
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private DataAnalysisService dataAnalysisService;
	
	/*
	 * get all own projects
	 */
	@RequestMapping(value = "da/projects/own", method = RequestMethod.GET)
	@ResponseBody
	public Set<Project> listMyProjects()
	{
		
		return projectService.listMyProjects();
	}
	
	/*
	 * get other's projects
	 */
	@RequestMapping(value = "da/projects/others", method = RequestMethod.GET)
	@ResponseBody
	public Set<Project> listOthersProjects()
	{
		
		return projectService.listOthersProjects();
	}
	
	/*
	 * get list of fitted curves
	 */
	@RequestMapping(value = "da/datapoints/{xAxis}/{yAxis}", method = RequestMethod.GET)
	@ResponseBody
	public List<Curve> listOfCurves(@PathVariable("xAxis") double[] x,
			                        @PathVariable("yAxis") double[] y, 
			                        @RequestParam(value="fit", required=false) String fit)
	{
		
		List<Curve> listOfCurves = new ArrayList<>();
		listOfCurves.add(dataAnalysisService.getLinearRegression(x, y, "line"));
		listOfCurves.add(dataAnalysisService.getLinearRegression(x, y, "semilog"));
		listOfCurves.add(dataAnalysisService.getPolynomialFit(x, y, 2));
		listOfCurves.add(dataAnalysisService.getPolynomialFit(x, y, 3));
		listOfCurves.add(dataAnalysisService.getPolynomialFit(x, y, 3));
		listOfCurves.add(dataAnalysisService.getDecay(x, y));
		listOfCurves.add(dataAnalysisService.getGrowth(x, y));
		//add one more: michaelis-menten
		
		return listOfCurves;
		
	}
	
}
