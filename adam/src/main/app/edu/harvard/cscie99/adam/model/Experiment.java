package edu.harvard.cscie99.adam.model;

import java.io.Serializable;

public class Experiment implements Serializable {
	
	/**
	 * Initial version
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String project;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	

}
