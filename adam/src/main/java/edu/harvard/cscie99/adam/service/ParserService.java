package edu.harvard.cscie99.adam.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.harvard.cscie99.adam.error.ParserException;
import edu.harvard.cscie99.adam.io.PlateFileParser;
import edu.harvard.cscie99.adam.io.ResultFileParser;
import edu.harvard.cscie99.adam.model.Plate;
import edu.harvard.cscie99.adam.model.ResultSnapshot;

/**
 * 
 * @author Gerson
 *
 */
@Component
public class ParserService {

	@Autowired
	private PlateFileParser plateParser;

	@Autowired
	private ResultFileParser resultParser;
	
	public Plate parsePlateFromFile(String filename) throws ParserException{
		
		Plate plate = null;
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			throw new ParserException ("File not found", filename);
		}
		try{
			plate = plateParser.parse(br);
		}
		catch (IOException ioe){
	    	throw new ParserException ("Error reading file", filename);
		}
		finally{
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	    
	    return plate;
	}
	
	public ResultSnapshot parseResultsFromFile(String filename) throws ParserException{
		
		ResultSnapshot result = null;
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			throw new ParserException ("File not found", filename);
		}
		try{
		    result = resultParser.parse(br);
		}
		catch (IOException ioe){
	    	throw new ParserException ("Error reading file", filename);
		}
		finally{
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	    return result;
	}

}
