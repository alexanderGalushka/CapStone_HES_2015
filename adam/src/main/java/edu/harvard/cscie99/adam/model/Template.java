package edu.harvard.cscie99.adam.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Template implements Serializable{
	
	/**
	 * Initial version
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String description;
	private int numWellsX;
	private int numWellsY;
	private Project project;
	private Plate.PlateType type;
	private List<String> tags;
	
	public Template(){
		tags = new ArrayList<String>();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Plate.PlateType getType() {
		return type;
	}
	public void setType(Plate.PlateType type) {
		this.type = type;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
