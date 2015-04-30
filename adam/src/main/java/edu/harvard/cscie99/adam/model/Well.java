package edu.harvard.cscie99.adam.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * Well class
 * 
 * Represents a single well in the plate
 * 
 * @author Gerson
 *
 */
@Entity
public class Well implements Serializable, Comparable<Well> {

	/**
	 * Initial version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Auto-generated DB Key
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "well_id")
	private Integer id;

	/**
	 * Well's row coordinate
	 */
	@Column(name = "row")
	private int row;

	/**
	 * Well's column coordinate
	 */
	@Column(name = "col")
	private int col;
	
	/**
	 * Well's control type (eg: NEGATIVE, POSITIVE)
	 */
	@Column(name = "control_type")
	private String controlType;

	/**
	 * Well's validity indicator 
	 */
	@Column(name = "if_valid")
	private boolean ifValid = true;

	/**
	 * List of well labels associated to Wells.
	 * For each well label in the plate, well wil have a WellLabel object with name and value
	 */
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "well_label_values", joinColumns = { @JoinColumn(name = "well_id", referencedColumnName = "well_id") }, inverseJoinColumns = { @JoinColumn(name = "well_label_id", referencedColumnName = "well_label_id") })
	private List<WellLabel> wellLabels;

	/**
	 * Results associated to Well
	 */
	@OneToMany(fetch = FetchType.LAZY)
	private List<ResultSnapshot> resultSnapshots;

	public Well() {
		wellLabels = new ArrayList<WellLabel>();
		resultSnapshots = new ArrayList<ResultSnapshot>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getControlType() {
		return controlType;
	}

	public void setControlType(String controlType) {
		this.controlType = controlType;
	}

	public List<ResultSnapshot> getResultSnapshots() {
		return resultSnapshots;
	}

	public void setResultSnapshots(List<ResultSnapshot> resultSnapshot) {
		this.resultSnapshots = resultSnapshot;
	}

	public boolean getIfValid() {
		return ifValid;
	}

	public void setIfValid(boolean ifValid) {
		this.ifValid = ifValid;
	}
	
	public List<WellLabel> getWellLabels() {
		return wellLabels;
	}

	public void setWellLabels(List<WellLabel> wellLabels) {
		this.wellLabels = wellLabels;
	}

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

	/**
	 * Allow well sorting per (1) row and (2) column
	 */
	@Override
	public int compareTo(Well other) {
		if (other != null){
			if (getRow() == other.getRow() && getCol() == other.getCol()){
				return 0;
			}
			else if (getRow() < other.getRow()){
				return -1;
			}
			else if (getRow() > other.getRow()){
				return 1;
			}
			else {
				if (getCol() < other.getCol()){
					return -1;
				}
				else{
					return 1;
				}
			}
		}
		return -1;
	}

}
