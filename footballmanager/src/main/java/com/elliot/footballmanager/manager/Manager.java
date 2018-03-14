package com.elliot.footballmanager.manager;

/**
 * @author Elliot
 * The Manager object is used to represent the person playing the game.
 * 
 */
public class Manager {
	
	private String firstName;
	private String lastName;
	
	public Manager() {
		
	}

	public Manager(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
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
}
