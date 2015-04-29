package edu.harvard.cscie99.adam.model.view.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import edu.harvard.cscie99.adam.model.Well;
//import edu.harvard.cscie99.adam.model.Well.ControlType;
import edu.harvard.cscie99.adam.model.WellLabel;

public class WellMapper {
	
	public static Well getPersistenceWell(HashMap<String, String> well){
		
		Well _well = new Well();
		
		if (well.containsKey("id")){
			_well.setId(Integer.parseInt(well.get("id")));
		}
		
		if (well.containsKey("controlType")){
			_well.setControlType(well.get("controlType"));
		}
		
		if (well.containsKey("col")){
			_well.setCol(Integer.parseInt(well.get("col")));
		}
		if (well.containsKey("row")){
			_well.setRow(Integer.parseInt(well.get("row")));
		}
		
		ArrayList<WellLabel> wlList = new ArrayList<WellLabel>();
		for (String key : well.keySet()){
			if ((!"id".equals(key)) && 
				(!"col".equals(key)) &&
				(!"row".equals(key)) &&
				(!"controlType".equals(key))){
				
				WellLabel wl = new WellLabel();
				wl.setName(key);
				wl.setValue(well.get(key));
				wlList.add(wl);
			}
		}
		_well.setWellLabels(wlList);
		
		return _well;
	}

	public static HashMap<String, String> getViewWell(Well well) {

		HashMap<String, String> _well = new HashMap<>();
		
		_well.put("col", ""+well.getCol());
		_well.put("controlType", well.getControlType());
		_well.put("id", ""+well.getId());
		_well.put("row", ""+well.getRow());
		
		for (WellLabel wl : well.getWellLabels()){
			_well.put(replaceSpaces(wl.getName()), wl.getValue());
		}
		
		return _well;

	}
	
	private static String replaceSpaces (String input){
		
		if (input != null && input.length() > 0){
			return input.replace(" ", "_");
		}
		return null;
	}

}
