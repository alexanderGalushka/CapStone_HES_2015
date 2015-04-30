package edu.harvard.cscie99.adam.model;

import java.util.List;

/**
*
* Axis class.
* Represents a single axis from the Graph object.
* Axis has a type associated (X, Y, Z), a list of points and corresponding outlier indicators
*
* @creator Gerson
*/
public class Axis {
	
	/**
	 * Axis type (X, Y, Z coordinate)
	 * 
	 * @author Gerson
	 *
	 */
	public enum Type {X, Y, Z}
	
	/**
	 * Axis type
	 */
	private Type type;
	
	/**
	 * List of points from Axis
	 */
	private List<Double> points;
	
	/**
	 * List of outliers indicators. Each outlier in index i shows if points[index] is an outlier 
	 */
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
