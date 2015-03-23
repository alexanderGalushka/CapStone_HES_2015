package edu.harvard.cscie99.adam.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.harvard.cscie99.adam.error.LoginFailedException;
import edu.harvard.cscie99.adam.error.LogoutFailedException;
import edu.harvard.cscie99.adam.profile.User;

/**
 * 
 * @author Alexander G.
 *
 */
@Component
public class AuthenticationService 
{

	public boolean checkUserAccess(String user, Integer plateId, String service) 
	{
		// TODO implement
		return true;
	}
	

	
	/**
	 * this method logs out the user by invalidating accToken,
	 * this method considered to be the end point of user's session
	 * @param accToken - access token
	 * @param login - login string
	 * @throws LogoutFailedException if failed to login
	 */
	public void logout( HttpSession session, String login ) throws LogoutFailedException
	{
		// call to DB
		/*
		if ( if login not in DB )
		{
			String message = "login is incorrect";
			throw new LogoutFailedException( message );
		}
		*/
		
		session.invalidate(); //invalidate session
	}
	
	private User validateCredentials(String username, String password)
	{
		
		User user = null;
		// storing hash, not the password itself
		String hashedPswd = hashPassword(password);
		
		// make a call to DB to check credentials
		// suff the user will all the entitlements he/she has and return the one back
		user = new User();
		
		return user;
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public @ResponseBody User login( @RequestParam("username") String username,
									 @RequestParam("password") String password,
									 HttpServletRequest request, 
									 HttpServletResponse response) throws LoginFailedException
	{
		HttpSession session = request.getSession();

	    //if User is already logged and session not expired
		
		// not sure what getAttribute would return if the attribute is not set - need to check!
		User user = (User) session.getAttribute("user");
		
		if(user.equals(null))
		{
			//authenticationService will lookup database and match user and password
			user = validateCredentials(username, password);
	       
			if (!user.equals(null))
			{
				session.setAttribute("user", user);
			}
			else
			{
				throw new LoginFailedException("bad credentials");
			}

			
		}
	
		return user;

	}
	
	
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
