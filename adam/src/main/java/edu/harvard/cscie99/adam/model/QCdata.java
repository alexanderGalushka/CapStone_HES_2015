package edu.harvard.cscie99.adam.model;

import java.util.List;

/**
 * 
 * QCdata class
 * 
 * Represents the QC virtual plate: the aggregation of
 * Plate information (wells, control types) and QC generated statistics
 * (Z-factor, z-Prime) 
 * 
 * @author Alex
 *
 */
public class QCdata 
{
	/**
	 * Measurement type 
	 */
	private String measurementType;
	
	/**
	 * List of data points
	 */
	private List<Double> values;
	
	/**
	 * Z Factor value
	 */
	private Double zFactor;
	
	/**
	 * Z Prime Factor value
	 */
	private Double zPrimeFactor;

	public String getMeasurementType() {
		return measurementType;
	}

	public void setMeasurementType(String measurementType) {
		this.measurementType = measurementType;
	}

	public List<Double> getValues() {
		return values;
	}

	public void setValues(List<Double> values) {
		this.values = values;
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
	
	
}
