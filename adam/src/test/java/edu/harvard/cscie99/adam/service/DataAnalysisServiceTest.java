//package edu.harvard.cscie99.adam.service;
//
//import java.util.List;
//
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.support.AnnotationConfigContextLoader;
//
//import edu.harvard.cscie99.adam.config.PersistenceConfig;
//import edu.harvard.cscie99.adam.config.PersistenceXmlConfig;
//import edu.harvard.cscie99.adam.model.DataSet;
//
//import static org.junit.Assert.*;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
//        classes = { PersistenceConfig.class, PersistenceXmlConfig.class })
//public class DataAnalysisServiceTest {
//	
//	@Autowired
//	private DataAnalysisService dataAnalysisService;
//	
////	public void getAllMeasuredValuesTest(){
////		
////		List<DataSet> values = dataAnalysisService.getValuesUsingFilter(null, null, "viscosity", null, null);
////		assertNotNull(values);
////	}
//
//}
