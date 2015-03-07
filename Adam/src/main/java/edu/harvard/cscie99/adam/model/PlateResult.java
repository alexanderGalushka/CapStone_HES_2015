package edu.harvard.cscie99.adam.model;

import java.io.Serializable;
import java.util.List;

public class PlateResult implements Serializable{
	
	/**
	 * Initial version
	 */
	private static final long serialVersionUID = 1L;
	
	private Plate plate;
	private List<WellResult> wells;

	public List<WellResult> getWells() {
		return wells;
	}

	public void setWells(List<WellResult> wells) {
		this.wells = wells;
	}

	public Plate getPlate() {
		return plate;
	}

	public void setPlate(Plate plate) {
		this.plate = plate;
	}
}
