package edu.harvard.cscie99.adam.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.harvard.cscie99.adam.error.LoginFailedException;
import edu.harvard.cscie99.adam.error.SessionTimeouException;
import edu.harvard.cscie99.adam.profile.Permission;
import edu.harvard.cscie99.adam.profile.User;

/**
 * 
 * @author Alexander G.
 *
 */
@Component
public class AuthenticationService 
{
	private HttpSession session;
	
	public HttpSession getSession() {
		return session;
	}
	
	public AuthenticationService()
	{
		session = null;
	}
	
	
	public boolean checkUserAccess(String userName, Integer projectId, String service) throws SessionTimeouException
	{
		boolean result = false;
		// it could be performance burden, but assures that the session is up
		Object userObject = session.getAttribute("user");
		
		if (userObject != null)
		{
			User user  = (User) userObject;
			// validate if the userName is not injected!
			if (user.getUsername().equals(userName))
			{
				// is Role is intended to be a permission like for simplification purpose?
				if(checkPermission(user.getPermissions(), service))
				{
					result = true;
				}
			}
			else
			{
				// TODO
			}	
		}
		else
		{
			//do we want to invalidate the session and logout the user here?
			logout();
			throw new SessionTimeouException("timeout exception");
		}
		
		return result;
	}
	
    private boolean checkPermission(List<Permission> permissions, String service)
    {
    	boolean result = false;
    	for (Permission perm : permissions)
    	{
    		if(perm.getService().equals(service))
    		{
    			result = true;
    			break;
    		}          
    	}
    	return result;
    }
	
	/**
	 * this method logs out the user by invalidating session,
	 * this method considered to be the end point of user's session
	 */
	public void logout()
	{
        // keep it simple
		session.invalidate(); //invalidate session
	}
	
	private User validateCredentials(String username, String password)
	{
		
		User user = null;
		// storing hash, not the password itself
		String hashedPswd = hashPassword(password);
		
		// make a call to DB to check credentials
		// stuff the user with all the entitlements and other user data from DB he/she has and return the one back
		user = new User();
		
		return user;
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public @ResponseBody User login( @RequestParam("username") String username,
									 @RequestParam("password") String password,
									 HttpServletRequest request, 
									 HttpServletResponse response) throws LoginFailedException
	{
		session = request.getSession();// it will go and look after the web.xml properties and check for the time out setting.

	    //if User is already logged and session not expired
		
		// not sure what getAttribute would return if the attribute is not set - need to check!
		
		Object userObject = session.getAttribute("user");
		User user = null;
		if(userObject == null)
		{
			//authenticationService will lookup database and match user and password
			user = validateCredentials(username, password);
	       
			if (user != null)
			{
				session.setAttribute("user", user);
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
	
		return user;

	}
	
	// it's a helper function which can potentially be a part of our Utils class
	private String hashPassword( String passwordToHash )
	{
		//****************************************//
		// Below code snippet has been borrowed from
		// http://howtodoinjava.com/2013/07/22/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
		// "Simple password security using MD5 algorithm"
		String generatedPassword = null;
		try 
		{
			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance( "MD5" );
			// Add password bytes to digest
			md.update( passwordToHash.getBytes() );
			// Get the hash's bytes 
			byte[] bytes = md.digest();
			// This bytes[] has bytes in decimal format;
			// Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for(int i=0; i< bytes.length ;i++)
			{
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			// Get complete hashed password in hex format
			generatedPassword = sb.toString();
		} 
		catch (NoSuchAlgorithmException e) 
		{
			e.printStackTrace();
		}
		
		return generatedPassword;
	   //****************************************//
   }
	
}
