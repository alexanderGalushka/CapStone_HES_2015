package edu.harvard.cscie99.adam.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * WellLabel class
 * 
 * This class is a container of well name and value. 
 * 
 * It is used by Plate
 * to enumerate the possible label names each well in a plate can assume.
 * 
 * It is also used in Well to store the well value (one value per label name in Plate)
 * @author Adam
 *
 */
@Entity
public class WellLabel implements Serializable {

	/**
	 * Init version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Auto-generated DB key
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "well_label_id")
	private int id;
	
	/**
	 * Well label Name. This information is setup in the Plate
	 */
	@Column(name = "name")
	private String name;
	
	/**
	 * Well label Value. This information is setup in the Well
	 */
	@Column(name = "value")
	private String value;
	
	/**
	 * Plate which contains the Well Label
	 */
	@ManyToOne(targetEntity = Plate.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Plate plate;

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
