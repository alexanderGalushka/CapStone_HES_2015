package edu.harvard.cscie99.adam.model;



/**
 * 
 * @author Alexander G.
 *
 */

public class Decay extends Curve
{
	private double decayRate;
	
	private double plateau;
	
	private double halfTime;
	
	private String graphType = "decay";
	
	public double getDecayRate() {
		return decayRate;
	}

	public void setDecayRate(double decayRate) {
		this.decayRate = decayRate;
	}

	public double getPlateau() {
		return plateau;
	}

	public void setPlateau(double plateau) {
		this.plateau = plateau;
	}


	public double getHalfTime() {
		return halfTime;
	}

	public void setHalfTime(double halfTime) {
		this.halfTime = halfTime;
	}


	@Override
	public double[] getDataPoints() 
	{
		double[] coeff = new double[] {plateau, decayRate, halfTime};
		return coeff;
	}

	@Override
	public String getCurveType()
	{
		return graphType;
	}
	
	
}
