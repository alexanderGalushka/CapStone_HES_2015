package edu.harvard.cscie99.adam.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 
 * @author Gerson
 *
 */
@Entity
public class Template implements Serializable{
	
	/**
	 * Initial version
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "template_id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "numWellsX")
	private int numWellsX;
	
	@Column(name = "numWellsY")
	private int numWellsY;
	
	@ManyToOne(targetEntity = Project.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;
	
	@Column(name = "plate_type")
	private Plate.PlateType type;
	
	@Column(name = "tags")
	private String tags;
	
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
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
