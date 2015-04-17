package edu.harvard.cscie99.adam.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.harvard.cscie99.adam.model.DataSet;

@Component
public class QueryService 
{
	@Autowired
    private SessionFactory sessionFactory;
	

	@SuppressWarnings("unchecked")
	public DataSet queryResultsData(Integer projectId, Integer plateId, String labelName, String labelValue, String measurementType, String time) throws JsonProcessingException{
		
		Session session = sessionFactory.openSession();
		DataSet filteredValues = new DataSet();
		
		try{
			ObjectMapper mapper = new ObjectMapper();
			
			@SuppressWarnings("unused")
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH);
			
			StringBuilder query = new StringBuilder();
			query.append("FROM DataSet D ");
			
			StringBuilder criteria = new StringBuilder("WHERE ");
			if (projectId != null){
				criteria.append("D.projectId = " + projectId + " AND ");
				filteredValues.setProjectId("" + projectId);
			}
			else{
				filteredValues.setProjectId("ALL");
			}
			if (plateId != null){
				criteria.append("D.plateId = " + plateId + " AND ");
				filteredValues.setPlateId("" + plateId);
			}
			else{
				filteredValues.setPlateId("ALL");
			}
			if (labelName != null && labelName.length()>0){
				criteria.append("D.labelName = " + labelName + " AND ");
				filteredValues.setLabelName(labelName);
			}
			else{
				filteredValues.setLabelName("ALL");
			}
			if (labelValue != null && labelValue.length()>0){
				criteria.append("D.labelValue = " + labelValue + " AND ");
				filteredValues.setLabelValue(labelValue);
			}
			else{
				filteredValues.setLabelValue("ALL");
			}
			if (measurementType != null && measurementType.length()>0){
				criteria.append("D.measurementType = " + measurementType + " AND ");
				filteredValues.setMeasurementType(measurementType);
			}
			else{
				filteredValues.setMeasurementType("ALL");
			}
			if (time != null && time.length() > 0){
				criteria.append("D.time = " + time + " AND ");
				filteredValues.setTime(time);
			}
			else{
				filteredValues.setTime("ALL");
			}
			
			String queryStr = null;
			if (criteria.toString().length() > 6){
				query.append (criteria);
				queryStr = query.toString().substring(0, query.toString().length() - 5);
			}
			else{
				queryStr = query.toString();
			}
			
			Query hibernateQuery = session.createQuery(queryStr);
			List<DataSet> results = hibernateQuery.list();
			
			List<Double> listValues = new ArrayList<Double>();
			for (DataSet result : results){
				
//				List<Double> values = labelNamesMap.get(labelName).get(labelValue).get(measurementType);
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
		}
		finally{
			session.close();	
		}
		
		return filteredValues;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<DataSet> getValuesUsingFilter(Integer projectId, Integer plateId, String measurementType,
			                                  String timeStamp, String labelName, String labelValue)
	{
		StringBuffer sb = new StringBuffer();
		org.hibernate.Session session = sessionFactory.openSession();
		List<DataSet> resultList = null;
		
		try{
		
			if (projectId != null)
			{
				sb.append("values.project_project_id = '" + projectId.toString() + "' and ");
			}
			if (plateId != null)
			{
				sb.append("values.plate_plate_id = '" + plateId.toString() + "' and ");
			}
			if (measurementType != null)
			{
				sb.append("values.measurement_type = '" + measurementType + "' and ");
			}
			if (timeStamp != null)
			{
				sb.append("values.time = '" + timeStamp + "' and "); // need to check if "values.time" and the type are correct!
			}
			if (labelName != null)
			{
				sb.append("values.label_name = '" + labelName + "' and ");
			}
			if (labelValue != null)
			{
				sb.append("values.label_value = '" + labelValue + "' and ");
			}
			
			String queryString = "from allmeasuredvalues as values ";
			if (sb.length() >= 4)
			{
				queryString = "where " + sb.toString().substring(0, sb.toString().length() - 5); // get rid off " and "
			}
			
			Query query = session.createQuery(queryString);
			resultList = query.list();
		}
		finally{
			session.close();	
		}
		
		return resultList;
	}
	
	public List<Integer> getPlateIdsPerProject (Integer projectId)
	{
		// TODO
		return null;
	}
	
}
