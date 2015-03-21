package edu.harvard.cscie99.adam.model;

import java.util.List;

import edu.harvard.cscie99.adam.profile.User;

public class Plate extends Template{
	
	/**
	 * Initial version
	 */
	private static final long serialVersionUID = 1L;
	
	public enum PlateType {STANDARD, DEEP, PERFORATED}

	private int id;
	private String barcode;
	private String description;
	private String protocol;
	private List<String> tags;
	private List<Well> wells;
	private User owner;
	private List<User> collaborators;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public List<Well> getWells() {
		return wells;
	}
	public void setWells(List<Well> wells) {
		this.wells = wells;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public List<User> getCollaborators() {
		return collaborators;
	}
	public void setCollaborators(List<User> collaborators) {
		this.collaborators = collaborators;
	}
	
}
