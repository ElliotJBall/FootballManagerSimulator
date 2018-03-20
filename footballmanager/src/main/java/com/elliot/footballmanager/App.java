package com.elliot.footballmanager;

import com.elliot.footballmanager.menu.StartMenu;

/**
 * @author Elliot
 * This is the Main entry point into the application. Calls the MainMenu class
 * to instansiate a new Menu for the user and list all of the available options.
 *
 */
public class App {
	
    public static void main( String[] args ) {
    	new StartMenu();
    }
}
