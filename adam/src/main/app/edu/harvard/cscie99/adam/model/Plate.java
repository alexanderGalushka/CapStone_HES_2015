package edu.harvard.cscie99.adam.model;

import java.io.Serializable;
import java.util.List;

public class Plate implements Serializable{
	
	/**
	 * Initial version
	 */
	private static final long serialVersionUID = 1L;
	
	public enum PlateType {STANDARD, DEEP, PERFORATED}

	private int id;
	private String barcode;
	private String name;
	private String description;
	private int numWellsX;
	private int numWellsY;
	private PlateType type;
	private Experiment experiment;
	private String protocol;
	private List<String> tags;
	private List<Well> wells;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getNumWellsX() {
		return numWellsX;
	}
	public void setNumWellsX(int numWellsX) {
		this.numWellsX = numWellsX;
	}
	public int getNumWellsY() {
		return numWellsY;
	}
	public void setNumWellsY(int numWellsY) {
		this.numWellsY = numWellsY;
	}
	public PlateType getType() {
		return type;
	}
	public void setType(PlateType type) {
		this.type = type;
	}
	public Experiment getExperiment() {
		return experiment;
	}
	public void setExperiment(Experiment experiment) {
		this.experiment = experiment;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public List<Well> getWells() {
		return wells;
	}
	public void setWells(List<Well> wells) {
		this.wells = wells;
	}
	
}
