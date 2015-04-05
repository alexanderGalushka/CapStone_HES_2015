//package edu.harvard.cscie99.adam.model;
//
//import java.io.Serializable;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
///**
// * 
// * @author Gerson
// *
// */
//@Entity
//public class Compound implements Serializable{
//	
//	/**
//	 * Initial version
//	 */
//	private static final long serialVersionUID = 1L;
//	
//	@Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "compound_id")
//	private int id;
//	
//	@Column(name = "name")
//	private String name;
//	
//	@Column(name = "dilution")
//	private double dilution;
//	
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	
//	public double getDilution() {
//		return dilution;
//	}
//	public void setDilution(double dilution) {
//		this.dilution = dilution;
//	}
//
//}
