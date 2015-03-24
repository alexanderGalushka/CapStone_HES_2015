package edu.harvard.cscie99.adam.error;

/**
 * The Class FeatureNotFoundException.
 */
public class SessionTimeouException extends Exception
{	
	
	/** serialization. */


	/**
	 * Instantiates a new feature not found exception.
	 */
	public SessionTimeouException()
	{ 
		super();
	}
	
	/**
	 * Instantiates a new feature not found exception.
	 *
	 * @param message the message
	 */
	public SessionTimeouException( String message ) 
	{
		super( message );
	}
}