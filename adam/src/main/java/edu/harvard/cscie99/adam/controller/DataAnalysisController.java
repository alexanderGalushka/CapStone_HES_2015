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
 * DataAnalysisController class
 * 
 * Intercept calls from the frontend application. 
 * Execute methods required by Data Analysis module in Adam.
 * 
 * @author Alexander G.
 *
 */
@RestController
@RequestMapping(value = "/")
public class DataAnalysisController 
{

	/**
	 * Reference for DataAnalysisService.
	 * Injected by the Spring container in runtime (Inversion of Control)
	 * 
	 */
	@Autowired
	private DataAnalysisService dataAnalysisService;
	
	/**
	 * Get the list of fitted curves for (x,y) axis
	 * 
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param fit - optional Fit of curve
	 * @return List of Curves that matches the input coordinates
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
		
		return listOfCurves;
		
	}
	
}
