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

@Entity
public class WellLabel implements Serializable {

	/**
	 * Init version
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "well_label_id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "value")
	private String value;
	
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
