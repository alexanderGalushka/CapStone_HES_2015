package edu.harvard.cscie99.adam.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import edu.harvard.cscie99.adam.model.Compound;
import edu.harvard.cscie99.adam.model.Project;
import edu.harvard.cscie99.adam.profile.User;

/**
 * 
 * @author Gerson
 *
 */
@Component
public class ProjectService {
	

	@Autowired
    private SessionFactory sessionFactory;

	//TODO remove
	public boolean checkUserAccess(String user, Integer plateId, String service) {
		return true;
	}
	
	public Project updateProject(Project project){
		try{
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.merge(project);
			session.getTransaction().commit();
			session.close();
			
			return project;
		}
		catch (Exception ex){
			return null;
		}
	}
	
	public List<Project> list(){
		
		Session session = sessionFactory.openSession();
		List<Project> projects = session.createCriteria(Project.class).list();
		
		return projects;
	}
	
	public boolean deleteProject(Project project){
		
		try{
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.delete(project);
			session.getTransaction().commit();
			session.close();
			return true;
		}
		catch (Exception ex){
			return false;
		}
	}
	
	public Project createProject(Project newProject){
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(newProject);
		session.getTransaction().commit();
		session.close();
		
		return newProject;
	}
	
	public Project retrieveProject(int projectId){

		Session session = sessionFactory.openSession();
		Project project = (Project) session.get(Project.class, projectId);
		project.getAllMeasuredValues();
		project.getComments();
		project.getCollaborators();
		session.close();
		
		return project;
	}
	
	public Set<Project> listMyProjects()
	{
		
		Set<Project> myProjects = null;
		//TODO
		
		// query DB or call the User method?
		
		
		// access to the User through the session...
		
		Object userObject = AuthenticationService.getInstance().getSession().getAttribute("user");
		
		if (userObject != null)
		{
			User user  = (User) userObject;
			
			myProjects = user.getProjects();
		}
		
		return myProjects;
	}
	
	public Set<Project> listOthersProjects()
	{
		//TODO
		return null;
	}
	
}
