package com.hersa.sample.project.dao.user;

import java.sql.Timestamp;

public class User {
	private int id;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private int active;
	private String imagePath;
	private String role;
	private int recentUnlock;
	private int failedAttempts;
	private Timestamp lastFailed;
	private int locked;
	private Timestamp lockedOn;
	private Timestamp firstFailed;


	public User() {
		// TODO Auto-generated constructor stub
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getRecentUnlock() {
		return recentUnlock;
	}

	public void setRecentUnlock(int recentUnlock) {
		this.recentUnlock = recentUnlock;
	}

	public int getFailedAttempts() {
		return failedAttempts;
	}

	public void setFailedAttempts(int failedAttempts) {
		this.failedAttempts = failedAttempts;
	}

	public Timestamp getLastFailed() {
		return lastFailed;
	}

	public void setLastFailed(Timestamp lastFailed) {
		this.lastFailed = lastFailed;
	}

	public int getLocked() {
		return locked;
	}

	public void setLocked(int locked) {
		this.locked = locked;
	}

	public Timestamp getLockedOn() {
		return lockedOn;
	}

	public void setLockedOn(Timestamp lockedOn) {
		this.lockedOn = lockedOn;
	}


	public Timestamp getFirstFailed() {
		return firstFailed;
	}


	public void setFirstFailed(Timestamp firstFailed) {
		this.firstFailed = firstFailed;
	}


}
