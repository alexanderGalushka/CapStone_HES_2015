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
import org.springframework.web.servlet.ModelAndView;

import edu.harvard.cscie99.adam.error.LoginFailedException;
import edu.harvard.cscie99.adam.error.SessionTimeouException;
import edu.harvard.cscie99.adam.error.UnauthorizedOperationException;
import edu.harvard.cscie99.adam.interceptor.AuthInterceptor;
import edu.harvard.cscie99.adam.model.Project;
import edu.harvard.cscie99.adam.profile.User;
import edu.harvard.cscie99.adam.service.AuthenticationService;

/**
 * 
 * AuthenticationController class
 * 
 * Intercepts user authentication method calls (login and logout)
 * 
 * @author Gerson
 *
 */
@RestController
@RequestMapping(value = "/")
public class AuthenticationController {
	
	private static final String ADAM_LAND_PAGE_URL = "redirect:/#/projects";

	/**
	 * AuthenticationService - Implements the logic of User credentials validation in DB
	 */
	@Autowired
	private AuthenticationService authService;
	
	/**
	 * login method
	 * 
	 * Executes the login. Method is called by the frontend application, and redirects the user
	 * to ADAM's landing page (Project list)
	 * 
	 * @param username - User's username
	 * @param password - User's password
	 * @param request - HTTPRequest object
	 * @param response - HTTPResponse object
	 * @return ModelAndView - redirection to ADAM landpage
	 * @throws LoginFailedException
	 */
	@RequestMapping(value = "/do_login", method = RequestMethod.GET)
	public ModelAndView login( @RequestParam("username") String username,
									 @RequestParam("password") String password,
									 HttpServletRequest request, 
									 HttpServletResponse response) throws LoginFailedException
	{
		//Retrieves the current Http session or creates a new Http Session if none exists
		HttpSession session = request.getSession();

		//Checks if user has logged in
		Object userObject = session.getAttribute(AuthenticationService.C_USER_SESSION);
		User user = null;
		if(userObject == null)
		{
			//If the user is not logged in, check its credentials with User information in DB
			user = authService.validateCredentials(username, password);
	       
			if (user != null)
			{
				//Login successful
				//Saves the user in session for further login attemps
				//Session lasts according to <session> attribute in web.xml
				session.setAttribute(AuthenticationService.C_USER_SESSION, user);
			}
			else
			{
				//Login unsuccessful
				throw new LoginFailedException("Failed to Login: check username and password");
			}			
		}
		else
		{
			user = (User) userObject;
		}
		
		//Hashes the user password brefore presenting to frontend
		user.setPassword(authService.hashPassword(user.getPassword()));
		//redirects the user to Adam's landpage
		return new ModelAndView(ADAM_LAND_PAGE_URL);

	}
	
	/**
	 * logout method
	 * 
	 * Removes the user from HttpSession and redirects to Login screen
	 * 
	 * @param request - HttpRequest object
	 * @param response - HttpResponse object
	 * @return boolean - operation successful
	 */
	@RequestMapping(value = "/do_logout", method = RequestMethod.GET)
	public boolean logout(HttpServletRequest request, 
									 HttpServletResponse response)
	{
		//Retrieves existing HTTP Session
		HttpSession session = request.getSession(false);
		//If session doesn't exists, user is not logged in
		if (session != null){
			//Invalidates the session
			session.invalidate();
		}
		
		//Redirects to the login page
		try {
			response.sendRedirect(AuthInterceptor.C_LOGIN_SCREEN);
		} catch (IOException e) {
			return false;
		}
		
		return true;

	}
}
