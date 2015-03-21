package edu.harvard.cscie99.adam.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import edu.harvard.cscie99.adam.model.Plate;

@Component
public class PlateService {
	
	public boolean save(int projectId, Plate plate){
		//TODO
		//persist plate in DB
		
		return true;
	}
	
	public List<Plate> search(Map<String, Object> parameters){
		//TODO
		//list plates from DB
		
		List<Plate> plates = new ArrayList<Plate>();
		
		for (int i =0; i<5; i++){
			Plate plate = new Plate();
			plate.setBarcode("1234");
			plate.setDescription("desc");
			plate.setId(1);
			plate.setName("name");
			
			plates.add(plate);
		}
		
		return plates;
	}
	
	

}
