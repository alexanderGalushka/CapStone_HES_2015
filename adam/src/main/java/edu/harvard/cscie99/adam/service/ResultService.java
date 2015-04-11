package edu.harvard.cscie99.adam.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
	
	

	public List<Well> retrieveResult(int projectId, int resultId){
		
		//TODO retrieve from DB
		
		Plate plate = new Plate();
		plate.setId(1);
//		plate.setName("plate");
		
		List<Well> wells = new ArrayList<>();
		for (int j = 0; j < 10; j++){
			Well wellResult = new Well();
			//wellResult.setCreationTime(new Date());
			wellResult.setId(j);
			wells.add(wellResult);
		}
				
		return wells;
	}
	
	public ResultSnapshot saveResultSnapshot(ResultSnapshot result){
		try{
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(result);
			session.getTransaction().commit();
			session.close();
			
			return result;
		}
		catch (Exception ex){
			return null;
		}
	}
	
	public boolean prepareResultsData(ResultSnapshot results) throws JsonProcessingException{
		
		HashMap<String, HashMap<String, List<Well>>> labelTree = new HashMap<String, HashMap<String, List<Well>>>();
		HashMap<Well, ArrayList<String[]>> linkingMap = new HashMap<Well, ArrayList<String[]>>();
		
		Plate plate = results.getPlate();
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
		
		return saveResultMeasurements(results, labelNamesMap);
	}

	private boolean saveResultMeasurements(
			ResultSnapshot results,
			HashMap<String, HashMap<String, HashMap<String, ArrayList<Double>>>> labelNamesMap) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		
//		Session session = sessionFactory.openSession();
//		session.beginTransaction();
		
		for (String labelName : labelNamesMap.keySet()){
			for (String labelValue : labelNamesMap.get(labelName).keySet()){
				
				for (String measurementType : labelNamesMap.get(labelName).get(labelValue).keySet()){
					
					Session session = sessionFactory.openSession();
					session.beginTransaction();
					
					DataSet allMeasuredValues = new DataSet();
					allMeasuredValues.setLabelName(labelName);
					allMeasuredValues.setLabelValue(labelValue);
					allMeasuredValues.setMeasurementType(measurementType);
					allMeasuredValues.setPlateId(results.getPlate().getId());
					allMeasuredValues.setProjectId(results.getProject().getId());
					allMeasuredValues.setTime(results.getTime());
					
					List<Double> values = labelNamesMap.get(labelName).get(labelValue).get(measurementType);
					String jsonValues = mapper.writeValueAsString(values);
					allMeasuredValues.setJsonValues(jsonValues);
					session.saveOrUpdate(allMeasuredValues);
					session.getTransaction().commit();
					session.close();
				}
			}
		}
//		session.getTransaction().commit();
//		session.close();
		return true;
	}
	
	public DataSet queryResultsData(Integer projectId, Integer plateId, String labelName, String labelValue, String measurementType, Date time) throws JsonProcessingException{
		
		Session session = sessionFactory.openSession();
		DataSet filteredValues = new DataSet();
		ObjectMapper mapper = new ObjectMapper();
		
		StringBuilder query = new StringBuilder();
		query.append("FROM DataSet D ");
		
		StringBuilder criteria = new StringBuilder("WHERE ");
		if (projectId != null){
			criteria.append("D.projectId = " + projectId + " AND");
			filteredValues.setProjectId(projectId);
		}
		if (plateId != null){
			criteria.append("D.plateId = " + plateId + " AND");
			filteredValues.setPlateId(plateId);
		}
		if (labelName != null && labelName.length()>0){
			criteria.append("D.labelName = " + labelName + " AND");
			filteredValues.setLabelName(labelName);
		}
		if (labelValue != null && labelValue.length()>0){
			criteria.append("D.labelValue = " + labelValue + " AND");
			filteredValues.setLabelValue(labelValue);
		}
		if (measurementType != null && measurementType.length()>0){
			criteria.append("D.measurementType = " + measurementType + " AND");
			filteredValues.setMeasurementType(measurementType);
		}
		
		String queryStr = null;
		if (criteria.toString().length() > 6){
			query.append (criteria);
			queryStr = query.toString().substring(0, query.toString().length() - 4);
		}
		else{
			queryStr = query.toString();
		}
		
		Query hibernateQuery = session.createQuery(queryStr);
		List<DataSet> results = hibernateQuery.list();
		
		List<Double> listValues = new ArrayList<Double>();
		for (DataSet result : results){
			
//			List<Double> values = labelNamesMap.get(labelName).get(labelValue).get(measurementType);
			String jsonValues = result.getJsonValues();
			ArrayList<Double> doubles = null;
			try {
				doubles = mapper.readValue(jsonValues, ArrayList.class);
				listValues.addAll(doubles);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		filteredValues.setJsonValues(mapper.writeValueAsString(listValues));
		
		return filteredValues;
	}

}