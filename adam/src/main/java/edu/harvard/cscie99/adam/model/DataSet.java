package edu.harvard.cscie99.adam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * 
 * Set of data points to be used in Data Analysis screen
 * 
 * @author Gerson
 *
 */
@Entity
public class DataSet 
{

	/**
	 * Auto-generated key
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dataset_id")
	private int id;
	
	/**
	 * Project associated to set of data points
	 */
	@Column(name = "project_id")
	private String projectId;
	
	/**
	 * Plate associated to set of data points
	 */
	@Column(name = "plate_id")
	private String plateId;
	
	/**
	 * Measurement type associated to set of data points
	 */
	@Column(name = "measurement_type")
	private String measurementType;
	
	/**
	 * Label name to set of data points
	 */
	@Column(name = "label_name")
	private String labelName;
	
	/**
	 * Label value to set of data points
	 */
	@Column(name = "label_value")
	private String labelValue;
	
	/**
	 * Date and time associated to set of data points
	 */
	@Column(name = "time")
	private String time;
	
	/**
	 * JSON-String representation of data points value.
	 * The data points is a JSON string that represents a serialized list of double values.
	 * This serves the purpose of storing multiple numerical values in a single column in DB.
	 * 
	 */
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
