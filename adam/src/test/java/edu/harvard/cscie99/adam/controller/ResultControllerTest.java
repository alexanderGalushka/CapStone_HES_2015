package edu.harvard.cscie99.adam.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.fasterxml.jackson.core.JsonProcessingException;

import edu.harvard.cscie99.adam.config.PersistenceConfig;
import edu.harvard.cscie99.adam.config.PersistenceXmlConfig;
import edu.harvard.cscie99.adam.model.Measurement;
import edu.harvard.cscie99.adam.model.Plate;
import edu.harvard.cscie99.adam.model.Project;
import edu.harvard.cscie99.adam.model.ResultSnapshot;
import edu.harvard.cscie99.adam.model.WellLabel;
import edu.harvard.cscie99.adam.service.PlateService;
import edu.harvard.cscie99.adam.service.ProjectService;
import edu.harvard.cscie99.adam.service.ResultService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
        classes = { PersistenceConfig.class, PersistenceXmlConfig.class })
public class ResultControllerTest {
	
	@Autowired
	private ResultController resultController;
	
	@Autowired
	private ResultService resultService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private PlateService plateService;
	
	private int recentlyCreatedResultID = 0;
	
	@Before
	public void populateData(){
		
		//Create a plate
		Plate plate = new Plate();
		plate.setName("plate1");
		plate.setNumberOfRows(5);
		plate.setNumberOfColumns(5);
		
		for (int i = 0; i < 3; i++){
			WellLabel wellLabel = new WellLabel();
			wellLabel.setName("compound" +i);
		}
		plateService.createPlate(plate);
		
		//Create a project
		Project project = new Project();
		project.setName("proj1");
		
		List<Plate> plates = new ArrayList<Plate>();
		plates.add(plate);
		project.setPlates(plates);
		projectService.createProject(project);
		
		//Assign plate to project
		plate.setProjectId(project.getId()+"");
		plateService.updatePlate(plate);
		
		//Create a result
		ResultSnapshot result = new ResultSnapshot();
		List<Measurement> measures = new ArrayList<Measurement>();
		
		for (int i = 0; i < 5; i++){
			for (int j = 0; j < 5; j++){
			
				Measurement m = new Measurement();
				m.setColumn(j);
				m.setRow(i);
				m.setMeasurementType("type"+i);
				m.setValue(Math.random());
				measures.add(m);
			}
		}
		result.setTime(new Date());
		result.setMeasurements(measures);
		resultService.saveResultSnapshot(result);
		
		//Assign result to plate
		List<ResultSnapshot> listResults = new ArrayList<ResultSnapshot>();
		listResults.add(result);
		plate.setResults(listResults);
		plateService.updatePlate(plate);
		
		recentlyCreatedResultID = result.getId();
	}
	
	
	@Test
	public void testGetResults(){
		
		ResultSnapshot result = null;
		try {
			result = resultController.getResults(recentlyCreatedResultID);
		} catch (JsonProcessingException e) {
			fail();
		}
		
		assertNotNull(result);
		assertNotNull(result.getTime());
		assertEquals(25, result.getMeasurements().size());
	}
	
	@Test
	public void testListResults(){
		
		List<ResultSnapshot> results = null;
		try {
			results = resultController.listResults();
		} catch (JsonProcessingException e) {
			fail();
		}
		
		assertNotNull(results);
		assertFalse(results.isEmpty());
	}
	
	@Test
	public void testGetAllResults(){
		
		List<HashMap<Object, Object>> results = null;
		try {
			results = resultController.getAllResults(1);
		} catch (JsonProcessingException e) {
			fail();
		}
		
		assertNotNull(results);
		assertFalse(results.isEmpty());
		assertTrue(results.get(0).containsKey("plateId"));
		assertEquals(results.get(0).get("plateId"), 1);
	}
	
	@Test
	public void testGetAllPossibleResults(){
		
		Map<Object, ArrayList<HashMap<Object, Object>>> results = null;
		try {
			results = resultController.getAllPossibleResults();
		} catch (JsonProcessingException e) {
			fail();
		}
		
		assertNotNull(results);
		assertFalse(results.isEmpty());
		assertTrue(results.containsKey(1));
	}
}
		

	

