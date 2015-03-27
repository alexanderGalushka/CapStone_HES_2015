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
	private Comment[] comments;
}
