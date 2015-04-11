package edu.harvard.cscie99.adam.service;


import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.util.FastMath;

import edu.harvard.cscie99.adam.model.DataSet;
import edu.harvard.cscie99.adam.model.Measurement;
import edu.harvard.cscie99.adam.model.Plate;
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
	
	public List<DataSet> getNormalizedData(Integer projectId)
	{
		// somehow get list of plate IDs per project
		List<Integer> plateIds = queryService.getPlateIdsPerProject(projectId);
		Map<Integer, List<DataSet>> dataSetsPerPlate = new HashMap<>();
		
		for (Integer idPl : plateIds)
		{
			Set<String> tempSetOfMeasType = new HashSet<>();
			Set<Date> tempSetOfTimeStamps = new HashSet<>();
			
			for (DataSet ds : queryService.getValuesUsingFilter(projectId, idPl, "all",
                "all", "all", "all")) // "all" is probably not right!!!, maybe "*"
			{
				tempSetOfMeasType.add(ds.getMeasurementType());
				tempSetOfTimeStamps.add(ds.getTime());
			}
		}
		
		// get the list of timestamps
		
		// get the list of measurement_types
		
		// run queries for each combination
		
		// still 3 for loops LOL
		
		return null;
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
