package edu.harvard.cscie99.adam.model;

import java.util.List;

public class QCdata 
{

	private String measurementType;
	
	private List<Double> values;
	
	private Double zFactor;
	
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
