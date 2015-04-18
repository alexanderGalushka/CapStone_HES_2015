package edu.harvard.cscie99.adam.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import edu.harvard.cscie99.adam.model.Plate;
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
	
	@Autowired
	private PlateService plateService;
	
	public Project updateProject(Project project){
		
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			
			for (Plate plate : project.getPlates()){
				session.saveOrUpdate(plate);
			}
			
			session.merge(project);
			session.getTransaction().commit();
			
			return project;
		}
		catch (Exception ex){
			return null;
		}
		finally{
			session.close();	
		}
	}
	
	public List<Project> list(){
		
		Session session = sessionFactory.openSession();
		List<Project> projects = null;
		
		try{
			projects = session.createCriteria(Project.class).list();
			for (Project project : projects){
				loadProject(project);
			}
		}
		finally{
			session.close();	
		}
		
		return projects;
	}
	
	public boolean deleteProject(Project project){
		
		Session session = sessionFactory.openSession();
		
		try{
			session.beginTransaction();
			session.delete(project);
			session.getTransaction().commit();
			return true;
		}
		catch (Exception ex){
			return false;
		}
		finally{
			session.close();	
		}
	}
	
	public Project createProject(Project newProject){
		
		newProject.setCreationDate(new Date());
		Session session = sessionFactory.openSession();
		
		try{
			session.beginTransaction();
			session.save(newProject);
			session.getTransaction().commit();
		}
		finally{
			session.close();	
		}
		
		return newProject;
	}
	
	public Project retrieveProject(int projectId){

		Session session = sessionFactory.openSession();
		Project project = null;
		
		try{
			project = (Project) session.get(Project.class, projectId);
			loadProject(project);
			if (!project.getPlates().isEmpty()){
				
			}
		}
		finally{
			session.close();	
		}
		return project;
	}
	
	public void loadProject(Project project){
		if (!project.getPlates().isEmpty()){
			for (Plate plate : project.getPlates()){
				plateService.loadPlate(plate);	
			}
			project.getOwner();
		}
	}
	
	public Set<Project> listMyProjects()
	{
		
		Set<Project> myProjects = null;
		//TODO
		
		// query DB or call the User method?
		
		
		// access to the User through the session...
		
//		Object userObject = AuthenticationService.getInstance().getSession().getAttribute("user");
		
//		if (userObject != null)
//		{
//			User user  = (User) userObject;
//			
//			myProjects = user.getProjects();
//		}
//		
//		return myProjects;
		return null;
	}
	
	public Set<Project> listOthersProjects()
	{
		//TODO
		return null;
	}
	
}
