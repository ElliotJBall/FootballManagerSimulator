package com.elliot.footballmanager.league;

/**
 * @author Elliot
 *
 */
public class League {

	private Integer leagueId;
	private String leagueName;
	private Integer countryId;
	
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
}
