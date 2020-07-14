package com.elliot.footballmanager;

import com.elliot.footballmanager.menu.StartMenu;

/**
 * This is the Main entry point into the application. Calls the MainMenu class to instantiate a new
 * Menu for the user and list all of the available options.
 *
 * @author Elliot
 */
public class FootballManagerApplication {

  public static void main(String[] args) {
    new StartMenu();
  }
}
