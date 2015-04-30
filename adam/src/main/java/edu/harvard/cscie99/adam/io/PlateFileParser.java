package edu.harvard.cscie99.adam.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import org.springframework.stereotype.Component;






import edu.harvard.cscie99.adam.error.InvalidPlateFileException;
import edu.harvard.cscie99.adam.model.ControlType;
//import edu.harvard.cscie99.adam.model.Compound;
import edu.harvard.cscie99.adam.model.Plate;
import edu.harvard.cscie99.adam.model.Well;
import edu.harvard.cscie99.adam.model.WellLabel;

/**
 * PlateFileParser class
 * 
 * Responsible to parse the input file stream into Plate objects.
 * 
 * @creator Gerson
 */
@Component
public class PlateFileParser {
	
	/**
	 * Parse method
	 * 
	 * Iterates over CSV file lines, creating Plate components: Plate, WellLabels, Wells and Control Types (eg. POSITIVE, NEGATIVE)
	 * 
	 * @param lines - Upload CSV file BufferedReader object
	 * @return plate - Plate object
	 * @throws IOException
	 * @throws InvalidPlateFileException
	 */
	public Plate parse(BufferedReader lines) throws IOException, InvalidPlateFileException{
		
		String line = lines.readLine();
		
		Plate plate = new Plate();
		
		//Well caching object
		HashMap<String, Well> wellsMap = new HashMap<String, Well>();
		HashSet<String> plateLabelNames = new HashSet<String>();
		HashSet<String> plateControlTypes = new HashSet<String>();
		
		int lineCount = 0;
		int plateDimensionRow = 0;
		int plateDimensionCol = 0;
		
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
        		
        		int row = 0;
        		int col = 0;
        		try{
	        		row = Integer.parseInt(fields[0].trim());
	        		col = Integer.parseInt(fields[1].trim());
        		} catch (Exception e){
        			throw new InvalidPlateFileException (String.format("Invalid well coordinates at line %d. Row: %s Column %s", lineCount, fields[0], fields[1]), fields[0], fields[1]);
        		}
        		
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
            		
        			well.setControlType(wellType);
            		well.setRow(row);
            		well.setCol(col);
        		}
        		
        		WellLabel label = new WellLabel();
        		label.setName(labelName);
        		label.setValue(labelValue);
        		
        		well.getWellLabels().add(label);
        		plateLabelNames.add(labelName);
        		plateControlTypes.add(wellType);
        		lineCount++;
        		
        		if (plateDimensionRow < row){
        			plateDimensionRow = row + 1;
        		}
        		if (plateDimensionCol < col){
        			plateDimensionCol = col + 1;
        		}
        		        		
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
        
        if (plateDimensionCol != 0 && plateDimensionRow != 0){
        	plate.setNumberOfRows(plateDimensionRow);
        	plate.setNumberOfColumns(plateDimensionCol);
        }
	    
		return plate;
	}

}
