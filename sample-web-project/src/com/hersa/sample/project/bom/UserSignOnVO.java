package com.hersa.sample.project.bom;

import java.sql.SQLException;
import java.util.Date;

import com.hersa.sample.project.dao.user.User;
import com.hersa.sample.project.dao.usersignon.UserSignOn;
import com.hersa.sample.project.dao.usersignon.UserSignOnFinderException;

/**
 * @author Victor
 * This class is used for usermanagement
 */
public class UserSignOnVO {
	
	/*=================================================
	 * Member Variables
	 * ================================================*/
	
	private User userDTO;
	private UserSignOn userSignOnDTO;
	
	/*=================================================
	 * Constructors
	 * ================================================*/
	
	public UserSignOnVO(User userDTO){
		this.userDTO = userDTO;
		setUserSignOnDTO(userDTO);
	}
	public UserSignOnVO(){
		
	}
	
	/*=================================================
	 * DTO Objects Accessors
	 * ================================================*/
	
	public User getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(User userDTO) {
		this.userDTO = userDTO;
	}

	public UserSignOn getUserSignOnDTO() {
		return userSignOnDTO;
	}

	public void setUserSignOnDTO(UserSignOn userSignonDTO) {
		this.userSignOnDTO = userSignonDTO;
	}
	
	/*=================================================
	 * DTO Accessors
	 * ================================================*/
	
	//userDTO accessors
	
	public String getEmail() {
		return this.userDTO.getEmail();
	}

	public void setEmail(String email) {
		this.userDTO.setEmail(email);
	}

	public int getActive() {
		return this.userDTO.getActive();
	}

	public void setActive(int active) {
		this.userDTO.setActive(active); 
	}

	public String getRole() {
		return this.userDTO.getRole();
	}

	public void setRole(String role) {
		this.userDTO.setRole(role);
	}

	public String getCreatedBy() {
		return this.userDTO.getCreatedBy();
	}

	public void setCreatedBy(String createdBy) {
		this.userDTO.setCreatedBy(createdBy);
	}

	public String getModifiedBy() {
		return this.userDTO.getModifiedBy();
	}
	
	public void setModifiedBy(String modifiedBy) {
		this.userDTO.setModifiedBy(modifiedBy);;
	}


	public Date getModifiedDate() {
		return this.userDTO.getModifiedDate();
	}

	public void setModifiedDate(Date modifiedDate) {
		this.userDTO.setModifiedDate(modifiedDate);
	}

	public String getUserName() {
		return this.userDTO.getUserName();
	}

	public void setUserName(String userName) {
		this.userDTO.setUserName(userName);
	}
	
	//userSignOnDTO accessors
	
	public long getRowid() {
		return this.userSignOnDTO.getRowid();
	}
	public void setRowid(long rowid) {
		this.userSignOnDTO.setRowid(rowid);;
	}

	public int getFailedAttempts() {
		return this.userSignOnDTO.getFailedAttempts();
	}
	public void setFailedAttempts(int failedAttempts) {
		this.userSignOnDTO.setFailedAttempts(failedAttempts);
	}
	public Date getLastFailed() {
		return this.userSignOnDTO.getLastFailed();
	}
	public void setLastFailed(Date lastFailed) {
		this.userSignOnDTO.setLastFailed(lastFailed);
	}
	public int getLocked() {
		return this.userSignOnDTO.getLocked();
	}
	public void setLocked(int locked) {
		this.userSignOnDTO.setLocked(locked);
	}
	public Date getLockedOn() {
		return this.userSignOnDTO.getLockedOn();
	}
	public void setLockedOn(Date lockedOn) {
		this.userSignOnDTO.setLockedOn(lockedOn);
	}
	public Date getFirstFailed() {
		return this.userSignOnDTO.getFirstFailed();
	}
	public void setFirstFailed(Date firstFailed) {
		this.userSignOnDTO.setFirstFailed(firstFailed);
	}
	public int getRecentUnlock() {
		return this.userSignOnDTO.getRecentUnlock();
	}
	public void setRecentUnlock(int recentUnlock) {
		this.userSignOnDTO.setRecentUnlock(recentUnlock);
	}

	public Date getLastLogin() {
		return this.userSignOnDTO.getLastLogin();
	}

	public void setLastLogin(Date lastLogin) {
		this.userSignOnDTO.setLastLogin(lastLogin);
	}

	public long getUserid() {
		return this.userSignOnDTO.getUserid();
	}

	public void setUserid(long userid) {
		this.userSignOnDTO.setUserid(userid);
	}
	
	
	/**
	 * @param userDTO
	 * 
	 * Retrieves and sets corresponding userSignOn info for current user.
	 */
	public void setUserSignOnDTO(User userDTO) {
		UserSignOnManager usm = new UserSignOnManager();
		
		try {
			this.userSignOnDTO = usm.retrieveUserSignOnByUserId(userDTO.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserSignOnFinderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
