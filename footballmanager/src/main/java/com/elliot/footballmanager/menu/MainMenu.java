package com.elliot.footballmanager.menu;

import java.util.Scanner;

import com.elliot.footballmanager.gamemanager.GameManager;

/**
 * @author Elliot
 * Once a user has loaded / created a save game the MainMenu class provides 
 * the list of options available throughout the FootballManager simulator.
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
	
	private void beginMainGameLoop() {
		MenuHelper.clearConsole();
		System.out.println("Press 0 at any point to save the game and quit!");
		
		while (!scanner.next().equals("0")) {
		
		}		
	}
}
