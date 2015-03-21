package edu.harvard.cscie99.adam.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import edu.harvard.cscie99.adam.profile.User;

/**
 * 
 * @author Gerson
 *
 */
@Component
public class ProfileService {

	public List<User> listAllUsers(){
		
		//TODO: get data from DB
		List<User> userList = new ArrayList<User>();
		
		for (int i = 0; i < 10; i++){
			User user = new User();
			user.setEmail("user"+i+"@adam.com");
			user.setUsername("user"+i);
			userList.add(user);
		}
		return userList;
	}
	
	public User getUserDetails(String username){
		//TODO implement
		return null;
	}
	
}
