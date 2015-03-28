package edu.harvard.cscie99.adam.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(value = "/projects/own", method = RequestMethod.GET)
	@ResponseBody
	public Set<Project> listMyProjects()
	{
		
		return projectService.listMyProjects();
	}
	
	/*
	 * get other's projects
	 */
	@RequestMapping(value = "/projects/others", method = RequestMethod.GET)
	@ResponseBody
	public Set<Project> listOthersProjects()
	{
		
		return projectService.listOthersProjects();
	}
	
	
	
}
