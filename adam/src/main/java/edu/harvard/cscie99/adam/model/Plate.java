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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import edu.harvard.cscie99.adam.profile.User;

/**
 * 
 * Plate model class
 * 
 * This class represents a Plate in the system, either imported by CSV file 
 * or edited on Plate Editor screen. This object is persisted in DB.
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

	/**
	 * id field
	 * Represent the auto-generated Id in DB Plate table
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "plate_id")
	private int id;
	
	/**
	 * Project associated to Plate
	 */
	private String projectId;

	/**
	 * Plate name
	 */
	@Column(name = "name")
	private String name;
	
	/**
	 * Protocol
	 */
	@Column(name = "protocol_id")
	private String protocolId;
	
	/**
	 * Number of rows in plate
	 */
	@Column(name = "numberOfRows")
	private Integer numberOfRows;
	
	/**
	 * Date of plate creation
	 */
	@Column(name = "creation_date")
	private String creationDate;
	
	/**
	 * Number of columns in plate
	 */
	@Column(name = "numberOfColumns")
	private Integer numberOfColumns;
	
	/**
	 * Plate's barcode
	 */
	@Column(name = "barcode")
	private String barcode;

	/**
	 * Tags associated to Plate
	 */
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	  @JoinTable(
	      name="project_tags",
	      joinColumns={@JoinColumn(name="plate_id", referencedColumnName="plate_id")},
	      inverseJoinColumns={@JoinColumn(name="tag_id", referencedColumnName="tag_id")})
	private List<Tag> tags;
	
	/**
	 * Plate's label
	 */
	@Column(name = "label")
	private String label;
	
	/**
	 * List of labels associated to every wells on plate
	 */
	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private List<WellLabel> wellLabels;
	
	/**
	 * List of Wells
	 */
	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Well> wells;
	
	/**
	 * List of results 
	 */
	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<ResultSnapshot> results;
	
	/**
	 * List of control types from Wells in the plate (eg. NEGATIVE, POSITIVE)
	 */
	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<ControlType> controlTypes;
	
	/**
	 * Plate owner
	 */
	@Column(name = "owner")
    private String owner;
	
	/**
	 * Constructor
	 * Initialize lists
	 */
	public Plate(){
		wells = new ArrayList<Well>();
		results = new ArrayList<ResultSnapshot>();
		wellLabels = new ArrayList<WellLabel>();
		controlTypes = new ArrayList<ControlType>();
	}
	
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	public List<Well> getWells() {
		return wells;
	}
	public void setWells(List<Well> wells) {
		this.wells = wells;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
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

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProtocolId() {
		return protocolId;
	}

	public void setProtocolId(String protocolId) {
		this.protocolId = protocolId;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public List<ControlType> getControlTypes() {
		return controlTypes;
	}

	public void setControlTypes(List<ControlType> controlTypes) {
		this.controlTypes = controlTypes;
	}

//	public Project getProject() {
//		return project;
//	}
//
//	public void setProject(Project project) {
//		this.project = project;
//	}
	
}
