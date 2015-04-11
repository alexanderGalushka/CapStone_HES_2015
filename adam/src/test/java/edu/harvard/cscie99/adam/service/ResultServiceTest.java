package edu.harvard.cscie99.adam.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.fasterxml.jackson.core.JsonProcessingException;

import edu.harvard.cscie99.adam.config.PersistenceConfig;
import edu.harvard.cscie99.adam.config.PersistenceXmlConfig;
import edu.harvard.cscie99.adam.model.DataSet;
import edu.harvard.cscie99.adam.model.Measurement;
import edu.harvard.cscie99.adam.model.Plate;
import edu.harvard.cscie99.adam.model.Project;
import edu.harvard.cscie99.adam.model.ResultSnapshot;
import edu.harvard.cscie99.adam.model.Well;
import edu.harvard.cscie99.adam.model.WellLabel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
        classes = { PersistenceConfig.class, PersistenceXmlConfig.class })
public class ResultServiceTest {
	
	@Autowired
	private ResultService  resultService;
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Test
	public void testServiceProject(){
		//TODO
		assertTrue(true);
	}

//	@Test
//	public void testAllMeasurementUniqueFeatures() {
//		
//		ResultSnapshot result = new ResultSnapshot();
//		
//		for (int i = 1; i < 5; i++){
//			for (int j = 1; j < 5; j++){
//				Measurement measure = new Measurement();
//				measure.setRow(i);
//				measure.setColumn(j);
//				
//				if (i % 2 == 0){
//					measure.setMeasurementType("viscosity");
//				}
//				else{
//					measure.setMeasurementType("temperature");
//				}
//				measure.setValue(1d/i+j);
//				result.getMeasurements().add(measure);
//			}
//		}
//
//		Project project = new Project();
//		Plate plate = new Plate();
//		
//		ArrayList<WellLabel> wellLabels = new ArrayList<WellLabel>();
//		WellLabel wellLabel = new WellLabel();
//		wellLabel.setName("compound type");
//		wellLabels.add(wellLabel);
//		
//		wellLabel = new WellLabel();
//		wellLabel.setName("cell type");
//		wellLabels.add(wellLabel);
//		
//		wellLabel = new WellLabel();
//		wellLabel.setName("protein bind %");
//		wellLabels.add(wellLabel);
//		
//		plate.setWellLabels(wellLabels);
//		
//		for (int i = 1; i < 5; i++){
//			for (int j = 1; j < 5; j++){
//				Well well = new Well();
//				well.setPlatePositionX(i);
//				well.setPlatePositionY(j);
//				
//				wellLabels = new ArrayList<WellLabel>();
//				wellLabel = new WellLabel();
//				wellLabel.setName("cell type");
//				wellLabel.setValue("zebra"+i);
//				wellLabels.add(wellLabel);
//				
//				wellLabel = new WellLabel();
//				wellLabel.setName("compound type");
//				wellLabel.setValue("H" + i +"Cl"+j);
//				wellLabels.add(wellLabel);
//				
//				wellLabel = new WellLabel();
//				wellLabel.setName("protein bind %");
//				wellLabel.setValue(i*j+"%");
//				wellLabels.add(wellLabel);
//				
//				well.setWellLabels(wellLabels);
//				plate.getWells().add(well);
//			}
//		}
//		
//		result.setPlate(plate);
//		result.setProject(project);
//		result.setTime(new Date());
//		
//		try {
//			assertTrue(resultService.prepareResultsData(result));
//		} catch (JsonProcessingException e) {
//			fail(e.getMessage());
//		}
//	}
	
//	@Test
//	public void testAllMeasurementGroupedFeatures() {
//		
//		ResultSnapshot result = new ResultSnapshot();
//		
//		for (int i = 1; i < 5; i++){
//			for (int j = 1; j < 5; j++){
//				Measurement measure = new Measurement();
//				measure.setRow(i);
//				measure.setColumn(j);
//				
//				if (i % 2 == 0){
//					measure.setMeasurementType("viscosity");
//				}
//				else{
//					measure.setMeasurementType("temperature");
//				}
//				measure.setValue(1d/i+j);
//				result.getMeasurements().add(measure);
//			}
//		}
//
//		Project project = new Project();
//		Plate plate = new Plate();
//		
//		ArrayList<WellLabel> wellLabels = new ArrayList<WellLabel>();
//		WellLabel wellLabel = new WellLabel();
//		wellLabel.setName("compound type");
//		wellLabels.add(wellLabel);
//		
//		wellLabel = new WellLabel();
//		wellLabel.setName("cell type");
//		wellLabels.add(wellLabel);
//		
//		wellLabel = new WellLabel();
//		wellLabel.setName("protein bind %");
//		wellLabels.add(wellLabel);
//		
//		plate.setWellLabels(wellLabels);
//		
//		for (int i = 1; i < 5; i++){
//			for (int j = 1; j < 5; j++){
//				Well well = new Well();
//				well.setRow(i);
//				well.setCol(j);
//				
//				wellLabels = new ArrayList<WellLabel>();
//				wellLabel = new WellLabel();
//				wellLabel.setName("cell type");
//				wellLabel.setValue("zebra");	
//				wellLabels.add(wellLabel);
//				
//				wellLabel = new WellLabel();
//				wellLabel.setName("compound type");
//				wellLabel.setValue("H1Cl4");
//				wellLabels.add(wellLabel);
//				
//				wellLabel = new WellLabel();
//				wellLabel.setName("protein bind %");
//				wellLabel.setValue("5%");
//				wellLabels.add(wellLabel);
//				
//				well.setWellLabels(wellLabels);
//				plate.getWells().add(well);
//			}
//		}
//		
//		plate.setProject(project);
////		result.setPlate(plate);
////		result.setProject(project);
//		result.setTime(new Date());
//		
//		Session session = sessionFactory.openSession();
//		session.beginTransaction();
//		session.save(project);
//		session.save(plate);
//		for (WellLabel label : plate.getWellLabels()){
//			session.save(label);
//		}
//		for (Well well : plate.getWells()){
//			for (WellLabel label : well.getWellLabels()){
//				session.save(label);
//			}
//			session.save(well);
//		}
//		session.save(result);
//		session.getTransaction().commit();
//		session.close();
//		
//		try {
//			assertTrue(resultService.prepareResultsData(result));
//		} catch (JsonProcessingException e) {
//			fail(e.getMessage());
//		}
//	}
}
