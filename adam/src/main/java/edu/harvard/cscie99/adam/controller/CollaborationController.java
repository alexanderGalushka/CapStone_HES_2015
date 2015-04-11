//package edu.harvard.cscie99.adam.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import edu.harvard.cscie99.adam.error.SessionTimeouException;
//import edu.harvard.cscie99.adam.error.UnauthorizedOperationException;
//import edu.harvard.cscie99.adam.model.Project;
//import edu.harvard.cscie99.adam.profile.User;
//import edu.harvard.cscie99.adam.service.AuthenticationService;
//import edu.harvard.cscie99.adam.service.ProfileService;
//import edu.harvard.cscie99.adam.service.ProjectService;
//
///**
// * 
// * @author Gerson
// *
// */
//@RestController
//@RequestMapping(value = "/")
//public class CollaborationController {
//	
//	@Autowired
//	private ProjectService projectService;
//	
//	@Autowired
//	private AuthenticationService authService;
//	
//	@Autowired
//	private ProfileService profileService;
//	
//	@RequestMapping(value = "/users/list", method = RequestMethod.GET)
//	@ResponseBody
//	public List<User> listAllUsers(){
//		
//		return profileService.listAllUsers();
//	}
//	
//	@RequestMapping(value = "/share/{projectId}/user/{username}", method = RequestMethod.GET)
//	@ResponseBody
//	public boolean addCollaboratorToProject(
//			@PathVariable("projectId") int projectId,
//			@PathVariable("username") String username){
//		
//		
//		Project project = projectService.retrieveProject(projectId);
//		User collaborator = profileService.getUserDetails(username);
//			
//		project.getCollaborators().add(collaborator);
//		projectService.updateProject(project);
//		return true;
//	}
//	
//	@RequestMapping(value = "/unshare/{projectId}/user/{username}", method = RequestMethod.POST)
//	@ResponseBody
//	public Project removeCollaboratorFromProject(
//			@PathVariable("projectId") int projectId,
//			@PathVariable("username") String username) throws UnauthorizedOperationException{
//		
//		
//		Project project = projectService.retrieveProject(projectId);
//		User collaborator = profileService.getUserDetails(username);
//		
//		project.getCollaborators().remove(collaborator);
//		projectService.updateProject(project);
//		
//		return project;
//		
//	}
//	
//}
