package edu.harvard.cscie99.adam.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * 
 * @author Gerson
 *
 */
@Entity
public class Well implements Serializable{
	
	/**
	 * Initial version
	 */
	private static final long serialVersionUID = 1L;
	
	public enum ControlType {POSITIVE, NEGATIVE, COMPOUND, EMPTY}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "well_id")
	private Integer id;
	
	@Column(name = "plate_position_x")
	private int platePositionX;
	
	@Column(name = "plate_position_y")
	private int platePositionY;
	
	// add each label to this string separated by space character
	@Column(name = "labels")
	private String labels;
	
	@Column(name = "color")
	private Integer color;
	
	@Column(name = "control_type")
	private ControlType controlType;
	
	@Column(name = "if_valid")
	private boolean ifValid;
	
	@Column(name = "substrate")
	private Substrate substare; 
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Compound> compounds;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ResultSnapshot> resultSnapshots;
	
	// only used when the data is uploaded
	@OneToMany(mappedBy = "well", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;
	
	public Well(){
		compounds = new ArrayList<Compound>();
		resultSnapshots = new ArrayList<ResultSnapshot>();
		comments = new ArrayList<Comment>();
	}
	
	public int getPlatePositionX() {
		return platePositionX;
	}
	public void setPlatePositionX(int platePositionX) {
		this.platePositionX = platePositionX;
	}
	public int getPlatePositionY() {
		return platePositionY;
	}
	public void setPlatePositionY(int platePositionY) {
		this.platePositionY = platePositionY;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLabels() {
		return labels;
	}
	public void setLabels(String labels) {
		this.labels = labels;
	}
	public Integer getColor() {
		return color;
	}
	public void setColor(Integer color) {
		this.color = color;
	}
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
	
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public Substrate getSubstare() {
		return substare;
	}
	public void setSubstare(Substrate substare) {
		this.substare = substare;
	}
	public List<Compound> getCompounds() {
		return compounds;
	}
	public void setCompounds(List<Compound> compounds) {
		this.compounds = compounds;
	}

}
