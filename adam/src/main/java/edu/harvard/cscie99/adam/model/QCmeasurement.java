package edu.harvard.cscie99.adam.model;

import java.util.Date;
import java.util.List;

/**
 * QCMeasurement class
 * 
 * Represents the QC data of a list of wells (well measurement type/value and Z-factors)
 * 
 * @author Alex
 *
 */
public class QCmeasurement 
{
	/**
	 * Measurement type
	 */
	private String measurementType;
	
	/**
	 * Timestamp 
	 */
	private Date timeStamp;
	
	/**
	 * Z-factor value
	 */
	private Double zFactor;
	
	/**
	 * Z-prime factor value
	 */
	private Double zPrimeFactor;
	
	/**
	 * List of QCWells
	 */
	private List<QCwell> wells;

	public String getMeasurementType() {
		return measurementType;
	}

	public void setMeasurementType(String measurementType) {
		this.measurementType = measurementType;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Double getzFactor() {
		return zFactor;
	}

	public void setzFactor(Double zFactor) {
		this.zFactor = zFactor;
	}

	public Double getzPrimeFactor() {
		return zPrimeFactor;
	}

	public void setzPrimeFactor(Double zPrimeFactor) {
		this.zPrimeFactor = zPrimeFactor;
	}

	public List<QCwell> getWells() {
		return wells;
	}

	public void setWells(List<QCwell> wells) {
		this.wells = wells;
	}
	
}
