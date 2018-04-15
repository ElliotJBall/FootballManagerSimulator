package com.elliot.footballmanager.league;

import java.util.List;

import com.elliot.footballmanager.fixture.Fixture;
import com.elliot.footballmanager.footballteam.FootballTeam;

/**
 * @author Elliot
 *
 */
public class League {

	private Integer leagueId;
	private String leagueName;
	private Integer countryId;
	private List<FootballTeam> footballTeams;
	private List<Fixture> upcomingFixtures;
	
	public League() {
		
	}

	public League(Integer leagueId, String leagueName, Integer countryId) {
		this.leagueId = leagueId;
		this.leagueName = leagueName;
		this.countryId = countryId;
	}

	public Integer getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(Integer leagueId) {
		this.leagueId = leagueId;
	}

	public String getLeagueName() {
		return leagueName;
	}

	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public List<FootballTeam> getFootballTeams() {
		return footballTeams;
	}

	public void setFootballTeams(List<FootballTeam> footballTeams) {
		this.footballTeams = footballTeams;
	}
	
	public List<Fixture> getUpcomingFixtures() {
		return upcomingFixtures;
	}

	public void setUpcomingFixtures(List<Fixture> upcomingFixtures) {
		this.upcomingFixtures = upcomingFixtures;
	}

	public String printLeagueMenuInfo() {
		return "[" + this.getLeagueId()  + "]" + " " + this.getLeagueName();
	}
}
