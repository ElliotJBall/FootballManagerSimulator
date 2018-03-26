package com.elliot.footballmanager.menu;

/**
 * @author Elliot
 * Helper class to methods that can be shared across 
 * all menu related classes. 
 */
public class MenuHelper {

	public MenuHelper() {

	}

	/**
	 * TODO: Find an alternative method to removing previous contents 
	 * from the console.
	 * Helper method that will remove all previous contents from the console.
	 */
	public static void clearConsole() {
		for (int i = 0; i < 25; i++) {
			System.out.println();
		}
	}
}
