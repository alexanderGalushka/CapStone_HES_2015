package edu.harvard.cscie99.adam.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import edu.harvard.cscie99.adam.profile.User;

/**
 * 
 * @author Gerson
 *
 */
public class DataPointMetadata implements Serializable {
	
	/**
	 * Init version
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private User user;
	private Date createdAt;
	private String comment;
	private List<String> tags;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}

}
