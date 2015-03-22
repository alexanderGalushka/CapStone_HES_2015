package edu.harvard.cscie99.adam.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import edu.harvard.cscie99.adam.profile.User;

/**
 * 
 * @author Gerson
 *
 */
@Entity
public class Comment implements Serializable {
	
	/**
	 * Init version
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
	private int id;
	
	@ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User user;
	
	@ManyToOne(targetEntity = Plate.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "plate_id")
    private Plate plate;
	
	@ManyToOne(targetEntity = PlateResult.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "plate_result_id")
    private PlateResult plateResult;
	
	@ManyToOne(targetEntity = WellResult.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "well_result_id")
    private WellResult wellResult;
	
	@ManyToOne(targetEntity = Project.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    private Project project;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "tags")
	private String tags;
	
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
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public Plate getPlate() {
		return plate;
	}
	public void setPlate(Plate plate) {
		this.plate = plate;
	}
	public PlateResult getPlateResult() {
		return plateResult;
	}
	public void setPlateResult(PlateResult plateResult) {
		this.plateResult = plateResult;
	}
	public WellResult getWellResult() {
		return wellResult;
	}
	public void setWellResult(WellResult wellResult) {
		this.wellResult = wellResult;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}

}
