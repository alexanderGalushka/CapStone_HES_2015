package edu.harvard.cscie99.adam.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * 
 * @author Gerson
 *
 */
@Entity
public class PlateResult implements Serializable{
	
	/**
	 * Initial version
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "plate_result_id")
	private int id;
	
	@OneToOne(mappedBy = "plate_id")
    private Plate plate;
	
	@Column(name = "creation_date")
	private Date creationDate;
	
	@OneToMany(mappedBy = "comments", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Comment> comments;
	
	@OneToMany(mappedBy = "well_result", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<WellResult> wells;
	
	public PlateResult(){
		comments = new ArrayList<Comment>();
		wells = new ArrayList<WellResult>();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Plate getPlate() {
		return plate;
	}
	public void setPlate(Plate plate) {
		this.plate = plate;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public List<WellResult> getWells() {
		return wells;
	}
	public void setWells(List<WellResult> wells) {
		this.wells = wells;
	}

}
