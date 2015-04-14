package edu.harvard.cscie99.adam.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
 * 
 * @author Gerson
 *
 */
@Entity
public class Well implements Serializable {

	/**
	 * Initial version
	 */
	private static final long serialVersionUID = 1L;

	public enum ControlType {
		POS, NEG, COMP, EMPTY
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "well_id")
	private Integer id;

	@Column(name = "row")
	private int row;

	@Column(name = "col")
	private int col;

	// add each label to this string separated by space character
	// @Column(name = "labels")
	// private String labels;

	@Column(name = "control_type")
	private ControlType controlType;

	@Column(name = "if_valid")
	private boolean ifValid = true;

	// @Column(name = "substrate")
	// private Substrate substare;

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "well_label_values", joinColumns = { @JoinColumn(name = "well_id", referencedColumnName = "well_id") }, inverseJoinColumns = { @JoinColumn(name = "well_label_id", referencedColumnName = "well_label_id") })
	private List<WellLabel> wellLabels;

	// @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	// private List<Compound> compounds;

	@OneToMany(fetch = FetchType.LAZY)
	private List<ResultSnapshot> resultSnapshots;

	public Well() {
		// compounds = new ArrayList<Compound>();
		wellLabels = new ArrayList<WellLabel>();
		resultSnapshots = new ArrayList<ResultSnapshot>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	// public String getLabels() {
	// return labels;
	// }
	// public void setLabels(String labels) {
	// this.labels = labels;
	// }
	// public Integer getColor() {
	// return color;
	// }
	// public void setColor(Integer color) {
	// this.color = color;
	// }
	public ControlType getControlType() {
		return controlType;
	}

	public void setControlType(ControlType controlType) {
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

	// public Substrate getSubstare() {
	// return substare;
	// }
	// public void setSubstare(Substrate substare) {
	// this.substare = substare;
	// }
	// public List<Compound> getCompounds() {
	// return compounds;
	// }
	// public void setCompounds(List<Compound> compounds) {
	// this.compounds = compounds;
	// }

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

}
