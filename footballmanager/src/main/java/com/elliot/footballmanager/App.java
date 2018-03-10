package com.elliot.footballmanager;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.elliot.footballmanager.footballteam.FootballTeam;

/**
 * Hello world!
 *
 */
public class App {
	
	private static Map<Integer, FootballTeam> footballTeams = new HashMap<Integer, FootballTeam>();
	private static Scanner scanner  = new Scanner(System.in);
	
    public static void main( String[] args ) {
    	addTeamsToMap();
    	
    	initialiseMenu();
    }
    
    private static void addTeamsToMap() {
    	//TODO: Remove the hard coded values and replace them with a list of countries, leagues and football teams
    	// that are stored in a database (SQL Lite)
    	footballTeams.put(1, new FootballTeam("Everton"));
		footballTeams.put(2, new FootballTeam("Chelsea"));
    	footballTeams.put(3, new FootballTeam("Liverpool"));
		footballTeams.put(4, new FootballTeam("Manchester United"));
    	footballTeams.put(5, new FootballTeam("Crystal Palace"));
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
        		case 1:
        			setupNewGame();
        			quit = true;
        			break;
        		case 2:
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
    	System.out.println("Welcome to Elliot's Java Football Manager!");
    	System.out.println("Please select the team you would like to play as:");
    	
    	displayListOfTeams();
    	
    	int choice;
    	boolean selected = false;
    	
    	do {
    		choice  = scanner.nextInt();
    		
    		if (footballTeams.get(choice) != null) {
    			FootballTeam selectedTeam = footballTeams.get(choice);
        		System.out.println("------------------------------------------------------------");
        		System.out.println("You have selected: " + selectedTeam.getTeamName());
        		selected = true;		
    		} else {
    			System.out.println("Invalid option, please try again.");    			
    		}
    	} while (!selected);
    }
    
    private static void displayListOfTeams() {
    	int counter = 0;
    	
    	for (FootballTeam team : footballTeams.values()) {
    		counter++;
    		System.out.println("[" + counter + "]" + team.getTeamName());
		}
    }
}
