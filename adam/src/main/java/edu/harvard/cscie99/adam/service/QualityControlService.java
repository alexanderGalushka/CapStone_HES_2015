package edu.harvard.cscie99.adam.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.util.FastMath;

import edu.harvard.cscie99.adam.model.Measurement;
import edu.harvard.cscie99.adam.model.Plate;
import edu.harvard.cscie99.adam.model.Project;
import edu.harvard.cscie99.adam.model.QCdata;
import edu.harvard.cscie99.adam.model.QCdataTimeWrapper;
import edu.harvard.cscie99.adam.model.ResultSnapshot;
import edu.harvard.cscie99.adam.model.Well;


/**
 * 
 * @author Alexander G.
 * data screening prior the data analysis
 * 
 * 
 */

@Component
public class QualityControlService
{
	// are we assuming one data point in the well when calculating all these statistical metrics?
	
	// 1. SHOW the separation between the distributions of the positive and negative controls using Z′-factor
	
	// Z′-factor for each plate
	
	// Z′-factor for the run as a whole
	
	// evaluate Z′-factor:
	// Values between 0.5 and 1 are excellent,
	// values between 0 and 0.5 may be acceptable, and values less than 0 indicate the assay is unlikely to be usable in a high-throughput context
	
	
	@Autowired
	private PlateService plateService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private QueryService queryService;
	
	private final static Double INVALID = 777d;	
	
	/**
	 * Quality Control ultimate method to aggregate data by time and measurement types for each plate ID,
	 * perform statistical calculations
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
		List<QCdataTimeWrapper> listOfQCdataTimeWrappers = new ArrayList<>();
		
		Map<Integer, List<QCdataTimeWrapper>> resultMap = new HashMap<>();
		
		for (Plate plate : project.getPlates()) //projPlate
		{
			
			//Get the Plate's data using plateService
			//Plate plate = plateService.retrievePlate(projPlate.getId());
			
			Integer numRow = plate.getNumberOfRows();
			Integer numCol = plate.getNumberOfColumns();
			
            List<Well> allWells = plate.getWells();
            
            Integer plateSize = allWells.size();
		
			//At this point, we have all the wells from the plate, and the result snapshots associated to plate
			
            Boolean getMeasuemntTypesFlag = false; // use this flag for the first iteration of results just to get all measurement types for plate
            
            Set<String> measurementTypes = new HashSet<>();
            Stack<String> measurementTypesStack = new Stack<>(); // to be used for measurements grouping
			
            for (ResultSnapshot result : plate.getResults())
			{
				
				Date timestamp = result.getTime();
				List<QCdata> qcDataList = new ArrayList<>();
				
				if (false == getMeasuemntTypesFlag)
				{
					for (Measurement measure : result.getMeasurements())
					{
						measurementTypes.add(measure.getMeasurementType());
					}
					getMeasuemntTypesFlag = true;

					measurementTypesStack.addAll(measurementTypes);
				}
				
				while (!measurementTypesStack.isEmpty())
				{
			
					String measTypeToFilter = measurementTypesStack.pop();
	
					List<Double> allRawValues = initializeArray(plateSize);
					// don't need to persists the order for the group of arrays below
					List<Double> bagOfPosValues = new ArrayList<>();
					List<Double> bagOfNegValues = new ArrayList<>();
					List<Double> bagOfsampleValues = new ArrayList<>();
					
					for (Measurement measure : result.getMeasurements())
					{
						//HINT: there is a method in plate called getWell(X, Y).
						//You can use this to associate the Well data (labels, controls) to the readout values
						if (measTypeToFilter.equals(measure.getMeasurementType()))
						{	
							Integer column = measure.getColumn();
							Integer row = measure.getRow();
							
							Well someWell = plate.getWell(row,column);

							Double value = measure.getValue();
							if(someWell.getIfValid()) //discriminate the invalid wells								
							{
								// shuffle the data in 3 buckets
								if (someWell.getControlType() == Well.ControlType.POS)
								{
									bagOfPosValues.add(value);
								}			
								else if (someWell.getControlType() == Well.ControlType.NEG)
								{
									bagOfNegValues.add(value);
								}
								else if (someWell.getControlType() == Well.ControlType.EMPTY)
								{
									bagOfsampleValues.add(value);
								}
								else if (someWell.getControlType() == null)
								{
									//no data
									// TODO
								}
							}
							
							else
							{
								value = INVALID;
							}
														
							allRawValues.add((row+1)*(column+1) - 1 , value); // row and the column are 0 based, that's why "+1"
						 }	
					}
					//create the low level data object, further add it to the list of this type of objects
					QCdata qcData = new QCdata();
					qcData.setMeasurementType(measTypeToFilter);
					
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

						// TODO crate a "toPrimitiveDouble" function
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
					qcDataList.add(qcData);	
				}
				QCdataTimeWrapper qcDataTimeWrapper = new QCdataTimeWrapper();
				qcDataTimeWrapper.setTimeStamp(timestamp);
				qcDataTimeWrapper.setQcData(qcDataList); 
				qcDataTimeWrapper.setNumberOfColumns(numCol);
				qcDataTimeWrapper.setNumberOfRows(numRow);
				listOfQCdataTimeWrappers.add(qcDataTimeWrapper);
			}
            resultMap.put(plate.getId(), listOfQCdataTimeWrappers);
		}
		
		return resultMap;
		
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
		return (1  - (3*(stdDevPos + stdDevPos)/Math.abs(meanPos - meanSample)));
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
