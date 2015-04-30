package edu.harvard.cscie99.adam.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * ControlType clas
 * 
 * Represents the control type associated to wells.
 * Can assume POSITIVE controls, NEGATIVE controls or custom type (created by user)
 * 
 * @author Gerson
 *
 */
@Entity
public class ControlType implements Serializable {
	
	/**
	 * Init version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Auto-generated key
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "control_type_id")
	private int id;
	
	/**
	 * Control type name (eg. POSITIVE)
	 */
	@Column(name = "name")
	private String name;
	
	/**
	 * Display char to show in Plate Editor screen
	 */
	@Column(name = "displayChar")
	private String displayChar;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayChar() {
		return displayChar;
	}

	public void setDisplayChar(String displayChar) {
		this.displayChar = displayChar;
	}

}
