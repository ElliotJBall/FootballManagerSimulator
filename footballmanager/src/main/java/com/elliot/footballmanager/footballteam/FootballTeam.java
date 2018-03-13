package com.elliot.footballmanager.footballteam;

import java.util.List;

import com.elliot.footballmanager.player.Player;

/**
 * @author Elliot
 *
 */
public class FootballTeam implements IFootballTeam {
	
	private Integer footballTeamId;
	private Integer leagueId;
	private String teamName;
	private List<Player> squad;
	
	public FootballTeam() {

	}

	public FootballTeam(Integer footballTeamId, Integer leagueId, String teamName) {
		this.footballTeamId = footballTeamId;
		this.leagueId = leagueId;
		this.teamName = teamName;
	}

	public Integer getFootballTeamId() {
		return footballTeamId;
	}

	public void setFootballTeamId(Integer footballTeamId) {
		this.footballTeamId = footballTeamId;
	}

	public Integer getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(Integer leagueId) {
		this.leagueId = leagueId;
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
	
	public String printFootballTeamMenuInfo() {
		return "[" + this.getFootballTeamId() + "]" + this.getTeamName();
	}	
}
