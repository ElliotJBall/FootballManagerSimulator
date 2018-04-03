package com.elliot.footballmanager.country;

import java.util.HashSet;
import java.util.Set;

import com.elliot.footballmanager.league.League;

/**
 * The Country object stores a Set of all Leagues that play within
 * that country along with the Country name and Id.
 * @author Elliot
 */
public class Country {
	
	private Integer countryId;
	private String CountryName;
	private Set<League> leagues;
	
	public Country() {
		
	}
	
	public Country(Integer countryId, String countryName) {
		this.countryId = countryId;
		CountryName = countryName;
	}

	public Country(Integer countryId, String countryName, Set<League> leagues) {
		this.countryId = countryId;
		CountryName = countryName;
		this.leagues = leagues;
	}
	
	public Integer getCountryId() {
		return countryId;
	}
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	public String getCountryName() {
		return CountryName;
	}
	public void setCountryName(String countryName) {
		CountryName = countryName;
	}
	public Set<League> getLeagues() {
		return leagues;
	}
	public void setLeagues(Set<League> leagues) {
		this.leagues = leagues;
	}
	
	public void addLeague(League league) {
		if (leagues == null) {
			leagues = new HashSet<League>();
		}
		leagues.add(league);
	}
	
	public String printCountryMenuInfo() {
		return "[" + this.getCountryId() + "]" + " " + this.getCountryName();
	}
}
