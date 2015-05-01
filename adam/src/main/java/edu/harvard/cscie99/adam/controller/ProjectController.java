package edu.harvard.cscie99.adam.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.harvard.cscie99.adam.error.UnauthorizedOperationException;
import edu.harvard.cscie99.adam.model.Plate;
//import edu.harvard.cscie99.adam.model.Compound;
import edu.harvard.cscie99.adam.model.Project;
import edu.harvard.cscie99.adam.profile.User;
import edu.harvard.cscie99.adam.service.AuthenticationService;
import edu.harvard.cscie99.adam.service.PlateService;
import edu.harvard.cscie99.adam.service.ProfileService;
import edu.harvard.cscie99.adam.service.ProjectService;
import edu.harvard.cscie99.adam.service.TagService;

/**
 * ProjectController class
 * 
 * Controller that handles request from the frontend to perform 
 * project related operations (create, remove, update, delete, list)
 * Methods use RESTful calls
 * 
 * @author Gerson
 *
 */
@RestController
@RequestMapping(value = "/")
public class ProjectController {

	/**
	 * ProjectService object
	 * Uses Spring IoC to instantiate PlateService in runtime
	 */
	@Autowired
	private ProjectService projectService;
	
	/**
	 * PlateService object
	 */
	@Autowired
	private PlateService plateService;

	/**
	 * AuthenticationService object
	 */
	@Autowired
	private AuthenticationService authService;
	
	/**
	 * ProfileService object
	 */
	@Autowired
	private ProfileService profileService;
	
	/**
	 * createProject method
	 * 
	 * Creates a new Project and persists in the DB.
	 * Implements the RESTful POST method. 
	 * 
	 * The method receives input parameters in JSON string format,
	 * and Jackson API maps the JSON string into Java Objects.
	 * The return is mapped back to JSON String with the same mechanism.
	 * 
	 * @param newProject - Project object with all project attributes
	 * @return Project object in JSON format
	 */
	@RequestMapping(value = "/rest/project", method = RequestMethod.POST)
	@ResponseBody
	public Project createProject(
			@RequestBody Project newProject,
			HttpServletRequest request) throws UnauthorizedOperationException{

		User owner = authService.getCurrentUser(request);
		newProject.setOwner(owner.getUsername());
		
		return projectService.createProject(newProject);
	}
	
	/**
	 * List Projects method
	 * 
	 * Lists all existing projects in the system.
	 * Implement RESTful method call.
	 * 
	 * The method receives input parameters in JSON string format,
	 * and Jackson API maps the JSON string into Java Objects.
	 * The return is mapped back to JSON String with the same mechanism.
	 * 
	 * @return list of Plate objects
	 */
	@RequestMapping(value = "/rest/project", method = RequestMethod.GET)
	@ResponseBody
	public List<Project> listProjects() throws UnauthorizedOperationException{
			
		List<Project> projects = projectService.list();
		for (Project project : projects){
			project.setPlates(null);
		}
		return projects;
	}
	
	/**
	 * getPproject method.
	 * 
	 * Retrieve project's details and returns a JSON representation of Project object.
	 * Implements a REST method call.
	 * 
	 * The method receives input parameters in JSON string format,
	 * and Jackson API maps the JSON string into Java Objects.
	 * The return is mapped back to JSON String with the same mechanism.
	 * 
	 * @param projectId - Key of Project table in DB
	 * @return Project object in JSON format
	 */
	@RequestMapping(value = "/rest/project/{project_id}", method = RequestMethod.GET)
	@ResponseBody
	public Project getProject(
			@PathVariable("project_id") int projectId) throws UnauthorizedOperationException{
		
		Project project = projectService.retrieveProject(projectId);
		
		project.setPlates(null);
		
		return project;
	}
	
	/**
	 * updateProject method.
	 * 
	 * Updates the project's details in DB and returns the updated object.
	 * Implements a REST method call.
	 * 
	 * The method receives input parameters in JSON string format,
	 * and Jackson API maps the JSON string into Java Objects.
	 * The return is mapped back to JSON String with the same mechanism.
	 * 
	 * @param projectId - Key of Project table in DB
	 * @param project - Fully-populated Project object
	 * @return Project object in JSON format
	 */
	@RequestMapping(value = "/rest/project/{project_id}", method = RequestMethod.PUT)
	public @ResponseBody Project updateProject(
			@PathVariable("project_id") int projectId,
			@RequestBody Project project,
			HttpServletRequest request) {
		
		Project currentProject = projectService.retrieveProject(projectId);
		currentProject.setCollaborators(project.getCollaborators());
		currentProject.setDescription(project.getDescription());
		currentProject.setLabel(project.getLabel());
		currentProject.setName(project.getName());
		currentProject.setPublic(project.isPublic());
		currentProject.setTags(project.getTags());
		currentProject.setType(project.getType());
		
		projectService.updateProject(currentProject);
		
		//Omit plates associated to this project
		currentProject.setPlates(null);
		return currentProject;
	}
	
	/**
	 * deleteProject method.
	 * 
	 * Hard-delete the Project from DB (and all Plates and Results associated).
	 * Implements a REST method call.
	 * 
	 * @param projectId - Key of Project table in DB to be deleted
	 * @return boolean indicatig success or failure in operation
	 */
	@RequestMapping(value = "/rest/project/{project_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public boolean deleteProject(
			@PathVariable("project_id") int projectId) throws UnauthorizedOperationException{
		
		Project project = projectService.retrieveProject(projectId);
		
		return projectService.deleteProject(project);
	}
	
	/**
	 * addPlateToProject method.
	 * 
	 * Associates a Plate object (refer to the documentation) to the Project.
	 * 
	 * RESTFul method. Will return a boolean indicating the success of the operation
	 * 
	 * @param projectId - Key of Project table in DB
	 * @param plateId - Key of Plate table in DB
	 * @return boolean indicatig success or failure in operation
	 */
	@RequestMapping(value = "/rest/project/{projectId}/add_plate/{plateId}", method = RequestMethod.POST)
	@ResponseBody
	public boolean addPlateToProject(
			@PathVariable("projectId") int projectId,
			@PathVariable("plateId") int plateId) throws UnauthorizedOperationException{
			
		Project project = projectService.retrieveProject(projectId);
		Plate plate = plateService.retrievePlate(plateId);
		project.getPlates().add(plate);
		plate.setProjectId("" + project.getId().intValue());
		
		projectService.updateProject(project);
		
		return true;
		
	}
	
	/**
	 * removesPlateToProject method.
	 * 
	 * Removes the association between Plate object (refer to the documentation) and the Project.
	 * 
	 * RESTFul method. Will return a boolean indicating the success of the operation
	 * 
	 * @param projectId - Key of Project table in DB
	 * @param plateId - Key of Plate table in DB
	 * @return boolean indicatig success or failure in operation
	 */
	@RequestMapping(value = "/rest/project/{projectId}/remove_plate/{plateId}", method = RequestMethod.POST)
	@ResponseBody
	public boolean removePlateFromProject(
			@PathVariable("projectId") int projectId,
			@PathVariable("plateId") int plateId) throws UnauthorizedOperationException{
			
		Project project = projectService.retrieveProject(projectId);
		Plate plate = plateService.retrievePlate(plateId);
		project.getPlates().remove(plate);
		plate.setProjectId(null);
		
		projectService.updateProject(project);
		
		return true;
		
	}
	
	@RequestMapping(value = "/rest/project/{projectId}/list_plates", method = RequestMethod.GET)
	@ResponseBody
	public List<Plate> listPlatesFromProject(
			@PathVariable("projectId") int projectId) throws UnauthorizedOperationException{
			
		Project project = projectService.retrieveProject(projectId);
		
		List<Plate> plates = new ArrayList<Plate>();
		for (Plate plate : project.getPlates()){
			plates.add(plateService.retrievePlate(plate.getId()));
		}
		
		return plates;
	}
	
//	@RequestMapping(value = "/project/{projectId}/add_comment/{comment}", method = RequestMethod.POST)
//	@ResponseBody
//	public boolean addCommentToProject(
//			@PathVariable("projectId") int projectId,
//			@PathVariable("comment") String comment) throws UnauthorizedOperationException{
//		
//		Project project = projectService.retrieveProject(projectId);
////			project.getComments().add(comment);
//		projectService.updateProject(project);
//		
//		return true;
//		
//	}
	
}
