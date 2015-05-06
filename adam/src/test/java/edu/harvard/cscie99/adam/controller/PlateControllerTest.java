package edu.harvard.cscie99.adam.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import edu.harvard.cscie99.adam.config.PersistenceConfig;
import edu.harvard.cscie99.adam.config.PersistenceXmlConfig;
import edu.harvard.cscie99.adam.error.UnauthorizedOperationException;
import edu.harvard.cscie99.adam.model.Measurement;
import edu.harvard.cscie99.adam.model.Project;
import edu.harvard.cscie99.adam.model.Plate;
import edu.harvard.cscie99.adam.model.ResultSnapshot;
import edu.harvard.cscie99.adam.model.Tag;
import edu.harvard.cscie99.adam.model.view.mapper.PlateMapper;
import edu.harvard.cscie99.adam.profile.User;
import edu.harvard.cscie99.adam.service.AuthenticationService;
import edu.harvard.cscie99.adam.service.PlateService;
import edu.harvard.cscie99.adam.service.ProjectService;
import edu.harvard.cscie99.adam.service.ResultService;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
        classes = { PersistenceConfig.class, PersistenceXmlConfig.class })
public class PlateControllerTest extends TestCase {

	Plate plate = null;
	ResultSnapshot result = null;
	User user = null;
	
	@Autowired
	private PlateController plateController;

	@Autowired
	private PlateService plateService;

	@Autowired
	private ResultService resultService;
	
	@Before
	public void prepareData(){
		plate = createPlateObject();
		plate = plateService.createPlate(plate);
		
		result = createResultObject();
		result = resultService.saveResultSnapshot(result);
		
		user = new User();
		user.setUsername("testUser");
		user.setPassword("testPass");
	}

	@Test
	public void testCreatePlate(){
		edu.harvard.cscie99.adam.model.view.Plate newPlate = new edu.harvard.cscie99.adam.model.view.Plate();
		newPlate.setNumberOfColumns(5);
		newPlate.setNumberOfRows(5);
		newPlate.setLabel("newPlate");
		
		HttpServletRequest request = (HttpServletRequest)Mockito.mock(HttpServletRequest.class);
		HttpSession session = (HttpSession)Mockito.mock(HttpSession.class);
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(session.getAttribute(AuthenticationService.C_USER_SESSION)).thenReturn(user);
		
		try{
			newPlate = plateController.createPlate(newPlate, request);
		}
		catch(Exception e){
			fail();
		}
		assertNotNull(newPlate);
		assertNotSame(newPlate.getId(), 0);
	}
	
	@Test
	public void testRetrievePlate(){
		
		edu.harvard.cscie99.adam.model.view.Plate _plate = null;
		try{
			_plate = plateController.getPlate(plate.getId());
		}
		catch(Exception e){
			fail();
		}
		assertNotNull(_plate);
	}
	
	@Test
	public void testListPlates(){
		
		List<edu.harvard.cscie99.adam.model.view.Plate> plates = null;
		try{
			plates = plateController.listPlates();
		}
		catch(Exception e){
			fail();
		}
		assertNotNull(plates);
		assertNotSame(plates.size(), 0);
	}
	
	@Test
	public void testUpdatePlate(){
		HttpServletRequest request = (HttpServletRequest)Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = (HttpServletResponse)Mockito.mock(HttpServletResponse.class);
		HttpSession session = (HttpSession)Mockito.mock(HttpSession.class);
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(session.getAttribute(AuthenticationService.C_USER_SESSION)).thenReturn(user);
		
		edu.harvard.cscie99.adam.model.view.Plate _plate = PlateMapper.getViewPlate(plate);
		
		try{
			_plate = plateController.updatePlate(_plate, _plate.getId(), request);
		}
		catch(Exception e){
			fail();
		}
		assertNotNull(_plate);
	}
	
	@Test
	public void testDeletePlate(){
		HttpServletRequest request = (HttpServletRequest)Mockito.mock(HttpServletRequest.class);
		HttpSession session = (HttpSession)Mockito.mock(HttpSession.class);
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(session.getAttribute(AuthenticationService.C_USER_SESSION)).thenReturn(user);
		
		Plate newPlate= createPlateObject();
		edu.harvard.cscie99.adam.model.view.Plate _plate = null;
		try {
			_plate = plateController.createPlate(PlateMapper.getViewPlate(newPlate), request);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		boolean success = false;
		try{
			success = plateController.removePlate(_plate.getId());
		}
		catch(Exception e){
			fail();
		}
		assertTrue(success);
	}
	
	@Test
	public void testAddingResultToPlate(){
		
		try {
			plateController.removeResultFromPlate(plate.getId(), result.getId());
		} catch (UnauthorizedOperationException e) {
			//no plate to remove
		}
		
		boolean success = false;
		try {
			success = plateController.addResultToPlate(plate.getId(), result.getId());
		} catch (UnauthorizedOperationException e) {
			fail();
		}
		
		assertTrue(success);
	}
	
	@Test
	public void testRemoveResultFromPlate(){
		
		try{
			plateController.removeResultFromPlate(plate.getId(), result.getId());
		}
		catch(Exception e){
			//already linked
		}
		
		boolean success = false;
		try {
			success = plateController.removeResultFromPlate(plate.getId(), result.getId());
		} catch (UnauthorizedOperationException e) {
			fail();
		}
		
		assertTrue(success);
	}
	
	private Plate createPlateObject(){
		Plate plate = new Plate();
		plate.setBarcode("barcode");
		plate.setCreationDate("date");
		plate.setIfValid(true);
		plate.setName("name");
		plate.setNumberOfColumns(5);
		plate.setNumberOfRows(5);
		return plate;
	}
	
	private ResultSnapshot createResultObject() {
		ResultSnapshot result = new ResultSnapshot();
		result.setTime(new Date());
		
		List<Measurement> measures = new ArrayList<Measurement>();
		for (int i = 0; i < 5; i++){
			for (int j = 0; j < 5; j++){
				Measurement measure = new Measurement();
				measure.setColumn(j);
				measure.setRow(i);
				measure.setValue(Math.random());
				measure.setMeasurementType("type"+i);
				measures.add(measure);
			}
		}
		result.setMeasurements(measures);
		return result;
	}

}
