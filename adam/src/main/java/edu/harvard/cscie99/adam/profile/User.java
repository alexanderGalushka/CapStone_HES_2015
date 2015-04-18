package edu.harvard.cscie99.adam.profile;

import java.util.List;
import java.util.Set;

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
import javax.persistence.OneToMany;

import org.hibernate.annotations.OrderBy;

import edu.harvard.cscie99.adam.model.Project;

/**
 * 
 * @author Gerson
 *
 */
@Entity
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
	private int id;
	
//	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
//	@JoinColumn(name="project_id")
	
//	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private Set<Project> projects;
	
//	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
//	@JoinColumn(name="project_id")
//	private Set<Project> projectsCollaboration;
	
    @Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "security_question")
	private String securityQuestion;
	
	@Column(name = "security_answer")
	private String securityAnswer;
	
//	@ManyToMany
//	  @JoinTable(
//	      name="user_role",
//	      joinColumns={@JoinColumn(name="user_id", referencedColumnName="user_id")},
//	      inverseJoinColumns={@JoinColumn(name="role_id", referencedColumnName="role_id")})
//	private List<Role> roles;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSecurityQuestion() {
		return securityQuestion;
	}
	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}
	public String getSecurityAnswer() {
		return securityAnswer;
	}
	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
