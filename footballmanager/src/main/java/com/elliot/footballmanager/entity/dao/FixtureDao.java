package com.elliot.footballmanager.entity.dao;

import com.elliot.footballmanager.entity.Fixture;
import java.util.Date;
import java.util.List;
import java.util.Queue;

import com.elliot.footballmanager.entity.FootballTeam;

/**
 * 
 * @author Elliot
 */
public interface FixtureDao {
	
	public void insertFixturesIntoDatabase(List<String> allFixtures);
	
	public Queue<Fixture> getFootballTeamsUpcomingFixtures(FootballTeam footballTeam);

	public List<Fixture> getFixturesForGivenDate(Date date);
}
