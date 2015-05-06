package edu.harvard.cscie99.adam.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import edu.harvard.cscie99.adam.config.PersistenceConfig;
import edu.harvard.cscie99.adam.config.PersistenceXmlConfig;
import edu.harvard.cscie99.adam.profile.User;
import edu.harvard.cscie99.adam.service.UserService;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
        classes = { PersistenceConfig.class, PersistenceXmlConfig.class })
public class UserServiceTest extends TestCase {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testUserCreation(){
		
		User user = new User();
		user.setUsername("testUser");
		user.setPassword("testPass");
		try{
			user = userService.createUser(user);
		}
		catch(Exception e){
			fail();
		}
		
		//ensures user was inserted in DB
		assertNotNull(user);
		assertNotSame(user.getId(), 0);
	}
	
	@Test
	public void testUserRetrieval(){
		
		User user = null;
		try{
			user = userService.retrieveUser("testUser");
		}
		catch(Exception e){
			fail();
		}
		assertNotNull(user);
		assertEquals("testUser", user.getUsername());
	}
	
	@Test
	public void testListAllUsers(){
		
		List<User> users = null;
		try{
			users = userService.listUsers(null);
		}
		catch(Exception e){
			fail();
		}
		assertNotNull(users);
		assertFalse(users.isEmpty());
	}
	
	@Test
	public void testListUsersButOne(){
		
		User user = new User();
		user.setUsername("not");
		user.setPassword("not");
		user = userService.createUser(user);
		
		List<User> users = null;
		try{
			//list all users but one
			users = userService.listUsers("not");
		}
		catch(Exception e){
			fail();
		}
		assertNotNull(users);
		assertSame(users.size(), 1);
	}
	
	@Test
	public void testEditUser(){
		
		User user = new User();
		user.setUsername("testUser");
		user.setLastName("changedLastName");
		user.setFirstName("changedFirstName");
		
		try{
			user = userService.editUser(user);
		}
		catch(Exception e){
			fail();
		}
		assertEquals(user.getLastName(), "changedLastName");
		assertEquals(user.getFirstName(), "changedFirstName");
	}
	
	@Test
	public void testRemoveUser(){
		
		User user = new User();
		user.setUsername("testUser");
		
		boolean result = false;
		try{
			result = userService.removeUser(user);
		}
		catch(Exception e){
			fail();
		}
		assertTrue(result);
	}

}

