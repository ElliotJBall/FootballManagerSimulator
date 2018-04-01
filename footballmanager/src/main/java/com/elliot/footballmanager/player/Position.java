package com.elliot.footballmanager.player;

/**
 * @author Elliot
 * Describes all the possible positions a player can be assigned. 
 * Note: A player object can have a primary Position they are proficient in and 
 * 0..N secondary positions they can also play.
 */
public enum Position {
	GK ("Goalkeeper"),
	RWB ("Right Wing Back"),   
	LWB ("Left Wing Back"),
	RB ("Right Back"),
	LB ("Left Back"),
	RCB ("Right Centre Back"),
	LCB ("Left Centre Back"), 
	CB ("Centre Back"),
	RDM ("Right Defensive Midfielder"),
	LDM ("Left Defensive Midfielder"),
	RM ("Right Midfielder"),
	LM ("Left Midfielder"),
	RCM ("Right Centre Midfielder"),
	LCM ("Left Centre Midfielder"),
	CDM ("Central Defensive Midfielder"),
	CM ("Centre Midfielder"),
	RAM ("Right Attacking Midfielder"),
	LAM ("Left Attacking Midfielder"),
	CAM ("Central Attacking Midfielder"),
	RW ("Right Winger"),
	LW ("Left Winger"),
	RF ("Right Forward"),
	LF ("Left Forward"),
	RS ("Right Striker"),
	LS ("Left Striker"),
	ST ("Striker"),
	CF ("Centre Forward");
	
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
	
	public static Position getPositionFromString(String positionAsString) {
		switch (positionAsString) {
			case "GK":
				return Position.GK;
			case "RWB":
				return Position.RWB;
			case "LWB":
				return Position.LWB;
			case "RB":
				return Position.RB;
			case "LB":
				return Position.LB;
			case "RCB":
				return Position.RCB;
			case "LCB":
				return Position.LCB;
			case "CB":
				return Position.CB;
			case "RDM":
				return Position.RDM;
			case "LDM":
				return Position.LDM;
			case "RM":
				return Position.RM;
			case "LM":
				return Position.LM;
			case "RCM":
				return Position.RCM;
			case "LCM":
				return Position.LCM;
			case "CDM":
				return Position.CDM;
			case "CM":
				return Position.CM;
			case "RAM":
				return Position.RAM;
			case "LAM":
				return Position.LAM;
			case "CAM":
				return Position.CAM;
			case "RW":
				return Position.RW;
			case "LW":
				return Position.LW;
			case "RF":
				return Position.RF;
			case "LF":
				return Position.LF;
			case "RS":
				return Position.RS;
			case "LS":
				return Position.LS;
			case "ST":
				return Position.ST;			
			case "CF":
				return Position.CF;
			default:
				//TODO: Find a more graceful way of doing this? Maybe just log to console and 
				// Construct player with empty position?
				throw new IllegalArgumentException("Cannot create Position: " + positionAsString);
		}
	}
}
