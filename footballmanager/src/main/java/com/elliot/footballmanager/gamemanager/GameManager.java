package com.elliot.footballmanager.gamemanager;

import java.util.Date;
import java.util.List;

import com.elliot.footballmanager.country.Country;
import com.elliot.footballmanager.fixture.Fixture;
import com.elliot.footballmanager.fixture.FixtureDao;
import com.elliot.footballmanager.fixture.FixtureDaoImpl;
import com.elliot.footballmanager.footballteam.FootballTeam;
import com.elliot.footballmanager.league.League;
import com.elliot.footballmanager.manager.Manager;
import com.elliot.footballmanager.menu.MainMenu;

/**
 * The GameManager class handles all the information required to successfully play the Football
 * Manager simulator.
 * @author Elliot
 */
public class GameManager {

	private Country currentCountry;
	private League currentLeague;
	private FootballTeam currentFootballTeam;
	private Manager manager;
	private Date currentDate;
	private List<Fixture> upcomingFixtures;
	
	public GameManager() {

	}
	
	public GameManager(Country currentCountry, League currentLeague, FootballTeam currentFootballTeam,
			Manager manager, Date currentDate) {
		this.currentCountry = currentCountry;
		this.currentLeague = currentLeague;
		this.currentFootballTeam = currentFootballTeam;
		this.manager = manager;
		this.currentDate = currentDate;
	}

	/**
	 * Retrieves the selected Country, League, FootballTeam and Manager 
	 * from the database and instantiates a new GameManager object. 
	 */
	public void loadSavedGame() {
		GameManagerDao gameManagerDao = new GameManagerDaoImpl();getClass();
		gameManagerDao.loadSavedGame();
		
		FixtureDao fixtureDao = new FixtureDaoImpl();
		this.setUpcomingFixtures(fixtureDao.getFootballTeamsUpcomingFixtures(this.getCurrentFootballTeam()));
		
		new MainMenu(this);
	}
	
	/**
	 * Persists the current information stored in the GameManager object 
	 * into the database.
	 */
	public void saveGame() {
		GameManagerDao gameManagerDao = new GameManagerDaoImpl();
		gameManagerDao.saveGame(this);
		
		new MainMenu(this);
	}
	
	
	/**
	 * Persists the current information stored in the GameManager object
	 * into the database and then exits the program.
	 */
	public void saveGameAndExit() {
		GameManagerDao gameManagerDao = new GameManagerDaoImpl();
		gameManagerDao.saveGame(this);
	}
	
	public String getQuickGameInfo() {
		return "";
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

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public List<Fixture> getUpcomingFixtures() {
		return upcomingFixtures;
	}

	public void setUpcomingFixtures(List<Fixture> upcomingFixtures) {
		this.upcomingFixtures = upcomingFixtures;
	}
}
