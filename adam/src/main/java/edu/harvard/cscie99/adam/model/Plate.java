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
	
	
	/**
	 * Plate with no controls is "PLAIN"
	 * Plate with only negative controls is "SEMI"
	 * Plate with both positive and negative controls is "FULL"
	 */
	public enum PlateType {PLAIN, SEMI, FULL}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "plate_id")
	private int id;
	
//	@ManyToOne(targetEntity = Project.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private Project project;
	
	@Column(name = "numberOfRows")
	private Integer numberOfRows;
	
	@Column(name = "numberOfColumns")
	private Integer numberOfColumns;
	
	@Column(name = "barcode")
	private String barcode;

	@Column(name = "tags")
	private String tags;
	
	@Column(name = "label")
	private String label;
	
	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private List<WellLabel> wellLabels;
	
	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Well> wells;
	
	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<ResultSnapshot> results;
	
	@ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    private User owner;
	
//	@ManyToMany 
//	@JoinTable(
//			name="plate_collab",
//			joinColumns={@JoinColumn(name="plate_id", referencedColumnName="plate_id")},
//			inverseJoinColumns={@JoinColumn(name="user_id", referencedColumnName="user_id")})
//	private List<User> collaborators;
	
	public Plate(){
		wells = new ArrayList<Well>();
		results = new ArrayList<ResultSnapshot>();
		wellLabels = new ArrayList<WellLabel>();
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
//	public String getDescription() {
//		return description;
//	}
//	public void setDescription(String description) {
//		this.description = description;
//	}
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
//	public List<User> getCollaborators() {
//		return collaborators;
//	}
//	public void setCollaborators(List<User> collaborators) {
//		this.collaborators = collaborators;
//	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<WellLabel> getWellLabels() {
		return wellLabels;
	}

	public void setWellLabels(List<WellLabel> wellLabels) {
		this.wellLabels = wellLabels;
	}
	
	public Well getWell(int row, int column){
		for (Well well : wells){
			if (well.getRow() == row && well.getCol() == column){
				return well;
			}
		}
		return null;
	}
	
	public List<ResultSnapshot> getResults() {
		return results;
	}

	public void setResults(List<ResultSnapshot> results) {
		this.results = results;
	}
	
	public Integer getNumberOfRows() {
		return numberOfRows;
	}

	public void setNumberOfRows(Integer numberOfRows) {
		this.numberOfRows = numberOfRows;
	}

	public Integer getNumberOfColumns() {
		return numberOfColumns;
	}

	public void setNumberOfColumns(Integer numberOfColumns) {
		this.numberOfColumns = numberOfColumns;
	}
	
	@Override
	public boolean equals(Object obj){
		
		if (obj == null){
			return false;
		}
		if (!(obj instanceof Plate)){
			return false;
		}
		Plate other = (Plate) obj;
		if (other.getId() != getId()){
			return false;
		}
		
		return true;
	}
	
	@Override
	public int hashCode(){
		return 17;
	}
//
//	public Project getProject() {
//		return project;
//	}
//
//	public void setProject(Project project) {
//		this.project = project;
//	}

//	public Project getProject() {
//		return project;
//	}
//
//	public void setProject(Project project) {
//		this.project = project;
//	}
	
}
