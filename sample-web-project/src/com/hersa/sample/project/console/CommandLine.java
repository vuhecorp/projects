package com.hersa.sample.project.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hersa.sample.project.bom.AuthenticationManager;
import com.hersa.sample.project.bom.UserManager;
import com.hersa.sample.project.dao.user.User;
import com.hersa.sample.project.utils.Constants;

public class CommandLine {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		boolean authenticated = login();
		if (authenticated) {
			int numOptions = printMainMenu();
			printValue("Please select an option: ");
			int choice = chooseInt();
			choice = validateIntChoice(choice, numOptions);
			runOption(choice);
		}else {
			main(null);
		}
		
		
	}
	private static boolean login() {
		boolean authenticated = false;
		printHeader("Login");
		AuthenticationManager am = new AuthenticationManager();
		String username = null;
		String password = null;
		printValue("Enter Username:");
		username = chooseString();
		System.out.println();
		printValue("Enter Password:");
		password = chooseString();
		
		User sessionUser = null;
		try {
			sessionUser = am.authenticateUser(username, password);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			sessionUser = null;
		}
		
		if (sessionUser != null && sessionUser.getRole().equalsIgnoreCase("sysAdmin")) {
			authenticated = true;
			System.out.println();
			printValue("Welcome " + sessionUser.getFirstName() + "!");
		}else{
			System.err.println("Authentication failed.");
		}
		
		return authenticated;
	}
	private static void runOption(int choice) {
		switch (choice) {
		case 1:
			printUserList();
			break;
		case 2:
			addUser();
			break;
		case 3:
			deleteUser();
			break;
		case 4:
			editUser();
			break;
		case 5:
			unlockUser();
			break;	
		default:
			break;
		}
		
		returnOptions(choice);
		
	}
	
	private static void unlockUser() {
		UserManager um = new UserManager();
		Map<String, Object> map = printLockedUserList();
		int numOptions = (int) map.get("numOptions");
		@SuppressWarnings("unchecked")
		List<User> userList = (List<User>) map.get("userList");
		if (numOptions > 0) {
			printValue("Select a user to unlock: ");
			int choice = chooseInt();
			choice = validateIntChoice(choice, numOptions);
			User user = userList.get(choice - 1);
			
			printValue("User to unlock: " + userList.get(choice - 1).getLastName() 
					  + " , " + userList.get(choice - 1).getFirstName());
			printValue("Unlock User?: Y/N");
			String choice1 = chooseString();
			boolean boolChoice1 = validateBoolean(choice1);
			if (boolChoice1) {
			try {
			um.resetUser(user);
			System.err.println("User unlocked.");
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}else {
			System.err.println("User not unlocked.");
			}
		}else{
			printValue("There are no locked users.");
		}
		
				
		
		
	}
	private static Map<String, Object> printLockedUserList() {
		printHeader("LOCKED USERS");
		Map<String, Object> opMap = new HashMap<String, Object>();
		UserManager um = new UserManager();
		String format = "%-3s %-15s %-15s %-15s %-20s\n";
		List<User> users = um.retrieveByLocked(1);
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
		opMap.put("numOptions", users.size());
		opMap.put("userList", users);
		System.out.println();
		System.out.println("Total Users: " + users.size());
		printClose();
		return opMap;
	}
	private static void editUser() {
		// TODO Auto-generated method stub
		
	}
	private static void deleteUser() {
		UserManager um = new UserManager();
		Map<String, Object> map = printUserList();
		int numOptions = (int) map.get("numOptions");
		@SuppressWarnings("unchecked")
		List<User> userList = (List<User>) map.get("userList");
		printValue("Select a user to delete: ");
		int choice = chooseInt();
		choice = validateIntChoice(choice, numOptions);
		printValue("User to delete: " + userList.get(choice - 1).getLastName() 
									  + " , " + userList.get(choice - 1).getFirstName());
		printValue("Delete User?: Y/N");
		String choice1 = chooseString();
		boolean boolChoice1 = validateBoolean(choice1);
		if (boolChoice1) {
			try {
				um.deleteUser(userList.get(choice - 1));
				System.err.println("User deleted.");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.err.println("User not deleted.");
		}
	}
	private static void addUser() {
		printHeader("New User");
		User newUser = new User();
		System.err.println();
		printValue("Enter First Name: ");
		newUser.setFirstName(chooseString());
		System.err.println();
		printValue("Enter Last Name: ");
		newUser.setLastName(chooseString());
		System.err.println();
		printValue("Enter Email: ");
		newUser.setEmail(chooseString());
		System.err.println();
		printValue("Enter Password: ");
		newUser.setPassword(chooseString());
		
		Map<String, Object> map = printRoleMenu();
		@SuppressWarnings("unchecked")
		List<String> opList = (List<String>) map.get("optionsList");
		int numOptions = (int) map.get("numOptions");
		int choice = chooseInt();
		choice = validateIntChoice(choice, numOptions);
		newUser.setRole(opList.get(choice - 1));
		printReview(newUser);
		
		printValue("Save?: Y/N");
		String choice1 = chooseString();
		boolean boolChoice1 = validateBoolean(choice1);
		if (boolChoice1) {
			try {
				UserManager um = new UserManager();
				um.createUser(newUser);
				System.out.println();
				System.err.println("New user created.");
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
		}else {
			System.err.println();
			System.err.println("User not created.");
		}
	}
	private static boolean validateBoolean(String choice1) {
		boolean valid = false;
		if (choice1.equalsIgnoreCase("y" ) || choice1.equalsIgnoreCase("n")) {
			valid = true;
		}
		while (!valid) {
			System.err.println("Invalid Input. Please type 'Y' or 'N'.");
			choice1 = chooseString();
		}
		if (choice1.equalsIgnoreCase("n")) {
			valid = false;
		}
		return valid;
	}
	private static void printReview(User newUser) {
		printHeader("Review");
		String format = "%-15s %-10s\n";
		System.out.printf(format, "First Name: ", newUser.getFirstName());
		System.out.printf(format, "Last Name: ", newUser.getLastName());
		System.out.printf(format, "Email: ", newUser.getEmail());
		System.out.printf(format, "Role: ", newUser.getRole());
		printClose();
	}
	private static Map<String, Object> printRoleMenu() {
		printValue("Select Role: ");
		Map<String, Object> opMap = new HashMap<String, Object>();
		List<String> list = Constants.getRoleMenuOptions();
		printMenu(list);
		opMap.put("numOptions", list.size());
		opMap.put("optionsList", list);
		return opMap;
	}
	public static Map<String, Object> printUserList() {
		printHeader("USERS");
		Map<String, Object> opMap = new HashMap<String, Object>();
		UserManager um = new UserManager();
		String format = "%-3s %-15s %-15s %-15s %-20s\n";
		List<User> users = um.retrieveAllUsers();
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
		opMap.put("numOptions", users.size());
		opMap.put("userList", users);
		System.out.println();
		System.out.println("Total Users: " + users.size());
		printClose();
		return opMap;
	}
	public static void printHeader(String title) {
		System.out.println();
		System.out.println("--------------------" + title + "--------------------");
		System.out.println();
	}
	public static void printClose() {
		System.out.println();
		System.out.println("-----------------------------------------------------");
		System.out.println();
	}
	public static void init() {
		try{Thread.sleep(500);}catch(InterruptedException e){System.out.println();}  
		System.out.println();
	}
	
	public static void printValue(String value) {
		System.out.println(value);
		System.out.println();
	}
	public static int chooseInt() {
		int option = 0;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			option = Integer.parseInt(reader.readLine());
		} catch (IOException e) {
			System.err.println("Invalid Input");
		}
		return option;
	}
	public static String chooseString() {
		String option = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			option = reader.readLine();
		} catch (IOException e) {
			System.err.println("Invalid Input");
		}
		return option;
	}
	public static void printMenu(List<String> options) {
		int count = 1;
		for (String string : options) {
			System.out.printf("%-2s %-20s\n", count + ".)", string);
			count++;
		}
	}
	public static int printMainMenu() {
		printHeader("OPTIONS");
		List<String> options = Constants.getMainMenuOptionsList();
		printMenu(options);
		printClose();
		return options.size();
	}
	public static int printReturnMenu() {
		printHeader("SUB MENU");
		List<String> options = Constants.getReturnMenuOptionsList();
		printMenu(options);
		return options.size();
	}
	public static int validateIntChoice(int choice, int numOptions) {
		while (choice < 1 || choice > numOptions) {
			choice = chooseInt();
		}
		return choice;
	}
	private static void returnOptions(int choice) {
		int numOptions = printReturnMenu();
		int runOption = chooseInt();
		runOption = validateIntChoice(runOption, numOptions);
		
		switch (runOption) {
		case 1:
			runOption(choice);
			break;
			
		case 2:
			main(null);
			break;
			
		case 3:
			System.exit(0);
			break;
			
		default:
			break;
		}
	}
	
	
}
