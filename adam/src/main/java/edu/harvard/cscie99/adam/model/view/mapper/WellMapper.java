package edu.harvard.cscie99.adam.model.view.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import edu.harvard.cscie99.adam.model.Well;
import edu.harvard.cscie99.adam.model.Well.ControlType;
import edu.harvard.cscie99.adam.model.WellLabel;

public class WellMapper {
	
	public static Well getPersistenceWell(HashMap<String, String> well){
		
		Well _well = new Well();
		
		if (well.containsKey("id")){
			_well.setId(Integer.parseInt(well.get("id")));
		}
		
		if (well.containsKey("controlType")){
			
			String controlTypeStr = well.get("controlType");
			
			if (ControlType.COMP.toString().equals(controlTypeStr)){
				_well.setControlType(ControlType.COMP);
			} else if (ControlType.EMPTY.toString().equals(controlTypeStr)){
				_well.setControlType(ControlType.EMPTY);
			} else if (ControlType.NEG.toString().equals(controlTypeStr)){
				_well.setControlType(ControlType.NEG);
			} else {
				_well.setControlType(ControlType.POS);
			}
		}
		
		if (well.containsKey("col")){
			_well.setCol(Integer.parseInt(well.get("col")));
		}
		if (well.containsKey("row")){
			_well.setCol(Integer.parseInt(well.get("row")));
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
		_well.put("controlType", well.getControlType().toString());
		_well.put("id", ""+well.getId());
		_well.put("row", ""+well.getRow());
		
		for (WellLabel wl : well.getWellLabels()){
			_well.put(wl.getName(), wl.getValue());
		}
		
		return _well;

	}

}
