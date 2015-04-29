package edu.harvard.cscie99.adam.model;

public class WellValidationContainer
{
	private Integer projectId;
	private Integer plateId;
	private Integer rowNum;
	private Integer colNum;
	private boolean ifValid;
	
	public Integer getPlateId() {
		return plateId;
	}
	public void setPlateId(Integer plateId) {
		this.plateId = plateId;
	}
	public Integer getRowNum() {
		return rowNum;
	}
	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}
	public Integer getColNum() {
		return colNum;
	}
	public void setColNum(Integer colNum) {
		this.colNum = colNum;
	}
	public boolean getIfValid() {
		return ifValid;
	}
	public void setIfValid(boolean ifValid) {
		this.ifValid = ifValid;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

}
