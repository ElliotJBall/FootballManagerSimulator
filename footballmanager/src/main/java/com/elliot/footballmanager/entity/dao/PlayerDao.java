package com.elliot.footballmanager.entity.dao;

import com.elliot.footballmanager.entity.Player;
import java.util.List;

import com.elliot.footballmanager.entity.FootballTeam;

/**
 * Provides a list of methods that are used to interact 
 * with the table Player in the database.
 * @author Elliot
 */
public interface PlayerDao {

	public List<Player> getAllPlayersForFootballTeam(FootballTeam footballTeam);
}
