package edu.harvard.cscie99.adam.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * Measurement class
 * 
 * Represents a measurement taken from the Plate Reader instrument.
 * The result CSV file will contain a list of measurements per well (ex: density)
 * 
 * @author Gerson
 *
 */
@Entity
public class Measurement implements Serializable
{
	/**
	 * Init version
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Auto-generated key
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "measurement_id")
	private int id;
	
	/**
	 * Measurement type: textual representation of measured value (eg: density)
	 */
	@Column(name = "measurement_type")
	private String measurementType;
	
	/**
	 * Value: readout from well 
	 */
	@Column(name = "value")
	private double value;
	
	/**
	 * Row: X position from the plate where the measurement value was read
	 */
	@Column(name = "row")
	private int row;
	
	/**
	 * Col: Y position from the plate where the measurement value was read
	 */
	@Column(name = "col")
	private int column;
	
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public String getMeasurementType() {
		return measurementType;
	}
	public void setMeasurementType(String measurementType) {
		this.measurementType = measurementType;
	}
}
