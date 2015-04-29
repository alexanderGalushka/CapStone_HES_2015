package edu.harvard.cscie99.adam.model;

import java.util.List;

/**
 * Graph class
 * Represents a saved result graph in Adam system
 * 
 * @author Gerson
 *
 */
public class Graph {
	
	/**
	 * Enumeration of graph types
	 * 
	 * @author Gerson
	 *
	 */
	public enum GraphType {BAR, SCATTERPLOT, CURVE, LINE, SURFACE}
	
	/**
	 * List of axis from graph
	 */
	private List<Axis[]> axisTuples;
	
	/**
	 * Graph type
	 */
	private GraphType graphType;
	
	/**
	 * Graph's curve: for curve fit graphs
	 */
	private Curve curve;
	
	public List<Axis[]> getAxisTuples() {
		return axisTuples;
	}
	public void setAxisTuples(List<Axis[]> axisTuples) {
		this.axisTuples = axisTuples;
	}
	public GraphType getGraphType() {
		return graphType;
	}
	public void setGraphType(GraphType graphType) {
		this.graphType = graphType;
	}
	public Curve getCurve() {
		return curve;
	}
	public void setCurve(Curve curve) {
		this.curve = curve;
	}
	
}
