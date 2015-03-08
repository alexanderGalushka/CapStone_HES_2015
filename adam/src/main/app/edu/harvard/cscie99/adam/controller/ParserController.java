package edu.harvard.cscie99.adam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.harvard.cscie99.adam.service.ParserService;

@RestController
@RequestMapping(value = "/")
public class ParserController {
	
	@Autowired
	private ParserService parserService;
	
	@RequestMapping(value = "/upload/{filename}", method = RequestMethod.POST)
	@ResponseBody
	public String uploadFile(
			@PathVariable("filename") String filename,
			@RequestParam(value="user", required=true) String user){
		
		//TODO implement
		
		return filename;
	}

}
