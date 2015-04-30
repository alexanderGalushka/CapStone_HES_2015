package edu.harvard.cscie99.adam.model;

import java.util.List;

/**
 * Linear class
 * 
 * Represents Linear graphs in the Data Analysis Save/Retrieve results screen
 * 
 * @author Alex
 *
 */
public class Linear extends Curve {
	
	/**
	 * List of datapoints
	 */
	private double[] dataPoints;
	
	/**
	 * Type of line
	 */
	private String lineType = "linear";
	
	/**
	 * Type of graph
	 */
	private String graphType = "chart";
	
	/** 
	 * Point of intercept
	 */
	private double intercept;
	
	/**
	 * R square value
	 */
	private double rSquared;
	
	/**
	 * Slope value
	 */
	private double slope;
	
	public double[] getDataPoints() {
		return dataPoints;
	}
	public void setDataPoints(double[] dataPoints) {
		this.dataPoints = dataPoints;
	}
	public String getLineType() {
		return lineType;
	}
	public void setLineType(String lineType) {
		this.lineType = lineType;
	}
	public double getIntercept() {
		return intercept;
	}
	public void setIntercept(double intercept) {
		this.intercept = intercept;
	}
	public double getrSquared() {
		return rSquared;
	}
	public void setrSquared(double rSquare) {
		this.rSquared = rSquare;
	}
	public double getSlope() {
		return slope;
	}
	public void setSlope(double slope) {
		this.slope = slope;
	}
	public String getGraphType() {
		return graphType;
	}
	public void setGraphType(String graphType) {
		this.graphType = graphType;
	}
	@Override
	public String getCurveType() {
		return "linear";
	}
	
	

}
