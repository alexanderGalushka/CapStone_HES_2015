package edu.harvard.cscie99.adam.error;

/**
 * 
 * @author Gerson
 *
 */
public class UnauthorizedOperationException extends Exception{

	/**
	 * Initial version
	 */
	private static final long serialVersionUID = 1L;
	
	private String user;
	private String operation;
	private String errorMsg;
	
	public UnauthorizedOperationException(){
	}
	
	public UnauthorizedOperationException(String message, String user, String operation){
		this.errorMsg = message;
		this.user = user;
		this.operation = operation;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
}
