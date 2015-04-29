package edu.harvard.cscie99.adam.error;

public class DbWriteException extends Exception
{
	/** serialization. */
	private static final long serialVersionUID = -513678955001022640L;

	/**
	 * Instantiates a new feature not found exception.
	 */
	public DbWriteException()
	{ 
		super();
	}
	
	/**
	 * Instantiates a new feature not found exception.
	 *
	 * @param message the message
	 */
	public DbWriteException( String message ) 
	{
		super( message );
	}
}
