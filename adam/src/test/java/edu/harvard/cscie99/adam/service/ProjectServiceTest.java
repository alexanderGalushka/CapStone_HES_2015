package edu.harvard.cscie99.adam.service;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import edu.harvard.cscie99.adam.config.PersistenceConfig;
import junit.framework.TestCase;

public class ProjectServiceTest extends TestCase {

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
