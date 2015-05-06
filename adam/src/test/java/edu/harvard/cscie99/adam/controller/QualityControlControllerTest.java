package edu.harvard.cscie99.adam.controller;

import java.util.ArrayList;
import java.util.Date;
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
import edu.harvard.cscie99.adam.error.UnauthorizedOperationException;
import edu.harvard.cscie99.adam.model.Measurement;
import edu.harvard.cscie99.adam.model.Plate;
import edu.harvard.cscie99.adam.model.PlateValidationContainer;
import edu.harvard.cscie99.adam.model.Project;
import edu.harvard.cscie99.adam.model.QCplate;
import edu.harvard.cscie99.adam.model.ResultSnapshot;
import edu.harvard.cscie99.adam.model.WellLabel;
import edu.harvard.cscie99.adam.model.WellValidationContainer;
import edu.harvard.cscie99.adam.service.PlateService;
import edu.harvard.cscie99.adam.service.ProjectService;
import edu.harvard.cscie99.adam.service.ResultService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
        classes = { PersistenceConfig.class, PersistenceXmlConfig.class })
public class QualityControlControllerTest {
	
	@Autowired
	private QualityControlController qcController;
	
	@Autowired
	private ResultController resultController;
	
	@Autowired
	private ResultService resultService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private PlateService plateService;
	
	Plate plate = null;
	Project project = null;
	ResultSnapshot resultSnapshot = null;
	
	List<WellValidationContainer> listWellValidationContainer = new ArrayList<WellValidationContainer>();
	List<PlateValidationContainer> listPlateValidationContainer = new ArrayList<PlateValidationContainer>();
	
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
		
		//Create list of Well Validators
		for (int i = 0; i < 5; i++){
			for (int j = 0; j < 5; j++){
				WellValidationContainer wvc = new WellValidationContainer();
				wvc.setColNum(j);
				wvc.setRowNum(i);
				wvc.setPlateId(plate.getId());
				wvc.setProjectId(project.getId());
				wvc.setIfValid(i != j);
				listWellValidationContainer.add(wvc);
			}
		}
		
		PlateValidationContainer pvc = new PlateValidationContainer();
		pvc.setIfValid(true);
		pvc.setPlateId(plate.getId());
		pvc.setProjectId(project.getId());
		listPlateValidationContainer.add(pvc);
		
		this.plate = plate;
		this.project = project;
		this.resultSnapshot = result;
	}
	
	@Test
	public void testNormalizedDataPerPlate(){
		
		QCplate qcPlate = null;
		try {
			qcPlate = qcController.getNormalizedDataPerPlate(plate.getId());
		} catch (UnauthorizedOperationException e) {
			org.junit.Assert.fail();
		}
		org.junit.Assert.assertNotNull(qcPlate);
	}
	
	@Test
	public void testGetNormalizedDataPerProject(){
		
		List<QCplate> listQcPlate = null;
		try {
			listQcPlate = qcController.getNormalizedDataPerProject(project.getId());
		} catch (UnauthorizedOperationException e) {
			org.junit.Assert.fail();
		}
		org.junit.Assert.assertNotNull(listQcPlate);
		org.junit.Assert.assertFalse(listQcPlate.isEmpty());
	}
	
	@Test
	public void testGetNormalizedDataPerPlate(){

		QCplate qcPlate = null;
		try {
			qcPlate = qcController.getNormalizedDataPerPlate(plate.getId());
		} catch (UnauthorizedOperationException e) {
			org.junit.Assert.fail();
		}
		org.junit.Assert.assertNotNull(qcPlate);
	}
	
	@Test
	public void testValidateGroupOfPlates(){
		
		boolean success = false;
		try {
			success = qcController.validateGroupOfPlates(listPlateValidationContainer);
		} catch (UnauthorizedOperationException e) {
			org.junit.Assert.fail();
		}
		org.junit.Assert.assertTrue(success);
	}
	
	@Test
	public void testValidateGroupOfWells(){
		
		List<QCplate> listQcPlate = null;
		try {
			listQcPlate = qcController.validateGroupOfWells(listWellValidationContainer);
		} catch (UnauthorizedOperationException e) {
			org.junit.Assert.fail();
		}
		org.junit.Assert.assertNotNull(listQcPlate);
	}
	
	@Test
	public void testValidateSingleWell(){
		
		QCplate qcPlate = null;
		try {
			qcPlate = qcController.validateSingleWell(listWellValidationContainer.get(0));
		} catch (UnauthorizedOperationException e) {
			org.junit.Assert.fail();
		}
		org.junit.Assert.assertNotNull(qcPlate);
	}
}
