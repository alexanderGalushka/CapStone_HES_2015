package edu.harvard.cscie99.adam.error;

public class DbReadException extends Exception
{
	/** serialization. */
	private static final long serialVersionUID = 8779853434169233804L;

	/**
	 * Instantiates a new feature not found exception.
	 */
	public DbReadException()
	{ 
		super();
	}
	
	/**
	 * Instantiates a new feature not found exception.
	 *
	 * @param message the message
	 */
	public DbReadException( String message ) 
	{
		super( message );
	}
}
