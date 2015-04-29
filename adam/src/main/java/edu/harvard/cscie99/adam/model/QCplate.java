package edu.harvard.cscie99.adam.model;

import java.util.Date;
import java.util.List;

/**
 * QCplate object
 * 
 * Analogous to the Plate object, encapsulate Plate information + data quality control statistics
 * @author Adam
 *
 */
public class QCplate
{

	/**
	 * Project associated to QCplate
	 */
	private String projectId;
	
	/**
	 * Plate id associated to QCplate
	 */
	private Integer plateId;
	
	/**
	 * Result id associated to QCplate
	 */
	private Integer resultId;
	
	/**
	 * Name
	 */
    private String name;
    
    /** 
     * Date of plate creation
     */
    private Date date;
    
    /**
     * Plate row dimensions
     */
	private Integer numberOfRows;
	
	/**
     * Plate column dimensions
     */
	private Integer numberOfColumns;
	
	/**
     * List of Control Types 
     */
    private List<ControlType> controlTypes;
 
	private List<MeasurementType> measurementTypes;
	
	private List<QCmeasurement> measurements;
    
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

	public List<QCmeasurement> getMeasurements() {
		return measurements;
	}

	public void setMeasurements(List<QCmeasurement> measurements) {
		this.measurements = measurements;
	}

	public List<TimeStamp> getTimeStamps() {
		return timeStamps;
	}

	public void setTimeStamps(List<TimeStamp> timeStamps) {
		this.timeStamps = timeStamps;
	}
	
}
