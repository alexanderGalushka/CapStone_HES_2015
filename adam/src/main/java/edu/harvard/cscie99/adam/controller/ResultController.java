
package edu.harvard.cscie99.adam.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
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
import edu.harvard.cscie99.adam.model.DataSet;
import edu.harvard.cscie99.adam.model.Measurement;
import edu.harvard.cscie99.adam.model.Plate;
import edu.harvard.cscie99.adam.model.Project;
import edu.harvard.cscie99.adam.model.ResultSnapshot;
import edu.harvard.cscie99.adam.model.Well;
import edu.harvard.cscie99.adam.model.WellLabel;
import edu.harvard.cscie99.adam.profile.User;
import edu.harvard.cscie99.adam.service.AuthenticationService;
import edu.harvard.cscie99.adam.service.ParserService;
import edu.harvard.cscie99.adam.service.ProfileService;
import edu.harvard.cscie99.adam.service.ProjectService;
import edu.harvard.cscie99.adam.service.QueryService;
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
	private ProjectService projectService;
	
	@Autowired
	private ResultService resultService;
	
	@Autowired
	private AuthenticationService authService;
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private QueryService queryService;
	
//	public static final String C_RESULT_FILE_PATH = "/home/adam_files/results/";
	public static final String C_RESULT_FILE_PATH = "c:/adam_files/results/";
	
	private HashMap<Integer, String> letterMapping = new HashMap<>();
	
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
	
	@RequestMapping(value="/rest/resultsnapshot/{result_id}/prepare", method=RequestMethod.POST)
	public @ResponseBody boolean prepareResultsData(
			@PathVariable("result_id") int resultId) throws JsonProcessingException {
		
		ResultSnapshot resultSnapshot = resultService.retrieveResult(resultId);
		
		return resultService.prepareResultsData(resultSnapshot);
	}
	
	@RequestMapping(value="/rest/resultsnapshot/{result_id}", method=RequestMethod.GET)
	public @ResponseBody ResultSnapshot getResults(
			@PathVariable("result_id") int resultId) throws JsonProcessingException {
		
		ResultSnapshot resultSnapshot = resultService.retrieveResult(resultId);
		
		return resultSnapshot;
	}
	
	@RequestMapping(value="/rest/resultsnapshot", method=RequestMethod.GET)
	public @ResponseBody List<ResultSnapshot> listResults() throws JsonProcessingException {
		
		List<ResultSnapshot> resultList = resultService.listResults();
		
		return resultList;
	}
	
	@RequestMapping(value="/getResults", method=RequestMethod.GET)
	public @ResponseBody DataSet queryResults(
			@RequestParam(value="projectId", required=false) Integer projectId,
			@RequestParam(value="plateId", required=false) Integer plateId,
			@RequestParam(value="labelName", required=false) String labelName,
			@RequestParam(value="labelValue", required=false) String labelValue,
			@RequestParam(value="measurementType", required=false) String measurementType,
			@RequestParam(value="time", required=false) String time
			) throws JsonProcessingException {
		
		return queryService.queryResultsData(projectId, plateId, labelName, labelValue, measurementType, time);
	}
	
	@RequestMapping(value="/getWells/{project_id}", method=RequestMethod.GET)
	public @ResponseBody ArrayList getAllResults(
			@PathVariable("project_id") int projectId) throws JsonProcessingException {
		
		ArrayList result = new ArrayList();
		populateLetterMapping();
		
		Project project = projectService.retrieveProject(projectId);
		
//		for (Plate plate: project.getPlates()){
//			for (Well well : plate.getWells()){
//				HashMap entry = new HashMap();
//				entry.put("Plate", plate.getName());
//				entry.put("Well", convertToLetter(well.getCol()) + "" + well.getRow());
//				
//				for (WellLabel wl : plate.getWellLabels()){
//					entry.put(wl.getName(), wl.getValue());	
//				}
//				result.add(entry);
//			}
//			
//		}
		
		for (Plate plate: project.getPlates()){
			
			for (ResultSnapshot rs : plate.getResults()){
				
				long time = rs.getTime().getTime();
				
				for (Well well : plate.getWells()){
					
					HashMap entry = new HashMap();
					entry.put("Plate", plate.getName());

					entry.put("time", time);
					
//					Well well = plate.getWell(m.getRow(), m.getColumn());
					entry.put("Well", letterMapping.get(well.getCol()) + "" + well.getRow());
					
					for (WellLabel wl : well.getWellLabels()){
						entry.put(wl.getName(), wl.getValue());	
					}
					
					for (Measurement m : rs.getMeasurementsFromWell(well.getRow(), well.getCol())){
						entry.put(m.getMeasurementType(), m.getValue());
					}
					result.add(entry);

				}
				
			}
		}
		
		return result;
	}
	
	private void populateLetterMapping(){
		letterMapping.put(1, "A");
		letterMapping.put(2, "B");
		letterMapping.put(3, "C");
		letterMapping.put(4, "D");
		letterMapping.put(5, "E");
		letterMapping.put(6, "F");
		letterMapping.put(7, "G");
		letterMapping.put(8, "H");
		letterMapping.put(9, "I");
		letterMapping.put(10, "J");
		letterMapping.put(11, "K");
		letterMapping.put(12, "L");
		letterMapping.put(13, "M");
		letterMapping.put(14, "N");
		letterMapping.put(15, "O");
		letterMapping.put(16, "P");
		letterMapping.put(17, "Q");
		letterMapping.put(18, "R");
		letterMapping.put(19, "S");
		letterMapping.put(20, "T");
		letterMapping.put(21, "U");
		letterMapping.put(22, "V");
		letterMapping.put(23, "W");
		letterMapping.put(24, "X");
		letterMapping.put(25, "Y");
		letterMapping.put(26, "Z");
		letterMapping.put(27, "AA");
		letterMapping.put(28, "AB");
		letterMapping.put(29, "AC");
		letterMapping.put(30, "AD");
		letterMapping.put(31, "AE");
		letterMapping.put(32, "AF");
		letterMapping.put(33, "AG");
		letterMapping.put(34, "AH");
		letterMapping.put(35, "AI");
		letterMapping.put(36, "AJ");
		letterMapping.put(37, "AK");
		letterMapping.put(38, "AL");
		letterMapping.put(39, "AM");
		letterMapping.put(40, "AN");
		letterMapping.put(41, "AO");
		letterMapping.put(42, "AP");
		letterMapping.put(43, "AQ");
		letterMapping.put(44, "AR");
		letterMapping.put(45, "AS");
		letterMapping.put(46, "AT");
		letterMapping.put(47, "AU");
		letterMapping.put(48, "AV");
		letterMapping.put(49, "AW");
		letterMapping.put(50, "AX");
		letterMapping.put(51, "AY");
		letterMapping.put(52, "AZ");
		letterMapping.put(53, "BA");
		letterMapping.put(54, "BB");
		letterMapping.put(55, "BC");
		letterMapping.put(56, "BD");
		letterMapping.put(57, "BE");
		letterMapping.put(58, "BF");
		letterMapping.put(59, "BG");
		letterMapping.put(60, "BH");
		letterMapping.put(61, "BI");
		letterMapping.put(62, "BJ");
		letterMapping.put(63, "BK");
		letterMapping.put(64, "BL");
		letterMapping.put(65, "BM");
		letterMapping.put(66, "BN");
		letterMapping.put(67, "BO");
		letterMapping.put(68, "BP");
		letterMapping.put(69, "BQ");
		letterMapping.put(70, "BR");
		letterMapping.put(71, "BS");
		letterMapping.put(72, "BT");
		letterMapping.put(73, "BU");
		letterMapping.put(74, "BV");
		letterMapping.put(75, "BW");
		letterMapping.put(76, "BX");
		letterMapping.put(77, "BY");
		letterMapping.put(78, "BZ");
	}
}
