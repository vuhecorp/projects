package com.hersa.sample.project.dao.usersignon;

/**
 * @author Victor
 *
 */
public class UserSignOnCreateException extends Exception {
	
	private static final long serialVersionUID = -6058350027934778556L;
	
	private String message = "There was a problem while creating usersignon info.";
	
	public UserSignOnCreateException(){
		
	}
	
	public UserSignOnCreateException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
