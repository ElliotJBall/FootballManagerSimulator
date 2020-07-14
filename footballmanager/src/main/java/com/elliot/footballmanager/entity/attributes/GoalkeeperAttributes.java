package com.elliot.footballmanager.entity.attributes;

import java.io.Serializable;

/**
 * Collection of all goalkeeper attributes that a player
 * can have.
 * @author Elliot
 */
public class GoalkeeperAttributes implements Serializable, PlayerAttribute {

	private Integer diving;
	private Integer handling;
	private Integer kicking;
	private Integer positioning;
	private Integer reflexes;
	
	public GoalkeeperAttributes() {
		
	}

	public GoalkeeperAttributes(Integer diving, Integer handling, Integer kicking, Integer positioning,
			Integer reflexes) {
		this.diving = diving;
		this.handling = handling;
		this.kicking = kicking;
		this.positioning = positioning;
		this.reflexes = reflexes;
	}

	public Integer getDiving() {
		return diving;
	}

	public void setDiving(Integer diving) {
		this.diving = diving;
	}

	public Integer getHandling() {
		return handling;
	}

	public void setHandling(Integer handling) {
		this.handling = handling;
	}

	public Integer getKicking() {
		return kicking;
	}

	public void setKicking(Integer kicking) {
		this.kicking = kicking;
	}

	public Integer getPositioning() {
		return positioning;
	}

	public void setPositioning(Integer positioning) {
		this.positioning = positioning;
	}

	public Integer getReflexes() {
		return reflexes;
	}

	public void setReflexes(Integer reflexes) {
		this.reflexes = reflexes;
	}
}
