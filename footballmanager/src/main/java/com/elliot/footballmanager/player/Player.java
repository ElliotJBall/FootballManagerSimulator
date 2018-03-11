package com.elliot.footballmanager.player;

import java.util.List;

/**
 * @author Elliot
 *
 */
public class Player implements IPlayer {
	
	private String firstName;
	private String lastName;
	private String fullName;
	private Position primaryPosition;
	private List<Position> secondaryPositions;
	private Integer overallRating;

	public Player() {

	}
	
	

	public Player(String firstName, String lastName, Position primaryPosition, 
			List<Position> secondaryPositions, Integer overallRating) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.primaryPosition = primaryPosition;
		this.secondaryPositions = secondaryPositions;
		this.overallRating = overallRating;
		this.fullName = firstName + " " + lastName;
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Position getPrimaryPosition() {
		return primaryPosition;
	}

	public void setPrimaryPosition(Position primaryPosition) {
		this.primaryPosition = primaryPosition;
	}

	public List<Position> getSecondaryPositions() {
		return secondaryPositions;
	}

	public void setSecondaryPositions(List<Position> secondaryPositions) {
		this.secondaryPositions = secondaryPositions;
	}



	public Integer getOverallRating() {
		return overallRating;
	}



	public void setOverallRating(Integer overallRating) {
		this.overallRating = overallRating;
	}
}
