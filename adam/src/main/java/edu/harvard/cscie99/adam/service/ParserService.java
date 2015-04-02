package edu.harvard.cscie99.adam.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import edu.harvard.cscie99.adam.error.ParserException;
import edu.harvard.cscie99.adam.model.ResultSnapshot;
import edu.harvard.cscie99.adam.model.Template;

/**
 * 
 * @author Gerson
 *
 */
@Component
public class ParserService {
	
	public Template parseTemplateFromFile(String filename) throws ParserException{
		
		Template template = new Template();
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			throw new ParserException ("File not found", filename);
		}
		try{
		    try {
		        String line = br.readLine();
	
		        while (line != null) {
		            String[] fields = line.split(";");
		            //TODO: do something with fields
		            
		            line = br.readLine();
		        }
		        
		    } finally {
		        br.close();
		    }
		}
		catch (IOException ioe){
	    	throw new ParserException ("Error reading file", filename);
		}
	    
		//TODO: remove
		template.setId(1);
		template.setDescription("desc");
		template.setName("name");
		
	    return template;
	}
	
	public List<ResultSnapshot> parseResultsFromFile(String filename) throws ParserException{
		
		ResultSnapshot resultSnapshot = new ResultSnapshot();
		List<ResultSnapshot> result  = new ArrayList<>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			throw new ParserException ("File not found", filename);
		}
		try{
		    try {
		        String line = br.readLine();
	
		        while (line != null) {
		            String[] fields = line.split(";");
		            //TODO: do something with fields
		            
		            line = br.readLine();
		        }
		        
		    } finally {
		        br.close();
		    }
		}
		catch (IOException ioe){
	    	throw new ParserException ("Error reading file", filename);
		}
	    
		//TODO: remove
		//resultplateResult.setId(1);
//		plateResult.setComments(new ArrayList<String>());
//		plateResult.setLastUpdate(new Date());
		
	    return result;
	}

}
