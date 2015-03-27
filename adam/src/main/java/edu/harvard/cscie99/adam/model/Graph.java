package edu.harvard.cscie99.adam.model;

import java.util.List;
import java.util.Set;

public class Graph {
	
	public enum GraphType {BAR, SCATTERPLOT, CURVE, LINE, SURFACE}
	
	private List<Axis[]> axisTuples;
	private GraphType graphType;
	private Curve curve;
	
}
