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

import edu.harvard.cscie99.adam.model.DataSet;
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
	
	
	// how would I get the data for control?
	

	//private double[][] posControls;
	//private double[][] negControls;
	//private double[][] samples;
	
	@Autowired
	private PlateService plateService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private QueryService queryService;
	
	
    private double[] posControlsVector;
	private double[] negControlsVector;
	private double[] samplesVector;
	private double[] zScoresVector;
	
	
	private double stdDevPos = 999d; 
	private double stdDevNeg = 999d; 
	private double stdDevSample = 999d; 
	
	private double meanPos = 999d; 
	private double meanNeg = 999d; 
	private double meanSample = 999d; 
	
	private double zPrimeFactor = 999d;
	private double zFactor = 999d;
	
	// QC is perform on each map, which would have positive and negative controls and samples
	// how to provide all these 3 data matrices?
	// how to split the plate editor data? how to shift the data in the matrix to get just samples matrix?
	public QualityControlService(Integer projectId) //double[][] posControls, double[][] negControls, double[][] samples)
	{
		//this.posControls = posControls;
		//this.negControls = negControls;
		//this.samples = samples;
		
//		List<Plate> projectPlates = plateService.listPlates( projectId );
		
		//Map<Integer, List<>>
		
		/*for (Plate plate : projectPlates)
		{
			for (Well well : plate.getWells())
			{
				for (ResultSnapshot  resultSnapshot : well.getResultSnapshots())
				{
					Date timeSamp = resultSnapshot.getTime();
					
					for (Measurement meas : resultSnapshot.getMeasurements())
					{
						
						String measType = meas.getMeasurementType();
						double measVal = meas.getValue();
					  	
					}
				}
			}
			
		}
		*/
		
		///posControlsVector = convertMatrixToVector(posControls);
		///negControlsVector = convertMatrixToVector(negControls);
		///samplesVector = convertMatrixToVector(samples);
	}
	
	public QualityControlService(){
	}
	
	/**
	 * calculates all the stats required for QC analysis
	 */
	public void calcQualityControlStats()
	{
		
		stdDevPos = calculateStdDevVector(posControlsVector); 
		stdDevNeg = calculateStdDevVector(negControlsVector);
		stdDevSample = calculateStdDevVector(samplesVector);
		
		meanPos = calculateMeanVector(posControlsVector); 
		meanNeg = calculateMeanVector(negControlsVector); 
		meanSample = calculateMeanVector(samplesVector);
		
		zPrimeFactor = calculalteZprimeFactor(stdDevPos, stdDevNeg, meanPos, meanNeg);
		zScoresVector = calculateZscores (samplesVector, stdDevNeg, meanNeg); // It has to be displayed in the heatmap! do we want to translate it to matrix? 
		zFactor = calculateZFactor (stdDevSample, stdDevPos, meanSample, meanPos);
		
	}
	
	/**
	 * Quality Control ultimate method to aggregate data by time and measurement types for each plate ID, perform statistical calculations
	 * @param projectId
	 * @return the Map, where the key is the Plate ID (Integer), and the values is the collection of QCdataTimeWrappers
	 */
	public Map<Integer, List<QCdataTimeWrapper>> getNormalizedData(Integer projectId)
	{
		
		//Get the Project data
		Project project = projectService.retrieveProject(projectId);
		
		List<QCdataTimeWrapper> listOfQCdataTimeWrappers = new ArrayList<>();
		
		Map<Integer, List<QCdataTimeWrapper>> resutMap = new HashMap<>();
		
		for (Plate projPlate : project.getPlates())
		{
			
			//Get the Plate's data using plateService
			Plate plate = plateService.retrievePlate(projPlate.getId());
			
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

							Double value = measure.getValue();
							
							Integer column = measure.getColumn();
							Integer row = measure.getRow();
							
							allRawValues.add((row+1)*(column+1) - 1 , value); // row and the column are 0 based, that's why "+1"

							// shuffle the data in 3 buckets
							Well someWell = plate.getWell(row,column);
		
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
						qcData.setValues(calculateNormalized(bagOfsampleValues,meanNeg1));
						
					}
					else if(!bagOfPosValues.isEmpty() && !bagOfNegValues.isEmpty() && !bagOfsampleValues.isEmpty())
					{
						//this is the most common case
						// calc Z and Z' 
						// return %Effect values

						// TODO crate a ""toPrimitiveDouble function
						double[] posControlsVector1 = ArrayUtils.toPrimitive(bagOfPosValues.toArray(new Double[bagOfPosValues.size()]));
						double[] negControlsVector1 = ArrayUtils.toPrimitive(bagOfNegValues.toArray(new Double[bagOfNegValues.size()]));
						double[] samplesVector1 = ArrayUtils.toPrimitive(bagOfsampleValues.toArray(new Double[bagOfsampleValues.size()]));
						
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
						qcData.setValues(calculatePercentEffect(bagOfsampleValues, meanPos1, meanNeg1));
						
					}
					
					qcDataList.add(qcData);
					
				}

				QCdataTimeWrapper qcDataTimeWrapper = new QCdataTimeWrapper();
				qcDataTimeWrapper.setTimeStamp(timestamp);
				qcDataTimeWrapper.setQcData(qcDataList); 
				listOfQCdataTimeWrappers.add(qcDataTimeWrapper);
			}
			
			
			resutMap.put(projPlate.getId(), listOfQCdataTimeWrappers);
		}
		
		return resutMap;
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
	
	public boolean evaluateZPrimeFactor()
	{
		boolean result = false; 
		if (zPrimeFactor > 0.5 && zPrimeFactor < 1) result =  true;
		return result;
	}
	
	
	/**
	 * returns the mean value of the data in the matrix
	 * @param positiveControlsMatrix - data matrix in form of [[0.5, 0.4, 0.5, 0.2],[0.6, 0.1, 0.1, 0.2], [0.9, 0.8, 0.5, 0.2]]
	 * @return
	 */
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
	

	private double[] calculateZscores (double[] samplesVector, double stdDevNeg, double meanNeg)
	{
		double[] zScores = new double[samplesVector.length]; 
		for (int i = 0; i<samplesVector.length; i++)
		{
			zScores[i] = (samplesVector[i] - meanNeg)/stdDevNeg;
		}
		return zScores;
	}
	
	private List<Double> calculatePercentEffect (List<Double> samplesVector, double meanPos, double meanNeg)
	{
		List<Double> result = new ArrayList<>();
		for (Double val : samplesVector)
		{
			result.add( (val-meanNeg)/(meanPos-meanNeg) );
		}
		return result;
	}
	
	private List<Double> calculateNormalized (List<Double> samplesVector, double meanNeg)
	{
		List<Double> result = new ArrayList<>();
		for (Double val : samplesVector)
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
		return result;
	}
	
	private double calculateZFactor (double stdDevSample, double stdDevPos, double meanSample, double meanPos)
	{
		return (1  - (3*(stdDevPos + stdDevPos)/Math.abs(meanPos - meanSample)));
	}
	
	/**
	 * flatmap matrix to vector
	 * @param dataMatrix - matrix data of doubles
	 * @return vector data of doubles
	 */
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

	/**
	 * @return the zScoresVector
	 */
	public double[] getzScoresVector() {
		return zScoresVector;
	}

	/**
	 * @return the stdDevPos
	 */
	public double getStdDevPos() {
		return stdDevPos;
	}

	/**
	 * @return the stdDevNeg
	 */
	public double getStdDevNeg() {
		return stdDevNeg;
	}

	/**
	 * @return the stdDevSample
	 */
	public double getStdDevSample() {
		return stdDevSample;
	}

	/**
	 * @return the meanPos
	 */
	public double getMeanPos() {
		return meanPos;
	}

	/**
	 * @return the meanNeg
	 */
	public double getMeanNeg() {
		return meanNeg;
	}

	/**
	 * @return the meanSample
	 */
	public double getMeanSample() {
		return meanSample;
	}

	/**
	 * @return the zPrimeFactor
	 */
	public double getzPrimeFactor() {
		return zPrimeFactor;
	}

	/**
	 * @return the zFactor
	 */
	public double getzFactor() {
		return zFactor;
	}

	
}
