package edu.harvard.cscie99.adam.model;

import java.util.Date;
import java.util.List;

/**
 * QCdataTimeWrapper class
 * 
 * Represent the list of QC data attached to time information
 * for pharmacokinetics (list of data point results moving throught time)
 * 
 * @author Alex
 *
 */
public class QCdataTimeWrapper 
{
    
	/**
	 * Timestamp associated to list of QC data
	 */
	private Date timeStamp;
	
	/**
	 * List of QC data
	 */
	private List<QCdata> qcDataList;
	
	/**
	 * Number of Rows in plate
	 */
	private Integer numberOfRows;
	
	/**
	 * Number of Wells in plate
	 */
	private Integer numberOfColumns;
	
	
	public Date getTimeStamp() 
	{
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) 
	{
		this.timeStamp = timeStamp;
	}
	public List<QCdata> getQcData()
	{
		return qcDataList;
	}
	public void setQcData(List<QCdata> qcDataList) 
	{
		this.qcDataList = qcDataList;
	}
	public Integer getNumberOfRows()
	{
		return numberOfRows;
	}
	public void setNumberOfRows(Integer numberOfRows)
	{
		this.numberOfRows = numberOfRows;
	}
	public Integer getNumberOfColumns() 
	{
		return numberOfColumns;
	}
	public void setNumberOfColumns(Integer numberOfColumns)
	{
		this.numberOfColumns = numberOfColumns;
	}
	
	
}
