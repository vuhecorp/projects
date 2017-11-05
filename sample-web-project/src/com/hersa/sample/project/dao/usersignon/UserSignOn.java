package com.hersa.sample.project.dao.usersignon;

import java.util.Date;

/**
 * @author Victor
 *
 */
public class UserSignOn {
	
	/*====================
	 * Member Variables
	 * ===================*/
	private long rowid;
	private long userid;
	private int failedAttempts;
	private Date lastFailed;
	private int locked;
	private Date lockedOn;
	private Date firstFailed;
	private int recentUnlock;
	private Date lastLogin;
	
	/*====================
	 * Constructors
	 * ===================*/
	
	public UserSignOn() {
		
	}
	
	/*====================
	 * Accessor Methods
	 * ===================*/
	
	public long getRowid() {
		return rowid;
	}
	public void setRowid(long rowid) {
		this.rowid = rowid;
	}

	public int getFailedAttempts() {
		return failedAttempts;
	}
	public void setFailedAttempts(int failedAttempts) {
		this.failedAttempts = failedAttempts;
	}
	public Date getLastFailed() {
		return lastFailed;
	}
	public void setLastFailed(Date lastFailed) {
		this.lastFailed = lastFailed;
	}
	public int getLocked() {
		return locked;
	}
	public void setLocked(int locked) {
		this.locked = locked;
	}
	public Date getLockedOn() {
		return lockedOn;
	}
	public void setLockedOn(Date lockedOn) {
		this.lockedOn = lockedOn;
	}
	public Date getFirstFailed() {
		return firstFailed;
	}
	public void setFirstFailed(Date firstFailed) {
		this.firstFailed = firstFailed;
	}
	public int getRecentUnlock() {
		return recentUnlock;
	}
	public void setRecentUnlock(int recentUnlock) {
		this.recentUnlock = recentUnlock;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}
}
