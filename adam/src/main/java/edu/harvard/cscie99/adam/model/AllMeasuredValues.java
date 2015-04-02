package edu.harvard.cscie99.adam.model;


public class AllMeasuredValues 
{
	private String measurementType;
	private double[] allMeasuredValues;
	
	public String getMeasurementType() {
		return measurementType;
	}
	public void setMeasurementType(String measurementType) {
		this.measurementType = measurementType;
	}
	public double[] getAllMeasuredValues() {
		return allMeasuredValues;
	}
	public void setAllMeasuredValues(double[] allMeasuredValues) {
		this.allMeasuredValues = allMeasuredValues;
	}
		
}
