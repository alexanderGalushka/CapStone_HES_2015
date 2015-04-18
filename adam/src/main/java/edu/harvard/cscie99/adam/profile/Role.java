//package edu.harvard.cscie99.adam.profile;
//
//import java.util.List;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//
///**
// * 
// * @author Gerson
// *
// */
//@Entity
//public class Role {
//	
//	@Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "role_id")
//	private int id;
//	
//	@Column(name = "description")
//	private String description;
//	
//	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private List<Permission> permissions;
//	
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	public String getDescription() {
//		return description;
//	}
//	public void setDescription(String description) {
//		this.description = description;
//	}
//	public List<Permission> getPermissions() {
//		return permissions;
//	}
//	public void setPermissions(List<Permission> permissions) {
//		this.permissions = permissions;
//	}
//	
//}
