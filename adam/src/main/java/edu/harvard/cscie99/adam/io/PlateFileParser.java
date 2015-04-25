package edu.harvard.cscie99.adam.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import org.springframework.stereotype.Component;





import edu.harvard.cscie99.adam.model.ControlType;
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
		
		HashMap<String, Well> wellsMap = new HashMap<String, Well>();
		HashSet<String> plateLabelNames = new HashSet<String>();
		HashSet<String> plateControlTypes = new HashSet<String>();
		
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
        		
        		int row = Integer.parseInt(fields[0].trim());
        		int col = Integer.parseInt(fields[1].trim());
        		String wellType = fields[2].trim();
        		String labelName = fields[3].trim();
        		String labelValue = fields[4].trim();
        		String compound = fields[5].trim();
        		String quantity = fields[6].trim();
        		String unit = null;
        		
        		if (fields.length >= 8){
        			unit = fields[7];
        		}
        		
        		Well well = null;
        		if (wellsMap.containsKey(row + "-" + col)){
        			well = wellsMap.get(row + "-" + col);
        			
        		}else{
        			well = new Well();
        			wellsMap.put(row+"-"+col, well);
        			
//        			ControlType ct = new ControlType();
//        			ct.setName(wellType);
            		
        			well.setControlType(wellType);
            		well.setRow(row);
            		well.setCol(col);
        		}
        		
        		WellLabel label = new WellLabel();
        		label.setName(labelName);
        		label.setValue(labelValue);
        		
        		well.getWellLabels().add(label);
//        		plate.getWells().add(well);
        		plateLabelNames.add(labelName);
        		plateControlTypes.add(wellType);
        		        		
        	}
        	catch (Exception ex){
        		line = lines.readLine();
        		continue;
        	}
            
            line = lines.readLine();
        }
        
        //add Wells to plate
        for (String wellKey : wellsMap.keySet()){
        	Well well = wellsMap.get(wellKey);
        	plate.getWells().add(well);
        }
        
        //add Label names to plate
        for (String labelName : plateLabelNames){
        	WellLabel label = new WellLabel();
        	label.setName(labelName);
        	
        	plate.getWellLabels().add(label);
        }
        
        //add Control Types to plate
        for (String controlType : plateControlTypes){
        	ControlType ct = new ControlType();
        	ct.setName(controlType);
        	
        	if (controlType != null && controlType.length() >= 1){
        		ct.setDisplayChar(controlType.substring(0, 1));
        	}
        	plate.getControlTypes().add(ct);
        }
	    
		return plate;
	}

}
