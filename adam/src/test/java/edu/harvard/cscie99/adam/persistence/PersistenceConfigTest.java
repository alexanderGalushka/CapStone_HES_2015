package edu.harvard.cscie99.adam.persistence;

import java.util.List;

import junit.framework.TestCase;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import edu.harvard.cscie99.adam.config.PersistenceConfig;
import edu.harvard.cscie99.adam.config.PersistenceXmlConfig;
//import edu.harvard.cscie99.adam.model.Compound;
import edu.harvard.cscie99.adam.profile.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
        classes = { PersistenceConfig.class, PersistenceXmlConfig.class })
public class PersistenceConfigTest extends TestCase{
	
	@Autowired
	private PersistenceConfig persistenceConfig;
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Test
	public void testDatabaseBootstrap(){
		
//		Compound compound = new Compound();
//		compound.setName("NaCl");
		
		Session session = sessionFactory.openSession();
//		session.beginTransaction();
//		session.save(compound);
//		session.getTransaction().commit();
		session.close();
		assertTrue(true);
		
	}

}
