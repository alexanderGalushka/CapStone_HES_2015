package edu.harvard.cscie99.adam.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import edu.harvard.cscie99.adam.config.PersistenceConfig;
import edu.harvard.cscie99.adam.config.PersistenceXmlConfig;
import edu.harvard.cscie99.adam.error.InvalidPlateFileException;
import edu.harvard.cscie99.adam.model.Plate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
        classes = { PersistenceConfig.class, PersistenceXmlConfig.class })
public class PlateFileParserTest {
	
	@Autowired
	private PlateFileParser plateFileParser;
	
	BufferedReader plateFile = null;
	
	@Before
	public void openFile(){
		try {
			plateFile = new BufferedReader(new FileReader("src/test/java/edu/harvard/cscie99/adam/io/plate_1.csv"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFileParser(){
		
		Plate plate = null;
		try {
			plate = plateFileParser.parse(plateFile);
		} catch (IOException | InvalidPlateFileException e) {
			org.junit.Assert.fail();
		}
		org.junit.Assert.assertNotNull(plate);
		org.junit.Assert.assertEquals(plate.getWells().size(), 45);
	}

}
