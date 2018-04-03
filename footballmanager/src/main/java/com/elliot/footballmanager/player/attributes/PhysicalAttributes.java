package com.elliot.footballmanager.player.attributes;

/**
 * Collection of all the physical attributes that a player
 * can have.
 * @author Elliot
 */
public class PhysicalAttributes implements PlayerAttribute {
	
	private Integer acceleration;
	private Integer sprintSpeed;
	private Integer agility;
	private Integer balance;
	private Integer reactions;
	private Integer jumping;
	private Integer stamina;
	private Integer strength;
	
	public PhysicalAttributes() {
		
	}

	public PhysicalAttributes(Integer acceleration, Integer sprintSpeed, Integer agility, Integer balance,
			Integer reactions, Integer jumping, Integer stamina, Integer strength) {
		this.acceleration = acceleration;
		this.sprintSpeed = sprintSpeed;
		this.agility = agility;
		this.balance = balance;
		this.reactions = reactions;
		this.jumping = jumping;
		this.stamina = stamina;
		this.strength = strength;
	}

	public Integer getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(Integer acceleration) {
		this.acceleration = acceleration;
	}

	public Integer getSprintSpeed() {
		return sprintSpeed;
	}

	public void setSprintSpeed(Integer sprintSpeed) {
		this.sprintSpeed = sprintSpeed;
	}

	public Integer getAgility() {
		return agility;
	}

	public void setAgility(Integer agility) {
		this.agility = agility;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public Integer getReactions() {
		return reactions;
	}

	public void setReactions(Integer reactions) {
		this.reactions = reactions;
	}

	public Integer getJumping() {
		return jumping;
	}

	public void setJumping(Integer jumping) {
		this.jumping = jumping;
	}

	public Integer getStamina() {
		return stamina;
	}

	public void setStamina(Integer stamina) {
		this.stamina = stamina;
	}

	public Integer getStrength() {
		return strength;
	}

	public void setStrength(Integer strength) {
		this.strength = strength;
	}
}
