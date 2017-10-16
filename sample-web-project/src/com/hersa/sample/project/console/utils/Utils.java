package com.hersa.sample.project.console.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utils {
	/*==================================================================================================
	 * Auxiliary 'Print' Functions
	 * ================================================================================================*/
	
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

	public static void printValue(String value) {
		System.out.println(value);
		System.out.println();
	}
	
	/*==================================================================================================
	 * Validation Functions
	 * ================================================================================================*/
	
	public static int validateIntChoice(int choice, int numOptions) {
		while (choice < 1 || choice > numOptions) {
			choice = chooseInt();
		}
		return choice;
	}
	public static boolean validateBoolean(String choice1) {
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
	/*==================================================================================================
	 * Console Input Functions
	 * ================================================================================================*/
	
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
	
}
