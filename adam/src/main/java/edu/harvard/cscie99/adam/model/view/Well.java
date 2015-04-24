//package edu.harvard.cscie99.adam.model.view;
//
//import java.io.Serializable;
//import java.util.HashMap;
//
//import javax.persistence.Entity;
//
//import edu.harvard.cscie99.adam.model.Well.ControlType;
//
///**
// * 
// * @author Gerson
// *
// */
//@Entity
//public class Well implements Serializable {
//
//	/**
//	 * Initial version
//	 */
//	private static final long serialVersionUID = 1L;
//
//	private Integer id;
//
//	private int row;
//
//	private int col;
//
//	private ControlType controlType;
//
//	private boolean ifValid = true;
//
//	private HashMap<String, String> labels;
//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public int getRow() {
//		return row;
//	}
//
//	public void setRow(int row) {
//		this.row = row;
//	}
//
//	public int getCol() {
//		return col;
//	}
//
//	public void setCol(int col) {
//		this.col = col;
//	}
//
//	public ControlType getControlType() {
//		return controlType;
//	}
//
//	public void setControlType(ControlType controlType) {
//		this.controlType = controlType;
//	}
//
//	public boolean isIfValid() {
//		return ifValid;
//	}
//
//	public void setIfValid(boolean ifValid) {
//		this.ifValid = ifValid;
//	}
//
//	public HashMap<String, String> getLabels() {
//		return labels;
//	}
//
//	public void setLabels(HashMap<String, String> labels) {
//		this.labels = labels;
//	}
//
//}
