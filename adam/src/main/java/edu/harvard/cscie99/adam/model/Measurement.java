package edu.harvard.cscie99.adam.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Measurement implements Serializable
{
	/**
	 * Init version
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "measurement_type")
	private String measurementType = "";
	
	@Column(name = "value")
	private double value;
	
	public String getMeasurementName() {
		return measurementType;
	}
	public void setMeasurementName(String measurementType) {
		this.measurementType = measurementType;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
	
}
