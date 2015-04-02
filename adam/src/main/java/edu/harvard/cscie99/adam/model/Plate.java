package edu.harvard.cscie99.adam.model;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import edu.harvard.cscie99.adam.profile.User;

/**
 * 
 * @author Gerson
 *
 */
@Entity
public class Plate implements Serializable{
	
	/**
	 * Initial version
	 */
	private static final long serialVersionUID = 1L;
	
	public enum PlateType {STANDARD, DEEP, PERFORATED}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "plate_id")
	private int id;
	
	@ManyToOne(targetEntity = Template.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Template template;
	
	@Column(name = "barcode")
	private String barcode;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "protocol")
	private String protocol;
	
	@Column(name = "tags")
	private String tags;
	
	@OneToMany(mappedBy = "plate", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Comment> comments;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Well> wells;
	
	@ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User owner;
	
	@OneToMany(mappedBy = "plate", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<PlateResult> plateResults;
	
	@ManyToMany 
	@JoinTable(
			name="plate_collab",
			joinColumns={@JoinColumn(name="plate_id", referencedColumnName="plate_id")},
			inverseJoinColumns={@JoinColumn(name="user_id", referencedColumnName="user_id")})
	private List<User> collaborators;
	
	public Plate(){
		wells = new ArrayList<Well>();
		collaborators = new ArrayList<User>();
		comments = new ArrayList<Comment>();
	}
	
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
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
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
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

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Set<PlateResult> getPlateResults() {
		return plateResults;
	}

	public void setPlateResults(Set<PlateResult> plateResults) {
		this.plateResults = plateResults;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
