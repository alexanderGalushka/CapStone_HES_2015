package edu.harvard.cscie99.adam.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.harvard.cscie99.adam.error.UnauthorizedOperationException;
import edu.harvard.cscie99.adam.model.DataSet;
import edu.harvard.cscie99.adam.model.Measurement;
import edu.harvard.cscie99.adam.model.Plate;
import edu.harvard.cscie99.adam.model.Project;
import edu.harvard.cscie99.adam.model.ResultSnapshot;
import edu.harvard.cscie99.adam.model.Well;
import edu.harvard.cscie99.adam.model.WellLabel;
import edu.harvard.cscie99.adam.profile.User;

/**
 * 
 * @author Gerson
 *
 */
@Component
public class ResultService {
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Autowired
	private PlateService plateService;
	
	@Autowired
	private ProjectService projectService;
	
	public List<Well> search(int projectId, int resultId, int plateId, Date creationDate, String comment, User owner){
		
		//TODO: search from DB
			
			List<Well> wells = new ArrayList<>();
			for (int j = 0; j < 10; j++){
				Well wellResult = new Well();
				//wellResult.setCreationTime(new Date());
				wellResult.setId(j);
				wells.add(wellResult);
			}
			
		
		return wells;
	}
	
	

	public ResultSnapshot retrieveResult(int resultId){
		
		Session session = sessionFactory.openSession();
		ResultSnapshot result = null;
		try{
			result = (ResultSnapshot) session.get(ResultSnapshot.class, resultId);
			loadResult(result);
		}
		finally{
			session.close();	
		}
		
		return result;
	}
	
	private void loadResult(ResultSnapshot result){
//		result.getPlate();
//		result.getProject();
		if (!result.getMeasurements().isEmpty()){
			for (Measurement measure : result.getMeasurements()){
				measure.getColumn();
				measure.getMeasurementType();
				measure.getRow();
				measure.getValue();
			}
		}
	}
	
	public List<ResultSnapshot> listResults(){
		
		Session session = sessionFactory.openSession();
		List<ResultSnapshot> results = null;
		
		try{
			results = session.createCriteria(ResultSnapshot.class).list();
			for (ResultSnapshot result : results){
				loadResult(result);
			}
		}
		finally{
			session.close();	
		}
		
		return results;
	}
	
	public ResultSnapshot saveResultSnapshot(ResultSnapshot result){
		Session session = sessionFactory.openSession();
		
		try{
			session.beginTransaction();
			session.save(result);
			session.getTransaction().commit();
			
			return result;
		}
		catch (Exception ex){
			return null;
		}
		finally{
			session.close();	
		}
	}
	
	private Plate getPlateAssociatedToResult(ResultSnapshot result){
		for (Plate plate : plateService.listPlates()){
			if (!plate.getResults().isEmpty()){
				for (ResultSnapshot plateResult : plate.getResults()){
					if (plateResult.getId() == result.getId()){
						return plate;
					}
				}
			}
		}
		return null;
	}
	
	private Project getProjectAssociatedToPlate(Plate plate){
		for (Project project : projectService.list()){
			if (!project.getPlates().isEmpty()){
				for (Plate projectPlate : project.getPlates()){
					if (plate.getId() == projectPlate.getId()){
						return project;
					}
				}
			}
		}
		return null;
	}
	
	public boolean prepareResultsData(ResultSnapshot results) throws JsonProcessingException{
		
		HashMap<String, HashMap<String, List<Well>>> labelTree = new HashMap<String, HashMap<String, List<Well>>>();
		HashMap<Well, ArrayList<String[]>> linkingMap = new HashMap<Well, ArrayList<String[]>>();
		
		Plate plate = getPlateAssociatedToResult(results);
		//Force to load Wells
		plate.getWells();
		
		for (WellLabel wellLabel : plate.getWellLabels()){
			labelTree.put(wellLabel.getName(), new HashMap<String, List<Well>>());
		}
		
		for (Well well : plate.getWells()){
			for (WellLabel wellLabel : well.getWellLabels()){
				
				List<Well> wells = labelTree.get(wellLabel.getName()).get(wellLabel.getValue());
				
				if (wells == null){
					wells = new ArrayList<Well>();
					labelTree.get(wellLabel.getName()).put(wellLabel.getValue(), wells);
				}
				wells.add(well);
				
				if (!linkingMap.containsKey(well)){
					ArrayList<String[]> labelNameValuePairs = new ArrayList<String[]>();
					labelNameValuePairs.add(new String[]{wellLabel.getName(), wellLabel.getValue()});
					linkingMap.put(well, labelNameValuePairs);
				}
				else{
					ArrayList<String[]> labelNameValuePairs = linkingMap.get(well);
					labelNameValuePairs.add(new String[]{wellLabel.getName(), wellLabel.getValue()});
				}
			}
		}
		
		HashMap<String, HashMap<String, HashMap<String, ArrayList<Double>>>> labelNamesMap = new HashMap<String, HashMap<String, HashMap<String, ArrayList<Double>>>>();
		for (Measurement measure : results.getMeasurements()){
			
			Well well = plate.getWell(measure.getRow(), measure.getColumn());
			
			List<String[]> labelNameValuePairs = linkingMap.get(well);
			
			if (labelNameValuePairs != null){
				for (String[] labelNameValuePair : labelNameValuePairs){
					
					HashMap<String, HashMap<String, ArrayList<Double>>> labelValuesMap = labelNamesMap.get(labelNameValuePair[0]);
					if (labelValuesMap == null){
						labelValuesMap = new HashMap<String, HashMap<String, ArrayList<Double>>>();
						labelNamesMap.put(labelNameValuePair[0], labelValuesMap);
					}
					
					HashMap<String, ArrayList<Double>> measureTypeMap = labelValuesMap.get(labelNameValuePair[1]);
					if (measureTypeMap == null){
						measureTypeMap = new HashMap<String, ArrayList<Double>>();
						labelValuesMap.put(labelNameValuePair[1], measureTypeMap);
					}
					
					ArrayList<Double> valueList = measureTypeMap.get(measure.getMeasurementType());
					if (valueList == null){
						valueList = new ArrayList<Double>();
						measureTypeMap.put(measure.getMeasurementType(), valueList);
					}
					
					labelNamesMap.get(labelNameValuePair[0]).get(labelNameValuePair[1]).get(measure.getMeasurementType()).add(measure.getValue());
				}
			}
		}
		
		return saveResultMeasurements(results, labelNamesMap);
	}

	private boolean saveResultMeasurements(
			ResultSnapshot results,
			HashMap<String, HashMap<String, HashMap<String, ArrayList<Double>>>> labelNamesMap) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		
//		Session session = sessionFactory.openSession();
//		session.beginTransaction();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH);
		
		Plate plate = getPlateAssociatedToResult(results);
		String plateId = "" + plate.getId();
		String projectId = "" + getProjectAssociatedToPlate(plate).getId();
		
		Session session = sessionFactory.openSession();
		
		try{
			session.beginTransaction();
			
			for (String labelName : labelNamesMap.keySet()){
				for (String labelValue : labelNamesMap.get(labelName).keySet()){
					
					for (String measurementType : labelNamesMap.get(labelName).get(labelValue).keySet()){
						
						try{
							
							DataSet allMeasuredValues = new DataSet();
							allMeasuredValues.setLabelName(labelName);
							allMeasuredValues.setLabelValue(labelValue);
							allMeasuredValues.setMeasurementType(measurementType);
							allMeasuredValues.setPlateId(plateId);
							allMeasuredValues.setProjectId(projectId);
							allMeasuredValues.setTime(dateFormat.format(results.getTime()));
							
							List<Double> values = labelNamesMap.get(labelName).get(labelValue).get(measurementType);
							String jsonValues = mapper.writeValueAsString(values);
							allMeasuredValues.setJsonValues(jsonValues);
							session.saveOrUpdate(allMeasuredValues);
						}
						catch(Throwable t){
							System.out.println(t);
						}
					}
				}
			}
			session.getTransaction().commit();
		}
		finally{
			session.close();	
		}
		
		return true;
	}
}