package com.elliot.footballmanager.footballteam;

import java.util.List;

import com.elliot.footballmanager.player.Player;

/**
 * @author Elliot
 *
 */
public class FootballTeam implements IFootballTeam {
	
	private String teamName;
	private List<Player> squad;
	
	public FootballTeam() {

	}

	public FootballTeam(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public List<Player> getSquad() {
		return squad;
	}

	public void setSquad(List<Player> squad) {
		this.squad = squad;
	}	
}
