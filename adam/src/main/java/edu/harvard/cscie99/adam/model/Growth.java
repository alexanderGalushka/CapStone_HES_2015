package edu.harvard.cscie99.adam.model;

/**
 * 
 * Growth class
 * 
 * Represents the growth curve.
 * 
 * @author Gerson
 *
 */
public class Growth extends Curve
{
	/**
	 * Rate of curve growth
	 */
	private double growthRate;
	
	/**
	 * Starting point
	 */
	private double startingPoint;
	
	/**
	 * Constant value for graph type
	 */
	private String graphType = "growth";
	
	public double getGrowthRate() 
	{
		return growthRate;
	}

	public void setGrowthRate(double growthRate)
	{
		this.growthRate = growthRate;
	}

	public double getStartingPoint() 
	{
		return startingPoint;
	}

	public void setStartingPoint(double startingPoint) 
	{
		this.startingPoint = startingPoint;
	}
	
	@Override
	public double[] getDataPoints() 
	{
		double[] coeff = new double[] {startingPoint, growthRate};
		return coeff;
	}

	@Override
	public String getCurveType()
	{
		return graphType;
	}
	
}
