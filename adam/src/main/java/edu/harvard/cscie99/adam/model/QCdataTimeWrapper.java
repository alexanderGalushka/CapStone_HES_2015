package edu.harvard.cscie99.adam.model;

import java.util.Date;

public class QCdataTimeWrapper 
{

	private Date timeStamp;
	private QCdata qcData;
	
	public Date getTimeStamp() 
	{
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) 
	{
		this.timeStamp = timeStamp;
	}
	public QCdata getQcData()
	{
		return qcData;
	}
	public void setQcData(QCdata qcData) 
	{
		this.qcData = qcData;
	}
	
	
}
