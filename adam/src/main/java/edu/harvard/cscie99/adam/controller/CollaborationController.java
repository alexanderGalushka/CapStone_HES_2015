package edu.harvard.cscie99.adam.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.harvard.cscie99.adam.error.LogoutFailedException;
import edu.harvard.cscie99.adam.error.SessionTimeouException;
import edu.harvard.cscie99.adam.error.UnauthorizedOperationException;
import edu.harvard.cscie99.adam.model.Project;
import edu.harvard.cscie99.adam.profile.User;
import edu.harvard.cscie99.adam.service.AuthenticationService;
import edu.harvard.cscie99.adam.service.ProfileService;
import edu.harvard.cscie99.adam.service.ProjectService;

/**
 * 
 * @author Gerson
 *
 */
@RestController
@RequestMapping(value = "/")
public class CollaborationController {
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private AuthenticationService authService;
	
	@Autowired
	private ProfileService profileService;
	
	@RequestMapping(value = "/users/list", method = RequestMethod.GET)
	@ResponseBody
	public List<User> listAllUsers(){
		
		return profileService.listAllUsers();
	}
	
	@RequestMapping(value = "/share/{projectId}/user/{username}", method = RequestMethod.GET)
	@ResponseBody
	public Project addCollaboratorToProject(
			@PathVariable("projectId") int projectId,
			@PathVariable("username") String username,
			@RequestParam(value="user", required=true) String user) throws UnauthorizedOperationException{
		
		boolean hasAccess = false;
		try {
			hasAccess = authService.checkUserAccess(user, null, "addCollaboratorToProject");
		} catch (SessionTimeouException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LogoutFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (hasAccess){
			Project project = projectService.retrieveProject(projectId);
			User collaborator = profileService.getUserDetails(username);
			
			project.getCollaborators().add(collaborator);
			projectService.updateProject(project);
			
			return project;
		}
		else{
			throw new UnauthorizedOperationException ("User don't have permission", user, "addCollaboratorToProject");
		}
	}
	
	@RequestMapping(value = "/unshare/{projectId}/user/{username}", method = RequestMethod.POST)
	@ResponseBody
	public Project removeCollaboratorFromProject(
			@PathVariable("projectId") int projectId,
			@PathVariable("username") String username,
			@RequestParam(value="user", required=true) String user) throws UnauthorizedOperationException{
		
		//TODO: get data from DB
		boolean hasAccess = false;
		try {
			hasAccess = authService.checkUserAccess(user, null, "removeCollaboratorFromProject");
		} catch (SessionTimeouException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LogoutFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (hasAccess){
			Project project = projectService.retrieveProject(projectId);
			User collaborator = profileService.getUserDetails(username);
			
			project.getCollaborators().remove(collaborator);
			projectService.updateProject(project);
			
			return project;
		}
		else{
			throw new UnauthorizedOperationException ("User don't have permission", user, "removeCollaboratorFromProject");
		}
	}
	
	@RequestMapping(value = "/project/{projectId}/make_private", method = RequestMethod.POST)
	@ResponseBody
	public Project removeAllCollaborators(
			@PathVariable("projectId") int projectId,
			@RequestParam(value="user", required=true) String user) throws UnauthorizedOperationException{
		
		//TODO: get data from DB
		boolean hasAccess = false;
		try {
			hasAccess = authService.checkUserAccess(user, null, "removeAllCollaborators");
		} catch (SessionTimeouException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LogoutFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (hasAccess){
			Project project = projectService.retrieveProject(projectId);
			
			project.getCollaborators().clear();
			projectService.updateProject(project);
			
			return project;
		}
		else{
			throw new UnauthorizedOperationException ("User don't have permission", user, "removeAllCollaborators");
		}
	}
	
}
