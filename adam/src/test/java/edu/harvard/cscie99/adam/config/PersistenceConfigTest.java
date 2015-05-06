package edu.harvard.cscie99.adam.config;

import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
        classes = { PersistenceConfig.class, PersistenceXmlConfig.class })
public class PersistenceConfigTest extends TestCase {

	@Autowired
	PersistenceConfig config;

	@Test
	public void testHibernateSessionCreation(){
		
		try{
			LocalSessionFactoryBean beanFactory = config.sessionFactory();
			assertNotNull(beanFactory);
		}
		catch (Exception ex){
			fail();
		}
	}
	
	@Test
	public void testRestDataSourceCreation(){
		
		try{
			DataSource ds = config.restDataSource();
			Connection c = ds.getConnection();
			assertNotNull(c);
			c.close();
		}
		catch (Exception ex){
			fail();
		}
	}
	
	@Test
	public void testHibernatePropertiesFile(){
		Properties prop = config.hibernateProperties();
		assertNotNull(prop);
		assertTrue(prop.containsKey("hibernate.hbm2ddl.auto"));
		assertTrue(prop.containsKey("hibernate.dialect"));
	}
	
	@Test
	public void testGetSessionFactory(){
		LocalSessionFactoryBean session = config.sessionFactory();
		assertNotNull(session);
	}

}
