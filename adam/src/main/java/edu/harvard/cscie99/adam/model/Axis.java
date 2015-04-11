package edu.harvard.cscie99.adam.model;

import java.util.List;

/**
*
* @creator Gerson
*/
public class Axis {
	
	public enum Type {X, Y, Z}
	
	private Type type;
	private List<Double> points;
	private List<Boolean> outliers;
	
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public List<Double> getPoints() {
		return points;
	}
	public void setPoints(List<Double> points) {
		this.points = points;
	}
	public List<Boolean> getOutliers() {
		return outliers;
	}
	public void setOutliers(List<Boolean> outliers) {
		this.outliers = outliers;
	}
}
