package com.elliot.footballmanager.entity.dao;

import com.elliot.footballmanager.entity.League;
import java.util.List;
import java.util.Map;

/**
 * @author Elliot
 */
public interface LeagueDao {

  public List<League> getAllLeagues();

  /**
   * @param countryId The Country Id for the Country you want to retrieve all the leagues for.
   * @return A Map of all the available leagues for the given CountryId: Key : leagueId Value :
   * League object
   */
  public Map<Integer, League> getAllLeaguesById(Integer countryId);

  /**
   * @param leagueId The unique Id for the League you want to retrieve.
   * @return A league object.
   */
  public League getLeagueById(Integer leagueId);

}
