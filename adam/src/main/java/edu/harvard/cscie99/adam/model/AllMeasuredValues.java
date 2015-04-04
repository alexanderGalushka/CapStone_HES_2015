package edu.harvard.cscie99.adam.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class AllMeasuredValues 
{
// - ALEX's work (let's pair program these attributes (Gerson))
//
//	private String measurementType;
//	private double[] allMeasuredValues;
//	
//	public String getMeasurementType() {
//		return measurementType;
//	}
//	public void setMeasurementType(String measurementType) {
//		this.measurementType = measurementType;
//	}
//	public double[] getAllMeasuredValues() {
//		return allMeasuredValues;
//	}
//	public void setAllMeasuredValues(double[] allMeasuredValues) {
//		this.allMeasuredValues = allMeasuredValues;
//	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "all_measured_values_id")
	private int id;
	
	@ManyToOne(targetEntity = Project.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Project project;
	
	@ManyToOne(targetEntity = Plate.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Plate plate;
	
	@Column(name = "measurement_type")
	private String measurementType;
	
	@Column(name = "time")
	private Date time;
	
	@Column(name = "label")
	private String label;
	
	@ManyToOne(targetEntity = Compound.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Compound compound;
	
	@ManyToOne(targetEntity = Substrate.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Substrate substrate;
	
	@Lob
	@Column(length=100000)
	private byte[] jsonValues;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Plate getPlate() {
		return plate;
	}

	public void setPlate(Plate plate) {
		this.plate = plate;
	}

	public String getMeasurementType() {
		return measurementType;
	}

	public void setMeasurementType(String measurementType) {
		this.measurementType = measurementType;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public byte[] getJsonValues() {
		return jsonValues;
	}

	public void setJsonValues(byte[] jsonValues) {
		this.jsonValues = jsonValues;
	}

		
}
