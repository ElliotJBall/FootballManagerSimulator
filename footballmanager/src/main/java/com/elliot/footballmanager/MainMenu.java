package com.elliot.footballmanager;

import java.util.Map;
import java.util.Scanner;

import com.elliot.footballmanager.country.Country;
import com.elliot.footballmanager.country.CountryDao;
import com.elliot.footballmanager.country.CountryDaoImpl;
import com.elliot.footballmanager.footballteam.FootballTeam;
import com.elliot.footballmanager.footballteam.FootballTeamDao;
import com.elliot.footballmanager.footballteam.FootballTeamDaoImpl;
import com.elliot.footballmanager.league.League;
import com.elliot.footballmanager.league.LeagueDao;
import com.elliot.footballmanager.league.LeagueDaoImpl;
import com.elliot.footballmanager.manager.Manager;
import com.elliot.footballmanager.manager.ManagerDao;
import com.elliot.footballmanager.manager.ManagerDaoImpl;

/**
 * @author Elliot
 * The MainMenu class is used to create a new Menu object that displays all 
 * the options that area available to the user
 */
public class MainMenu {
	
	private GameManager gameManager;
	private static Scanner scanner  = new Scanner(System.in);
	private boolean quit = false;
	
	public MainMenu() {
		displayMenu();
	}
	
	public void displayMenu() {
        System.out.println("------------------------------------------------------------");
        System.out.println("Elliots Java Football Manager!");
        System.out.println("------------------------------------------------------------");
        System.out.println("Please choose one of the following options:");
        System.out.println("[0] Exit Game! (Please note you can hit 0 at any point to exit the game!");
        System.out.println("[1] Start New Game!");
        
        boolean quit = false;
        
        do {
        	switch (scanner.nextInt()) {
	        	case 0: // [0] Exit Game
	        		System.out.println("Thanks for playing!");
	        		quit = true;
	        		break;
        		case 1: // [1] Start New Game
        			gameManager = new GameManager();
        			createNewGame();
        			quit = true;
        			break;
        		default: 
        			System.out.println("Invalid option, please try again.");
        	}
        } while (!quit);
        
        scanner.close();
	}
	
	private void createNewGame() {
    	chooseCountry();
    	chooseLeague();
    	chooseTeam();
    	createNewManager();
    	//TODO: Add new Classes that connect to the Database and retrieve the information needed such as countries,
    	// Leagues, FootballTeams...
	}
	
	private void chooseCountry() {
		System.out.println("Please select the country that you would like to play in:");
		
		CountryDao countryDao = new CountryDaoImpl();
    	Map<Integer, Country> allCountries = countryDao.getAllCountries();
    	
    	for (Country country : allCountries.values()) {
			System.out.println(country.printCountryMenuInfo());
		}
    	
    	quit = false;
        do {
        	int choice = scanner.nextInt();
        	
        	if (allCountries.get(choice) != null) {
        		Country country = allCountries.get(choice);
        		gameManager.setSelectedCountry(country);
        		
        		System.out.println("You have selected : " + country.printCountryMenuInfo());
        		quit = true;
        	} else if (choice == 0) {
        		quit = true;
        	} else {        		
        		System.out.println("Invalid option, please try again.");
        	}
        	
        } while (!quit);
	}
	
	private void chooseLeague() {
		System.out.println("Please select the league that you would like to play in:");
		
		LeagueDao leagueDao = new LeagueDaoImpl();
		Map<Integer, League> leagues = leagueDao.getAllLeagues(gameManager.getSelectedCountry().getCountryId());
		
		for (League league : leagues.values()) {
			System.out.println(league.printLeagueMenuInfo());
		}
		
    	quit = false;
        do {
        	int choice = scanner.nextInt();
        	
        	if (leagues.get(choice) != null) {
        		League league = leagues.get(choice);
        		gameManager.setCurrentLeague(league);
        		
        		System.out.println("You have selected : " + league.printLeagueMenuInfo());
        		quit = true;
        	} else if (choice == 0){
        		quit = true;
        	} else {        		
        		System.out.println("Invalid option, please try again.");
        	}
        	
        } while (!quit);
	}
	
	private void chooseTeam() {
		System.out.println("Please select the FootballTeam that you would like to play as:");
		
		FootballTeamDao footballTeamDao = new FootballTeamDaoImpl();
		Map<Integer, FootballTeam> footballTeams = footballTeamDao.getAllFootballTeams(gameManager.getCurrentLeague().getLeagueId());
		
		for (FootballTeam footballTeam: footballTeams.values()) {
			System.out.println(footballTeam.printFootballTeamMenuInfo());
		}
		
		quit = false;
		do {
			int choice = scanner.nextInt();
			
			if (footballTeams.get(choice) != null) {
				FootballTeam footballTeam = footballTeams.get(choice);
				gameManager.setCurrentFootballTeam(footballTeam);
				
				System.out.println("You have selected : " + footballTeam.printFootballTeamMenuInfo());
				quit = true;
			} else if (choice == 0) {
				quit = true;
			} else {				
				System.out.println("Invalid option, please try again.");
			}
		} while (!quit);
	}
	
	private void createNewManager() {
		System.out.println("Please enter the managers first name:");
		
		String firstName, lastName;
		quit = false;
		
		do {
			firstName = scanner.next().toUpperCase();
			System.out.println("Is " + firstName + " correct? (Y/N)");
			
			if (scanner.next().equals("Y")) {
				quit = true;
			} else {
				System.out.println("Please enter the managers first name:");
			}
		} while (!quit);
		
		System.out.println("Please enter the managers last name:");
		quit = false;
		
		do {
			lastName = scanner.next().toUpperCase();
			System.out.println("Is " + lastName + " correct? (Y/N)");
			
			if (scanner.next().equals("Y")) {
				quit = true;
			} else {
				System.out.println("Please enter the managers last name:");
			}
		} while (!quit);
		
		// Set the Manager to the GameManager object and insert into the Manager table
		Manager manager = new Manager(firstName, lastName, gameManager.getCurrentFootballTeam());
		
		ManagerDao managerDao = new ManagerDaoImpl();
		managerDao.insertIntoManagerTable(manager);
		
		gameManager.setManager(manager);
	}
}
