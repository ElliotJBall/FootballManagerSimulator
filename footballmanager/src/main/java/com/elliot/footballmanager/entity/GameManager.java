package com.elliot.footballmanager.entity;

import com.elliot.footballmanager.entity.dao.GameManagerDao;
import com.elliot.footballmanager.entity.dao.impl.GameManagerDaoImpl;
import java.util.Date;
import java.util.Queue;

import com.elliot.footballmanager.DateUtils;
import com.elliot.footballmanager.entity.dao.FixtureDao;
import com.elliot.footballmanager.entity.dao.impl.FixtureDaoImpl;
import com.elliot.footballmanager.menu.MainMenu;
import com.elliot.footballmanager.menu.MatchDayMenu;

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
	private Queue<Fixture> upcomingFixtures;
	
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
		GameManagerDao gameManagerDao = new GameManagerDaoImpl();
		gameManagerDao.loadSavedGame(this);
		
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
		return  DateUtils.FIXTURE_DATE_DISPLAY_FORMAT.format(this.getCurrentDate())
				+ " " + this.getCurrentFootballTeam().getTeamName()
				+ " " + this.getCurrentLeague().getLeagueName();
	}

	/**
	 * Advances the currentDate by a day until a new Fixture is found.
	 */
	public void simulateGame() {
		while (currentDate.before(this.getUpcomingFixtures().peek().getDateOfFixture())) {
			currentDate = DateUtils.addDays(currentDate, 1);
			System.out.println("Current date : " + DateUtils.FIXTURE_DATE_DISPLAY_FORMAT.format(currentDate));
		}

		// Match day
		if (currentDate.equals(this.getUpcomingFixtures().peek().getDateOfFixture())) {
			MatchDayMenu matchDayMenu = new MatchDayMenu(this);
			matchDayMenu.beginMenuSelectionLoop();
		}
	}

	public void beginMatchDaySimulation() {

	}

	public boolean isMatchDay() {
		return this.getCurrentDate().equals(this.getUpcomingFixtures().peek().getDateOfFixture());
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

	public Queue<Fixture> getUpcomingFixtures() {
		return upcomingFixtures;
	}

	public void setUpcomingFixtures(Queue<Fixture> upcomingFixtures) {
		this.upcomingFixtures = upcomingFixtures;
	}
}
