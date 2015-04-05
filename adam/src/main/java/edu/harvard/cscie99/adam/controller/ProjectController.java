package edu.harvard.cscie99.adam.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.harvard.cscie99.adam.error.SessionTimeouException;
import edu.harvard.cscie99.adam.error.UnauthorizedOperationException;
import edu.harvard.cscie99.adam.model.Comment;
//import edu.harvard.cscie99.adam.model.Compound;
import edu.harvard.cscie99.adam.model.Project;
import edu.harvard.cscie99.adam.profile.User;
import edu.harvard.cscie99.adam.service.AuthenticationService;
import edu.harvard.cscie99.adam.service.ProfileService;
import edu.harvard.cscie99.adam.service.ProjectService;
import edu.harvard.cscie99.adam.service.TagService;

/**
 * 
 * @author Gerson
 *
 */
@RestController
@RequestMapping(value = "/")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private TagService tagService;

	@Autowired
	private AuthenticationService authService;
	
	@Autowired
	private ProfileService profileService;
	
	@RequestMapping(value = "/project", method = RequestMethod.POST)
	@ResponseBody
	public Project createProject(
			@RequestBody Project newProject) throws UnauthorizedOperationException{
		
		return projectService.createProject(newProject);
	}
	
	@RequestMapping(value = "/project", method = RequestMethod.GET)
	@ResponseBody
	public List<Project> listProjects() throws UnauthorizedOperationException{
			
		return projectService.list();
	}
	
	@RequestMapping(value = "/project/{project_id}", method = RequestMethod.GET)
	@ResponseBody
	public Project getProject(
			@PathVariable("project_id") int projectId) throws UnauthorizedOperationException{
		
		Project project = projectService.retrieveProject(projectId);
		
		return project;
	}
	
	@RequestMapping(value = "/project", method = RequestMethod.PUT)
	public @ResponseBody Project updateProject(
			@RequestBody Project project) {
		
		return projectService.updateProject(project);
	}
	
	@RequestMapping(value = "/project", method = RequestMethod.DELETE)
	@ResponseBody
	public boolean deleteProject(
			@RequestBody Project project) throws UnauthorizedOperationException{
		
		return projectService.deleteProject(project);
	}
	
	@RequestMapping(value = "/project/{projectId}/add_tag/{tag}", method = RequestMethod.POST)
	@ResponseBody
	public boolean tagProject(
			@PathVariable("projectId") int projectId,
			@PathVariable("tag") String tag) throws UnauthorizedOperationException{
		
		
			
		Project project = projectService.retrieveProject(projectId);
//			project.getTags().add(tag);
		projectService.updateProject(project);
		
		return true;
		
	}
	
	@RequestMapping(value = "/project/{projectId}/add_comment/{comment}", method = RequestMethod.POST)
	@ResponseBody
	public boolean addCommentToProject(
			@PathVariable("projectId") int projectId,
			@PathVariable("comment") String comment) throws UnauthorizedOperationException{
		
		Project project = projectService.retrieveProject(projectId);
//			project.getComments().add(comment);
		projectService.updateProject(project);
		
		return true;
		
	}
	
	@RequestMapping(value = "/project/{projectId}/updates", method = RequestMethod.POST)
	@ResponseBody
	public List<Comment> getProjectUpdates(
			@PathVariable("projectId") int projectId) throws UnauthorizedOperationException{
		
		
		Project project = projectService.retrieveProject(projectId);
		return project.getComments();
	}
	
	@RequestMapping(value = "/tags/list", method = RequestMethod.GET)
	@ResponseBody
	public List<String> listRecentTags(@RequestParam(value="number", required=false) int numberOfTags) throws UnauthorizedOperationException{
			
		List<String> tags = tagService.listRecentTags(numberOfTags);
			
		return tags;
	}
		
//	@RequestMapping(value = "/project/{project_id}/compounds/list", method = RequestMethod.GET)
//	@ResponseBody
//	public List<Compound> listCompoundsFromProject(
//			@PathVariable("projectId") int projectId) throws UnauthorizedOperationException{
//			
////		User user = UserContext.getUser(request);
////		List<Project> projects = projectService.list();
////			
////		return projects;
//		
//		List<Compound> compounds = new ArrayList<Compound>();
//		
//		for (int i = 0; i < 10; i++){
//			Compound compound = new Compound();
//			compound.setId(i);
//			compound.setName("compound" + i);
//			compounds.add(compound);
//		}
//		
//		return compounds;
//	}
	
}
