package edu.harvard.cscie99.adam.error;

public class BadWellValidatorException  extends Exception
{
	/** serialization. */
	private static final long serialVersionUID = 4785693532975108375L;

	/**
	 * Instantiates a new feature not found exception.
	 */
	public BadWellValidatorException()
	{ 
		super();
	}
	
	/**
	 * Instantiates a new feature not found exception.
	 *
	 * @param message the message
	 */
	public BadWellValidatorException( String message ) 
	{
		super( message );
	}
}
