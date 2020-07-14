package com.elliot.footballmanager.menu;

import com.elliot.footballmanager.entity.FootballTeam;
import com.elliot.footballmanager.entity.dao.FootballTeamDao;
import com.elliot.footballmanager.entity.dao.impl.FootballTeamDaoImpl;
import com.elliot.footballmanager.entity.GameManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Helper class for methods that can be used across all the menu classes.
 *
 * @author Elliot
 */
public class MenuHelper {

  private MenuHelper() {

  }

  public static Map<Integer, FootballTeam> buildFootballTeamMapDisplay(GameManager gameManager) {
    Map<Integer, FootballTeam> footballTeamToIds = new HashMap<Integer, FootballTeam>();

    FootballTeamDao footballTeamDao = new FootballTeamDaoImpl();
    for (FootballTeam footballTeam : footballTeamDao
        .getAllFootballTeams(gameManager.getCurrentLeague().getLeagueId())) {
      footballTeamToIds.put(footballTeam.getFootballTeamId(), footballTeam);
    }
    return footballTeamToIds;
  }
}
