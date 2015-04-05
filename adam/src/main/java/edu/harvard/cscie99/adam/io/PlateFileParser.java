package edu.harvard.cscie99.adam.io;

import java.io.BufferedReader;
import java.io.IOException;

import org.springframework.stereotype.Component;


//import edu.harvard.cscie99.adam.model.Compound;
import edu.harvard.cscie99.adam.model.Plate;
import edu.harvard.cscie99.adam.model.Well;
import edu.harvard.cscie99.adam.model.WellLabel;

/**
 * 
 * @creator Gerson
 */
@Component
public class PlateFileParser {
	
	public Plate parse(BufferedReader lines) throws IOException{
		
		String line = lines.readLine();
		
		Plate plate = new Plate();
		
        while (line != null) {
        	String[] fields = line.split(",");
        	
        	//Ignore blank lines
        	if (fields == null || fields.length == 0){
        		continue;
        	}
        	
        	//Ignore commented lines
        	if (fields[0].startsWith("#")){
        		continue;
        	}
        	
        	try{
        		
        		int row = Integer.parseInt(fields[0]);
        		int col = Integer.parseInt(fields[1]);
        		String wellType = fields[2];
        		String labelName = fields[3];
        		String labelValue = fields[4];
        		String compound = fields[5];
        		double quantity = Double.parseDouble(fields[6]);
        		String unit = null;
        		
        		if (fields.length >= 8){
        			unit = fields[7];
        		}
        		
        		Well well = new Well();
        		if (Well.ControlType.NEGATIVE.equals(wellType) ){
        			well.setControlType(Well.ControlType.NEGATIVE);
        		}
        		else if (Well.ControlType.POSITIVE.equals(wellType)){
        			well.setControlType(Well.ControlType.POSITIVE);
        		}
        		else if (Well.ControlType.COMPOUND.equals(wellType)){
        			well.setControlType(Well.ControlType.COMPOUND);
        		}
        		else if (Well.ControlType.EMPTY.equals(wellType)){
        			well.setControlType(Well.ControlType.EMPTY);
        		}
        		
        		well.setPlatePositionX(row);
        		well.setPlatePositionY(col);
        		
        		WellLabel label = new WellLabel();
        		label.setName(labelName);
        		label.setValue(labelValue);
        		
        		well.getWellLabels().add(label);
        		plate.getWells().add(well);
        		        		
        	}
        	catch (Exception ex){
        		continue;
        	}
            
            line = lines.readLine();
        }
	    
		return plate;
	}

}
