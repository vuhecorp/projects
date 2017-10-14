package com.hersa.sample.project.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {
	public static final String AUTH_PROVIDER = "chat_db";
	public static final String USER_PROVIDER = "chat_db";
	//CommandLine menu options
	public static String[] MAIN_MENU_OPTIONS = {"List Users","Add User","Delete User", "Edit User", "Unlock User"};
	public static String[] RETURN_MENU_OPTIONS = {"Run Again","Return to Main Menu","Exit"};
	public static String[] ROLE_MENU_OPTIONS = {"user","admin","sysAdmin"};

	public static String getAUTH_PROVIDER() {
		return AUTH_PROVIDER;
	}

	public static String getUSER_PROVIDER() {
		return USER_PROVIDER;
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
	
}
