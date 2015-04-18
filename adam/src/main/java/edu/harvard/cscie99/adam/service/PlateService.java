package edu.harvard.cscie99.adam.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.harvard.cscie99.adam.model.Measurement;
import edu.harvard.cscie99.adam.model.Plate;
import edu.harvard.cscie99.adam.model.ResultSnapshot;
import edu.harvard.cscie99.adam.model.Well;
import edu.harvard.cscie99.adam.model.WellLabel;

/**
 * 
 * @author Gerson
 *
 */
@Component
public class PlateService {
	

	@Autowired
    private SessionFactory sessionFactory;
	
	public List<Plate> listPlates(){
		Session session = sessionFactory.openSession();
		HashSet<Plate> uniquePlates = null;
		
		try{
			List<Plate> plateList = session.createCriteria(Plate.class).list();
			
			uniquePlates = new HashSet<Plate>();
			for (Plate plate : plateList){
				uniquePlates.add(plate);
			}
			
			for (Plate plate : uniquePlates){
				loadPlate(plate);
			}
		}
		finally {
			session.close();
		}
		
		return new ArrayList<Plate>(uniquePlates);
	}
	
	public Plate retrievePlate(int plateId){

		Session session = sessionFactory.openSession();
		Plate plate = null;
		try{
			plate = (Plate) session.get(Plate.class, plateId);
			loadPlate(plate);
		}
		finally {
			session.close();
		}
		
		return plate;
	}
	
	public Plate createPlate(Plate plate){

		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			
			for (Well well : plate.getWells()){
				for (WellLabel wellLabel : well.getWellLabels()){
					session.saveOrUpdate(wellLabel);
				}
				session.save(well);
			}
			
			for (WellLabel label : plate.getWellLabels()){
				session.save(label);
			}
			for (Well well : plate.getWells()){
				session.save(well);
			}
			session.save(plate);
			session.getTransaction().commit();
		}
		finally{
			session.close();	
		}
		
		return plate;
	}
	
	public boolean removePlate(Plate plate){

		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			session.delete(plate);
			session.getTransaction().commit();
		}
		finally{
			session.close();	
		}
		
		return true;
	}
	
	public Plate editPlate(Plate plate){

		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			session.merge(plate);
			session.getTransaction().commit();
		}
		finally{
			session.close();	
		}
		
		return plate;
	}
	
	public void loadPlate(Plate plate){
//		plate.getDataSet().isEmpty();
//		plate.getCollaborators().isEmpty();
		plate.getWellLabels().isEmpty();
		if (!plate.getWells().isEmpty()){
			for (Well well : plate.getWells()){
				well.getWellLabels().isEmpty();
				well.getResultSnapshots().isEmpty();
			}
		}
		if (!plate.getResults().isEmpty()){
			for (ResultSnapshot result : plate.getResults()){
				if (!result.getMeasurements().isEmpty()){
					for (Measurement measure : result.getMeasurements()){
						measure.getColumn();
						measure.getMeasurementType();
						measure.getRow();
						measure.getValue();
					}
				}
				result.getTime();
			}
		}
		plate.getOwner();
	}
	
//	public List<Plate> search(Map<String, Object> parameters){
//		//TODO
//		//list plates from DB
//		
//		List<Plate> plates = new ArrayList<Plate>();
//		
//		for (int i =0; i<5; i++){
//			Plate plate = new Plate();
//			plate.setBarcode("1234");
//			plate.setId(1);
////			plate.setName("name");
//			
//			plates.add(plate);
//		}
//		
//		return plates;
//	}

}
