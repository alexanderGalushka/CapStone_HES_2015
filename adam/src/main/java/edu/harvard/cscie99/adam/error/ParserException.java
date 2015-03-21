package edu.harvard.cscie99.adam.error;

/**
 * 
 * @author Gerson
 *
 */
public class ParserException extends Exception{
	
	/**
	 * Init version
	 */
	private static final long serialVersionUID = 1L;
	
	private String msg;
	private String filename;
	
	public ParserException (String msg, String filename){
		this.msg = msg;
		this.filename = filename;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}
