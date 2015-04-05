package edu.harvard.cscie99.adam.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Component;

import edu.harvard.cscie99.adam.model.Plate;
import edu.harvard.cscie99.adam.model.Project;
import edu.harvard.cscie99.adam.model.Template;

/**
 * 
 * @author Gerson
 *
 */
@Component
public class PlateService {
	

	@Autowired
    private SessionFactory sessionFactory;
	
	public List<Template> listTemplates(){
		Session session = sessionFactory.openSession();
		List<Template> templateList = session.createCriteria(Template.class).list();
		
		return templateList;
	}
	
	public Template retrieveTemplate(int templateId){

		Session session = sessionFactory.openSession();
		Template template = (Template) session.get(Template.class, templateId);
		session.close();
		
		return template;
	}
	
	public boolean createTemplate(Template template){

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(template);
		session.getTransaction().commit();
		session.close();
		
		return true;
	}
	
	public boolean removeTemplate(Template template){

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(template);
		session.getTransaction().commit();
		session.close();
		
		return true;
	}
	
	public boolean editTemplate(Template template){

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.merge(template);
		session.getTransaction().commit();
		session.close();
		
		return true;
	}
	
	public List<Plate> listPlates(){
		Session session = sessionFactory.openSession();
		
//		Session session = SessionFactoryUtils.doGetSession(sessionFactory, false);
//		Session session = sessionFactory.getCurrentSession();
		List<Plate> plateList = session.createCriteria(Plate.class).list();
		
		for (Plate plate : plateList){
			plate.getAllMeasuredValues().isEmpty();
			plate.getCollaborators().isEmpty();
			plate.getComments().isEmpty();
			plate.getWellLabels().isEmpty();
			plate.getWells().isEmpty();
		}
		
		session.close();
		
		return plateList;
	}
	
	//TODO 
	public List<Plate> listPlates(int projectId){
		
		// lookup by specific project ID 
		
		Session session = sessionFactory.openSession();
		List<Plate> plateList = session.createCriteria(Plate.class).list();
		
		for (Plate plate : plateList){
			plate.getAllMeasuredValues();
			plate.getCollaborators();
			plate.getComments();
			plate.getWellLabels();
			plate.getWells();
		}
		
		session.close();
		
		return plateList;
	}
	
	
	public Plate retrievePlate(int plateId){

		Session session = sessionFactory.openSession();
		Plate plate = (Plate) session.get(Plate.class, plateId);
		
		plate.getAllMeasuredValues().isEmpty();
		plate.getCollaborators().isEmpty();
		plate.getComments().isEmpty();
		plate.getWellLabels().isEmpty();
		plate.getWells().isEmpty();
		
		session.close();
		
		return plate;
	}
	
	public Plate createPlate(Plate plate){

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(plate);
		session.getTransaction().commit();
		session.close();
		
		return plate;
	}
	
	public boolean removePlate(Plate plate){

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(plate);
		session.getTransaction().commit();
		session.close();
		
		return true;
	}
	
	public Plate editPlate(Plate plate){

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.merge(plate);
		session.getTransaction().commit();
		session.close();
		
		return plate;
	}
	
	
	
	public List<Plate> search(Map<String, Object> parameters){
		//TODO
		//list plates from DB
		
		List<Plate> plates = new ArrayList<Plate>();
		
		for (int i =0; i<5; i++){
			Plate plate = new Plate();
			plate.setBarcode("1234");
			plate.setId(1);
//			plate.setName("name");
			
			plates.add(plate);
		}
		
		return plates;
	}
	
	

}
