package com.hersa.sample.project;

public class DuplicateUserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6013224035746754437L;
	public String message = "Username or Email already exists.";
	
	public DuplicateUserException() {
		
	}
	public DuplicateUserException(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
