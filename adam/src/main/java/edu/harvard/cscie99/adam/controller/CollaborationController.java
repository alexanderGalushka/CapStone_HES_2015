package edu.harvard.cscie99.adam.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.harvard.cscie99.adam.error.UnauthorizedOperationException;
import edu.harvard.cscie99.adam.profile.User;

@RestController
@RequestMapping(value = "/")
public class CollaborationController {

	@RequestMapping(value = "/users/list", method = RequestMethod.GET)
	@ResponseBody
	public List<User> listAllUsers(){
		
		//TODO
		return null;
	}
	
	@RequestMapping(value = "/share/{projectId}/user/{username}", method = RequestMethod.GET)
	@ResponseBody
	public boolean addCollaboratorToProject(
			@PathVariable("projectId") int projectId,
			@PathVariable("username") String username,
			@RequestParam(value="user", required=true) String user) throws UnauthorizedOperationException{
		
		//TODO
		return true;
	}
	
	@RequestMapping(value = "/unshare/{projectId}/user/{username}", method = RequestMethod.POST)
	@ResponseBody
	public boolean removeCollaboratorToProject(
			@PathVariable("projectId") int projectId,
			@PathVariable("username") String username,
			@RequestParam(value="user", required=true) String user) throws UnauthorizedOperationException{
		
		//TODO
		return true;
	}
	
	@RequestMapping(value = "/unshare/{projectId}/", method = RequestMethod.POST)
	@ResponseBody
	public boolean removeAllCollaborators(
			@PathVariable("projectId") int projectId,
			@RequestParam(value="user", required=true) String user) throws UnauthorizedOperationException{
		
		//TODO
		return true;
	}
	
}
