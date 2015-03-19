package edu.harvard.cscie99.adam.service;

import org.springframework.stereotype.Component;

import edu.harvard.cscie99.adam.model.Project;

@Component
public class ProjectService {

	public boolean checkUserAccess(String user, Integer plateId, String service) {
		// TODO implement
		return true;
	}
	
	public boolean updateProject(Project project){
		//TODO
		return true;
	}
	
	public Project createProject(Project project){
		//TODO
		return null;
	}
	
	public Project retrieveProject(int projectId){
		//TODO
		return null;
	}
	
}
