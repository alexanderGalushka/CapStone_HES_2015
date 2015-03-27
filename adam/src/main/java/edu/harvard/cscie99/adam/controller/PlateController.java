package edu.harvard.cscie99.adam.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.harvard.cscie99.adam.model.Plate;
import edu.harvard.cscie99.adam.model.Template;
import edu.harvard.cscie99.adam.service.AuthenticationService;
import edu.harvard.cscie99.adam.service.PlateService;

@RestController
@RequestMapping(value = "/")
public class PlateController {
	
	//TODO implement
	
	@Autowired
	private PlateService plateService;
	
	@Autowired
	private AuthenticationService authService;
	
	
	@RequestMapping(value = "/project/{project_id}/plate/list", method = RequestMethod.GET)
	@ResponseBody
	public List<Plate> listPlates(
			@PathVariable("project_id") int projectId,
			@RequestParam(value="search", required=false) String search){
		
		List<Plate> plates = new ArrayList<Plate>();
		
		for (int i = 0; i < 10; i++){
			Plate plate = new Plate();
			plate.setBarcode("1234");
			plate.setDescription("desc"+i);
			plate.setProtocol("protocol");
			plate.setTags("cells");
			plates.add(plate);
		}
		
		return plates;
	}
	
	
	
	@RequestMapping(value = "/template/search", method = RequestMethod.GET)
	@ResponseBody
	public List<Template> searchTemplate(
			@RequestParam(value="id", required=false) int id,
			@RequestParam(value="name", required=false) String name,
			@RequestParam(value="description", required=false) String description,
			@RequestParam(value="tag", required=false) String tag,
			@RequestParam(value="type", required=false) Plate.PlateType type){
		
		//TODO
		return null;
	}
	
	@RequestMapping(value = "/project/{project_id}/template/{template_id}/detail", method = RequestMethod.GET)
	@ResponseBody
	public Template getTemplateDetails(
			@PathVariable("project_id") int project_id,
			@PathVariable("template_id") int template_id){
		
		//TODO
		return null;
	}
	
	@RequestMapping(value = "/project/{project_id}/template/load_from_file", method = RequestMethod.POST)
	@ResponseBody
	public Template loadTemplateFromFile(
			@PathVariable("project_id") int project_id,
			@RequestParam(value="filename", required=false) String filename){
		
		//TODO
		return null;
	}
	
	@RequestMapping(value = "/project/{project_id}/template/save", method = RequestMethod.POST)
	@ResponseBody
	public boolean saveTemplate(
			@PathVariable("project_id") int project_id,
			@RequestParam(value="template", required=true) Template templater){
		
		//TODO
		return true;
	}
	
	@RequestMapping(value = "/project/{project_id}/plate/create", method = RequestMethod.POST)
	@ResponseBody
	public boolean savePlate(
			@PathVariable("project_id") int project_id,
			@RequestParam(value="plate", required=true) Plate plate){
		
		//TODO
		return true;
	}
	
	@RequestMapping(value = "/project/{project_id}/plate/{plate_id}/update", method = RequestMethod.POST)
	@ResponseBody
	public boolean updatePlate(
			@PathVariable("project_id") int project_id,
			@RequestParam(value="plate", required=true) Plate plate){
		
		//TODO
		return true;
	}
	
	@RequestMapping(value = "/project/{project_id}/plate/{plate_id}/remove", method = RequestMethod.POST)
	@ResponseBody
	public boolean removePlate(
			@PathVariable("project_id") int project_id,
			@RequestParam(value="plate", required=true) Plate plate){
		
		//TODO
		return true;
	}
	

}
