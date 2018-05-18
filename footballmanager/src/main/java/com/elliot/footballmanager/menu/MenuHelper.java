package com.elliot.footballmanager.menu;

import com.elliot.footballmanager.footballteam.FootballTeam;
import com.elliot.footballmanager.footballteam.FootballTeamDao;
import com.elliot.footballmanager.footballteam.FootballTeamDaoImpl;
import com.elliot.footballmanager.gamemanager.GameManager;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for methods that can be used across all
 * the menu classes.
 * @author Elliot
 */
public class MenuHelper {

	private MenuHelper() {

	}

	public static Map<Integer, FootballTeam> buildFootballTeamMapDisplay(GameManager gameManager) {
		Map<Integer, FootballTeam> footballTeamToIds = new HashMap<Integer, FootballTeam>();

		FootballTeamDao footballTeamDao = new FootballTeamDaoImpl();
		for (FootballTeam footballTeam : footballTeamDao.getAllFootballTeams(gameManager.getCurrentLeague().getLeagueId())) {
			footballTeamToIds.put(footballTeam.getFootballTeamId(), footballTeam);
		}
		return footballTeamToIds;
	}

	/**
	 * TODO: Find an alternative method to removing previous contents 
	 * from the console.
	 * Helper method that will remove all previous contents from the console.
	 */
	public static void clearConsole() {

	}
}
