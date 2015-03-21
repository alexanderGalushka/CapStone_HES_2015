package edu.harvard.cscie99.adam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.harvard.cscie99.adam.error.UnauthorizedOperationException;
import edu.harvard.cscie99.adam.model.PlateResult;
import edu.harvard.cscie99.adam.model.Project;
import edu.harvard.cscie99.adam.service.AuthenticationService;
import edu.harvard.cscie99.adam.service.ProjectService;
import edu.harvard.cscie99.adam.service.ResultService;
import edu.harvard.cscie99.adam.service.TagService;

@RestController
@RequestMapping(value = "/")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private TagService tagService;
	
	@Autowired
	private AuthenticationService authService;
	
	@RequestMapping(value = "/project/create", method = RequestMethod.POST)
	@ResponseBody
	public Project createProject(
			@PathVariable("project") Project project,
			@RequestParam(value="user", required=true) String user) throws UnauthorizedOperationException{
		
		boolean hasAccess = authService.checkUserAccess(user, null, "createProject");
		
		if (hasAccess){
			return projectService.createProject(project);
		}
		else{
			throw new UnauthorizedOperationException ("User don't have permission", user, "createProject");
		}
	}
	
	@RequestMapping(value = "/project/{projectId}/details", method = RequestMethod.GET)
	@ResponseBody
	public Project getProject(
			@PathVariable("projectId") int projectId,
			@RequestParam(value="user", required=true) String user) throws UnauthorizedOperationException{
		
		boolean hasAccess = authService.checkUserAccess(user, projectId, "getProject");
		
		if (hasAccess){
			return projectService.retrieveProject(projectId);
		}
		else{
			throw new UnauthorizedOperationException ("User don't have permission", user, "getProject");
		}
	}
	
	@RequestMapping(value = "/project/{projectId}/update", method = RequestMethod.GET)
	@ResponseBody
	public boolean updateProject(
			@PathVariable("project") Project project,
			@RequestParam(value="user", required=true) String user) throws UnauthorizedOperationException{
		
		boolean hasAccess = authService.checkUserAccess(user, project.getId(), "updateProject");
		
		if (hasAccess){
			return projectService.updateProject(project);
		}
		else{
			throw new UnauthorizedOperationException ("User don't have permission", user, "updateProject");
		}
	}
	
	@RequestMapping(value = "/project/{projectId}/add_tag/{tag}", method = RequestMethod.POST)
	@ResponseBody
	public boolean addTagToProject(
			@PathVariable("projectId") int projectId,
			@PathVariable("tag") String tag,
			@RequestParam(value="user", required=true) String user) throws UnauthorizedOperationException{
		
		boolean hasAccess = authService.checkUserAccess(user, projectId, "addTagToProject");
		
		if (hasAccess){
			
			Project project = projectService.retrieveProject(projectId);
			project.getTags().add(tag);
			projectService.updateProject(project);
			
			return true;
		}
		else{
			throw new UnauthorizedOperationException ("User don't have permission", user, "addTagToProject");
		}
	}
	
	@RequestMapping(value = "/project/{projectId}/add_comment/{comment}", method = RequestMethod.POST)
	@ResponseBody
	public boolean addCommentToProject(
			@PathVariable("projectId") int projectId,
			@PathVariable("comment") String comment,
			@RequestParam(value="user", required=true) String user) throws UnauthorizedOperationException{
		
		boolean hasAccess = authService.checkUserAccess(user, projectId, "addCommentToProject");
		
		if (hasAccess){
			
			Project project = projectService.retrieveProject(projectId);
			project.getComments().add(comment);
			projectService.updateProject(project);
			
			return true;
		}
		else{
			throw new UnauthorizedOperationException ("User don't have permission", user, "addCommentToProject");
		}
	}
	
	@RequestMapping(value = "/project/{projectId}/updates", method = RequestMethod.POST)
	@ResponseBody
	public List<String> getProjectUpdates(
			@PathVariable("projectId") int projectId,			
			@RequestParam(value="user", required=true) String user) throws UnauthorizedOperationException{
		
		boolean hasAccess = authService.checkUserAccess(user, projectId, "addCommentToProject");
		
		if (hasAccess){
			
			Project project = projectService.retrieveProject(projectId);
			return project.getComments();
		}
		else{
			throw new UnauthorizedOperationException ("User don't have permission", user, "addCommentToProject");
		}
	}
	
	@RequestMapping(value = "/tags/list", method = RequestMethod.GET)
	@ResponseBody
	public List<String> listRecentTags(@RequestParam(value="number", required=false) int numberOfTags) throws UnauthorizedOperationException{
			
		List<String> tags = tagService.listRecentTags(numberOfTags);
			
		return tags;
	}
	
}