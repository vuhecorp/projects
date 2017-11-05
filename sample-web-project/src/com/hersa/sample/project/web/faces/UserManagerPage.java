package com.hersa.sample.project.web.faces;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.hersa.sample.project.DuplicateUserException;
import com.hersa.sample.project.bom.UserManager;
import com.hersa.sample.project.bom.UserSignOnManager;
import com.hersa.sample.project.bom.UserSignOnVO;
import com.hersa.sample.project.dao.user.User;
import com.hersa.sample.project.dao.usersignon.UserSignOn;
import com.hersa.sample.project.dao.usersignon.UserSignOnDeleteException;
import com.hersa.sample.project.utils.StaticMethodUtils;

@ManagedBean(name="userManagementBean")
@ViewScoped
public class UserManagerPage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7884563377621899855L;
	
	/* ========================================================
	 * Member Variables
	 * =======================================================*/
	
	/*User*/
	private User sessionUser;
	private User selectedUser;
	private User newUser;
	private List<User> allUsersList;
	private List<User> filteredUsers;
	private List<UserSignOnVO> userSignOnList;
	private List<UserSignOnVO> filteredUserSignOnList;
	
	private boolean dispNewUserPanel;
	private Map<String, String> userRoleMap;
	
	private String password1; //password
	private String password2; //verification
	public FacesContext context;
	
	private static final String CREATE = "create";
	private static final String UPDATE = "update";

	/*Message Strings*/
	private String userBtnText;
	
	/*Managers*/
	private UserManager um = new UserManager();
	
	@PostConstruct
	public void init() {
		initializeVariables();
		loadUsers();
		generateMaps();
	}
	public void createUser() {
		if (validateUser(newUser, CREATE)) {
			try {
				newUser.setCreatedBy(sessionUser.getUserName());
				newUser = userToLower(newUser);
				um.createUser(newUser);
				loadUsers();
				resetNewUserPanel();
				StaticMethodUtils.addFacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "User has been created successfully.");
			} catch (SQLException e) {
				StaticMethodUtils.addFacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "User could not be created.");
				e.printStackTrace();
			} catch (DuplicateUserException e) {
				StaticMethodUtils.addFacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage());
				e.printStackTrace();
			}
		}
	}
//	public void resetUser(){
//		try {
//			um.resetUser(selectedUser);
//			loadUsers();
//			StaticMethodUtils.addFacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "User has been unlocked successfully.");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			StaticMethodUtils.addFacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "User could not be unlocked.");
//		}
//	}
//	
	public void resetUser() throws Exception{
		try {
			UserSignOnManager usm = new UserSignOnManager();
			UserSignOn userSignOn = usm.retrieveUserSignOnByUserId(selectedUser.getId());
			userSignOn.setLocked(0);
			userSignOn.setRecentUnlock(1);
			userSignOn.setFailedAttempts(0);
			usm.updateUserSignOn(userSignOn);
			loadUsers();
			StaticMethodUtils.addFacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "User has been unlocked successfully.");
		} catch (Exception e) {
			StaticMethodUtils.addFacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "User could not be unlocked.");
		}
	
	}
	public void updateUser(){
		if (validateUser(selectedUser, UPDATE)) {
			try {
				selectedUser.setModifiedBy(sessionUser.getUserName());
				selectedUser.setModifiedDate(new Date());
				selectedUser = userToLower(selectedUser);
				um.updateUser(selectedUser);
				loadUsers();
				StaticMethodUtils.addFacesMessage(FacesMessage.SEVERITY_INFO,"Success!", "User has been updated successfully.");
			} catch (Exception e) {
				StaticMethodUtils.addFacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage());
			}
		}
	}
	
	public void deleteUser() {
		try {
			um.deleteUser(selectedUser);
			loadUsers();
			selectedUser = new User();
			StaticMethodUtils.addFacesMessage(FacesMessage.SEVERITY_INFO,"Success!", "User has been deleted successfully.");
		} catch (SQLException e) {
			StaticMethodUtils.addFacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "User not deleted." );
			e.printStackTrace();
		} catch (UserSignOnDeleteException e) {
			StaticMethodUtils.addFacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() );
			e.printStackTrace();
		}
	}
	/* ========================================================
	 * Auxiliary Methods
	 * =======================================================*/
	public User userToLower(User user) {
		String userName = user.getUserName().toLowerCase().trim();
		String fname = user.getFirstName().toLowerCase().trim();
		String lname = user.getLastName().toLowerCase().trim();
		String email = user.getEmail().toLowerCase().trim();
		
		user.setUserName(userName);
		user.setFirstName(fname);
		user.setLastName(lname);
		user.setEmail(email);
		
		return user;
	}
	public void initializeVariables(){
		sessionUser = new User();
		dispNewUserPanel = false;
		userBtnText = "New User";
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		sessionUser = (User) session.getAttribute("User");
		allUsersList = new ArrayList<User>();
		userRoleMap = new LinkedHashMap<String, String>();
		context =  FacesContext.getCurrentInstance();
		userSignOnList = new ArrayList<UserSignOnVO>();
	}
	public void generateMaps(){
		userRoleMap.put("--Select a Role--", "x");
		userRoleMap.put("System Admin", "sysadmin");
		userRoleMap.put("Admin", "admin");
		userRoleMap.put("User", "user");
	}
	public void loadUsers(){
		//load users
		allUsersList = new ArrayList<User>();
		allUsersList = um.retrieveAllUsers();
		
		//load usersignon objects
		userSignOnList = new ArrayList<UserSignOnVO>();
		for (User user : allUsersList) {
			UserSignOnVO vo = new UserSignOnVO(user);
			userSignOnList.add(vo);
		}
		
	}
	public void showNewUserPanel() {
		if (dispNewUserPanel) {
			dispNewUserPanel = false;
			userBtnText = "New User";
			newUser = new User();
		}else {
			dispNewUserPanel = true;
			userBtnText = "Cancel";
			newUser = new User();
		}
	}
	private void resetNewUserPanel() {
		newUser = new User();
		password1 = new String();
		password2 = new String();
	}
	private boolean validateUser(User user, String action) {
		// validate user input
		// for now, only validate passwords match;
		boolean valid = true;
		
		if (action.equals(CREATE)) {
			if(!validatePasswordMatch()) {
				valid = false;
				StaticMethodUtils.addFacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Passwords do not match.");
			}
		}
		
		if (user.getRole().equalsIgnoreCase("x")) {
			valid = false;
			StaticMethodUtils.addFacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Please select a Role.");
		}
		return valid;
	}
	private boolean validatePasswordMatch() {
		boolean valid = true;
		if (!password2.equals(password1)) {
			valid = false;
		}else {
			newUser.setPassword(password1);
		}
		return valid;
	}
	/*========================================================
	 * Accessors / Mutators
	 * =======================================================*/
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
	public boolean isDispNewUserPanel() {
		return dispNewUserPanel;
	}
	public void setDispNewUserPanel(boolean dispNewUserPanel) {
		this.dispNewUserPanel = dispNewUserPanel;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public UserManager getUm() {
		return um;
	}
	public FacesContext getContext() {
		return context;
	}
	public String getUserBtnText() {
		return userBtnText;
	}
	public User getNewUser() {
		return newUser;
	}
	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}
	public void setUserBtnText(String userBtnText) {
		this.userBtnText = userBtnText;
	}
	public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public List<User> getFilteredUsers() {
		return filteredUsers;
	}
	public void setFilteredUsers(List<User> filteredUsers) {
		this.filteredUsers = filteredUsers;
	}
	public List<UserSignOnVO> getUserSignOnList() {
		return userSignOnList;
	}
	public void setUserSignOnList(List<UserSignOnVO> userSignOnList) {
		this.userSignOnList = userSignOnList;
	}
	public List<UserSignOnVO> getFilteredUserSignOnList() {
		return filteredUserSignOnList;
	}
	public void setFilteredUserSignOnList(List<UserSignOnVO> filteredUserSignOnList) {
		this.filteredUserSignOnList = filteredUserSignOnList;
	}
}
