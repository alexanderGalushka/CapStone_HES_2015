package edu.harvard.cscie99.adam.error;

/**
 * The Class FeatureNotFoundException.
 */
public class LogoutFailedException extends Exception
{	
	
	/** serialization. */
	private static final long serialVersionUID = -3768622731203463698L;

	/**
	 * Instantiates a new feature not found exception.
	 */
	public LogoutFailedException()
	{ 
		super();
	}
	
	/**
	 * Instantiates a new feature not found exception.
	 *
	 * @param message the message
	 */
	public LogoutFailedException( String message ) 
	{
		super( message );
	}
}
