package com.hersa.sample.project.dao.user;

import java.io.Serializable;
import java.util.Date;

import com.hersa.sample.project.bom.SystemUser;

public class User extends SystemUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7511955582123319577L;

	
	private String email;
	private int active;
	private String role;
	private String createdBy;
	private String modifiedBy;
	private Date modifiedDate;
	private String userName;


	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public String getModifiedBy() {
		return modifiedBy;
	}


	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String toString() {
		String user = "";
//		role + " | " + createdBy + " | " + userName;
		return user;
	}

}
