package com.elliot.footballmanager.menu;

import java.util.Scanner;

import com.elliot.footballmanager.fixture.Fixture;
import com.elliot.footballmanager.gamemanager.GameManager;

/**
 * Once a user has loaded / created a save game the MainMenu class provides 
 * the list of options available throughout the FootballManager simulator.
 * @author Elliot
 */
public class MainMenu {

	private static Scanner scanner  = new Scanner(System.in);
	private GameManager gameManager;
	
	public MainMenu() {
		
	}

	public MainMenu(GameManager gameManager) {
		this.gameManager = gameManager;
		
		beginMainGameLoop();
	}
	
	/**
	 * The main loop used to play the game and display the different
	 * pieces of information required.
	 * The options provided are: 
	 * [0] : Save and quit
	 * [1] : Simulate Game
	 * [2] : View football team options
	 * [3] : -
	 * [4] : View quick information information
	 */
	private void beginMainGameLoop() {
		MenuHelper.clearConsole();
		
		System.out.println("Press 0 at any point to save the game and quit!");
		displayMainGameOptions();
		
		boolean quit = false;
		do {	
			switch (scanner.nextInt()) {
				case 0:
					this.getGameManager().saveGameAndExit();
					quit = true;
					break;
				case 1:
					this.getGameManager().simulateGame();
					break;
				case 2:
					break;
				case 3: 
					getUpcomingFixtures();
					break;	
				case 4:
					displayQuickInfo();
					break;
			}
		} while (!quit);
	}
	
	private void displayMainGameOptions() {
		System.out.println("[1] Simulate / Progress Game");
		System.out.println("[2] View football team options");
		System.out.println("[3] View upcoming fixtures");		
		//TODO: Print a current timestamp of the date in which the simulation is in option 3
		// (E.g. 01/01/2020 | Current Team  | League Position)
		System.out.println("[4] View quick information information");		
	}
	
	private void displayQuickInfo() {
		System.out.println(this.getGameManager().getQuickGameInfo());
	}
	
	public GameManager getGameManager() {
		return gameManager;
	}
	
	private void getUpcomingFixtures() {
		for (Fixture fixture : this.getGameManager().getUpcomingFixtures()) {
			System.out.println(fixture.getHomeTeam().getTeamName() 
					+ " " + "VS" + " "
					+ fixture.getAwayTeam().getTeamName()
					+ " " + "|"
					+ fixture.getDateOfFixture());
		}
	}
}
