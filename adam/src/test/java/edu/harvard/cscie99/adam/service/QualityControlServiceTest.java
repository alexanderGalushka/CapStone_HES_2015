package edu.harvard.cscie99.adam.service;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import edu.harvard.cscie99.adam.config.PersistenceConfig;
import edu.harvard.cscie99.adam.config.PersistenceXmlConfig;
import edu.harvard.cscie99.adam.model.Measurement;
import edu.harvard.cscie99.adam.model.Plate;
import edu.harvard.cscie99.adam.model.Project;
import edu.harvard.cscie99.adam.model.QCdataTimeWrapper;
import edu.harvard.cscie99.adam.model.ResultSnapshot;
import edu.harvard.cscie99.adam.model.Well;
import edu.harvard.cscie99.adam.model.WellLabel;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
        classes = { PersistenceConfig.class, PersistenceXmlConfig.class })
public class QualityControlServiceTest {

	@Autowired
    private SessionFactory sessionFactory;
	
	@Autowired
    private ProjectService projectService;
	
	@Autowired
    private QualityControlService qualityControlService;
	
	@Test
	public void test()
	{
		testIt(false);
	}

	
	
	private void testIt (boolean ifToUseDB)
	{
		if(ifToUseDB)
		{
			Project moqupProject = createProjectData();

			Project createdProject = projectService.createProject(moqupProject);

			Map<Integer, List<QCdataTimeWrapper>> resultsMap = qualityControlService.qualifyData(createdProject.getId());
			
			Set<Integer> setOfKeys = resultsMap.keySet();
			
			Integer arrayOfKeys[] = new Integer[setOfKeys.size()];

			int i = 0;
			for(Integer key : setOfKeys)
			{
				arrayOfKeys[i] = key;
				i++;
			}
	        // test for correct plate ID
			assertEquals(Integer.valueOf(100001), arrayOfKeys[0]);
			
			List<QCdataTimeWrapper> listOfQCdataTimeWrappers = resultsMap.get(100001);
			
			// Need the values, need time, etc.
			
			boolean isDone = projectService.deleteProject(createdProject);
			
			// when done with a test - delete project, close session 
			fail("Not yet implemented");
		}
		else
		{
			Project moqupProject = createProjectData();

			Map<Integer, List<QCdataTimeWrapper>> resultsMap = qualityControlService.actualDataQualification(moqupProject);
			
			Set<Integer> setOfKeys = resultsMap.keySet();
			
			Integer arrayOfKeys[] = new Integer[setOfKeys.size()];

			int i = 0;
			for(Integer key : setOfKeys)
			{
				arrayOfKeys[i] = key;
				i++;
			}
	        // test for correct plate ID
			assertEquals(Integer.valueOf(100001), arrayOfKeys[0]);
			
			List<QCdataTimeWrapper> listOfQCdataTimeWrappers = resultsMap.get(100001);
			
			// Need the values, need time, etc.
			
			
			// when done with a test - delete project, close session 
			fail("Not yet implemented");
		}
	}
	
	
	private Project createProjectData()
	{
		Date date = new Date();
		Project project = new Project();
		Plate plate = new Plate();
		
		plate.setId(100001);
		
		ArrayList<WellLabel> wellLabels = new ArrayList<WellLabel>();
		WellLabel wellLabel = new WellLabel();
		wellLabel.setName("compound type");
		wellLabels.add(wellLabel);
		
		wellLabel = new WellLabel();
		wellLabel.setName("cell type");
		wellLabels.add(wellLabel);
		
		wellLabel = new WellLabel();
		wellLabel.setName("protein bind %");
		wellLabels.add(wellLabel);
		
		plate.setWellLabels(wellLabels);
		
		for (int i = 1; i < 12; i++)
		{
			for (int j = 1; j < 12; j++)
			{
				Well well = new Well();
				well.setRow(i);
				well.setCol(j);
				
				
				
				if (i == 1 && j == 2 || i == 2 && j == 1)
				{
					well.setIfValid(false);
				}
				
				if(i%3 == 0)
				{
					well.setControlType(Well.ControlType.NEG);
				}
				else if(i%3 == 1)
				{
					well.setControlType(Well.ControlType.POS);
				}
				else
				{
					well.setControlType(Well.ControlType.EMPTY);
				}
				
				List<Measurement> listMeasures = new ArrayList<>();
				
				for (int k = 0; k < 5; k++)
				{
					Measurement measure = new Measurement();
					measure.setRow(i);
					measure.setColumn(j);
					
					if (k % 2 == 0){
						measure.setMeasurementType("viscosity");
					}
					else{
						measure.setMeasurementType("temperature");
					}
					measure.setValue(1d/k+j);
					listMeasures.add(measure);
				}
				
		
				ResultSnapshot result = new ResultSnapshot();
				result.setMeasurements(listMeasures);

				result.setTime(date);
				
				List<ResultSnapshot> resultSnapshots = new ArrayList<>();
				well.setResultSnapshots(resultSnapshots);
				
				wellLabels = new ArrayList<WellLabel>();
				wellLabel = new WellLabel();
				wellLabel.setName("cell type");
				wellLabel.setValue("zebra"+i);
				wellLabels.add(wellLabel);
				
				wellLabel = new WellLabel();
				wellLabel.setName("compound type");
				wellLabel.setValue("H" + i +"Cl"+j);
				wellLabels.add(wellLabel);
				
				wellLabel = new WellLabel();
				wellLabel.setName("protein bind %");
				wellLabel.setValue(i*j+"%");
				wellLabels.add(wellLabel);
				
				well.setWellLabels(wellLabels);
				plate.getWells().add(well);
			}
		}
		return project;
		
	}
}

