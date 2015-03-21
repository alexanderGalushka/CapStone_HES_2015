package edu.harvard.cscie99.adam.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 
 * @author Gerson
 *
 */
@Entity
public class Sample implements Serializable{
	
	/**
	 * Initial version
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "plate_result_id")
	private int id;
	
	@Column(name = "label")
	private String label;
	
	@ManyToOne(targetEntity = Compound.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "compound_id")
    private Compound compound;
	
	@ManyToOne(targetEntity = Substrate.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "substrate_id")
	private Substrate substrate;
	
	@Column(name = "dilution")
	private double dilution;
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Compound getCompound() {
		return compound;
	}
	public void setCompound(Compound compound) {
		this.compound = compound;
	}
	public Substrate getSubstrate() {
		return substrate;
	}
	public void setSubstrate(Substrate substrate) {
		this.substrate = substrate;
	}
	public double getDilution() {
		return dilution;
	}
	public void setDilution(double dilution) {
		this.dilution = dilution;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
