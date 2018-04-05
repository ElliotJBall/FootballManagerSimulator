package com.elliot.footballmanager.fixture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.elliot.footballmanager.footballteam.FootballTeam;
import com.elliot.footballmanager.footballteam.FootballTeamDao;
import com.elliot.footballmanager.footballteam.FootballTeamDaoImpl;
import com.elliot.footballmanager.league.League;
import com.elliot.footballmanager.league.LeagueDao;
import com.elliot.footballmanager.league.LeagueDaoImpl;

/**
 * Given a <link>League</link> containing all the <link>FootballTeam</link>'s 
 * that are part of that league this will return a collection of 
 * <link>Fixture</link>'s generated using a round robin scheduling system.
 * @author Elliot
 *
 */
public class FixtureGenerator {

	private List<League> leaguesForGeneration = new ArrayList<League>();
	
	public FixtureGenerator() {
		prepareFixtureGeneration();
		
	}
	
	private void prepareFixtureGeneration() {
		LeagueDao leagueDao = new LeagueDaoImpl();
		this.setLeaguesForGeneration(leagueDao.getAllLeagues());
		
		FootballTeamDao footballTeamDao = new FootballTeamDaoImpl();
		for (League league : this.getLeaguesForGeneration()) {
			//TODO: Create a simpler way of getting the set of FootballTeams?
			// Can create the method in League or change SELECT statement
			league.setFootballTeams(new ArrayList<FootballTeam>(footballTeamDao.getAllFootballTeams(league.getLeagueId()).values()));
		}
		
		for (League league : this.getLeaguesForGeneration()) {
			buildRoundRobinFixtures(league.getFootballTeams());
		}
	}
	
	private void buildRoundRobinFixtures(List<FootballTeam> footballTeams) {	
		// E.G. 20 Team league = 20 * 20 = 400 - 20 = 380
		int counter = 0;
		int totalGamesToPlay = footballTeams.size() * footballTeams.size() - footballTeams.size();
		Collections.shuffle(footballTeams);

		List<Fixture> allFixtures = new ArrayList<Fixture>();
		
		List<FootballTeam> firstHalf, secondHalf;
		while (counter < totalGamesToPlay) {
			firstHalf = footballTeams.subList(0, (footballTeams.size() / 2));
			secondHalf = footballTeams.subList(footballTeams.size() / 2, footballTeams.size());
			
			for (int i = 0; i < firstHalf.size(); i++) {
				allFixtures.add(new Fixture(firstHalf.get(i), secondHalf.get(i), new Date()));
			}
			shuffleFootballTeamList(footballTeams);
			counter = allFixtures.size();
		}

	}
	
	private void shuffleFootballTeamList(List<FootballTeam> footballTeams) {
		
	}

	public List<League> getLeaguesForGeneration() {
		return leaguesForGeneration;
	}

	private void setLeaguesForGeneration(List<League> leaguesForGeneration) {
		this.leaguesForGeneration = leaguesForGeneration;
	}
}
