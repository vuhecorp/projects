package com.hersa.sample.project.console.bom;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.hersa.sample.project.bom.UserManager;
import com.hersa.sample.project.console.Menu;
import com.hersa.sample.project.console.utils.Utils;
import com.hersa.sample.project.dao.user.User;

public class UserManagement {
	/*==================================================================================================
	 * Business Functions
	 * ================================================================================================*/
	
	public static void unlockUser() {
		UserManager um = new UserManager();
		Map<String, Object> map = Menu.printLockedUserList();
		int numOptions = (int) map.get("numOptions");
		@SuppressWarnings("unchecked")
		List<User> userList = (List<User>) map.get("userList");
		if (numOptions > 0) {
			Utils.printValue("Select a user to unlock: ");
			int choice = Utils.chooseInt();
			choice = Utils.validateIntChoice(choice, numOptions);
			User user = userList.get(choice - 1);
			
			Utils.printValue("User to unlock: " + userList.get(choice - 1).getLastName() 
					  + " , " + userList.get(choice - 1).getFirstName());
			Utils.printValue("Unlock User?: Y/N");
			String choice1 = Utils.chooseString();
			boolean boolChoice1 = Utils.validateBoolean(choice1);
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
			Utils.printValue("There are no locked users.");
		}
	}
	public static void editUser() {
		UserManager um = new UserManager();
		//print user list to select a user.
		Map<String, Object> map = Menu.printAllUserList();
		
		//select a user from the list.
		int numOptions = (int) map.get("numOptions");
		@SuppressWarnings("unchecked")
		List<User> userList = (List<User>) map.get("userList");
		Utils.printValue("Select a user to edit: ");
		int choice = Utils.chooseInt();
		choice = Utils.validateIntChoice(choice, numOptions);
		User editUser =  userList.get(choice - 1);
		Utils.printValue("Editing User: " + editUser.getLastName() + ", " + editUser.getFirstName());
		
		//build a property string map to present user with properties to edit.
		Map<String, String> userValsMap = buildUserMap(editUser);
		editUser = editUserProperties(editUser, userValsMap);
		
	}
	private static User editUserProperties(User editUser, Map<String, String> userValsMap) {
		//iterate over property list and allow user to select an action.
		Utils.printHeader("Edit User Properties");
		
		for (Entry<String, String> user : userValsMap.entrySet()) {
			Utils.printValue("Property Name: " + user.getKey());
			Utils.printValue("Current Value: " + user.getValue());
			Utils.printValue("What would you like to do?");
			Map<String, Object> map1 = Menu.printEditUserPropertyMenu();
			int choice1 = Utils.chooseInt();
			choice1 = Utils.validateIntChoice(choice1, (Integer) map1.get("numOptions"));
			Utils.printClose();
		}
		
		return editUser;
	}
	
	private static Map<String, String> buildUserMap(User user) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("First Name", user.getFirstName());
		map.put("Last Name", user.getLastName());
		map.put("Email", user.getEmail());
		map.put("Role", user.getRole());
		return map;
	}
	public static void deleteUser() {
		UserManager um = new UserManager();
		Map<String, Object> map = Menu.printAllUserList();
		int numOptions = (int) map.get("numOptions");
		
		@SuppressWarnings("unchecked")
		List<User> userList = (List<User>) map.get("userList");
		Utils.printValue("Select a user to delete: ");
		int choice = Utils.chooseInt();
		choice = Utils.validateIntChoice(choice, numOptions);
		User userDelete = userList.get(choice - 1);
		Utils.printValue("User to delete: " + userDelete.getLastName() 
									  + " , " + userDelete.getFirstName());
		Utils.printValue("Delete User?: Y/N");
		String choice1 = Utils.chooseString();
		boolean boolChoice1 = Utils.validateBoolean(choice1);
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
	public static void addUser() {
		Utils.printHeader("New User");
		User newUser = new User();
		System.err.println();
		Utils.printValue("Enter First Name: ");
		newUser.setFirstName(Utils.chooseString());
		System.err.println();
		Utils.printValue("Enter Last Name: ");
		newUser.setLastName(Utils.chooseString());
		System.err.println();
		Utils.printValue("Enter Email: ");
		newUser.setEmail(Utils.chooseString());
		System.err.println();
		Utils.printValue("Enter Password: ");
		newUser.setPassword(Utils.chooseString());
		
		Map<String, Object> map = Menu.printRoleMenu();
		@SuppressWarnings("unchecked")
		List<String> opList = (List<String>) map.get("optionsList");
		int numOptions = (int) map.get("numOptions");
		int choice = Utils.chooseInt();
		choice = Utils.validateIntChoice(choice, numOptions);
		newUser.setRole(opList.get(choice - 1));
		Menu.printReview(newUser);
		
		Utils.printValue("Save?: Y/N");
		String choice1 = Utils.chooseString();
		boolean boolChoice1 = Utils.validateBoolean(choice1);
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

}
