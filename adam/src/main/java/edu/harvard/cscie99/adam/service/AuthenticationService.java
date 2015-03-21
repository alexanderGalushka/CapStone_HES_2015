package edu.harvard.cscie99.adam.service;

import org.springframework.stereotype.Component;

import edu.harvard.cscie99.adam.profile.User;

@Component
public class AuthenticationService {

	public boolean checkUserAccess(String user, Integer plateId, String service) {
		// TODO implement
		return true;
	}
	
	public User getUserDetails(String username){
		//TODO implement
		return null;
	}
}
