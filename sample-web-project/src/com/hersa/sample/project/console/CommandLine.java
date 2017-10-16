package com.hersa.sample.project.console;

import com.hersa.sample.project.bom.AuthenticationManager;
import com.hersa.sample.project.console.utils.Utils;
import com.hersa.sample.project.dao.user.User;

public class CommandLine {
	
	/**
	 * @param args
	 */
	public static boolean authenticated;
	
	public static void main(String[] args) {
		String loggedIn;
		/*Check id user is logged in.*/
		try {loggedIn = args[0];} 
		catch (Exception e) { loggedIn = null;}
		
		if (loggedIn != null) {
			authenticated = true;
		}else {
			authenticated = login();
		}
		/*Display main menu.*/
		if (authenticated) {
			Menu.dispMainMenuOptions(); 
		}else {
			main(null);
		}
		
	}
	
	/*==================================================================================================
	 * Authentication Functions
	 * ================================================================================================*/
	
	private static boolean login() {
		boolean authenticated = false;
		Utils.printHeader("Login");
		AuthenticationManager am = new AuthenticationManager();
		String username = null;
		String password = null;
		Utils.printValue("Enter Username:");
		username = Utils.chooseString();
		System.out.println();
		Utils.printValue("Enter Password:");
		password = Utils.chooseString();
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
			Utils.printValue("Welcome " + sessionUser.getFirstName() + "!");
		}else{
			System.err.println("Authentication failed.");
		}
		
		return authenticated;
	}
	
	public static void logOut() {
		main(null);
	}
	
}
