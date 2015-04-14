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
public class DataSet 
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
    @Column(name = "dataset_id")
	private int id;
	
	@Column(name = "project_id")
	private String projectId;
	
	@Column(name = "plate_id")
	private String plateId;
	
	@Column(name = "measurement_type")
	private String measurementType;
	
	@Column(name = "label_name")
	private String labelName;
	
	@Column(name = "label_value")
	private String labelValue;
	
	@Column(name = "time")
	private String time;
	
//	@ManyToOne(targetEntity = Compound.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	private Compound compound;
//	
//	@ManyToOne(targetEntity = Substrate.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	private Substrate substrate;
	
	@Lob
	@Column(length=100000)
	private String jsonValues;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMeasurementType() {
		return measurementType;
	}

	public void setMeasurementType(String measurementType) {
		this.measurementType = measurementType;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getJsonValues() {
		return jsonValues;
	}

	public void setJsonValues(String jsonValues) {
		this.jsonValues = jsonValues;
	}

	public String getLabelValue() {
		return labelValue;
	}

	public void setLabelValue(String labelValue) {
		this.labelValue = labelValue;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getPlateId() {
		return plateId;
	}

	public void setPlateId(String plateId) {
		this.plateId = plateId;
	}

		
}
