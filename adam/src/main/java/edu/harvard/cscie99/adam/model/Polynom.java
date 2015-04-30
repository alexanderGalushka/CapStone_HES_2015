package edu.harvard.cscie99.adam.model;


/**
 * Polynom class
 * 
 * Represents a polynom curve in the saved results section of Data Analysis
 * 
 * @author Alexander G.
 */

public class Polynom extends Curve
{
	/**
	 * Array of polinomial coefficients. Each position in the array represents a variable degree (index=2 is variable to second degree)
	 */
	private double[] polinimialCoeff;
	
	/**
	 * R squared value: squared value of R
	 */
	private double rSquared;
	
	/**
	 * Type of curve
	 */
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

