package com.hersa.sample.project.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.hersa.sample.project.bom.UserManager;
import com.hersa.sample.project.dao.user.User;

public class CommandLine {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int numOptions = printMainMenu();
		printValue("Please select an option: ");
		int choice = chooseInt();
		choice = validateIntChoice(choice, numOptions);
		runOption(choice);
		
	}
	private static void runOption(int choice) {
		switch (choice) {
		case 1:
			printUserList();
			break;
		case 2:

			break;
		case 3:

			break;

		default:
			break;
		}
		
		returnOptions(choice);
		
	}
	
	public static void createUser() {
		
	}
	public static void printUserList() {
		UserManager um = new UserManager();
		List<User> users = um.retrieveAllUsers();
		System.out.printf("%-20s %-20s\n", "Last Name", "First Name");
		System.out.printf("%-20s %-20s\n", "---------", "----------");
		for (User user : users) {
			String firstName = user.getFirstName();
			String lastName = user.getLastName();
			System.out.printf("%-20s %-20s\n", lastName, firstName);
		}
		System.out.println();
		System.out.println("Total Users: " + users.size());
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
	
	public static void printInstruction(String instruction) {
		
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
	public static void printMenu(List<String> options) {
		int count = 1;
		for (String string : options) {
			System.out.printf("%-2s %-20s\n", count + ".)", string);
			count++;
		}
	}
	public static int printMainMenu() {
		printHeader("OPTIONS");
		List<String> options = new ArrayList<String>();
		options.add("List Users");
		options.add("Add User");
		options.add("Delete User");
		printMenu(options);
		printClose();
		return options.size();
	}
	public static int printReturnMenu() {
		printHeader("SUB MENU");
		List<String> options = new ArrayList<String>();
		options.add("Run again");
		options.add("Return to Main Menu");
		options.add("Exit");
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
