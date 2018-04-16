package com.elliot.footballmanager.fixture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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
	
	public RoundRobinFixtureGenerator() {

	}
	
	private void generateFixturesFromArray(List<String> allFixtures, FootballTeam[] footballTeams) {
		int halfway = footballTeams.length / 2;
		for (int i = 0; i < footballTeams.length / 2; i++) {
			allFixtures.add(createFixtureInsertStatement(footballTeams[i], footballTeams[halfway], 
					new Date()));
			halfway++;
		}
	}

	private void shiftFootballTeamArray(FootballTeam[] footballTeams) {
		FootballTeam[] shiftedFootballTeams = new FootballTeam[footballTeams.length];
		for (int i = 0; i < footballTeams.length - 1; i++) {
			shiftedFootballTeams[i + 1] = footballTeams[i];
		}
		shiftedFootballTeams[0] = footballTeams[footballTeams.length - 1];
		footballTeams = shiftedFootballTeams;
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
			shiftFootballTeamArray(Arrays.copyOfRange(footballTeamsArray, 1, footballTeamsArray.length));
		} 
		
		// Reverse FootballTeams array to generate away Fixtures
		reverseArrayOrder(footballTeamsArray);
		
		// Away Fixtures
		 while (selectedLeagueFixtures.size() != TOTAL_GAMES_IN_SEASON) {
			generateFixturesFromArray(selectedLeagueFixtures, footballTeamsArray);
			// Shift all teams one place to the right (Bar the first one | Round Robin method)
			shiftFootballTeamArray(Arrays.copyOfRange(footballTeamsArray, 1, footballTeamsArray.length));
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
}
