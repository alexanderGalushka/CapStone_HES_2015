package edu.harvard.cscie99.adam.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;

import edu.harvard.cscie99.adam.error.LogoutFailedException;
import edu.harvard.cscie99.adam.error.ParserException;
import edu.harvard.cscie99.adam.error.SessionTimeouException;
import edu.harvard.cscie99.adam.error.UnauthorizedOperationException;
import edu.harvard.cscie99.adam.model.Plate;
import edu.harvard.cscie99.adam.model.Project;
import edu.harvard.cscie99.adam.model.ResultSnapshot;
import edu.harvard.cscie99.adam.profile.User;
import edu.harvard.cscie99.adam.service.AuthenticationService;
import edu.harvard.cscie99.adam.service.ParserService;
import edu.harvard.cscie99.adam.service.ProfileService;
import edu.harvard.cscie99.adam.service.ResultService;

/**
 * 
 * @author Gerson
 *
 */
@RestController
@RequestMapping(value = "/")
public class ResultController {
	
	@Autowired
	private ParserService parserService;
	
	@Autowired
	private ResultService resultService;
	
	@Autowired
	private AuthenticationService authService;
	
	@Autowired
	private ProfileService profileService;
	
	public static final String C_RESULT_FILE_PATH = "/home/adam_files/results/";
//	public static final String C_RESULT_FILE_PATH = "c:/adam_files/results/";
	
	@RequestMapping(value="/upload_result", method=RequestMethod.POST)
	public @ResponseBody ResultSnapshot handleResultUpload(
			@RequestParam(value="name", required=false) String name,
			@RequestParam(value="file", required=false) MultipartFile file) throws IOException, ParserException{
		
		ResultSnapshot resultSnapshot = null;
		
		if (file != null && !file.isEmpty()){
		
			byte[] bytes = file.getBytes();
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(C_RESULT_FILE_PATH + name)));
			stream.write(bytes);
			stream.close();
		
			resultSnapshot = parserService.parseResultsFromFile(C_RESULT_FILE_PATH + name);
		}
		
		resultService.saveResultSnapshot(resultSnapshot);
		
		return resultSnapshot;
	}
	
	@RequestMapping(value="/resultsnapshot/prepare", method=RequestMethod.POST)
	public @ResponseBody boolean prepareResultsData(
			@RequestBody ResultSnapshot result) throws JsonProcessingException {
		
		ResultSnapshot resultSnapshot = null;
		
		return resultService.prepareResultsData(resultSnapshot);
	}
	
}
