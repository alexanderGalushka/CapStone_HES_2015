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
import edu.harvard.cscie99.adam.model.Well;
import edu.harvard.cscie99.adam.profile.User;

/**
 * 
 * @author Gerson
 *
 */
@Component
public class ResultService {
	
	
	public List<Well> search(int projectId, int resultId, int plateId, Date creationDate, String comment, User owner){
		
		//TODO: search from DB
			
			List<Well> wells = new ArrayList<>();
			for (int j = 0; j < 10; j++){
				Well wellResult = new Well();
				//wellResult.setCreationTime(new Date());
				wellResult.setId(j);
				wells.add(wellResult);
			}
			
		
		return wells;
	}
	
	

	public List<Well> retrieveResult(int projectId, int resultId){
		
		//TODO retrieve from DB
		
		Plate plate = new Plate();
		plate.setId(1);
		plate.setDescription("description");
//		plate.setName("plate");
		
		List<Well> wells = new ArrayList<>();
		for (int j = 0; j < 10; j++){
			Well wellResult = new Well();
			//wellResult.setCreationTime(new Date());
			wellResult.setId(j);
			wells.add(wellResult);
		}
				
		return wells;
	}

}