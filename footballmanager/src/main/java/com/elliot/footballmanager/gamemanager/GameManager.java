package com.elliot.footballmanager.gamemanager;

import com.elliot.footballmanager.country.Country;
import com.elliot.footballmanager.footballteam.FootballTeam;
import com.elliot.footballmanager.league.League;
import com.elliot.footballmanager.manager.Manager;

/**
 * @author Elliot
 * The GameManager class handles all the information required to successfully play the Football
 * Manager simulator.
 */
public class GameManager {

	private Country currentCountry;
	private League currentLeague;
	private FootballTeam currentFootballTeam;
	private Manager manager;
	
	public GameManager() {

	}
	
	
	
	public GameManager(Country currentCountry, League currentLeague, FootballTeam currentFootballTeam,
			Manager manager) {
		this.currentCountry = currentCountry;
		this.currentLeague = currentLeague;
		this.currentFootballTeam = currentFootballTeam;
		this.manager = manager;
	}



	/**
	 * Retrieves the selected Country, League, FootballTeam and Manager 
	 * from the database and instantiates a new GameManager object. 
	 */
	public void loadSavedGame() {
		GameManagerDao gameManagerDao = new GameManagerDaoImpl();getClass();
		gameManagerDao.loadSavedGame();
	}
	
	/**
	 * Persists the current information stored in the GameManager object 
	 * into the database.
	 */
	public void saveGame() {
		GameManagerDao gameManagerDao = new GameManagerDaoImpl();
		gameManagerDao.createNewSaveGame(this);
	}

	public Country getCurrentCountry() {
		return currentCountry;
	}

	public void setCurrentCountry(Country selectedCountry) {
		this.currentCountry = selectedCountry;
	}

	public League getCurrentLeague() {
		return currentLeague;
	}

	public void setCurrentLeague(League currentLeague) {
		this.currentLeague = currentLeague;
	}

	public FootballTeam getCurrentFootballTeam() {
		return currentFootballTeam;
	}

	public void setCurrentFootballTeam(FootballTeam footballTeam) {
		this.currentFootballTeam = footballTeam;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}
}
