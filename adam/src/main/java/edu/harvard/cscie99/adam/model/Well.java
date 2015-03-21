package edu.harvard.cscie99.adam.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
public class Well implements Serializable{
	
	/**
	 * Initial version
	 */
	private static final long serialVersionUID = 1L;
	
	public enum ControlType {POSITIVE, NEGATIVE, REFERENCE}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "well_id")
	private Integer id;
	
	@ManyToOne(targetEntity = Plate.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "plate_id")
    private Plate plate;
	
	@Column(name = "plate_position_x")
	private int platePositionX;
	@Column(name = "plate_position_y")
	private int platePositionY;
	
	@Column(name = "labels")
	private String labels;
	
	@Column(name = "color")
	private Integer color;
	
	@Column(name = "control_type")
	private ControlType controlType;
	
	private List<Sample> samples;
	private List<WellResult> wellResults;
	
	public Well(){
		samples = new ArrayList<Sample>();
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
	public String getLabels() {
		return labels;
	}
	public void setLabels(String labels) {
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
