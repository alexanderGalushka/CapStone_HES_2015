package edu.harvard.cscie99.adam.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import edu.harvard.cscie99.adam.model.PlateResult;
import edu.harvard.cscie99.adam.model.Sample;
import edu.harvard.cscie99.adam.model.WellResult;

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

}
