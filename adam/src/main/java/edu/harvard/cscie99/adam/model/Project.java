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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import edu.harvard.cscie99.adam.profile.User;

/**
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
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "project_id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
//	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private List<Compound> compounds;

//	@OneToMany(mappedBy = "substrate", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private List<Substrate> substrates;
	
	@Column(name = "project_type")
	private String type;
	
	@Column(name = "creation_date")
	private Date creationDate;
	
//	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
	
	@ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    private User owner;
	
//	@ManyToMany
//	@LazyCollection(LazyCollectionOption.FALSE)
//	  @JoinTable(
//	      name="project_collab",
//	      joinColumns={@JoinColumn(name="project_id", referencedColumnName="project_id")},
//	      inverseJoinColumns={@JoinColumn(name="user_id", referencedColumnName="user_id")})
//	private List<User> collaborators;
	
	@Column(name="tags")
	private String tags;
	
//	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private List<Comment> comments;
	
	@Column(name = "public")
	private boolean isPublic;
	
	@Column(name = "protocol_id")
	private String protocolId;
	
	@Column(name = "label")
	private String label;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Plate> plates;
	
	public Project(){
		plates = new ArrayList<Plate>();
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
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
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

//	public List<Compound> getCompounds() {
//		return compounds;
//	}
//
//	public void setCompounds(List<Compound> compounds) {
//		this.compounds = compounds;
//	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProtocolId() {
		return protocolId;
	}

	public void setProtocolId(String protocolId) {
		this.protocolId = protocolId;
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
	
	@Override
	public int hashCode(){
		return 31;
	}
}
