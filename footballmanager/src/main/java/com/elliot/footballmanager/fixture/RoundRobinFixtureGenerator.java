package com.elliot.footballmanager.fixture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.elliot.footballmanager.DateUtils;
import com.elliot.footballmanager.footballteam.FootballTeam;
import com.elliot.footballmanager.league.League;

/**
 * Given a <link>League</link> containing all the <link>FootballTeam</link>'s 
 * that are part of that league this will return a collection of 
 * <link>Fixture</link>'s generated using a round robin scheduling system.
 * @author Elliot
 *
 */
public class RoundRobinFixtureGenerator extends AbstractFixtureFactory implements FixtureGenerator {

	private Integer TOTAL_GAMES_IN_SEASON;
	private Integer HALF_GAMES_IN_SEASON;
	private Date fixtureDate = new GregorianCalendar(2018, Calendar.AUGUST, 11).getTime();
	
	public RoundRobinFixtureGenerator() {

	}
	
	private void generateFixturesFromArray(List<String> allFixtures, FootballTeam[] footballTeams) {
		// Games stored in array at index 8 or higher will be generated as Sunday fixtures
		int gamesForSunday = 8;
		int total = footballTeams.length - 1;
		for (int i = 0; i < footballTeams.length / 2; i++) {
			if (i < gamesForSunday) {
				allFixtures.add(createFixtureInsertStatement(footballTeams[i], footballTeams[total], 
						this.getFixtureDate()));				
			} else {
				allFixtures.add(createFixtureInsertStatement(footballTeams[i], footballTeams[total], 
						moveFixtureToNextDay(this.getFixtureDate())));		
			}
			total--;
		}
		
		// Add a Week onto the Fixture date
		fixtureDate = DateUtils.addDays(fixtureDate, 7);
	}
	
	private Date moveFixtureToNextDay(Date fixtureDate) {
		fixtureDate = DateUtils.addDays(fixtureDate, 1);
		return fixtureDate;
	}

	private FootballTeam[] shiftFootballTeamArray(FootballTeam[] footballTeams, FootballTeam firstTeam) {
		FootballTeam[] shiftedFootballTeams = new FootballTeam[footballTeams.length];
		for (int i = 0; i < footballTeams.length - 1; i++) {
			shiftedFootballTeams[i + 1] = footballTeams[i];
		}
		shiftedFootballTeams[0] = footballTeams[footballTeams.length - 1];
		
		FootballTeam[] updatedFootballTeams = new FootballTeam[footballTeams.length + 1];
		updatedFootballTeams[0] = firstTeam;
		for (int i = 0; i < updatedFootballTeams.length - 1; i++) {
			updatedFootballTeams[i + 1] = shiftedFootballTeams[i];
		}
		
		return updatedFootballTeams;
	}
	
	private void reverseArrayOrder(FootballTeam[] footballTeams) {
		for (int i = 0; i < footballTeams.length / 2; i++) {
			FootballTeam temp = footballTeams[i];
			footballTeams[i] = footballTeams[footballTeams.length - i - 1];
			footballTeams[footballTeams.length - i - 1] = temp;
		}
	}
	
	private List<String> buildFixturesList(List<FootballTeam> footballTeams) {
		if (footballTeams.size() == 0 || footballTeams == null) {
			return new ArrayList<String>();
		}
		
		Collections.shuffle(footballTeams);
		
		List<String> selectedLeagueFixtures = new ArrayList<String>();	
		
		FootballTeam[] footballTeamsArray = new FootballTeam[footballTeams.size()];
		footballTeams.toArray(footballTeamsArray);
		
		TOTAL_GAMES_IN_SEASON = (footballTeams.size() * footballTeams.size()) - footballTeams.size();
		HALF_GAMES_IN_SEASON = TOTAL_GAMES_IN_SEASON / 2;
		
		// Home Fixtures
		while (selectedLeagueFixtures.size() != HALF_GAMES_IN_SEASON) {
			generateFixturesFromArray(selectedLeagueFixtures, footballTeamsArray);
			// Shift all teams one place to the right (Bar the first one | Round Robin method)
			footballTeamsArray = shiftFootballTeamArray(Arrays.copyOfRange(footballTeamsArray, 1, footballTeamsArray.length), footballTeamsArray[0]);
		} 
		
		// Reverse FootballTeams array to generate away Fixtures
		reverseArrayOrder(footballTeamsArray);
		
		// Away Fixtures
		 while (selectedLeagueFixtures.size() != TOTAL_GAMES_IN_SEASON) {
			generateFixturesFromArray(selectedLeagueFixtures, footballTeamsArray);
			// Shift all teams one place to the right (Bar the first one | Round Robin method)
			footballTeamsArray = shiftFootballTeamArray(Arrays.copyOfRange(footballTeamsArray, 1, footballTeamsArray.length), footballTeamsArray[0]);
		};
		
		return selectedLeagueFixtures;
	}

	@Override
	public List<String> generateFixtureInsertStatements() {
		prepareLeaguesForFixtureGeneration();
		
		for (League league : this.getLeaguesForGeneration()) {
			this.getAllFixtures().addAll(buildFixturesList(league.getFootballTeams()));
		}
		
		return this.getAllFixtures();
	}

	@Override
	public void insertFixturesIntoDatabase(List<String> fixtures) {
		FixtureDao fixtureDao = new FixtureDaoImpl();
		fixtureDao.insertFixturesIntoDatabase(fixtures);
	}

	public Date getFixtureDate() {
		return fixtureDate;
	}

	public void setFixtureDate(Date fixtureDate) {
		this.fixtureDate = fixtureDate;
	}
}
