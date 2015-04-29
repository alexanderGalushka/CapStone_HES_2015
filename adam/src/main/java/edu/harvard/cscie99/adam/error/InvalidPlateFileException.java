package edu.harvard.cscie99.adam.error;

public class InvalidPlateFileException extends Exception {
	
	/**
	 * Init version
	 */
	private static final long serialVersionUID = 1L;
	
	private String row;
	private String col;
	private String message;
	
	public InvalidPlateFileException(String message, String row, String col){
		this.message = message;
		this.row = row;
		this.col = col;
	}
	
	@Override
	public String getMessage(){
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public String getCol() {
		return col;
	}

	public void setCol(String col) {
		this.col = col;
	}

}
