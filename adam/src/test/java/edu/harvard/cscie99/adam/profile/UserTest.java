package edu.harvard.cscie99.adam.profile;

import static org.junit.Assert.*;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import edu.harvard.cscie99.adam.config.PersistenceConfig;

public class UserTest {
	
	@Autowired
	private PersistenceConfig persistenceConfig;
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Test
	public void testReadColumnBasedFile(){
		
		//TODO
		assertTrue(true);
	}

}
