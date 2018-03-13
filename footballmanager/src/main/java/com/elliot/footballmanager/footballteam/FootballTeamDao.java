package com.elliot.footballmanager.footballteam;

import java.util.Map;

/**
 * @author Elliot
 *
 */
public interface FootballTeamDao {
	
	/**
	 * @return 
	 */
	/**
	 * @param leagueId The leagueId to return all the FootballTeams currently
	 * associated with that leagueId.
	 * @return A Map of all the FootballTeams that are currently within the 
	 * League via the leagueId.
	 * Key : footballTeamId
	 * Value : FootballTeam object
	 */
	public Map<Integer, FootballTeam> getAllFootballTeams(Integer leagueId);

}
