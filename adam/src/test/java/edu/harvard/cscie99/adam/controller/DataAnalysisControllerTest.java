package edu.harvard.cscie99.adam.controller;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import edu.harvard.cscie99.adam.config.PersistenceConfig;
import edu.harvard.cscie99.adam.config.PersistenceXmlConfig;
import edu.harvard.cscie99.adam.model.Curve;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
        classes = { PersistenceConfig.class, PersistenceXmlConfig.class })
public class DataAnalysisControllerTest {
	
	@Autowired
	private DataAnalysisController daController;
	
	private double[] xArray;
	private double[] yArray;
	private String fit;
	
	@Before
	public void populateData(){
		xArray= new double[]{0.1, 0.2, 0.3, 0.4, 0.1, 0.2, 0.3, 0.4, 0.1, 0.2, 0.3, 0.4};
		yArray= new double[]{0.9, 0.8, 0.7, 0.6, 0.1, 0.2, 0.3, 0.4, 0.9, 0.8, 0.7, 0.6};
		fit = "fitX";
	}
	
	
	@Test
	public void testGettingListOfCurves(){
		List<Curve> curves = daController.listOfCurves(xArray, yArray, fit);
		
		assertNotNull(curves);
		assertEquals(7, curves.size());
	}

}
