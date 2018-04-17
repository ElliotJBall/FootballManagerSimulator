package com.elliot.footballmanager.fixture;

import java.util.List;

import com.elliot.footballmanager.footballteam.FootballTeam;

/**
 * 
 * @author Elliot
 */
public interface FixtureDao {
	
	public void insertFixturesIntoDatabase(List<String> allFixtures);
	
	public List<Fixture> getFootballTeamsUpcomingFixtures(FootballTeam footballTeam);
}
