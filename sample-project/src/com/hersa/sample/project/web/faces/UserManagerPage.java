package com.hersa.sample.project.web.faces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.print.attribute.standard.Severity;
import javax.servlet.http.HttpSession;

import com.hersa.sample.project.bom.UserManager;
import com.hersa.sample.project.dao.user.User;
import com.hersa.sample.project.utils.StaticMethodUtils;

@ManagedBean(name="userManagementBean")
@SessionScoped
public class UserManagerPage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7884563377621899855L;
	/**
	 * 
	 */

	private User sessionUser;
	private User selectedUser;
	private List<User> allUsersList;
	
	private Map<String, String> userRoleMap;
	
	private UserManager um = new UserManager();
	FacesContext context;
	
	@PostConstruct
	public void init() {
		initializeVariables();
		loadUsers();
		generateMaps();
	}
	public void initializeVariables(){
		sessionUser = new User();
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		sessionUser = (User) session.getAttribute("User");
		allUsersList = new ArrayList<User>();
		userRoleMap = new HashMap<String, String>();
		context =  FacesContext.getCurrentInstance();
	}
	public void generateMaps(){
		userRoleMap.put("System Admin", "sysadmin");
		userRoleMap.put("Admin", "admin");
		userRoleMap.put("User", "user");
	}
	public void loadUsers(){
		allUsersList = new ArrayList<User>();
		allUsersList = um.retrieveAllUsers();
	}
	public void resetUser(){
		try {
			um.resetUser(selectedUser);
			loadUsers();
			StaticMethodUtils.addFacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "User has been unlocked successfully.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			StaticMethodUtils.addFacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "User could not be unlocked.");
		}
	}
	public void updateUser(){
		try {
			um.updateUser(selectedUser);
			loadUsers();
			StaticMethodUtils.addFacesMessage(FacesMessage.SEVERITY_INFO,"Success!", "User has been updated successfully.");
		} catch (Exception e) {
			StaticMethodUtils.addFacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "There was an error while updating this user." );
		}
	}
	public User getSessionUser() {
		return sessionUser;
	}
	
	public void setSessionUser(User sessionUser) {
		this.sessionUser = sessionUser;
	}
	public List<User> getAllUsersList() {
		return allUsersList;
	}
	public void setAllUsersList(List<User> allUsersList) {
		this.allUsersList = allUsersList;
	}
	public Map<String, String> getUserRoleMap() {
		return userRoleMap;
	}
	public void setUserRoleMap(Map<String, String> userRoleMap) {
		this.userRoleMap = userRoleMap;
	}
	public User getSelectedUser() {
		return selectedUser;
	}
	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

}
