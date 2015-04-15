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
 * 
 * @author Alexander G.
 *
 */
@Entity
public class ResultSnapshot implements Serializable{
	
	/**
	 * Initial version
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "result_snapshot_id")
	private int id;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="plate_id")
//    private Plate plate;
	
	// accounted for pharmacokinetics
	@Column(name = "time")
	private Date time;
			
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Measurement> measurements;
	
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

//	public Plate getPlate() {
//		return plate;
//	}
//
//	public void setPlate(Plate plate) {
//		this.plate = plate;
//	}
	
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
	
	@Override
	public int hashCode(){
		return 31;
	}
}
