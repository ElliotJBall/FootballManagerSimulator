package com.elliot.footballmanager.footballteam;

import java.util.List;

import com.elliot.footballmanager.player.Player;

/**
 * @author Elliot
 *
 */
public class FootballTeam implements IFootballTeam {
	
	private Integer footballTeamId;
	private String teamName;
	private Integer leagueId;
	private String location;
	private String stadium;
	private Integer stadiumCapacity;
	private List<Player> squad;
	
	public FootballTeam() {

	}
	
	
	
	public FootballTeam(Integer footballTeamId, String teamName, Integer leagueId, 
			String location, String stadium, Integer stadiumCapacity) {
		this.footballTeamId = footballTeamId;
		this.teamName = teamName;
		this.leagueId = leagueId;
		this.location = location;
		this.stadium = stadium;
		this.stadiumCapacity = stadiumCapacity;
	}

	public Integer getFootballTeamId() {
		return footballTeamId;
	}

	public void setFootballTeamId(Integer footballTeamId) {
		this.footballTeamId = footballTeamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Integer getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(Integer leagueId) {
		this.leagueId = leagueId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStadium() {
		return stadium;
	}

	public void setStadium(String stadium) {
		this.stadium = stadium;
	}

	public Integer getStadiumCapacity() {
		return stadiumCapacity;
	}

	public void setStadiumCapacity(Integer stadiumCapacity) {
		this.stadiumCapacity = stadiumCapacity;
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
