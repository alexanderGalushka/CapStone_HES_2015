package edu.harvard.cscie99.adam.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.harvard.cscie99.adam.model.ControlType;
import edu.harvard.cscie99.adam.model.Measurement;
import edu.harvard.cscie99.adam.model.Plate;
import edu.harvard.cscie99.adam.model.ResultSnapshot;
import edu.harvard.cscie99.adam.model.Tag;
import edu.harvard.cscie99.adam.model.Well;
//import edu.harvard.cscie99.adam.model.Well.ControlType;
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
		
//		for (Plate plate : uniquePlates){
//			if (plate.getProject() != null)
//				plate.getProject().setPlates(null);
//		}
		return new ArrayList<Plate>(uniquePlates);
	}
	
	public Plate retrievePlate(int plateId){

		Session session = sessionFactory.openSession();
		Plate plate = null;
		try{
			plate = (Plate) session.get(Plate.class, plateId);
			if (plate != null){
				loadPlate(plate);
			}
		}
		finally {
			session.close();
		}
		
//		if (plate.getProject() != null)
//			plate.getProject().setPlates(null);
//		
		return plate;
	}
	
	public Plate createPlate(Plate plate){
		
		//Feature: auto create wells with labels
		if (plate.getWells() == null || plate.getWells().size() == 0){
			
			List<Well> wellList = new ArrayList<Well>();
			for (int row = 0; row < plate.getNumberOfRows(); row++){
				for (int col = 0; col < plate.getNumberOfColumns(); col++){
					
					Well well = new Well();
					well.setCol(col);
					well.setRow(row);
					well.setControlType("");
					
					if (plate.getWellLabels() != null){
						ArrayList<WellLabel> wlList = new ArrayList<>();
						for (WellLabel wl : plate.getWellLabels()){
							
							WellLabel wlv = new WellLabel();
							wlv.setName(wl.getName());
							wlList.add(wlv);
						}
						well.setWellLabels(wlList);
					}
					wellList.add(well);
				}
			}
			plate.setWells(wellList);
		}

		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			
			for (ControlType ct : plate.getControlTypes()){
				session.saveOrUpdate(ct);
			}
			
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
	
	public Plate updatePlate(Plate plate){

		Session session = sessionFactory.openSession();
		
		HashSet<String> plateWellLabels = new HashSet<String>();
		for (WellLabel wl : plate.getWellLabels()){
			plateWellLabels.add(wl.getName());
		}
		
		for (Well well : plate.getWells()){
			
			List<WellLabel> toRemove = new ArrayList<WellLabel>();
			
			for (WellLabel wl : well.getWellLabels()){
				if (!plateWellLabels.contains(wl.getName())){
					toRemove.add(wl);
				}
			}
			
			well.getWellLabels().removeAll(toRemove);
			
			for (String plateWellLabel : plateWellLabels){
				
				boolean contain = false;
				
				for (WellLabel wl : well.getWellLabels()){
					if (wl.getName().equals(plateWellLabel)){
						contain = true;
					}
				}
				if (!contain){
					WellLabel new_wl = new WellLabel();
					new_wl.setName(plateWellLabel);
					well.getWellLabels().add(new_wl);
				}
			}
		}
		
		
		try{
			session.beginTransaction();
			
			if (plate.getWellLabels() != null){
				for (WellLabel wl : plate.getWellLabels()){
					session.saveOrUpdate(wl);
				}
			}
			
			if (plate.getResults() != null){
				for (ResultSnapshot result : plate.getResults()){
					session.saveOrUpdate(result);
				}
			}
			
			if (plate.getControlTypes() != null){
				for (ControlType ct : plate.getControlTypes()){
					session.saveOrUpdate(ct);
				}
			}
			
			if (plate.getWells() != null){
				for (Well well : plate.getWells()){
					
					if (well.getWellLabels() != null){
						for (WellLabel wl : well.getWellLabels()){
							session.saveOrUpdate(wl);
						}
					}
					
					if (well.getResultSnapshots() != null){
						for (ResultSnapshot result : well.getResultSnapshots()){
							
							if (result.getMeasurements() != null){
								for (Measurement measure : result.getMeasurements()){
									session.saveOrUpdate(measure);
								}
							}
							
							session.saveOrUpdate(result);
						}
					}
					
					session.saveOrUpdate(well);
				}
			}
			
			if (plate.getTags() != null){
				for (Tag tag : plate.getTags()){
					session.saveOrUpdate(tag);
				}
			}
			
			session.merge(plate);
			session.getTransaction().commit();
		}
		finally{
			session.close();	
		}
		
		return plate;
	}
	
	public void loadPlate(Plate plate){
		if (plate.getWellLabels() != null){
			plate.getWellLabels().isEmpty();
		}
		if (plate.getWells() != null && !plate.getWells().isEmpty()){
			for (Well well : plate.getWells()){
				well.getWellLabels().isEmpty();
				well.getResultSnapshots().isEmpty();
			}
		}
		if (plate.getControlTypes() != null){
			plate.getControlTypes().isEmpty();
		}
		
		
		if (plate.getResults() != null && !plate.getResults().isEmpty()){
			for (ResultSnapshot result : plate.getResults()){
				if (result.getMeasurements() != null && !result.getMeasurements().isEmpty()){
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
}
