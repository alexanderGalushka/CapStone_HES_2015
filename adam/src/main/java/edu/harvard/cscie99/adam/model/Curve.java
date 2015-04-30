package edu.harvard.cscie99.adam.model;

/**
 * 
 * @author Alexander G.
 * Abstract class for all curves: Linear, Polynomial, Exponential
 */
public abstract class Curve 
{
	
	/**
	 * 
	 * Get list of points from curve
	 * 
	 * @return list of points
	 */
	public abstract double[] getDataPoints();

	/**
	 * Get type of curve (eg. Polinomial, Exponential)
	 * 
	 * @return
	 */
	public abstract String getCurveType();
	
}
