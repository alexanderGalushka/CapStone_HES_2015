package edu.harvard.cscie99.adam.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import edu.harvard.cscie99.adam.model.Compound;
import edu.harvard.cscie99.adam.model.Project;
import edu.harvard.cscie99.adam.profile.User;

/**
 * 
 * @author Gerson
 *
 */
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
	
	public Project createProject(String name, String type, List<Compound> compounds, String description, List<String> tags, List<User> collaborators, User owner){
		
		//TODO
		
		Project project = new Project();
		project.setId(1);
		project.setName(name);
		project.setCreationDate(new Date());
		project.setCollaborators(collaborators);
		project.setOwner(owner);
//		project.setTags(tags);
		project.setDescription(description);
//		project.setCompounds(compounds);
		project.setType(type);
		
		return project;
	}
	
	public Project retrieveProject(int projectId){
		//TODO
		
		Project project = new Project();
		project.setId(1);
		project.setName("project1");
		project.setCreationDate(new Date());
		
		return project;
	}
	
}
