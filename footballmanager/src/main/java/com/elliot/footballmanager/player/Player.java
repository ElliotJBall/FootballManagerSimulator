package com.elliot.footballmanager.player;

import java.util.List;

public class Player implements IPlayer {
	
	private String playerName;
	private Position primaryPosition;
	private List<Position> secondaryPositions;

	public Player() {

	}
	
	public Player(String playerName, Position primaryPosition, List<Position> secondaryPositions) {
		this.playerName = playerName;
		this.primaryPosition = primaryPosition;
		this.secondaryPositions = secondaryPositions;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
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
}
