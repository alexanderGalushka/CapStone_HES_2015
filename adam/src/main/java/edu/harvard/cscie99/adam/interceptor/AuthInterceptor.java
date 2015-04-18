package edu.harvard.cscie99.adam.interceptor;

import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import edu.harvard.cscie99.adam.service.AuthenticationService;
 
public class AuthInterceptor implements HandlerInterceptor  {
	
	public static final String C_LOGIN_SCREEN = "/adam/login.html";
	
	public HashSet<String> servicesMappings = null;
	
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
    	
    	HttpSession session = request.getSession();
    	
    	if (!request.getRequestURI().contains("login") &&
    			requiresAuthentication(request.getRequestURI())){
    	
	    	if (null != session.getAttribute(AuthenticationService.C_USER_SESSION)){
	        	return true;
	    		
	    	} else {
	    		response.sendRedirect(C_LOGIN_SCREEN);
	    		return false;
	    	}
    	}
    	return true;
    }
    //override postHandle() and afterCompletion() 

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		System.out.println("Post-handle");
		
	}

	public String getLoginScreenMapping() {
		return C_LOGIN_SCREEN;
	}
	
	private HashSet<String> getServicesMappings(){
		if (servicesMappings == null){
			servicesMappings = new HashSet<String>();
			servicesMappings.add("rest/project");
			servicesMappings.add("rest/plate");
			servicesMappings.add("rest/resultsnapshot");
			servicesMappings.add("project");
			servicesMappings.add("plate");
			servicesMappings.add("getResults");
			servicesMappings.add("#");
		}
		
		return servicesMappings;
	}
	
	private boolean requiresAuthentication(String requestURI){
		
		HashSet<String> sm = getServicesMappings();
		
		for (String servicePath : sm){
			if (requestURI.contains(servicePath)){
				return true;
			}
		}
		return false;
	}
}