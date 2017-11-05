package com.hersa.sample.project.console;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hersa.sample.project.bom.UserManager;
import com.hersa.sample.project.console.utils.Utils;
import com.hersa.sample.project.dao.user.User;
import com.hersa.sample.project.utils.Constants;

public class Menu {
	/* ================================================================
	 * These functions are used to print module option menus.
	 * ===============================================================*/
	public static void printMenu(List<String> options) {
		int count = 1;
		for (String string : options) {
			System.out.printf("%-2s %-20s\n", count + ".)", string);
			count++;
		}
	}
	public static void dispMainMenuOptions() {
		int numOptions = Menu.printMainMenu();
		Utils.printValue("Please select an option: ");
		int choice = Utils.chooseInt();
		choice = Utils.validateIntChoice(choice, numOptions);
		RunMenuOptions.runMainOption(choice);
	}
	/*-------------------------------------Main Menu-------------------------------------*/
	public static void dispUserMenuOptions() {
		int numOptions = Menu.printUserMainMenu();
		Utils.printValue("Please select an option: ");
		int choice = Utils.chooseInt();
		choice = Utils.validateIntChoice(choice, numOptions);
		RunMenuOptions.runUserMainOption(choice);
	}
	public static int printMainMenu() {
		Utils.printHeader("Main Menu");
		List<String> options = Constants.getMainMenuOptionsList();
		printMenu(options);
		Utils.printClose();
		return options.size();
	}
	/*-------------------------------User Management Module-------------------------------*/
	public static int printUserMainMenu() {
		Utils.printHeader("User Management Menu");
		List<String> options = Constants.getUserMainMenuOptionsList();
		printMenu(options);
		Utils.printClose();
		return options.size();
	}
	public static int printUserReturnMenu() {
		Utils.printHeader("Sub Menu");
		List<String> options = Constants.getReturnMenuOptionsList();
		printMenu(options);
		return options.size();
	}
	
	public static Map<String, Object> printRoleMenu() {
		Utils.printValue("Select Role: ");
		Map<String, Object> opMap = new HashMap<String, Object>();
		List<String> list = Constants.getRoleMenuOptions();
		Menu.printMenu(list);
		opMap.put("numOptions", list.size());
		opMap.put("optionsList", list);
		return opMap;
	}
//	public static Map<String, Object> printLockedUserList() {
//		Utils.printHeader("Locked Users");
//		Map<String, Object> opMap = new HashMap<String, Object>();
//		UserManager um = new UserManager();
//		List<User> users = um.retrieveByLocked(1);
//		printUserList(users);
//		opMap.put("numOptions", users.size());
//		opMap.put("userList", users);
//		System.out.println();
//		System.out.println("Total Locked Users: " + users.size());
//		Utils.printClose();
//		return opMap;
//	}
	public static Map<String, Object> printAllUserList() {
		Utils.printHeader("Users");
		Map<String, Object> opMap = new HashMap<String, Object>();
		UserManager um = new UserManager();
		List<User> users = um.retrieveAllUsers();
		printUserList(users);
		opMap.put("numOptions", users.size());
		opMap.put("userList", users);
		System.out.println();
		System.out.println("Total Users: " + users.size());
		Utils.printClose();
		return opMap;
	}
	public static Map<String, Object> printEditUserPropertyMenu() {
		Map<String, Object> opMap = new HashMap<String, Object>();
		List<String> options = Constants.getEditUserPropertyMenuOptions();
		printMenu(options);
		opMap.put("numOptions", options.size());
		return opMap; 
	}
	public static void printUserList(List<User> users) {
		String format = "%-3s %-15s %-15s %-15s %-20s\n";
		System.out.printf(format, "", "Role",      "Last Name", "First Name", "Email");
		System.out.printf(format, "", "---------", "---------", "---------", "---------");
		int count = 1;
		for (User user : users) {
			String firstName = user.getFirstName();
			String lastName  = user.getLastName();
			String email     = user.getEmail();
			String role      = user.getRole();
			System.out.printf(format, count + ".", role, lastName, firstName, email);
			count++;
		}
	}
	/*-------------------------------List Other Modules Here-------------------------------*/
}
