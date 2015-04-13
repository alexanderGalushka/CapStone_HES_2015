package edu.harvard.cscie99.adam.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.harvard.cscie99.adam.model.DataSet;

public class QueryService 
{
	@Autowired
    private SessionFactory sessionFactory;
	

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
	
	
	public List<DataSet> getValuesUsingFilter(Integer projectId, Integer plateId, String measurementType,
			                                  String timeStamp, String labelName, String labelValue)
	{
		StringBuffer sb = new StringBuffer();
		
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
		
		org.hibernate.Session session = sessionFactory.openSession();
		Query query = session.createQuery(queryString);
		List<DataSet> resultList = query.list();
		
		return resultList;
	}
	
	public List<Integer> getPlateIdsPerProject (Integer projectId)
	{
		// TODO
		return null;
	}
	
	
	
}
