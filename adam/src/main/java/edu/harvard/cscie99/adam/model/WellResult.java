package edu.harvard.cscie99.adam.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import edu.harvard.cscie99.adam.profile.User;

/**
 * 
 * @author Gerson
 *
 */
@Entity
public class WellResult implements Serializable{
	
	/**
	 * Initial version
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "well_result_id")
	private int id;
	
	@Column(name = "position_x")
	private int positionX;
	
	@Column(name = "position_y")
	private int positionY;
	
	@Column(name = "value")
	private double value;
	
	@Column(name = "time")
	private Date time;
	
	@Column(name = "label")
	private String label;
	
	@ManyToOne(targetEntity = Well.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Well well;
	
	@OneToMany(mappedBy = "wellResult", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Comment> comments;
	
	@ManyToOne(targetEntity = PlateResult.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private PlateResult plateResult;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	public int getPositionX() {
		return positionX;
	}
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}
	public int getPositionY() {
		return positionY;
	}
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	public PlateResult getPlateResult() {
		return plateResult;
	}
	public void setPlateResult(PlateResult plateResult) {
		this.plateResult = plateResult;
	}
	public Well getWell() {
		return well;
	}
	public void setWell(Well well) {
		this.well = well;
	}

}
