package edu.harvard.cscie99.adam.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mockito.Mockito;

import com.amazonaws.services.identitymanagement.model.User;

import edu.harvard.cscie99.adam.service.AuthenticationService;
import junit.framework.TestCase;

public class AuthInterceptorTest extends TestCase {

	AuthInterceptor authInterceptor = new AuthInterceptor();
	
public void testUserNotAuthenticated(){
		
		HttpServletRequest request = (HttpServletRequest)Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = (HttpServletResponse)Mockito.mock(HttpServletResponse.class);
		HttpSession session = (HttpSession)Mockito.mock(HttpSession.class);
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(request.getRequestURI()).thenReturn("/plate");
		
		boolean isAuthenticated = false;
		try {
			isAuthenticated = authInterceptor.preHandle(request, response, null);
		} catch (Exception e) {
			fail();
		}
		assertFalse(isAuthenticated);
	}

	public void testUserIsAuthenticated(){
	
		User user = new User();
		user.setUserName("testUser");
		
		HttpServletRequest request = (HttpServletRequest)Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = (HttpServletResponse)Mockito.mock(HttpServletResponse.class);
		HttpSession session = (HttpSession)Mockito.mock(HttpSession.class);
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(request.getRequestURI()).thenReturn("/plate");
		Mockito.when(session.getAttribute(AuthenticationService.C_USER_SESSION)).thenReturn(user);
		
		boolean isAuthenticated = false;
		try {
			isAuthenticated = authInterceptor.preHandle(request, response, null);
		} catch (Exception e) {
			fail();
		}
		assertTrue(isAuthenticated);
	}

}
