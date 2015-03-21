package edu.harvard.cscie99.adam.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Gerson
 *
 */
public class Sample implements Serializable{
	
	/**
	 * Initial version
	 */
	private static final long serialVersionUID = 1L;
	
	private double value;
	private String label;
	private Date time;
	private Compound compound;
	private Substrate substrate;
	private double dilution;
	private DataPointMetadata comment;
	
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Compound getCompound() {
		return compound;
	}
	public void setCompound(Compound compound) {
		this.compound = compound;
	}
	public Substrate getSubstrate() {
		return substrate;
	}
	public void setSubstrate(Substrate substrate) {
		this.substrate = substrate;
	}
	public double getDilution() {
		return dilution;
	}
	public void setDilution(double dilution) {
		this.dilution = dilution;
	}
	public DataPointMetadata getComment() {
		return comment;
	}
	public void setComment(DataPointMetadata comment) {
		this.comment = comment;
	}

}
