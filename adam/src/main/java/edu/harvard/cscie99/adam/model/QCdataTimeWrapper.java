package edu.harvard.cscie99.adam.model;

import java.util.Date;
import java.util.List;

public class QCdataTimeWrapper 
{
    
	private Date timeStamp;
	private List<QCdata> qcDataList;
	
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
	
	
}
