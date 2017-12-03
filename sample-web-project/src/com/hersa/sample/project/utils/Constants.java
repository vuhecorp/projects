package com.hersa.sample.project.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {
	
	//application constants
	public static final String APPLICATON_CLIENT = "java:comp/env/configuration/applicationClient";
	public static final String AUTH_PROVIDER = "sample_project";
	public static final String USER_PROVIDER = "sample_project";
	
	public static final String USERS = "users";
	public static final String USER_PROFILE = "userprofile";
	//CommandLine menu options
	public static String[] MAIN_MENU_OPTIONS = {"User Management","Log Out"};
	public static String[] USER_MAIN_MENU_OPTIONS = {"List Users","Add User","Delete User", "Edit User", "Unlock User", "Log Out"};
	public static String[] RETURN_MENU_OPTIONS = {"Run Again","Return to Menu","Return to Main Menu","Log Out"};
	public static String[] ROLE_MENU_OPTIONS = {"user","admin","sysAdmin"};
	public static String[] EDIT_USER_PROPERTY_OPTIONS = {"Keep value", "Enter new value."};
	
	public static String getAUTH_PROVIDER() {
		return AUTH_PROVIDER;
	}

	public static String getUSER_PROVIDER() {
		return USER_PROVIDER;
	}
	public static List<String> getUserMainMenuOptionsList(){
		List<String> list = new ArrayList<String>();
		for (String string : USER_MAIN_MENU_OPTIONS) {
			list.add(string);
		}
		return list;
	}
	public static List<String> getMainMenuOptionsList(){
		List<String> list = new ArrayList<String>();
		for (String string : MAIN_MENU_OPTIONS) {
			list.add(string);
		}
		return list;
	}
	public static List<String> getReturnMenuOptionsList(){
		List<String> list = new ArrayList<String>();
		for (String string : RETURN_MENU_OPTIONS) {
			list.add(string);
		}
		return list;
	}

	public static List<String> getRoleMenuOptions() {
		List<String> list = new ArrayList<String>();
		for (String string : ROLE_MENU_OPTIONS) {
			list.add(string);
		}
		return list;
	}
	public static List<String> getEditUserPropertyMenuOptions(){
		List<String> list = new ArrayList<String>();
		for (String string : EDIT_USER_PROPERTY_OPTIONS) {
			list.add(string);
		}
		return list;
	}
}
