package com.elliot.footballmanager.league;

import java.util.Map;

/**
 * @author Elliot
 *
 */
public interface LeagueDao {

	/**
	 * @param countryId The Country Id for the Country you want to retrieve 
	 * all the leagues for.
	 * @return A Map of all the available leagues for the given CountryId:
	 * Key : leagueId
	 * Value : League object
	 */
	public Map<Integer, League> getAllLeagues(Integer countryId);
	
	/**
	 * @param leagueId The unique Id for the League you want to retrieve.
	 * @return A league object.
	 */
	public League getLeagueById(Integer leagueId);
	
}
