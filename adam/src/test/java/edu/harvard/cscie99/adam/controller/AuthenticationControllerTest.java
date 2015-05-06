package edu.harvard.cscie99.adam.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import edu.harvard.cscie99.adam.config.PersistenceConfig;
import edu.harvard.cscie99.adam.config.PersistenceXmlConfig;
import edu.harvard.cscie99.adam.error.LoginFailedException;
import edu.harvard.cscie99.adam.profile.User;
import edu.harvard.cscie99.adam.service.AuthenticationService;
import edu.harvard.cscie99.adam.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
        classes = { PersistenceConfig.class, PersistenceXmlConfig.class })
public class AuthenticationControllerTest {
	
	@Autowired
	private AuthenticationController authController;
	
	@Autowired
	private UserService userService;
	
	@Before
	public void createUser() throws Exception {

		//Check if user exists, and creates one if it doesnt
		if (userService.retrieveUser("ivan") == null){
			User user = new User();
			user.setUsername("ivan");
			user.setPassword("ivan");
			userService.createUser(user);
		};
	}
	
	@Test
	public void testLoginSuccess(){
		
		HttpServletRequest request = (HttpServletRequest)Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = (HttpServletResponse)Mockito.mock(HttpServletResponse.class);
		HttpSession session = (HttpSession)Mockito.mock(HttpSession.class);
		Mockito.when(request.getSession()).thenReturn(session);
		
		try {
			authController.login("ivan", "ivan", request, response);
		} catch (LoginFailedException e) {
			fail();
		}
		//Login success!
		assertTrue(true);
	}
	
	@Test
	public void testLoginFail(){
		
		HttpServletRequest request = (HttpServletRequest)Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = (HttpServletResponse)Mockito.mock(HttpServletResponse.class);
		HttpSession session = (HttpSession)Mockito.mock(HttpSession.class);
		Mockito.when(request.getSession()).thenReturn(session);
		
		try {
			authController.login("ivan", "notIvan", request, response);
		} catch (LoginFailedException e) {
			assertTrue(true);
			return;
		}
		//login successful
		fail();
	}
	
	@Test
	public void testLogout(){
		
		HttpServletRequest request = (HttpServletRequest)Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = (HttpServletResponse)Mockito.mock(HttpServletResponse.class);
		HttpSession session = (HttpSession)Mockito.mock(HttpSession.class);
		Mockito.when(request.getSession()).thenReturn(session);
		
		try {
			authController.logout(request, response);
		} catch (Exception e) {
			fail();
		}
		//login successful
		assertTrue(true);
	}
	
	

}
