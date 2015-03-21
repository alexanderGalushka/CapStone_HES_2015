package edu.harvard.cscie99.adam.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.harvard.cscie99.adam.profile.User;

public class Project implements Serializable {
	
	public enum ProjectType {BIOLOGICAL, PHARMACEUTICAL}
	
	/**
	 * Initial version
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String description;
	private List<Compound> compounds;
	private String type;
	private Date creationDate;
	private User owner;
	private List<User> collaborators;
	private List<String> tags;
	private List<String> comments;
	private boolean isPublic;
	
	public Project(){
		collaborators = new ArrayList<User>();
		tags = new ArrayList<String>();
		comments = new ArrayList<String>();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
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
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public List<String> getComments() {
		return comments;
	}
	public void setComments(List<String> comments) {
		this.comments = comments;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Compound> getCompounds() {
		return compounds;
	}

	public void setCompounds(List<Compound> compounds) {
		this.compounds = compounds;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
