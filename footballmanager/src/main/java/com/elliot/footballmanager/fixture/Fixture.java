package com.elliot.footballmanager.fixture;

import java.util.Date;

import com.elliot.footballmanager.footballteam.FootballTeam;

/**
 * The Fixture class provides information about an upcoming Football match. 
 * Information about the two FootballTeams, competition and date are stored.
 * @author Elliot
 */
public class Fixture {

	private FootballTeam homeTeam;
	private FootballTeam awayTeam;
	private Date dateOfFixture;
	
	public Fixture() {
		
	}

	public Fixture(FootballTeam homeTeam, FootballTeam awayTeam, Date dateOfFixture) {
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.dateOfFixture = dateOfFixture;
	}

	public FootballTeam getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(FootballTeam homeTeam) {
		this.homeTeam = homeTeam;
	}

	public FootballTeam getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(FootballTeam awayTeam) {
		this.awayTeam = awayTeam;
	}

	public Date getDateOfFixture() {
		return dateOfFixture;
	}

	public void setDateOfFixture(Date dateOfFixture) {
		this.dateOfFixture = dateOfFixture;
	}

	@Override
	public String toString() {
		return "Fixture: [Home Team: " + homeTeam + ", Away Team:" + awayTeam + ", Fixture Date=" + dateOfFixture + "]";
	}
}
