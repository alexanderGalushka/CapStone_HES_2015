package edu.harvard.cscie99.adam.model;

public class PlateValidationContainer
{
	private Integer projectId;
	private Integer plateId;
	private boolean ifValid;	
	
	public Integer getPlateId() {
		return plateId;
	}
	public void setPlateId(Integer plateId) {
		this.plateId = plateId;
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
