package edu.harvard.cscie99.adam.model;

/**
 * QCwell class
 * 
 * Analogous to Well object, encapsulate well information with data quality control metricts (eg, valid/invalid indicator)
 * 
 * @author Alex
 *
 */
public class QCwell 
{
	/**
	 * Well row position
	 */
	private int row;

	/**
	 * Well col position
	 */
	private int col;

	/**
	 * Well control type
	 */
	private String controlType;
	
	/**
	 * Indicates valid/invalid wells
	 */
	private boolean ifValid = true;
	
	/**
	 * Well value
	 */
	private double value;

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public String getControlType() {
		return controlType;
	}

	public void setControlType(String controlType) {
		this.controlType = controlType;
	}

	public boolean isIfValid() {
		return ifValid;
	}

	public void setIfValid(boolean ifValid) {
		this.ifValid = ifValid;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}


	
}
