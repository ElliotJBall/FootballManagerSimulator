package com.elliot.footballmanager.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.elliot.footballmanager.DateUtils;
import com.elliot.footballmanager.fixture.Fixture;
import com.elliot.footballmanager.gamemanager.GameManager;

/**
 * Once a user has loaded / created a save game the MainMenu class provides 
 * the list of options available throughout the FootballManager simulator.
 * @author Elliot
 */
public class MainMenu implements GameMenu {

	private static Scanner scanner  = new Scanner(System.in);
	private GameManager gameManager;
	
	public MainMenu() {
		
	}

	public MainMenu(GameManager gameManager) {
		this.gameManager = gameManager;
		
		beginMenuSelectionLoop();
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
	 * [5] : Go to match day menu
	 */
	public void beginMenuSelectionLoop() {
		System.out.println("Press 0 at any point to save the game and quit!");
		displayMenuOptions();
		
		boolean quit = false;
		do {
			try {
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
					case 5:
						openMatchDayMenu();
						break;
					default:
						System.out.println("Invalid selection! Please try again.");
						break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid selection! Please try again.");
				scanner.next();
			}
		} while (!quit);
		scanner.close();
	}
	
	public void displayMenuOptions() {
		System.out.println("[1] Simulate / Progress Game");
		System.out.println("[2] View football team options");
		System.out.println("[3] View upcoming fixtures");
		// (E.g. 01/01/2020 | Current Team  | League Position)
		System.out.println("[4] View quick information information");		
		System.out.println("[5] Open match day menu");
	}
	
	private void displayQuickInfo() {
		System.out.println(this.getGameManager().getQuickGameInfo());
	}
	
	private GameManager getGameManager() {
		return gameManager;
	}
	
	private void getUpcomingFixtures() {
		for (Fixture fixture : this.getGameManager().getUpcomingFixtures()) {
			System.out.println(DateUtils.FIXTURE_DATE_DISPLAY_FORMAT.format(fixture.getDateOfFixture())
					+ " " + "VS" + " "
					+ fixture.getHomeTeam().getTeamName()
					+ " " + "|" + " "
					+ fixture.getAwayTeam().getTeamName());
		}
	}

	private void openMatchDayMenu() {
		if (!this.getGameManager().isMatchDay()) {
			System.out.println("There is no match to play! It is not currently a Match day. " +
					"Please simulate the game to a Match day first.");
			displayMenuOptions();
			return;
		}

		GameMenu matchDayMenu = new MatchDayMenu(gameManager);
		matchDayMenu.beginMenuSelectionLoop();
	}
}
