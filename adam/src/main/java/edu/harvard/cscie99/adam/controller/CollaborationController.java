package edu.harvard.cscie99.adam.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.harvard.cscie99.adam.profile.User;
import edu.harvard.cscie99.adam.service.AuthenticationService;
import edu.harvard.cscie99.adam.service.UserService;

@RestController
@RequestMapping(value = "/")
public class CollaborationController {
	
	//TODO implement

	@Autowired
	private AuthenticationService authService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/rest/user", method = RequestMethod.GET)
	public @ResponseBody List<User> getListUser(){
		
		List<User> listUser = userService.listUsers(null);
		
		for (User user : listUser){
			user.setPassword(null);
		}
		return listUser;
	}
	
	@RequestMapping(value = "/rest/user/others", method = RequestMethod.GET)
	@ResponseBody
	public List<User> listOtherUsers(HttpServletRequest request){
		
		User currentUser = authService.getCurrentUser(request);
		
		List<User> listUser = userService.listUsers(currentUser.getUsername());
		for (User user : listUser){
			user.setPassword(null);
		}
		return listUser;
	}
	
	@RequestMapping(value = "/rest/user/{username}", method = RequestMethod.GET)
	@ResponseBody
	public User getUser(@PathVariable("username") String username){
		
		User user = userService.retrieveUser(username);
		if (user != null)
			user.setPassword(null);
		return user;
	}
	
	@RequestMapping(value = "/rest/user", method = RequestMethod.POST)
	@ResponseBody
	public User create(@RequestBody User user,
			HttpServletRequest request){
		
		return userService.createUser(user);
	}
	
	@RequestMapping(value = "/rest/user/{username}", method = RequestMethod.PUT)
	@ResponseBody
	public User editUser(@RequestBody User user,
			@PathVariable("username") String username,
			HttpServletRequest request){
		
		User currentUser = userService.retrieveUser(username);
		currentUser.setEmail(user.getEmail());
		currentUser.setFirstName(user.getFirstName());
		currentUser.setLastName(user.getLastName());
		currentUser.setPassword(user.getPassword());
		currentUser.setSecurityAnswer(user.getSecurityAnswer());
		currentUser.setSecurityQuestion(user.getSecurityQuestion());
		
		User resultUser = userService.editUser(currentUser);
		resultUser.setPassword(null);
		return resultUser;
	}
	
	@RequestMapping(value = "/rest/user/{username}", method = RequestMethod.DELETE)
	@ResponseBody
	public boolean removeUser(@PathVariable("username") String username){
		
		User user = userService.retrieveUser(username);
		
		return userService.removeUser(user);
	}
}
