package com.hersa.sample.project.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hersa.sample.project.bom.UserManager;
import com.hersa.sample.project.bom.UserSignOnManager;
import com.hersa.sample.project.dao.user.User;
import com.hersa.sample.project.dao.usersignon.UserSignOn;
import com.hersa.sample.project.dao.usersignon.UserSignOnCreateException;
import com.hersa.sample.project.dao.usersignon.UserSignOnDeleteException;
import com.hersa.sample.project.dao.usersignon.UserSignOnFinderException;

public class Test {

	public static void main(String[] args) {
		
//		System.out.println("---------------------------------------");
//		listUserSignon();
//		System.out.println("---------------------------------------");
//		updateUser();
		
	//	deleteUser();
		//
		try {
			resetUser("victor@email.com");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createInitialUser();
	}
	
	private static void createInitialUser() {
		
		
	}
	public static void resetUser(String email) throws Exception{
		UserManager um = new UserManager();
		UserSignOnManager usm = new UserSignOnManager();
		User user = um.getUserByUsername(email);
		UserSignOn userSignOn = usm.retrieveUserSignOnByUserId(user.getId());
		userSignOn.setLocked(0);
		userSignOn.setRecentUnlock(1);
		userSignOn.setFailedAttempts(0);
		usm.updateUserSignOn(userSignOn);
	}
	@SuppressWarnings("unused")
	private static void deleteUser() {
		UserSignOnManager usm = new UserSignOnManager();
		UserSignOn signon = new UserSignOn();
		signon.setUserid(59);
		signon.setFailedAttempts(54);
		Date now = new Date();
		signon.setFirstFailed(now);
		signon.setLastFailed(now);
		signon.setRecentUnlock(0);
		signon.setLockedOn(now);
		signon.setLocked(0);
		
		try {
			usm.deleteUserSignOn(signon);
			System.err.println("User deleted.");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (UserSignOnDeleteException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings("unused")
	private static void updateUser() {
		UserSignOnManager usm = new UserSignOnManager();
		UserSignOn signon = new UserSignOn();
		signon.setUserid(60);
		signon.setFailedAttempts(54);
		Date now = new Date();
		signon.setFirstFailed(now);
		signon.setLastFailed(now);
		signon.setRecentUnlock(0);
		signon.setLockedOn(now);
		signon.setLocked(0);
		
		try {
			usm.updateUserSignOn(signon);
		} catch (SQLException | UserSignOnFinderException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
	}

	public static void createUserSignon() {
		UserSignOnManager usm = new UserSignOnManager();
		UserSignOn signon = new UserSignOn();
		signon.setUserid(60);
		signon.setFailedAttempts(2);
		Date now = new Date();
		signon.setFirstFailed(now);
		signon.setLastFailed(now);
		signon.setRecentUnlock(0);
		signon.setLockedOn(now);
		signon.setLocked(0);
		try {
			usm.createUserSignOn(signon);
			System.err.println("User Created");
		} catch (SQLException | UserSignOnCreateException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

	}
	public static void listUserSignon() {
		UserSignOnManager usm = new UserSignOnManager();
		List<UserSignOn> list = new ArrayList<UserSignOn>();
		
		try {
			list = usm.retrieveAllUserSignOn();
		} catch (UserSignOnFinderException e) {
			System.err.println(e.getMessage());
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
		if (list.size() > 0 && list != null) {
			for (UserSignOn userSignOn : list) {
				System.out.println(userSignOn.getUserid());
				System.out.println(userSignOn.getFailedAttempts());
				System.out.println(userSignOn.getFirstFailed());
				System.out.println(userSignOn.getRecentUnlock());
			}
		}
	}
}
