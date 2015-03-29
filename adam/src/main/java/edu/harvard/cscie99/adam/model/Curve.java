package edu.harvard.cscie99.adam.model;

/**
 * 
 * @author Alexander G.
 * Abstract class for all curves: Linear, Polynomial, Exponential
 */
public abstract class Curve 
{
	
	public abstract double[] getDataPoints();

	public abstract String getCurveType();
	
}
