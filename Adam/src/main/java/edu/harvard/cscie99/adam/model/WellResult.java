package edu.harvard.cscie99.adam.model;

import java.io.Serializable;
import java.util.List;

public class WellResult implements Serializable{
	
	/**
	 * Initial version
	 */
	private static final long serialVersionUID = 1L;
	
	private Well well;
	private List<Double> readings;
	private List<String> labels;
	
	public List<Double> getReadings() {
		return readings;
	}
	public void setReadings(List<Double> readings) {
		this.readings = readings;
	}
	public List<String> getLabels() {
		return labels;
	}
	public void setLabels(List<String> label) {
		this.labels = label;
	}
	public Well getWell() {
		return well;
	}
	public void setWell(Well well) {
		this.well = well;
	}

}
