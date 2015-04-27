package edu.harvard.cscie99.adam.model;

import java.util.Date;
import java.util.List;

public class QCplate
{

	private String projectId;
	private Integer plateId;
	private Integer resultId;
    private String name;
    private Date date;
    
	//plate dimensions
	private Integer numberOfRows;
	private Integer numberOfColumns;
	
    private List<ControlType> controlTypes;
 
	private List<MeasurementType> measurementTypes;
	
	private List<QCmeasurement> qcMeasurement;
    
	private List<TimeStamp> timeStamps;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public Integer getPlateId() {
		return plateId;
	}

	public void setPlateId(Integer plateId) {
		this.plateId = plateId;
	}

	public Integer getResultId() {
		return resultId;
	}

	public void setResultId(Integer resultId) {
		this.resultId = resultId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getNumberOfRows() {
		return numberOfRows;
	}

	public void setNumberOfRows(Integer numberOfRows) {
		this.numberOfRows = numberOfRows;
	}

	public Integer getNumberOfColumns() {
		return numberOfColumns;
	}

	public void setNumberOfColumns(Integer numberOfColumns) {
		this.numberOfColumns = numberOfColumns;
	}

	public List<ControlType> getControlTypes() {
		return controlTypes;
	}

	public void setControlTypes(List<ControlType> controlTypes) {
		this.controlTypes = controlTypes;
	}

	public List<MeasurementType> getMeasurementTypes() {
		return measurementTypes;
	}

	public void setMeasurementTypes(List<MeasurementType> measurementTypes) {
		this.measurementTypes = measurementTypes;
	}

	public List<QCmeasurement> getQcMeasurement() {
		return qcMeasurement;
	}

	public void setQcMeasurement(List<QCmeasurement> qcMeasurement) {
		this.qcMeasurement = qcMeasurement;
	}

	public List<TimeStamp> getTimeStamps() {
		return timeStamps;
	}

	public void setTimeStamps(List<TimeStamp> timeStamps) {
		this.timeStamps = timeStamps;
	}
	
}
