package edu.harvard.cscie99.adam.model;

/**
 * WellValidationContainer object
 * 
 * Model class used to transit information from frontend to QC services,
 * regarding validation of Wells (heat map screen)
 * 
 * @author Alex
 *
 */
public class WellValidationContainer
{
	/**
	 * Project Id 
	 */
	private Integer projectId;
	
	/**
	 * Plate Id
	 */
	private Integer plateId;
	
	/**
	 * Row number position
	 */
	private Integer rowNum;
	
	/**
	 * Col number position
	 */
	private Integer colNum;
	
	/**
	 * Indicator of valid/invalid well
	 */
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
