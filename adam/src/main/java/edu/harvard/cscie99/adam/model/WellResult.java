package edu.harvard.cscie99.adam.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * 
 * @author Gerson
 *
 */
@Entity
public class WellResult implements Serializable{
	
	/**
	 * Initial version
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "well_result_id")
	private int id;
	
	@Column(name = "position_x")
	private int positionX;
	
	@Column(name = "position_y")
	private int positionY;
	
	@Column(name = "value")
	private double value;
	
	@Column(name = "time")
	private Date time;
	
	@Column(name = "label")
	private String label;
	
	@OneToOne(mappedBy = "comment_id")
    private Comment comment;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}
	public int getPositionX() {
		return positionX;
	}
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}
	public int getPositionY() {
		return positionY;
	}
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

}
