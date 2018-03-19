package com.elliot.footballmanager.manager;

import com.elliot.footballmanager.footballteam.FootballTeam;

/**
 * @author Elliot
 * The Manager object is used to represent the person playing the game.
 * Stores information about the current <link>FootballTeam</link> the
 * Manager is assigned to.
 * 
 */
public class Manager {
	
	private Integer managerId;
	private String firstName;
	private String lastName;
	private FootballTeam currentFootballTeam;
	
	public Manager() {
		
	}

	public Manager(Integer managerId, String firstName, String lastName, FootballTeam currentFootballTeam) {
		this.setManagerId(managerId);
		this.firstName = firstName;
		this.lastName = lastName;
		this.currentFootballTeam = currentFootballTeam;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public FootballTeam getCurrentFootballTeam() {
		return currentFootballTeam;
	}

	public void setCurrentFootballTeam(FootballTeam currentFootballTeam) {
		this.currentFootballTeam = currentFootballTeam;
	}
}
