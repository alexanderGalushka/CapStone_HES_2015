package edu.harvard.cscie99.adam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.harvard.cscie99.adam.error.SessionTimeouException;
import edu.harvard.cscie99.adam.error.UnauthorizedOperationException;
import edu.harvard.cscie99.adam.model.Project;
import edu.harvard.cscie99.adam.profile.User;
import edu.harvard.cscie99.adam.service.AuthenticationService;

/**
 * 
 * @author Gerson
 *
 */
@RestController
@RequestMapping(value = "/")
public class AuthenticationController {

	@Autowired
	private AuthenticationService authService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ResponseBody
	public User addCollaboratorToProject(
			@RequestParam(value="user", required=true) String user,
			@RequestParam(value="password", required=true) String password) throws UnauthorizedOperationException{
		
		//TODO
		return null;
	}
}
