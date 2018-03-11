package com.elliot.footballmanager;

import java.util.Scanner;

import com.elliot.footballmanager.country.Country;
import com.elliot.footballmanager.country.CountryDao;
import com.elliot.footballmanager.country.CountryDaoImpl;

/**
 * @author Elliot
 * This is the Main entry point into the application. A menu is provided displaying all 
 * the options available to the user.
 *
 */
public class App {
	
	private static Scanner scanner  = new Scanner(System.in);
	
    public static void main( String[] args ) {    	
    	initialiseMenu();
    }
    
    private static void initialiseMenu() {
        System.out.println("------------------------------------------------------------");
        System.out.println("Elliots Java Football Manager!");
        System.out.println("------------------------------------------------------------");
        System.out.println("Please choose one of the following options:");
        System.out.println("[1] Start New Game!");
        System.out.println("[2] Exit Game!");
        
        int choice;
        boolean quit = false;
        
        do {
        	choice  = scanner.nextInt();
        	
        	switch (choice) {
        		case 1: // [1] Start New Game
        			setupNewGame();
        			quit = true;
        			break;
        		case 2: // [2] Exit Game
        			System.out.println("Thanks for playing!");
        			quit = true;
        			break;
        		default: 
        			System.out.println("Invalid option, please try again.");
        	}
        } while (!quit);
        
        scanner.close();
    }
    
    private static void setupNewGame() {
    	System.out.println("Welcome to Elliot's Football Manager!");
    	System.out.println("Please select the country that you would like to play in:");
    	
    	CountryDao countryDao = new CountryDaoImpl();
    	
    	for (Country country : countryDao.getAllCountries()) {
			System.out.println("[" + country.getCountryId() + "]" + " " + country.getCountryName());
		}
    	//TODO: Add new Classes that connect to the Database and retrieve the information needed such as countries,
    	// Leagues, FootballTeams...
    }
}
