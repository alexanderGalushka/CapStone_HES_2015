package edu.harvard.cscie99.adam.model;

import java.util.Date;

/**
 * Timestamp class
 * 
 * QC custom implementation of timestamps
 * 
 * @author Alex
 *
 */
public class TimeStamp
{
	/**
	 * Timestamp value
	 */
	private Date value;

	public Date getValue() {
		return value;
	}

	public void setValue(Date value) {
		this.value = value;
	}
}
