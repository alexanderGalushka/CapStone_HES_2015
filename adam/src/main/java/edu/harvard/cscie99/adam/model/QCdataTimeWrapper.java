package edu.harvard.cscie99.adam.model;

import java.util.Date;
import java.util.List;

public class QCdataTimeWrapper 
{
    
	private Date timeStamp;
	private List<QCdata> qcDataList;
	
	//need provide the data layout
	private Integer numberOfRows;
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
