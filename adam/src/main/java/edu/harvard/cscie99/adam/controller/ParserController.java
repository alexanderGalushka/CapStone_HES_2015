package edu.harvard.cscie99.adam.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.harvard.cscie99.adam.error.ParserException;
import edu.harvard.cscie99.adam.error.UnauthorizedOperationException;
import edu.harvard.cscie99.adam.model.PlateResult;
import edu.harvard.cscie99.adam.model.Template;
import edu.harvard.cscie99.adam.service.AuthenticationService;
import edu.harvard.cscie99.adam.service.ParserService;

/**
 * 
 * @author Gerson
 *
 */
@RestController
@RequestMapping(value = "/")
public class ParserController {
	
	@Autowired
	private ParserService parserService;
	
	@Autowired
	private AuthenticationService authService;
	
	@RequestMapping(value = "/upload/{filename}", method = RequestMethod.POST)
	@ResponseBody
	public boolean uploadFile(
			@PathVariable("filename") String filename,
			@RequestParam("file") MultipartFile file,
			@RequestParam(value="user", required=true) String user){
		
		//TODO: save filename and path in DB
		
		if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(filename)));
                stream.write(bytes);
                stream.close();
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
	}
	
	@RequestMapping(value = "/project/{project_id}/template/parse/{filename}", method = RequestMethod.POST)
	@ResponseBody
	public Template parseTemplate(
			@PathVariable("project_id") int projectId,
			@PathVariable("filename") String filename,
			@RequestParam(value="user", required=true) String user) throws ParserException, UnauthorizedOperationException{
		
		boolean hasAccess = authService.checkUserAccess(user, null, "removeCollaboratorFromProject");
		
		if (hasAccess){
			return parserService.parseTemplateFromFile(filename);
		}
		else{
			throw new UnauthorizedOperationException ("User don't have permission", user, "removeCollaboratorFromProject");
		}
	}
	
	@RequestMapping(value = "/project/{project_id}/result/parse/{filename}", method = RequestMethod.POST)
	@ResponseBody
	public PlateResult parseResults(
			@PathVariable("project_id") int projectId,
			@PathVariable("filename") String filename,
			@RequestParam(value="user", required=true) String user) throws ParserException, UnauthorizedOperationException{
		
		boolean hasAccess = authService.checkUserAccess(user, projectId, "parseResults");
		
		if (hasAccess){
			return parserService.parseResultsFromFile(filename);
		}
		else{
			throw new UnauthorizedOperationException ("User don't have permission", user, "parseResults");
		}
	}
}
