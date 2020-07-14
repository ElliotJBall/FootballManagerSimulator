package com.elliot.footballmanager.entity.attributes;

import java.io.Serializable;

/**
 * Collection of all mental attributes that a player
 * can have.
 * @author Elliot
 */
public class MentalAttributes implements Serializable, PlayerAttribute {
	
	private Integer positioning;
	private Integer vision;
	private Integer composure;
	private Integer interceptions;
	private Integer aggression;
	
	public MentalAttributes() {
		
	}
	
	public MentalAttributes(Integer positioning, Integer vision, Integer composure, Integer interceptions,
			Integer aggression) {
		super();
		this.positioning = positioning;
		this.vision = vision;
		this.composure = composure;
		this.interceptions = interceptions;
		this.aggression = aggression;
	}

	public Integer getPositioning() {
		return positioning;
	}

	public void setPositioning(Integer positioning) {
		this.positioning = positioning;
	}

	public Integer getVision() {
		return vision;
	}

	public void setVision(Integer vision) {
		this.vision = vision;
	}

	public Integer getComposure() {
		return composure;
	}

	public void setComposure(Integer composure) {
		this.composure = composure;
	}

	public Integer getInterceptions() {
		return interceptions;
	}

	public void setInterceptions(Integer interceptions) {
		this.interceptions = interceptions;
	}

	public Integer getAggression() {
		return aggression;
	}

	public void setAggression(Integer aggression) {
		this.aggression = aggression;
	}
}
