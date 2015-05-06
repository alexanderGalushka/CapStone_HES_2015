package edu.harvard.cscie99.adam.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.util.FastMath;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.harvard.cscie99.adam.error.BadPlateValidatorException;
import edu.harvard.cscie99.adam.error.BadWellValidatorException;
import edu.harvard.cscie99.adam.error.DbReadException;
import edu.harvard.cscie99.adam.error.DbWriteException;
import edu.harvard.cscie99.adam.model.Measurement;
import edu.harvard.cscie99.adam.model.MeasurementType;
import edu.harvard.cscie99.adam.model.Plate;
import edu.harvard.cscie99.adam.model.PlateValidationContainer;
import edu.harvard.cscie99.adam.model.Project;
import edu.harvard.cscie99.adam.model.QCdata;
import edu.harvard.cscie99.adam.model.QCdataTimeWrapper;
import edu.harvard.cscie99.adam.model.QCmeasurement;
import edu.harvard.cscie99.adam.model.QCplate;
import edu.harvard.cscie99.adam.model.QCwell;
import edu.harvard.cscie99.adam.model.ResultSnapshot;
import edu.harvard.cscie99.adam.model.TimeStamp;
import edu.harvard.cscie99.adam.model.Well;
import edu.harvard.cscie99.adam.model.WellValidationContainer;


/**
 * 
 * @author Alexander G.
 * Data qualification prior the data analysis
 * 
 */

@Component
public class QualityControlService
{

	@Autowired
	private PlateService plateService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private QueryService queryService;
	
	@Autowired
    private SessionFactory sessionFactory;
	
	private final static Double INVALID = 777d;	
	
	/**
	 * wipes out results data associated with plate specified
	 * @param plateId
	 * @return true/false
	 */
	public boolean removeResultsFromPlate (int plateId)
	{
		
		boolean result = true;
		Plate plate = plateService.retrievePlate(plateId);
		
		Session session = sessionFactory.openSession();
	    try
	    {
	    	session.beginTransaction();
	    	
			for (ResultSnapshot resultSnapshot: plate.getResults())
			{
				session.delete(resultSnapshot);
			}
			plate.setResults (null);
			session.merge(plate);
			session.getTransaction().commit();
	    }
	    catch(Exception e)
	    {
	    	result = false;
	    }
		finally
		{
			session.close();	
		}
		return result;
	}
	
	/**
	 * validates single well
	 * @param wellValidator
	 * @throws BadWellValidatorException
	 * @throws DbWriteException
	 * @throws DbReadException
	 */
	public void validateSingleWell (WellValidationContainer wellValidator) throws BadWellValidatorException,
																				  DbWriteException,
																				  DbReadException
	{
		if (wellValidator.getRowNum() == null || wellValidator.getColNum() == null)
		{

			throw new BadWellValidatorException("Row or Column number is null");
		}		
		Well wellToValidate = null;
		try
		{
			Plate plate = plateService.retrievePlate(wellValidator.getPlateId());
			wellToValidate = plate.getWell(wellValidator.getRowNum(), wellValidator.getColNum());
			wellToValidate.setIfValid(wellValidator.getIfValid());
		}
		catch (Exception e)
		{
			throw new DbReadException("Couldn't retrieve the plate data");
		}
		finally
		{
			Session session = null;
			try
			{
				session = sessionFactory.openSession();
				session.beginTransaction();
				session.saveOrUpdate(wellToValidate);
				session.getTransaction().commit();
			}
			catch (Exception e)
			{
				throw new DbWriteException("Couldn't update the single well");
			}
			finally
			{
				session.close();
			}
		}
	}
	
	/**
	 * validates group of wells
	 * @param groupOfWellsValidator
	 * @throws BadWellValidatorException
	 * @throws DbReadException
	 * @throws DbWriteException
	 */
	public void validateGroupOfWell (List<WellValidationContainer> groupOfWellsValidator) throws BadWellValidatorException,
																								 DbReadException,
																								 DbWriteException
	{
        Plate plate = null;
		try
		{
			plate = plateService.retrievePlate(groupOfWellsValidator.get(0).getPlateId());
		}
		catch(Exception e)
		{
			throw new DbReadException("Couldn't retrieve the plate data");
		}
		finally
		{
			Session session = null;
			try
			{
				session = sessionFactory.openSession();
				session.beginTransaction();
				
				for (WellValidationContainer wellValidator : groupOfWellsValidator)
				{
					if (wellValidator.getRowNum() == null || wellValidator.getColNum() == null)
					{
	
						throw new BadWellValidatorException("Row or Column number is null");
					}
					Well wellToValidate = plate.getWell(wellValidator.getRowNum(), wellValidator.getColNum());
					wellToValidate.setIfValid(wellValidator.getIfValid());
					session.saveOrUpdate(wellToValidate);
				}
				
				session.getTransaction().commit();
			}
			catch (Exception e)
			{
				throw new DbWriteException("Couldn't update the single well");
			}
			finally
			{
				session.close();
			}
		}

	}
	
	/**
	 * validates group of plates
	 * @param groupOfPlateValidators
	 * @throws BadPlateValidatorException
	 * @throws DbReadException
	 * @throws DbWriteException
	 */
	public void validateGroupOfPlates (List<PlateValidationContainer> groupOfPlateValidators) throws BadPlateValidatorException,
	                                                                                         		 DbReadException,
	                                                                                         		 DbWriteException
	 {
        Session session = null;
		try
		{
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			for (PlateValidationContainer plateValidator : groupOfPlateValidators)
			{
				if (null == plateValidator.getPlateId())
				{
					throw new BadPlateValidatorException("Plate ID is null");
				}
				Plate plateToValidate = null;
				try
				{
					plateToValidate = plateService.retrievePlate(plateValidator.getPlateId());
				}
				catch (Exception e)
				{
					throw new DbReadException("Couldn't retrieve the plate data");
				}
				finally
				{
					plateToValidate.setIfValid(plateValidator.getIfValid());
					session.saveOrUpdate(plateToValidate);
				}
			}
			
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			throw new DbWriteException("Couldn't write the plate data");
		}
		finally
		{
			session.close();
		}
	 }
	
	/**
	 * validates single plate
	 * @param plateValidator
	 * @throws BadPlateValidatorException
	 * @throws DbReadException
	 * @throws DbWriteException
	 */
	public void validateSinglePlate (PlateValidationContainer plateValidator) throws BadPlateValidatorException,
		DbReadException,
		DbWriteException
	{
		Session session = null;
		if (null == plateValidator.getPlateId())
		{
			throw new BadPlateValidatorException("Plate ID is null");
		}
		Plate plateToValidate = null;
		try
		{
			plateToValidate = plateService.retrievePlate(plateValidator.getPlateId());
		}
		catch (Exception e)
		{
			throw new DbReadException("Couldn't retrieve the plate data");
		}
		finally
		{
			try
			{
				session = sessionFactory.openSession();
				session.beginTransaction();
				plateToValidate.setIfValid(plateValidator.getIfValid());
				session.saveOrUpdate(plateToValidate);
			}
			catch (Exception e)
			{
				throw new DbWriteException("Couldn't write the plate data");
			}
			finally
			{
				session.close();
			}
		}
	
	}
	
	/**
	 * Quality Control ultimate method to aggregate data by time and measurement types for each plate ID,
	 * perform statistical calculations
	 * this method serves the purpose of integration test
	 * @param projectId
	 * @return the Map, where the key is the Plate ID (Integer), and the values is the collection of QCdataTimeWrappers
	 */
	public Map<Integer, List<QCdataTimeWrapper>> qualifyData(Integer projectId)
	{	
		//Get the Project data
		Project project = projectService.retrieveProject(projectId);
		
		return actualDataQualification (project);
	}

	
	public Map<Integer, List<QCdataTimeWrapper>> actualDataQualification (Project project)
	{
		
		Map<Integer, List<QCdataTimeWrapper>> resultMap = new HashMap<>();
		
		for (Plate plate : project.getPlates()) //projPlate
		{
			
			//Get the Plate's data using plateService
			//Plate plate = plateService.retrievePlate(projPlate.getId());
			
			Integer numRow = plate.getNumberOfRows();
			Integer numCol = plate.getNumberOfColumns();
			
            List<Well> allWells = plate.getWells();
            
            Integer plateSize = allWells.size();

            Set<String> measurementTypes = new HashSet<>();
            Set<Date> timeStamps = new HashSet<>();
            
            List<ResultSnapshot> listOfResSnashots = plate.getResults();
            
            // collect all possible timestamps to iterate through
            for (ResultSnapshot result : listOfResSnashots)
            {
            	timeStamps.add(result.getTime());
            }

            // collect all possible measurement types to iterate through
            if (!listOfResSnashots.isEmpty())
            {
				for (Measurement measure : listOfResSnashots.get(0).getMeasurements())
				{
					measurementTypes.add(measure.getMeasurementType());
				}
            }

            List<QCdataTimeWrapper> listOfQCdataTimeWrappers = new ArrayList<>();
            
			for (Date timeStampToFilter: timeStamps) // have to be outer loop
			{
				List<QCdata> qcDataList = new ArrayList<>();
				
				for (String measTypeToFilter: measurementTypes) // have to be outer loop
				{ 
	
					List<Double> allRawValues = initializeArray(plateSize);
					// don't need to persists the order for the group of arrays below
					List<Double> bagOfPosValues = new ArrayList<>();
					List<Double> bagOfNegValues = new ArrayList<>();
					List<Double> bagOfsampleValues = new ArrayList<>();
					
					QCdata qcData = new QCdata();
					qcData.setMeasurementType(measTypeToFilter);

					for (ResultSnapshot result : listOfResSnashots)
					{				
						Date timeStamp = result.getTime();
						if (timeStampToFilter.equals(timeStamp))
						{	
							List<Measurement> listOfMeasurements = result.getMeasurements();
							for (Measurement measure : listOfMeasurements)
							{
								// filter out the measurement currently not in process
								if (measTypeToFilter.equals(measure.getMeasurementType()))
								{	
									Integer column = measure.getColumn();
									Integer row = measure.getRow();
									
									Well someWell = plate.getWell(row,column);
		
									Double value = measure.getValue();
									if(someWell.getIfValid()) //discriminate the invalid wells								
									{
										// shuffle the data in 3 buckets
										if ("positive".equalsIgnoreCase(someWell.getControlType()))
										{
											bagOfPosValues.add(value);
										}
										else if ("negative".equalsIgnoreCase(someWell.getControlType()))
										{
											bagOfNegValues.add(value);
										}
										else 
										{
											bagOfsampleValues.add(value);
										}
									}
									
									else
									{
										value = INVALID;
									}
																
									allRawValues.set((row*numCol + column) , value); // that's for 0 based matrix (plate)
								}
								
								//create the low level data object, further add it to the list of this type of objects								
							}
						}
					}
					
					
					if (bagOfPosValues.isEmpty() && bagOfNegValues.isEmpty() && !bagOfsampleValues.isEmpty())
					{
						// no Z and Z' to calc
						// return raw values
						
						qcData.setValues(allRawValues);
						
					}
					else if(bagOfPosValues.isEmpty() && !bagOfNegValues.isEmpty() && !bagOfsampleValues.isEmpty())
					{
						// no Z and Z' to calc
						// return normalized values
						
						double[] negControlsVector1 = ArrayUtils.toPrimitive(bagOfNegValues.toArray(new Double[bagOfNegValues.size()]));
						double meanNeg1 = calculateMeanVector(negControlsVector1);
						qcData.setValues(calculateNormalized(allRawValues,meanNeg1));
						
					}
					else if(!bagOfPosValues.isEmpty() && !bagOfNegValues.isEmpty() && !bagOfsampleValues.isEmpty())
					{
						//this is the most common case
						// calc Z and Z' 
						// return %Effect values

						double[] posControlsVector1 = convertToPrimitiveDouble(bagOfPosValues);
						double[] negControlsVector1 = convertToPrimitiveDouble(bagOfNegValues);
						double[] samplesVector1 = convertToPrimitiveDouble(bagOfsampleValues);
						
						double stdDevPos1 = calculateStdDevVector(posControlsVector1); 
						double stdDevNeg1 = calculateStdDevVector(negControlsVector1);
						double stdDevSample1 = calculateStdDevVector(samplesVector1);
						
						double meanPos1 = calculateMeanVector(posControlsVector1); 
						double meanNeg1 = calculateMeanVector(negControlsVector1); 
						double meanSample1 = calculateMeanVector(samplesVector1);
						
						double zPrimeFactor1 = calculalteZprimeFactor(stdDevPos1, stdDevNeg1, meanPos1, meanNeg1);
						double zFactor1 = calculateZFactor (stdDevSample1, stdDevPos1, meanSample1, meanPos1);
						
						qcData.setzFactor(zFactor1);
						qcData.setzPrimeFactor(zPrimeFactor1);
						qcData.setValues(calculatePercentEffect(allRawValues, meanPos1, meanNeg1));		
					}
					else
					{
						System.out.print("Really?");
						
					}
					qcDataList.add(qcData);	
				
				}
				QCdataTimeWrapper qcDataTimeWrapper = new QCdataTimeWrapper();
				qcDataTimeWrapper.setTimeStamp(timeStampToFilter);
				qcDataTimeWrapper.setQcData(qcDataList); 
				qcDataTimeWrapper.setNumberOfColumns(numCol);
				qcDataTimeWrapper.setNumberOfRows(numRow);
				listOfQCdataTimeWrappers.add(qcDataTimeWrapper);
		    }

            resultMap.put(plate.getId(), listOfQCdataTimeWrappers);
		}
		
		return resultMap;	
	}
	
	/**
	 * qualifies results data per each plate
	 * @param plateId
	 * @return QCplate object
	 */
	public QCplate qualifyDataPerPlate(int plateId)
	{
		QCplate qcPlate = null;
		Plate plate = plateService.retrievePlate(plateId);
		if(true == plate.getIfValid())
		{
			qcPlate = actualDataQualificationPerPlate(plate);
		}
		return qcPlate;
	}
	
	/**
	 * qualifies results all data of specified project
	 * @param plateId
	 * @return list of QCplate objects
	 */
	public List<QCplate> qualifyDataPerProject (int projectId)
	{
		Project project = projectService.retrieveProject(projectId);
		
		List<QCplate> qcPlateList = new ArrayList<>();
		
		for (Plate plate : project.getPlates() )
		{
			if (true == plate.getIfValid())
			{
				qcPlateList.add(actualDataQualificationPerPlate (plate));
			}
		}
		
		return qcPlateList;
	}
	
	public QCplate actualDataQualificationPerPlate (Plate plate)
	{

		QCplate qcPlate = new QCplate();
		
		// set QC plate data
		qcPlate.setProjectId(plate.getProjectId());
		qcPlate.setPlateId(plate.getId());
		qcPlate.setResultId(null);  //what is it?
		qcPlate.setName(plate.getName());
		qcPlate.setDate(null); // why do we need it? we don't have it in Plate object now!!!
		qcPlate.setControlTypes(plate.getControlTypes());
		
		//Get the Plate's data using plateService
		//Plate plate = plateService.retrievePlate(projPlate.getId());
		
		Integer numRow = plate.getNumberOfRows();
		Integer numCol = plate.getNumberOfColumns();
		
		// set QC plate data
		qcPlate.setNumberOfRows(numRow);
		qcPlate.setNumberOfColumns(numCol);
		
        List<Well> allWells = plate.getWells();
        
        Integer plateSize = allWells.size();

        Set<String> measurementTypes = new HashSet<>();
        Set<Date> timeStamps = new HashSet<>();
        
        List<ResultSnapshot> listOfResSnashots = plate.getResults();
                
        // collect all possible timestamps to iterate through
        for (ResultSnapshot result : listOfResSnashots)
        {
        	timeStamps.add(result.getTime());
        }
        
        List<TimeStamp> qcTimeStamps = new ArrayList<>();
        for (Date timeStamp : timeStamps)
        {
        	TimeStamp qcTimeStamp = new TimeStamp();
        	qcTimeStamp.setValue(timeStamp);
        	qcTimeStamps.add(qcTimeStamp);
        }
        
        // set QC plate data
        qcPlate.setTimeStamps(qcTimeStamps);

        // collect all possible measurement types to iterate through
        if (!listOfResSnashots.isEmpty())
        {
			for (Measurement measure : listOfResSnashots.get(0).getMeasurements())
			{
				measurementTypes.add(measure.getMeasurementType());
			}
        }
        
        List<MeasurementType> measurementQcTypes = new ArrayList<>();
        for (String measure : measurementTypes)
        {
        	MeasurementType qcMeasurementType = new MeasurementType();
        	qcMeasurementType.setName(measure);
        	measurementQcTypes.add(qcMeasurementType);
        }
        // set QC plate data
        qcPlate.setMeasurementTypes(measurementQcTypes);

        List<QCmeasurement> qcMeasurements = new ArrayList<>();
        
        
		for (Date timeStampToFilter: timeStamps) // have to be outer loop
		{
			
			for (String measTypeToFilter: measurementTypes) // have to be outer loop
			{ 
				QCmeasurement qcMeasurement = new QCmeasurement();
				qcMeasurement.setTimeStamp(timeStampToFilter);
				qcMeasurement.setMeasurementType(measTypeToFilter);
				
				Map<String, QCwell> qcWellsTempMap = new HashMap<>();
				
				List<Double> allRawValues = initializeArray(plateSize);
				// don't need to persists the order for the group of arrays below
				List<Double> bagOfPosValues = new ArrayList<>();
				List<Double> bagOfNegValues = new ArrayList<>();
				List<Double> bagOfsampleValues = new ArrayList<>();
				
				QCdata qcData = new QCdata();
				qcData.setMeasurementType(measTypeToFilter);

				for (ResultSnapshot result : listOfResSnashots)
				{				
					Date timeStamp = result.getTime();
					if (timeStampToFilter.equals(timeStamp))
					{	
						List<Measurement> listOfMeasurements = result.getMeasurements();
						for (Measurement measure : listOfMeasurements)
						{
							// filter out the measurement currently not in process
							if (measTypeToFilter.equals(measure.getMeasurementType()))
							{	
								Integer column = measure.getColumn();
								Integer row = measure.getRow();
								
								Well someWell = plate.getWell(row,column);
	                            QCwell qcWell = new QCwell();
	                            
								Double value = measure.getValue();
								if(someWell.getIfValid()) //discriminate the invalid wells								
								{
									// shuffle the data in 3 buckets
									if ("positive".equalsIgnoreCase(someWell.getControlType()))
									{
										bagOfPosValues.add(value);
										qcWell.setControlType("positive");
									}
									else if ("negative".equalsIgnoreCase(someWell.getControlType()))
									{
										bagOfNegValues.add(value);
										qcWell.setControlType("negative");
									}
									else 
									{
										bagOfsampleValues.add(value);
										qcWell.setControlType(someWell.getControlType());
									}
									
									qcWell.setIfValid(true);
								}
								
								else
								{
									qcWell.setIfValid(false);
									value = INVALID;
								}
								
								qcWell.setCol(column);
								qcWell.setRow(row);
								qcWellsTempMap.put(row.toString()+column.toString(), qcWell);
								try
								{
									allRawValues.set((row*numCol + column) , value); // that's for 0 based matrix (plate)
								}
								catch (Exception e)
								{
									System.out.print(e.getMessage());
								}
								
							}			
							//create the low level data object, further add it to the list of this type of objects								
						}
					}
				}
				
				
				if (bagOfPosValues.isEmpty() && bagOfNegValues.isEmpty() && !bagOfsampleValues.isEmpty())
				{
					// no Z and Z' to calc
					// return raw values
					qcMeasurement.setWells(populateQCwells (qcWellsTempMap, allRawValues, numRow, numCol));
					
				}
				else if(bagOfPosValues.isEmpty() && !bagOfNegValues.isEmpty() && !bagOfsampleValues.isEmpty())
				{
					// no Z and Z' to calc
					// return normalized values
					
					double[] negControlsVector1 = ArrayUtils.toPrimitive(bagOfNegValues.toArray(new Double[bagOfNegValues.size()]));
					double meanNeg1 = calculateMeanVector(negControlsVector1);
					List<Double> valuesForQC = calculateNormalized(allRawValues,meanNeg1);
					qcMeasurement.setWells(populateQCwells (qcWellsTempMap, valuesForQC, numRow, numCol));
					
				}
				else if(!bagOfPosValues.isEmpty() && !bagOfNegValues.isEmpty() && !bagOfsampleValues.isEmpty())
				{
					//this is the most common case
					// calc Z and Z' 
					// return %Effect values

					double[] posControlsVector1 = convertToPrimitiveDouble(bagOfPosValues);
					double[] negControlsVector1 = convertToPrimitiveDouble(bagOfNegValues);
					double[] samplesVector1 = convertToPrimitiveDouble(bagOfsampleValues);
					
					double stdDevPos1 = calculateStdDevVector(posControlsVector1); 
					double stdDevNeg1 = calculateStdDevVector(negControlsVector1);
					double stdDevSample1 = calculateStdDevVector(samplesVector1);
					
					double meanPos1 = calculateMeanVector(posControlsVector1); 
					double meanNeg1 = calculateMeanVector(negControlsVector1); 
					double meanSample1 = calculateMeanVector(samplesVector1);
					
					double zPrimeFactor1 = calculalteZprimeFactor(stdDevPos1, stdDevNeg1, meanPos1, meanNeg1);
					double zFactor1 = calculateZFactor (stdDevSample1, stdDevPos1, meanSample1, meanPos1);
					
					List<Double> valuesForQC = calculatePercentEffect(allRawValues, meanPos1, meanNeg1);
					qcMeasurement.setWells(populateQCwells (qcWellsTempMap, valuesForQC, numRow, numCol));
					qcMeasurement.setzFactor(zFactor1);
					qcMeasurement.setzPrimeFactor(zPrimeFactor1);
				}
				else
				{		
					// TODO
				}

				qcMeasurements.add(qcMeasurement);
			}
	    }

		qcPlate.setMeasurements(qcMeasurements);
		return qcPlate;	
	}
	
	private List<QCwell> populateQCwells (Map<String, QCwell> qcWellsTempMap, List<Double> valuesForQC, Integer numRow, Integer numCol)
	{
		List<QCwell> qcWellsList = new ArrayList<>();
		for (Integer r = 0; r< numRow; r++)
		{
			for (Integer c = 0; c< numCol; c++)
			{
				QCwell qcWell = qcWellsTempMap.get(r.toString()+c.toString());
				
				qcWell.setValue(valuesForQC.get(r*numCol + c));
				qcWellsList.add(qcWell);
			}
		}
		return qcWellsList;
	}
	
	
	private double[] convertToPrimitiveDouble( List<Double> arayToConvert )
	{
		
		return ArrayUtils.toPrimitive(arayToConvert.toArray(new Double[arayToConvert.size()]));
	}
	
	private List<Double> initializeArray(Integer size)
	{
        
        List<Double> actualValues = new ArrayList<>();
        for(int i = 0; i< size; i++)
        {
        	actualValues.add(0d);
        }
        return actualValues;
	}
	
	
	private double calculateStdDevVector(double[] dataVector)
	{
		return FastMath.sqrt(StatUtils.variance(dataVector));
	}
	
	
	private double calculateMeanVector(double[] dataVector)
	{
		return StatUtils.mean(dataVector);
	}
	
	private double calculalteZprimeFactor(double stdDevPos, double stdDevNeg, double meanPos, double meanNeg)
	{
		return (1  - (3*(stdDevPos + stdDevNeg)/Math.abs(meanPos - meanNeg)));
	}
	
	private double calculateZFactor (double stdDevSample, double stdDevPos, double meanSample, double meanPos)
	{
		return (1  - (3*(stdDevPos + stdDevSample)/Math.abs(meanPos - meanSample)));
	}
	
	
	private List<Double> calculatePercentEffect (List<Double> samplesVector, double meanPos, double meanNeg)
	{
		List<Double> result = new ArrayList<>();
		for (Double val : samplesVector)
		{
			if (INVALID == val)
			{
				// propagate INVALID value
				result.add(val);
			}
			else
			{
				result.add( (val-meanNeg)/(meanPos-meanNeg) );
			}
		}
		return result;
	}
	
	private List<Double> calculateNormalized (List<Double> samplesVector, double meanNeg)
	{
		List<Double> result = new ArrayList<>();
		for (Double val : samplesVector)
		{
			if (INVALID == val)
			{
				// propagate INVALID value
				result.add(val);
			}
			else
			{
				if (val > meanNeg)
				{
					result.add( (val-meanNeg)/(meanNeg) );
				}
				else
				{
					result.add( (meanNeg-val)/(meanNeg) );
				}
			}
		}
		return result;
	}
	

	/**
	 * flatmap matrix to vector
	 * @param dataMatrix - matrix data of doubles
	 * @return vector data of doubles
	 */
	@SuppressWarnings("unused")
	private double[] convertMatrixToVector(double[][] dataMatrix)
	{
		int numRows = dataMatrix.length;
		int numColumns = dataMatrix[0].length;
		double[] vector = new double[numRows*numColumns];
		int vectorCount = 0;
		for (int r = 0; r < numRows; r++)
		{
			for (int c = 0; c < numColumns; c++)
			{
				vector[vectorCount] = dataMatrix[r][c];
				vectorCount++;
			}
		}
		return vector;
	}
	
	@SuppressWarnings("unused")
	private double[] calculateZscores (double[] samplesVector, double stdDevNeg, double meanNeg)
	{
		double[] zScores = new double[samplesVector.length]; 
		for (int i = 0; i<samplesVector.length; i++)
		{
			zScores[i] = (samplesVector[i] - meanNeg)/stdDevNeg;
		}
		return zScores;
	}

	
}
