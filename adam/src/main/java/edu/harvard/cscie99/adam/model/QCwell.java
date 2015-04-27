package edu.harvard.cscie99.adam.model;


public class QCwell 
{
	private int row;

	private int col;

	private String controlType;
	
	private boolean ifValid = true;
	
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
