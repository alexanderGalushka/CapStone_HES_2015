package edu.harvard.cscie99.adam.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.harvard.cscie99.adam.error.LoginFailedException;
import edu.harvard.cscie99.adam.error.SessionTimeouException;
import edu.harvard.cscie99.adam.error.UnauthorizedOperationException;
import edu.harvard.cscie99.adam.interceptor.AuthInterceptor;
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
	
	@RequestMapping(value = "/do_login", method = RequestMethod.GET)
	public @ResponseBody User login( @RequestParam("username") String username,
									 @RequestParam("password") String password,
									 HttpServletRequest request, 
									 HttpServletResponse response) throws LoginFailedException
	{
		HttpSession session = request.getSession();// it will go and look after the web.xml properties and check for the time out setting.

	    //if User is already logged and session not expired
		
		// not sure what getAttribute would return if the attribute is not set - need to check!
		
		Object userObject = session.getAttribute(AuthenticationService.C_USER_SESSION);
		User user = null;
		if(userObject == null)
		{
			//authenticationService will lookup database and match user and password
			user = authService.validateCredentials(username, password);
	       
			if (user != null)
			{
				session.setAttribute(AuthenticationService.C_USER_SESSION, user);
			}
			else
			{
				throw new LoginFailedException("bad credentials");
			}			
		}
		else
		{
			user = (User) userObject;
		}
		
		//clear password for security
//		user.setPassword(null);
	
		return user;

	}
	
	@RequestMapping(value = "/do_logout", method = RequestMethod.GET)
	public boolean logout(HttpServletRequest request, 
									 HttpServletResponse response) throws LoginFailedException
	{
		HttpSession session = request.getSession();// it will go and look after the web.xml properties and check for the time out setting.
		session.invalidate();
		
		try {
			response.sendRedirect(AuthInterceptor.C_LOGIN_SCREEN);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;

	}
}
