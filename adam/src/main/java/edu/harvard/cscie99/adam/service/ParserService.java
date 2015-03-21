package edu.harvard.cscie99.adam.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Component;

import edu.harvard.cscie99.adam.error.ParserException;
import edu.harvard.cscie99.adam.model.PlateResult;
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
	
	public PlateResult parseResultsFromFile(String filename) throws ParserException{
		
		PlateResult plateResult = new PlateResult();
		
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
		plateResult.setId(1);
		plateResult.setComments(new ArrayList<String>());
		plateResult.setLastUpdate(new Date());
		
	    return plateResult;
	}

}
