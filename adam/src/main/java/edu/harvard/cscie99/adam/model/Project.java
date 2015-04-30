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

import edu.harvard.cscie99.adam.profile.User;

/**
 * Project class
 * 
 * Represents one project in Adam system.
 * The project is also an experiment, and support multiple plates.
 * It also has an owner and collaborators that can interact (add plates, edit results) with the project.
 * 
 * @author Gerson
 *
 */
@Entity
public class Project implements Serializable {
	
	public enum ProjectType {BIOLOGICAL, PHARMACEUTICAL}
	
	/**
	 * Initial version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Auto-generated project key
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "project_id")
	private Integer id;
	
	/**
	 * Project name
	 */
	@Column(name = "name")
	private String name;
	
	/**
	 * Project's description
	 */
	@Column(name = "description")
	private String description;
	
	/**
	 * Project type (eg. Biological, Pharmaceutical)
	 */
	@Column(name = "project_type")
	private String type;
	
	/**
	 * Project creation date
	 */
	@Column(name = "creation_date")
	private String creationDate;
	
	/**
	 * Project owner: user that initially created the project
	 */
	@Column(name = "owner")
	private String owner;
	
	/**
	 * Collaborators
	 * 
	 * List of users that collaborate to Project in any form.
	 */
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	  @JoinTable(
	      name="project_collab",
	      joinColumns={@JoinColumn(name="project_id", referencedColumnName="project_id")},
	      inverseJoinColumns={@JoinColumn(name="user_id", referencedColumnName="user_id")})
	private List<User> collaborators;

	/**
	 * List of project tags (eg: "cancer cure")
	 */
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	  @JoinTable(
	      name="project_tags",
	      joinColumns={@JoinColumn(name="project_id", referencedColumnName="project_id")},
	      inverseJoinColumns={@JoinColumn(name="tag_id", referencedColumnName="tag_id")})
	private List<Tag> tags;
	
	/**
	 * isPublic: indicates if the project is in public domain
	 */
	@Column(name = "public")
	private boolean isPublic;
	
	/**
	 * Project's label: metadata associated to project
	 */
	@Column(name = "label")
	private String label;
	
	/**
	 * Collection of plates
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Plate> plates;
	
	/**
	 * Constructor
	 * 
	 * Initializing lists
	 */
	public Project(){
		plates = new ArrayList<Plate>();
		tags = new ArrayList<Tag>();
		collaborators = new ArrayList<User>();
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
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<Plate> getPlates() {
		return plates;
	}

	public void setPlates(List<Plate> plates) {
		this.plates = plates;
	}
	

	public List<User> getCollaborators() {
		return collaborators;
	}

	public void setCollaborators(List<User> collaborators) {
		this.collaborators = collaborators;
	}

	/**
	 * Custom implementation of equals method.
	 * Allow proper handling of projects sets by their logic key (id field)
	 */
	@Override
	public boolean equals(Object obj){
		
		if (obj == null){
			return false;
		}
		if (!(obj instanceof Project)){
			return false;
		}
		Project other = (Project) obj;
		if (other.getId() != getId()){
			return false;
		}
		
		return true;
	}
	
	/**
	 * Custom implementation of hashcode
	 */
	@Override
	public int hashCode(){
		return 31;
	}

}
