package edu.harvard.cscie99.adam.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Gerson
 *
 */
public class Well implements Serializable{
	
	/**
	 * Initial version
	 */
	private static final long serialVersionUID = 1L;
	
	public enum ControlType {POSITIVE, NEGATIVE, REFERENCE}
	
	private Plate plate;
	private int platePositionX;
	private int platePositionY;
	
	private Integer id;
//	private List<Compound> compounds;
	private List<String> labels;
	private Integer color;
	private ControlType controlType;
	
//	private Double dosagePerc;
	private List<Sample> samples;
	private List<WellResult> wellResults;
	
	public Well(){
		samples = new ArrayList<Sample>();
		labels = new ArrayList<String>();
		wellResults = new ArrayList<WellResult>();
	}
	
	public Plate getPlate() {
		return plate;
	}
	public void setPlate(Plate plate) {
		this.plate = plate;
	}
	public int getPlatePositionX() {
		return platePositionX;
	}
	public void setPlatePositionX(int platePositionX) {
		this.platePositionX = platePositionX;
	}
	public int getPlatePositionY() {
		return platePositionY;
	}
	public void setPlatePositionY(int platePositionY) {
		this.platePositionY = platePositionY;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<String> getLabels() {
		return labels;
	}
	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
	public Integer getColor() {
		return color;
	}
	public void setColor(Integer color) {
		this.color = color;
	}
	public ControlType getControlType() {
		return controlType;
	}
	public void setControlType(ControlType controlType) {
		this.controlType = controlType;
	}
	public List<WellResult> getWellResults() {
		return wellResults;
	}
	public void setWellResults(List<WellResult> wellResults) {
		this.wellResults = wellResults;
	}
	public List<Sample> getSamples() {
		return samples;
	}
	public void setSamples(List<Sample> samples) {
		this.samples = samples;
	}
}
