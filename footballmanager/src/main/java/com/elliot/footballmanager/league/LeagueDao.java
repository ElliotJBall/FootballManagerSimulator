package com.elliot.footballmanager.league;

import java.util.List;

/**
 * @author Elliot
 *
 */
public interface LeagueDao {

	/**
	 * @param countryId The Country Id for the Country you want to retrieve 
	 * all the leagues for.
	 * @return A list of all the available leagues for that given country.
	 */
	public List<League> getAllLeagues(Integer countryId);
	
}
