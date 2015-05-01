package edu.harvard.cscie99.adam.error;

public class BadPlateValidatorException extends Exception
{

	/** serialization. */
	private static final long serialVersionUID = 7259086953990777410L;

	/**
	 * Instantiates a new feature not found exception.
	 */
	public BadPlateValidatorException()
	{ 
		super();
	}
	
	/**
	 * Instantiates a new feature not found exception.
	 *
	 * @param message the message
	 */
	public BadPlateValidatorException( String message ) 
	{
		super( message );
	}
}
