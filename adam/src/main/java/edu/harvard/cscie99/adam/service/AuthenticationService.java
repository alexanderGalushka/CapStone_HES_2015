package edu.harvard.cscie99.adam.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	
	@Autowired
    private SessionFactory sessionFactory;
	
	private static AuthenticationService _obj;
	
	public static final String C_USER_SESSION = "user";
	
	public HttpSession getSession() {
		return session;
	}
	
//	private AuthenticationService()
//	{
//		session = null;
//	}
	
	
//    /**
//     * A special static method to access the single AuthServiceImpl instance
//     * @return _obj - type: AuthServiceImpl
//     * Singleton pattern
//     */
//    public static AuthenticationService getInstance()
//    {
//    	//Checking if the instance is null, then it will create new one and return it
//        if (_obj == null)  
//        //otherwise it will return previous one.
//        {
//            _obj = new AuthenticationService();
//        }
//        return _obj;
//    }
	
//	
//	public boolean checkUserAccess(String userName, Integer projectId, String service) throws SessionTimeouException
//	{
//		boolean result = false;
//		// it could be performance burden, but assures that the session is up
//		Object userObject = session.getAttribute(C_USER_SESSION);
//		
//		if (userObject != null)
//		{
//			User user  = (User) userObject;
//			// validate if the userName is not injected!
//			if (user.getUsername().equals(userName))
//			{
//				// is Role is intended to be a permission like for simplification purpose?
////				if(checkPermission(user.getPermissions(), service))
////				{
////					result = true;
////				}
//			}
//			else
//			{
//				// TODO
//			}	
//		}
//		else
//		{
//			//do we want to invalidate the session and logout the user here?
//			logout();
//			throw new SessionTimeouException("timeout exception");
//		}
//		
//		return result;
//	}
	
//    private boolean checkPermission(List<Permission> permissions, String service)
//    {
//    	boolean result = false;
//    	for (Permission perm : permissions)
//    	{
//    		if(perm.getService().equals(service))
//    		{
//    			result = true;
//    			break;
//    		}          
//    	}
//    	return result;
//    }
	
	
	public User validateCredentials(String username, String password)
	{
		Session session = sessionFactory.openSession();
		
		Query query = session.createQuery("from User where username = '" + username + "' and password = '" + password + "'");
		
		List<User> userList = query.list();
		
		if (userList != null && userList.size() > 0){
			return userList.get(0);
		} else
		{
			return null;
		}
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

	public User getCurrentUser(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute(AuthenticationService.C_USER_SESSION) != null){
			return (User)session.getAttribute(AuthenticationService.C_USER_SESSION);
		}
		
		return null;
	}
	
}
