package edu.harvard.cscie99.adam.model;

import javax.persistence.Entity;

/**
 * 
 * @author Alexander G.
 *
 */

public class Line
{

	public double getSlope() {
		return slope;
	}

	public void setSlope(double slope) {
		this.slope = slope;
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

	public void setrSquared(double rSquared) {
		this.rSquared = rSquared;
	}

	private double slope;
	
	private double intercept;
	
	private double rSquared;
	
}
