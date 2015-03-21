package edu.harvard.cscie99.adam.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.harvard.cscie99.adam.error.UnauthorizedOperationException;
import edu.harvard.cscie99.adam.model.Plate;
import edu.harvard.cscie99.adam.service.AuthenticationService;
import edu.harvard.cscie99.adam.service.PlateService;

@RestController
@RequestMapping(value = "/")
public class PlateController {
	
	@Autowired
	private PlateService plateService;
	
	@Autowired
	private AuthenticationService authService;
	
//	/**
//	 * Saves the Plate object into the DB. (FR 3.1)
//	 * 
//	 * @param plate
//	 * @param user
//	 * @return
//	 * @throws UnauthorizedOperationException 
//	 */
//	@RequestMapping(value = "/project/{project_id}/plate/save", method = RequestMethod.POST)
//	@ResponseBody
//	public boolean savePlate(
//			@RequestParam(value="plate", required=true) Plate plate,
//			@RequestParam(value="user", required=true) String username) throws UnauthorizedOperationException{
//		
//		//TODO: save data into DB
//		boolean hasAccess = authService.checkUserAccess(username, null, "savePlate");
//		
//		if (hasAccess){
//			
//			User user = authService.getUserDetails(username);
//			
//			plate.setOwner(user);
//			return plateService.save(plate);
//		}
//		else{
//			throw new UnauthorizedOperationException ("User don't have permission", username, "savePlate");
//		}
//	}
	
	@RequestMapping(value = "/plate/search", method = RequestMethod.GET)
	@ResponseBody
	public List<Plate> searchPlate(
			@RequestParam(value="id", required=false) int id,
			@RequestParam(value="name", required=false) String name,
			@RequestParam(value="description", required=false) String description,
			@RequestParam(value="tag", required=false) String tag,
			@RequestParam(value="type", required=false) Plate.PlateType type,
			@RequestParam(value="user", required=true) String user){
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("id", id);
		parameters.put("name", name);
		parameters.put("description", description);
		parameters.put("tag", tag);
		parameters.put("type", type);
		parameters.put("user", user);
		
		List<Plate> plates = plateService.search(parameters);
		return plates;
	}
	
	//Removed from Model: Confirm with Alex
	
//	@RequestMapping(value = "/project/{project_id}/template/{template_id}/detail", method = RequestMethod.GET)
//	@ResponseBody
//	public Template getTemplateDetails(
//			@PathVariable("project_id") int project_id,
//			@PathVariable("template_id") int template_id,
//			@RequestParam(value="user", required=true) String user){
//		
//		//TODO: save data into DB
//		boolean hasAccess = authService.checkUserAccess(user, project_id, "getTemplateDetails");
//		
//		if (hasAccess){
//			
//			return plateService.retrieveTemplate(project_id, template_id);
//		}
//		else{
//			throw new UnauthorizedOperationException ("User don't have permission", user, "getTemplateDetails");
//		}
//	}
	
	//Removed from model. Confirm with Alex
//	@RequestMapping(value = "/project/{project_id}/template/save", method = RequestMethod.POST)
//	@ResponseBody
//	public boolean saveTemplate(
//			@PathVariable("project_id") int project_id,
//			@RequestParam(value="template", required=true) Template template,
//			@RequestParam(value="user", required=true) String user){
//		
//		//TODO: save data into DB
//		boolean hasAccess = authService.checkUserAccess(user, project_id, "saveTemplate");
//		
//		if (hasAccess){
//			
//			return plateService.saveTemplate(project_id, template);
//		}
//		else{
//			throw new UnauthorizedOperationException ("User don't have permission", user, "saveTemplate");
//		}
//	}
	
	@RequestMapping(value = "/project/{project_id}/plate/save", method = RequestMethod.POST)
	@ResponseBody
	public boolean savePlate(
			@PathVariable("project_id") int project_id,
			@RequestParam(value="plate", required=true) Plate plate,
			@RequestParam(value="user", required=true) String user) throws UnauthorizedOperationException{
		
		//TODO: save data into DB
		boolean hasAccess = authService.checkUserAccess(user, project_id, "savePlate");
		
		if (hasAccess){
			
			return plateService.save(project_id, plate);
		}
		else{
			throw new UnauthorizedOperationException ("User don't have permission", user, "savePlate");
		}
	}

}
