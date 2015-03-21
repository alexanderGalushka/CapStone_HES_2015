package edu.harvard.cscie99.adam.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.harvard.cscie99.adam.error.UnauthorizedOperationException;
import edu.harvard.cscie99.adam.model.Plate;
import edu.harvard.cscie99.adam.model.PlateResult;
import edu.harvard.cscie99.adam.model.Sample;
import edu.harvard.cscie99.adam.model.Well;
import edu.harvard.cscie99.adam.model.WellResult;
import edu.harvard.cscie99.adam.profile.User;

@Component
public class ResultService {
	
	public PlateResult getPlateResult(Integer plateNumber){
		
		//TODO: Read data from DB
		PlateResult plateResult = new PlateResult();
		
		List<WellResult> wellResults = new ArrayList<WellResult>();
		
		for (int i = 0; i< 100; i++){
			WellResult wellResult = new WellResult();
			
			List<String> labels = new ArrayList<String>();
			List<Sample> samples = new ArrayList<Sample>();

			Calendar cal = Calendar.getInstance(); 
		    cal.setTime(new Date());
		    
		    labels.add("compound1");
		    labels.add("compound2");
		    labels.add("compound3");
		    labels.add("compound4");

			for (int j = 0; j < 4; j++){
				Sample sample = new Sample();
				sample.setValue(Math.random());
				sample.setTime(cal.getTime());
				
				cal.add(Calendar.HOUR_OF_DAY, 1);
				samples.add(sample);
			}
			
			wellResult.setSamples(samples);
			
			wellResults.add(wellResult);
		}
		plateResult.setWells(wellResults);
		return plateResult;
	}
	
	public List<PlateResult> search(int projectId, int resultId, int plateId, Date creationDate, String comment, User owner){
		
		//TODO: search from DB
		
		List<PlateResult> results = new ArrayList<PlateResult>();
		for (int i = 0; i < 10; i++){
			
			Plate plate = new Plate();
			plate.setId(i);
			plate.setDescription("description"+i);
			plate.setName("plate"+i);
			
			List<WellResult> wells = new ArrayList<>();
			for (int j = 0; j < 10; j++){
				WellResult wellResult = new WellResult();
				wellResult.setCreationTime(new Date());
				wellResult.setId(j);
				wells.add(wellResult);
			}
			
			PlateResult result = new PlateResult();
			result.setCreationDate(new Date());
			result.setId(i);
			result.setPlate(plate);
			result.setWells(wells);
			
			results.add(result);
		}
		return results;
	}
	
	

	public PlateResult retrieveResult(int projectId, int resultId){
		
		//TODO retrieve from DB
		
		Plate plate = new Plate();
		plate.setId(1);
		plate.setDescription("description");
		plate.setName("plate");
		
		List<WellResult> wells = new ArrayList<>();
		for (int j = 0; j < 10; j++){
			WellResult wellResult = new WellResult();
			wellResult.setCreationTime(new Date());
			wellResult.setId(j);
			wells.add(wellResult);
		}
		
		PlateResult result = new PlateResult();
		result.setCreationDate(new Date());
		result.setId(1);
		result.setPlate(plate);
		result.setWells(wells);
		
		return result;
	}

}