package com.elliot.footballmanager.league;

import java.util.Set;

import com.elliot.footballmanager.footballteam.FootballTeam;

/**
 * @author Elliot
 *
 */
public class League {

	private Integer leagueId;
	private String leagueName;
	private Integer countryId;
	private Set<FootballTeam> footballTeams;
	
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

	public Set<FootballTeam> getFootballTeams() {
		return footballTeams;
	}

	public void setFootballTeams(Set<FootballTeam> footballTeams) {
		this.footballTeams = footballTeams;
	}
	
	public String printLeagueMenuInfo() {
		return "[" + this.getLeagueId()  + "]" + " " + this.getLeagueName();
	}
}
