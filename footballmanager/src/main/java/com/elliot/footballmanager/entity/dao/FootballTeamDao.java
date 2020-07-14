package com.elliot.footballmanager.entity.dao;

import com.elliot.footballmanager.entity.FootballTeam;
import java.util.Collection;

/**
 * @author Elliot
 */
public interface FootballTeamDao {

  /**
   * @param leagueId The leagueId to return all the FootballTeams currently associated with that
   * leagueId.
   * @return A Collection of all the FootballTeams that are currently within the League via the
   * leagueId.
   */
  public Collection<FootballTeam> getAllFootballTeams(Integer leagueId);

  /**
   * @param footballTeamId The unique Id for the FootballTeam you want to retrieve.
   * @return FootballTeam information linked to the given footballTeamId.
   */
  public FootballTeam getFootballTeamById(Integer footballTeamId);

  /**
   * @param footballTeamName The name of the <link>FootballTeam</link> you would like to search in
   * the database for.
   * @return A FootballTeam object of the football team name provided.
   */
  public FootballTeam getFootballTeamByName(String footballTeamName);
}
