package edu.harvard.cscie99.adam.service;


import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.springframework.stereotype.Component;

import edu.harvard.cscie99.adam.model.Decay;
import edu.harvard.cscie99.adam.model.Growth;
import edu.harvard.cscie99.adam.model.Linear;
import edu.harvard.cscie99.adam.model.Polynom;

/**
 * 
 * @author Alexander G.
 * Data Analysis Service with primary focus on curve fit
 */
@Component
public class DataAnalysisService 
{

	/**
	 * Covers covers line and semilog line fit (y = slope*x + intercept; y = slope*log(x) + intercept)
	 * @param x array of X-axis values
	 * @param y array of Y-axis values
	 * @param lineType type of line to pass: "line" or "semilog"
	 * @return
	 */
	public Linear getLinearRegression( double x[], double y[], String lineType )
	{
		SimpleRegression regression = new SimpleRegression();
		Linear line  = new Linear();
		line.setGraphType(lineType);
		
		if (x.length == y.length)
		{
			if(lineType.equals("line"))
			{
				for (int i = 0; i < x.length; i++)
				{
					
					regression.addData(x[i], y[i]);
				}
			}
			else
			{
				for (int i = 0; i < x.length; i++)
				{
					
					regression.addData(Math.log10(x[i]), y[i]);
				}
			}
			line.setIntercept(regression.getIntercept());
			line.setSlope(regression.getIntercept());
			line.setrSquared(regression.getRSquare());

		}
		return line;
	}
	

	// will be required for semilog, maybe need to combine in sep. function...
	// stupid Java doesn't allow you to pass the second-order function as parameter
	// so have to introduce second parameter ifLn or hae to deal with bulky Command pattern.
	public double[] calculateLogarithm( double x[], boolean ifLn)
	{
		double xOut[] = new double[x.length];
		for (int i = 0; i < x.length; i++)
		{
			if(ifLn)
			{
				xOut[i] = Math.log(x[i]); //natural log
			}
			else
			{
				xOut[i] = Math.log10(x[i]); 
			}
   
		}
		return xOut;
	}
	

	/**
	 * covers quadratic and cubic (2nd and 3d degree polynoms)
	 * @param x array of X-axis values
	 * @param y array of Y-axis values
	 * @param degree p0lynomial degree, e.g. y(x) = a0 + a1*x + a2*x^2  - 2nd degree polynom
	 * @return Polynom as an object, the most important is that this object would contains polynomial
	 *         coefficients from the example above: [a0, a1, a2] - array of doubles
	 */
	public Polynom getPolynomialFit(double x[], double y[], int degree)
	{
		Polynom polynom = new Polynom();
		if ( 2 == degree )
		{
			polynom.setCurveType("quadratic");
		}
		else if( 3 == degree )
		{
			polynom.setCurveType("cubic");
		}
		
		WeightedObservedPoints obs = new WeightedObservedPoints();
		
		if (x.length == y.length)
		{
		
			for (int i = 0; i < x.length; i++)
			{
				obs.add(x[i], y[i]);
			}
			
			PolynomialCurveFitter fitter = PolynomialCurveFitter.create(degree);
			polynom.setPolinomialCoeff(fitter.fit(obs.toList()));

		}		
		// data is "not aligned" Polynom object would be returned empty
		return polynom;
	}
	

	/**
	 * Curve fit for "Plateau followed by one phase decay"
	 * @param x array of X-axis values
	 * @param y array of Y-axis values
	 * @return Decay object with coefficients for Y = Plateau+(Y0-Plateau)*exp(-K*(X))
	 */
	public Decay getDecay(double x[], double y[])
	{
		// Assume that the half-life decay starts at time x0 = 0, y0=first y reading	    
		Decay decay = new Decay();
		
		if (x.length == y.length && ifDecay(y)) //check if it's even decaying data
		{
			double xNew[] = new double[x.length];
			double yNew[] = new double[x.length];
			for(int i = 0; i < x.length; i++)
			{
				xNew[i] = x[i] - x[0];
				yNew[i] = Math.log(y[i]);
			}
			
			Linear line = getLinearRegression( xNew, yNew, "line" );
			double intercept = line.getIntercept();
			double decayRate = line.getSlope();
			
			/* 
			 determine P and K: 
			 Ln(Y) = Ln(Y0-P) - K*X
			 Ln(Y0-P) = intercept 
			 Y0 - P = exp(intercept)
			 P = Y0 - exp(intercept)
			 */
			
			double plateau = y[0] - Math.exp(intercept); // is it Ok if negative?
			
			// TODO
			// calculate plateau empirically with delta???? (most probable close to Plateau value will be y[y.length - 1])
			// make determination if empirical plateau is close to the calc. one
			
			decay.setDecayRate(decayRate);
			decay.setPlateau(plateau);			
			decay.setHalfTime(Math.log(2)/decayRate);

		}
		return decay;
	}
	
	/**
	 * Curve fit for "Plateau followed by one phase decay"
	 * @param x array of X-axis values
	 * @param y array of Y-axis values
	 * @return Growth object with coefficients for Y = Y0*exp(K*X)
	 * @return
	 */
	public Growth getGrowth (double x[], double y[])
	{
		Growth growth = new Growth();
		growth.setStartingPoint(y[0]);
		
		double xNew[] = new double[x.length];
		double yNew[] = new double[x.length];
		for(int i = 0; i < x.length; i++)
		{
			xNew[i] = x[i] - x[0];
			yNew[i] = Math.log(y[i]);
		}
		
		Linear line = getLinearRegression( x, yNew, "line" );
		double growthRate = line.getSlope();
		
		growth.setGrowthRate(growthRate);
		
		return growth;
	}
	
	//TODO
	// michaelis-menten	y = vmax*x/[km + x]
	
	public void getMichaelisMenten (double x[], double y[])
	{
	    //LevenbergMarquardtEstimator estimator = 
          //      new LevenbergMarquardtEstimator()
	}
	
	private boolean ifDecay(double y[])
	{
		boolean result = false;
		
		if (y[0] > y[y.length - 1])
		{
			// does it make sense to go element by element to check if the value is less
			// the data could be noisy
			
			result = true;
		}
		
		return result;
	}
	

	
}
