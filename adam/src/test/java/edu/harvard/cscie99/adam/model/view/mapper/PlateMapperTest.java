package edu.harvard.cscie99.adam.model.view.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import junit.framework.TestCase;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import edu.harvard.cscie99.adam.config.PersistenceConfig;
import edu.harvard.cscie99.adam.config.PersistenceXmlConfig;
import edu.harvard.cscie99.adam.model.ControlType;
import edu.harvard.cscie99.adam.model.Tag;
import edu.harvard.cscie99.adam.model.Well;
import edu.harvard.cscie99.adam.model.WellLabel;
import edu.harvard.cscie99.adam.model.Plate;
//import edu.harvard.cscie99.adam.model.Compound;
import edu.harvard.cscie99.adam.profile.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
        classes = { PersistenceConfig.class, PersistenceXmlConfig.class })
public class PlateMapperTest extends TestCase{
	
	private Plate persistPlate = null;
	private edu.harvard.cscie99.adam.model.view.Plate viewPlate = null;
	
	@Before
	public void createPersistencePlate(){
		persistPlate = new Plate();
		persistPlate.setId(1);
		persistPlate.setBarcode("barcode");
		
		ArrayList<ControlType> controlTypes = new ArrayList<ControlType>();
		ControlType ct = new ControlType();
		ct.setDisplayChar("c");
		ct.setName("Control");
		controlTypes.add(ct);
		persistPlate.setControlTypes(controlTypes);
		persistPlate.setCreationDate("date");
		persistPlate.setLabel("label");
		persistPlate.setName("name");
		persistPlate.setNumberOfColumns(5);
		persistPlate.setNumberOfRows(5);
		persistPlate.setOwner("testUser");
		persistPlate.setProjectId("1");
		persistPlate.setProtocolId("protocolId");
		
		List<Tag> tags = new ArrayList<Tag>();
		Tag tag = new Tag();
		tag.setDescription("tag");
		tags.add(tag);
		
		persistPlate.setTags(tags);
		
		List<WellLabel> wellLabels = new ArrayList<WellLabel>();
		for (int i = 0; i < 3; i++){
			WellLabel wl = new WellLabel();
			wl.setName("well"+i);
			wellLabels.add(wl);
		}
		persistPlate.setWellLabels(wellLabels);
		
		List<Well> wells = new ArrayList<Well>();
		for (int i = 0; i < 5; i++){
			for (int j = 0; j < 5; j++){
				Well well = new Well();
				well.setRow(i);
				well.setCol(j);
				well.setIfValid(true);
				well.setWellLabels(wellLabels);
				wells.add(well);
			}
		}
		persistPlate.setWells(wells);
	}
	
	@Before
	public void createViewPlate(){
		viewPlate = new edu.harvard.cscie99.adam.model.view.Plate();
		viewPlate.setBarcode("barcode");
		viewPlate.setId(1);
		
		ArrayList<ControlType> controlTypes = new ArrayList<ControlType>();
		ControlType ct = new ControlType();
		ct.setDisplayChar("c");
		ct.setName("Control");
		controlTypes.add(ct);
		viewPlate.setControlTypes(controlTypes);
		viewPlate.setCreationDate("date");
		viewPlate.setLabel("label");
		viewPlate.setName("name");
		viewPlate.setNumberOfColumns(5);
		viewPlate.setNumberOfRows(5);
		viewPlate.setOwner("testUser");
		viewPlate.setProjectId("1");
		viewPlate.setProtocolId("protocolId");
		
		List<Tag> tags = new ArrayList<Tag>();
		Tag tag = new Tag();
		tag.setDescription("tag");
		tags.add(tag);
		
		viewPlate.setTags(tags);
		
		List<WellLabel> wellLabels = new ArrayList<WellLabel>();
		for (int i = 0; i < 3; i++){
			WellLabel wl = new WellLabel();
			wl.setName("well"+i);
			wellLabels.add(wl);
		}
		viewPlate.setWellLabels(wellLabels);
		
		List<HashMap<String, String>> wells = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < 5; i++){
			for (int j = 0; j < 5; j++){
				HashMap<String, String> well = new HashMap<String, String>();
				well.put("row", ""+i);
				well.put("col", ""+j);
				well.put("ifValid", "true");
				
				for (WellLabel wl : wellLabels){
					well.put(wl.getName(), wl.getValue());
				}
				
				wells.add(well);
			}
		}
		viewPlate.setWells(wells);
	}
	
	@Test
	public void testConversionViewToPersistence(){
		Plate converted = PlateMapper.getPersistencePlate(viewPlate);
		assertNotNull(converted);
		assertEquals(converted, persistPlate);
	}
	
	@Test
	public void testConversionPersistencetoView(){
		edu.harvard.cscie99.adam.model.view.Plate converted = PlateMapper.getViewPlate(persistPlate);
		assertNotNull(converted);
		assertEquals(converted, viewPlate);
	}
}
