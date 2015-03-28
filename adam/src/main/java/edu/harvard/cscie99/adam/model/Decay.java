package edu.harvard.cscie99.adam.model;

import javax.persistence.Entity;

/**
 * 
 * @author Alexander G.
 *
 */
@Entity
public class Decay 
{

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


	private double decayRate;
	
	private double plateau;
	
	private double halfTime;
	
	
}
