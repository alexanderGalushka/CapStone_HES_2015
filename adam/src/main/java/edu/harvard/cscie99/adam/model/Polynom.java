package edu.harvard.cscie99.adam.model;


/**
 * 
 * @author Alexander G.
 *
 */

public class Polynom extends Curve
{

	private double[] polinimialCoeff;
	
	private double rSquared;
	
	private String curveType;
	
	public double getrSquared()
	{
		return rSquared;
	}

	public void setrSquared(double rSquared)
	{
		this.rSquared = rSquared;
	}

	public double[] getPolinomialCoeff()
	{
		return polinimialCoeff;
	}

	public void setPolinomialCoeff(double[] polinimialCoeff)
	{
		this.polinimialCoeff = polinimialCoeff;
	}


	public void setCurveType(String curveType)
	{
		this.curveType = curveType;
	}
	
	@Override
	public double[] getDataPoints()
    {
		return polinimialCoeff;
	}

	@Override
	public String getCurveType() 
	{
		return curveType;
	}

	
}

