package edu.harvard.cscie99.adam.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Component;

import edu.harvard.cscie99.adam.model.Measurement;
import edu.harvard.cscie99.adam.model.ResultSnapshot;

@Component
public class ResultFileParser {
	
	public ResultSnapshot parse(BufferedReader lines) throws IOException{
		
		String line = lines.readLine();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH);
		Date measuredAt = null;
		
		ResultSnapshot resultSnapshot = new ResultSnapshot();
		
        while (line != null) {
        	String[] fields = line.split(",");
        	
        	//Ignore blank lines
        	if (fields == null || fields.length == 0){
        		line = lines.readLine();
        	}
        	
        	//Ignore commented lines
        	if (fields[0].startsWith("#")){
        		line = lines.readLine();
        	}
        	
        	try{
        		
        		int row = Integer.parseInt(fields[0]);
        		int col = Integer.parseInt(fields[1]);
        		double value = Double.parseDouble(fields[2]);
        		String label = null;
        		
        		if (fields.length >= 4){
        			label = fields[3];
        		}
        		if (fields.length >= 5){
        			measuredAt = dateFormat.parse(fields[4]);
        			resultSnapshot.setTime(measuredAt);
        		}
        		
        		Measurement measure = new Measurement();
        		measure.setColumn(col);
        		measure.setRow(row);
        		measure.setValue(value);
        		measure.setMeasurementType(label); 
        		resultSnapshot.getMeasurements().add(measure);
        		        		
        	}
        	catch (Exception ex){
        		continue;
        	}
            
            line = lines.readLine();
        }
	    
		return resultSnapshot;
	}

}
