package com.hersa.sample.project.dao.usersignon;

public class UserSignOnFinderException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2604655250565909737L;
	private String message = "Could not locate usersignon info.";
	
	public UserSignOnFinderException() {
		
	}
	public UserSignOnFinderException(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}

