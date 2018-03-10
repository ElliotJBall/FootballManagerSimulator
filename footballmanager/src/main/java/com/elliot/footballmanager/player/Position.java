package com.elliot.footballmanager.player;

/**
 * @author Elliot
 * Describes all the possible positions a player can be assigned. 
 * Note: A player object can have a primary Position they are proficient in and 
 * 0..N secondary positions they can also play.
 */
public enum Position {
	GK ("Goalkeeper"),
	SW ("Sweeper"),
	RWB ("Right Wing Back"),
	LWB ("Left Wing Back"),
	RB ("Right Back"),
	LB ("Left Back"),
	CB ("Centre Back"),
	DM ("Defensive Midfielder"),
	RW ("Right Winger"),
	LW ("Left Winger"),
	RM ("Right Midfielder"),
	LM ("Left Midfielder"),
	CM ("Centre Midfielder"),
	AM ("Attacking Midfielder"),
	CF ("Centre Forward"),
	RF ("Right Forward"),
	LF ("Left Forward"),
	ST ("Striker");
	
	private String positionFullName;
	
	Position(String positionFullName) {
		this.setPositionFullName(positionFullName);
	}

	public String getPositionFullName() {
		return positionFullName;
	}

	public void setPositionFullName(String positionFullName) {
		this.positionFullName = positionFullName;
	}
}
