package com.hersa.sample.project.console;

import com.hersa.sample.project.console.bom.UserManagement;
import com.hersa.sample.project.console.utils.Utils;

public class RunMenuOptions {
	
	/*========================================================================
	 * Run User Selection Functions
	 * 
	 * These functions run the users selection for 
	 * specific module menus and corresponding return 
	 * menus.
	 * =======================================================================*/
	
	/*-Main Menu-*/
	public static void runMainOption(int choice) {
		switch (choice) {
		case 1:
			Menu.dispUserMenuOptions();
			break;
		case 2:
			CommandLine.logOut();
			break;	
		default:
			break;
		}
	}
	
	/*------------------------User Management Module-------------------------*/
	
	/*-Module Menu-*/
	public static void runUserMainOption(int choice) {
		switch (choice) {
		case 1:
			Menu.printAllUserList();
			break;
		case 2:
			UserManagement.addUser();
			break;
		case 3:
//			UserManagement.deleteUser();
			break;
		case 4:
			UserManagement.editUser();
			break;
		case 5:
//			UserManagement.unlockUser();
			break;	
		case 6:
			CommandLine.logOut();
			break;	
		default:
			break;
		}
		runUserReturnOptions(choice);
	}
	
	/*-Return Menu- */
	public static void runUserReturnOptions(int choice) {
		int numOptions = Menu.printUserReturnMenu();
		int runOption = Utils.chooseInt();
		runOption = Utils.validateIntChoice(runOption, numOptions);
		
		switch (runOption) {
		case 1:
			runUserMainOption(choice);
			break;
		case 2:
			Menu.dispUserMenuOptions();
			break;
		case 3:
			String[] args = {"loggedIn"};
			CommandLine.main(args);
			break;
		case 4:
			System.exit(0);
			break;
		default:
			break;
		}
	}
	
	/*------------------------List Other Modules Here-------------------------*/
	
}
