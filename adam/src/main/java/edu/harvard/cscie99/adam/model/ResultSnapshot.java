package edu.harvard.cscie99.adam.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


/**
 * ResultSnapshot class.
 * 
 * Represents one instance of results loaded from Plate Reader instrument,
 * in a certain period of time.
 * 
 * @author Gerson
 *
 */
@Entity
public class ResultSnapshot implements Serializable{
	
	/**
	 * Initial version
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Auto-generated Key
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "result_snapshot_id")
	private int id;
	
	/**
	 * Timestamp: date and time the reading took place.
	 * This accounts for pharmacokinetic data
	 */
	@Column(name = "time")
	private Date time;
	
	/**
	 * List of measurements
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Measurement> measurements;
	
	/**
	 * Constructor
	 */
	public ResultSnapshot(){
		this.measurements = new ArrayList<Measurement>();
	}
	
	public List<Measurement> getMeasurements() {
		return measurements;
	}
	public void setMeasurements(List<Measurement> measurements) { 
		this.measurements = measurements;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * getMeasurementsFromWell: auxiliary method that returns list of Measurements
	 * for a particular row and column
	 *  
	 * @param row
	 * @param col
	 * @return list of measurements
	 */
	public List<Measurement> getMeasurementsFromWell(int row, int col){
		
		List<Measurement> measures = new ArrayList<Measurement>();
		
		for (Measurement m : getMeasurements()){
			if (m.getRow() == row && m.getColumn() == col){
				measures.add(m);
			}
		}
		return measures;
	}
	
	/**
	 * Overriding custom equals to enforce proper ResutlSnapshot set handling
	 */
	@Override
	public boolean equals(Object obj){
		
		if (obj == null){
			return false;
		}
		if (!(obj instanceof ResultSnapshot)){
			return false;
		}
		ResultSnapshot other = (ResultSnapshot) obj;
		if (other.getId() != getId()){
			return false;
		}
		
		return true;
	}
	
	/**
	 * Custom implementation of hashcode
	 */
	@Override
	public int hashCode(){
		return 31;
	}
}
