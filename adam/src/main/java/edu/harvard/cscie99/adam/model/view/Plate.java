package edu.harvard.cscie99.adam.model.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.harvard.cscie99.adam.model.Tag;
import edu.harvard.cscie99.adam.model.WellLabel;
/**
 * 
 * @author Gerson
 *
 */
public class Plate implements Serializable{
	
	/**
	 * Initial version
	 */
	private static final long serialVersionUID = 1L;
	
	public enum PlateType {PLAIN, SEMI, FULL}

	private int id;
	
	private String projectId;

	private String name;
	
	private String protocolId;
	
	private Integer numberOfRows;
	
	private String creationDate;
	
	private Integer numberOfColumns;
	
	private String barcode;

	private List<Tag> tags;
	
	private String label;
	
	private List<WellLabel> wellLabels;
	
	private List<HashMap<String, String>> wells;
	
	private String owner;
	
	public Plate(){
		wells = new ArrayList<HashMap<String, String>>();
		wellLabels = new ArrayList<WellLabel>();
		tags = new ArrayList<Tag>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Integer getNumberOfRows() {
		return numberOfRows;
	}

	public void setNumberOfRows(Integer numberOfRows) {
		this.numberOfRows = numberOfRows;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getNumberOfColumns() {
		return numberOfColumns;
	}

	public void setNumberOfColumns(Integer numberOfColumns) {
		this.numberOfColumns = numberOfColumns;
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

	public List<HashMap<String, String>> getWells() {
		return wells;
	}

	public void setWells(List<HashMap<String, String>> wells) {
		this.wells = wells;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
}
