package de.arguments.exceptions;

public class JSONException extends Exception {

	/**
		 * 
		 */
	private static final long serialVersionUID = -3812452630801660337L;
	private String message;

	public JSONException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
