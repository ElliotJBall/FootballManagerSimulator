package com.elliot.footballmanager.player;

import java.util.*;


/**
 * Describes all the possible positions a player can be assigned. 
 * Note: A player object can have a primary Position they are proficient in and 
 * 0..N secondary positions they can also play.
 * @author Elliot
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

	private static Map<Position, GeneralisedPosition> generalisedDefenderPositions;
	private static Map<Position, GeneralisedPosition> generalisedMidfielderPositions ;
	private static Map<Position, GeneralisedPosition> generalisedStrikerPositions;

	Position(String positionFullName) {
		this.setPositionFullName(positionFullName);
	}

	private static void initializeGeneralisedPositions() {
		initializeGeneralisedDefenderPositions();
		initializeGeneralisedMidfielderPositions();
		initializeGeneralisedStrikerPositions();
	}

	private static void initializeGeneralisedDefenderPositions() {
		generalisedDefenderPositions = new HashMap<Position, GeneralisedPosition>();
		generalisedDefenderPositions.put(Position.RWB, GeneralisedPosition.DEFENDER);
		generalisedDefenderPositions.put(Position.LWB, GeneralisedPosition.DEFENDER);
		generalisedDefenderPositions.put(Position.RB, GeneralisedPosition.DEFENDER);
		generalisedDefenderPositions.put(Position.LB, GeneralisedPosition.DEFENDER);
		generalisedDefenderPositions.put(Position.RCB, GeneralisedPosition.DEFENDER);
		generalisedDefenderPositions.put(Position.LCB, GeneralisedPosition.DEFENDER);
		generalisedDefenderPositions.put(Position.CB, GeneralisedPosition.DEFENDER);


	}

	private static void initializeGeneralisedMidfielderPositions() {
		generalisedMidfielderPositions = new HashMap<Position, GeneralisedPosition>();

		generalisedMidfielderPositions.put(Position.RDM, GeneralisedPosition.MIDFIELDER);
		generalisedMidfielderPositions.put(Position.LDM, GeneralisedPosition.MIDFIELDER);
		generalisedMidfielderPositions.put(Position.RM, GeneralisedPosition.MIDFIELDER);
		generalisedMidfielderPositions.put(Position.LM, GeneralisedPosition.MIDFIELDER);
		generalisedMidfielderPositions.put(Position.RCM, GeneralisedPosition.MIDFIELDER);
		generalisedMidfielderPositions.put(Position.LCM,  GeneralisedPosition.MIDFIELDER);
		generalisedMidfielderPositions.put(Position.CDM, GeneralisedPosition.MIDFIELDER);
		generalisedMidfielderPositions.put(Position.CM, GeneralisedPosition.MIDFIELDER);
		generalisedMidfielderPositions.put(Position.RAM, GeneralisedPosition.MIDFIELDER);
		generalisedMidfielderPositions.put(Position.LAM, GeneralisedPosition.MIDFIELDER);
		generalisedMidfielderPositions.put(Position.CAM, GeneralisedPosition.MIDFIELDER);
	}

	private static void initializeGeneralisedStrikerPositions() {
		generalisedStrikerPositions = new HashMap<Position, GeneralisedPosition>();

		generalisedStrikerPositions.put(Position.RW, GeneralisedPosition.ATTACKER);
		generalisedStrikerPositions.put(Position.LW, GeneralisedPosition.ATTACKER);
		generalisedStrikerPositions.put(Position.RF, GeneralisedPosition.ATTACKER);
		generalisedStrikerPositions.put(Position.LF, GeneralisedPosition.ATTACKER);
		generalisedStrikerPositions.put(Position.RS, GeneralisedPosition.ATTACKER);
		generalisedStrikerPositions.put(Position.LS, GeneralisedPosition.ATTACKER);
		generalisedStrikerPositions.put(Position.ST, GeneralisedPosition.ATTACKER);
		generalisedStrikerPositions.put(Position.CF, GeneralisedPosition.ATTACKER);
	}

	public static GeneralisedPosition getGeneralPositionBySpecificPosition(Position position) {
		if (generalisedDefenderPositions == null || generalisedMidfielderPositions == null || generalisedStrikerPositions == null) {
			initializeGeneralisedPositions();
		}

		if (generalisedDefenderPositions.containsKey(position)) {
			return GeneralisedPosition.DEFENDER;
		}

		if (generalisedMidfielderPositions.containsKey(position)) {
			return GeneralisedPosition.MIDFIELDER;
		}

		if (generalisedStrikerPositions.containsKey(position)) {
			return GeneralisedPosition.ATTACKER;
		}
		return GeneralisedPosition.GOALKEEPER;
	}

	public String getPositionFullName() {
		return positionFullName;
	}

	public void setPositionFullName(String positionFullName) {
		this.positionFullName = positionFullName;
	}
	
	public static Position getPositionFromString(String positionAsString) throws IllegalArgumentException {
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
				throw new IllegalArgumentException("Cannot create Position: " + positionAsString + " Assigning default position (CM).");
		}
	}

	public enum GeneralisedPosition {
		GOALKEEPER,
		DEFENDER,
		MIDFIELDER,
		ATTACKER;
	}
}
