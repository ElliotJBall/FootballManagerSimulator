package com.elliot.footballmanager.fixture;

import java.util.List;
import java.util.Queue;

import com.elliot.footballmanager.footballteam.FootballTeam;

/**
 * 
 * @author Elliot
 */
public interface FixtureDao {
	
	public void insertFixturesIntoDatabase(List<String> allFixtures);
	
	public Queue<Fixture> getFootballTeamsUpcomingFixtures(FootballTeam footballTeam);
}
