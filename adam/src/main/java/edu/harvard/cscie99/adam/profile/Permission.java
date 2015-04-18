//package edu.harvard.cscie99.adam.profile;
//
//import java.util.Date;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//
//@Entity
//public class Permission {
//	
//	@Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id")
//	private int id;
//	
//	@Column(name = "service_name")
//	private String service;
//	
//	@Column(name = "dateCreated")
//	private Date dateCreated;
//	
//	@ManyToOne(targetEntity = Role.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	private Role role;
//	
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	public String getService() {
//		return service;
//	}
//	public void setService(String service) {
//		this.service = service;
//	}
//	public Date getDateCreated() {
//		return dateCreated;
//	}
//	public void setDateCreated(Date dateCreated) {
//		this.dateCreated = dateCreated;
//	}
//	public Role getRole() {
//		return role;
//	}
//	public void setRole(Role role) {
//		this.role = role;
//	}
//
//}
