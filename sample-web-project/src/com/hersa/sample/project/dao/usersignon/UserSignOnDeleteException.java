package com.hersa.sample.project.dao.usersignon;

public class UserSignOnDeleteException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8549149227295282027L;
	private String message = "Could not delete usersign on info.";

	public UserSignOnDeleteException() {
		
	}
	public UserSignOnDeleteException(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
