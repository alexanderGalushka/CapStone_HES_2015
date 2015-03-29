package edu.harvard.cscie99.adam.model;


/**
 * 
 * @author Alexander G.
 *
 */

public class Linear extends Curve
{

	private double slope;
	
	private double intercept;
	
	private double rSquared;
	
	private String graphType;
	
	public double getSlope() 
	{
		return slope;
	}

	public void setSlope(double slope)
	{
		this.slope = slope;
	}

	public double getIntercept() 
	{
		return intercept;
	}

	public void setIntercept(double intercept)
	{
		this.intercept = intercept;
	}

	public double getrSquared() 
	{
		return rSquared;
	}

	public void setrSquared(double rSquared) 
	{
		this.rSquared = rSquared;
	}

	public void setGraphType(String graphType) 
	{
		this.graphType = graphType;
	}
	
	@Override
	public double[] getDataPoints()
	{
		double[] coeff = new double[] {slope, intercept, rSquared};
		return coeff;
	}

	@Override
	public String getCurveType() 
	{
		return graphType;
	}
	
}
