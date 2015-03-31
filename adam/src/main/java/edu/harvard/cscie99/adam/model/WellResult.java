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
	
	// accounted for pharmacokinetics
	@Column(name = "time")
	private Date time;
	
	// measurement type 
	@Column(name = "label")
	private String label;
		
	@OneToMany(mappedBy = "wellResult", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Comment> comments;
	
	@OneToMany(mappedBy = "wellResult", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Measurement> measurements;
	
	@ManyToOne(targetEntity = PlateResult.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private PlateResult plateResult;
	
	public Set<Measurement> getMeasurements() {
		return measurements;
	}
	public void setMeasurements(Set<Measurement> measurements) {
		this.measurements = measurements;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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

	public PlateResult getPlateResult() {
		return plateResult;
	}
	public void setPlateResult(PlateResult plateResult) {
		this.plateResult = plateResult;
	}

}
