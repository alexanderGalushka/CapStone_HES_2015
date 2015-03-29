package edu.harvard.cscie99.adam.model;

public class Growth extends Curve
{
	private double growthRate;
	
	private double startingPoint;
	
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
