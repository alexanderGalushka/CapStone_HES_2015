package edu.harvard.cscie99.adam.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlateResult implements Serializable{
	
	/**
	 * Initial version
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private Plate plate;
	private Date creationDate;
	private Date lastUpdate;
	private List<String> comments;
	private List<WellResult> wells;
	
	public PlateResult(){
		comments = new ArrayList<String>();
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
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public List<String> getComments() {
		return comments;
	}
	public void setComments(List<String> comments) {
		this.comments = comments;
	}
	public List<WellResult> getWells() {
		return wells;
	}
	public void setWells(List<WellResult> wells) {
		this.wells = wells;
	}

}
