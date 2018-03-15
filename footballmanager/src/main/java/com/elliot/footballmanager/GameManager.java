package com.elliot.footballmanager;

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

	private Country selectedCountry;
	private League currentLeague;
	private FootballTeam currentFootballTeam;
	private Manager manager;
	
	public GameManager() {

	}

	public Country getSelectedCountry() {
		return selectedCountry;
	}

	public void setSelectedCountry(Country selectedCountry) {
		this.selectedCountry = selectedCountry;
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
