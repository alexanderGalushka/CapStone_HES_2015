package edu.harvard.cscie99.adam.service;

import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.springframework.stereotype.Component;

import edu.harvard.cscie99.adam.model.Decay;
import edu.harvard.cscie99.adam.model.Line;

/**
 * 
 * @author Alexander G.
 *
 */
@Component
public class DataAnalysisService 
{
    // that covers line and semilog line
	public Line getLinearRegression( double x[], double y[] )
	{
		SimpleRegression regression = new SimpleRegression();
		Line line  = new Line();
		
		if (x.length == y.length)
		{
		
			for (int i = 0; i < x.length; i++)
			{
				regression.addData(x[i], y[i]);
			}
			
			line.setIntercept(regression.getIntercept());
			line.setSlope(regression.getIntercept());
			line.setrSquared(regression.getRSquare());

		}
		return line;
	}
	

	// will be required for semilog, maybe need to combine in sep. funstion...
	// stupid Java doesn't allow you to pass the second-order funcion as parameter
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
	
	// covers quadratic and cubic
	public double[] getPolynomianFit(double x[], double y[], int degree)
	{
		// not the best way to initialize
		double[] coeff = null;
		
		WeightedObservedPoints obs = new WeightedObservedPoints();
		
		if (x.length == y.length)
		{
		
			for (int i = 0; i < x.length; i++)
			{
				obs.add(x[i], y[i]);
			}
			
			PolynomialCurveFitter fitter = PolynomialCurveFitter.create(degree);
			coeff = fitter.fit(obs.toList());

		}
		
		return coeff;
	}
	
	// Y = Plateau+(Y0-Plateau)*exp(-K*(X))
	
	public Decay decay(double x[], double y[])
	{
		//check if it's even decaying data
		
		// You can assume that the half-life decay starts at time x0 = 0, y=first reading
	    
		Decay decay = new Decay();
		
		if (x.length == y.length && ifDecay(y))
		{
			double xNew[] = new double[x.length];
			double yNew[] = new double[x.length];
			for(int i = 0; i < x.length; i++)
			{
				//y decay delta
				
				xNew[i] = x[i] - x[0];
				yNew[i] = Math.log(y[i]);
			}
			x[0] = 0d;
			
			Line line = getLinearRegression( xNew, yNew );
			double intercept = line.getIntercept();
			double decayRate = line.getSlope();
			// to determine P and K 
			// Ln(Y) = Ln(Y0-P) - K*X
			// Ln(Y0-P) = intercept; Y0 - P = exp(intercept); P = Y0 - exp(intercept);
			
			double plateau = y[0] - Math.exp(intercept); // is it Ok if negative?
			
			// calculate plateau empirically with delta???? (most probable close to Plateau value will be y[y.length - 1])
			
			decay.setDecayRate(decayRate);
			decay.setPlateau(plateau);			
			decay.setHalfTime(Math.log(2)/decayRate);
			
			//int indexOfX0 = Arrays.asList(x).indexOf(x0);
		}
		return decay;
	}
	
	
	private boolean ifDecay(double y[])
	{
		boolean result = false;
		
		if (y[0] > y[y.length - 1])
		{
			// does it make sense to go element by element to check if the value is less
			// the data could b noisy
			
			result = true;
		}
		
		return result;
	}
	
}
