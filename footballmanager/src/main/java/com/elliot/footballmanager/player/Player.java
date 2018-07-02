package com.elliot.footballmanager.player;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.elliot.footballmanager.footballteam.FootballTeam;
import com.elliot.footballmanager.player.attributes.GoalkeeperAttributes;
import com.elliot.footballmanager.player.attributes.MentalAttributes;
import com.elliot.footballmanager.player.attributes.PhysicalAttributes;
import com.elliot.footballmanager.player.attributes.TechnicalAttributes;

/**
 * Provides information about a given Player object and stores information
 * about how good they are via the different attributes.
 * @author Elliot
 */
public class Player implements Serializable, IPlayer {
	
	private Integer id;
	private String name;
	private Integer age;
	private String nationality;
	private Integer overallRating;
	private FootballTeam currentClub;
	private Double value;
	private Double wages;
	private Set<Position> preferredPositions;
	
	private PhysicalAttributes physicalAttributes;
	private TechnicalAttributes technicalAttributes;
	private MentalAttributes mentalAttributes;
	private GoalkeeperAttributes goalkeeperAttributes;

	private Integer xCoordinate;
	private Integer yCoordinate;

	public Player() {

	}

	public Player(Integer id, String name, Integer age, String nationality, Integer overallRating,
			FootballTeam currentClub, Double value, Double wages, Set<Position> preferredPositions) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.nationality = nationality;
		this.overallRating = overallRating;
		this.currentClub = currentClub;
		this.value = value;
		this.wages = wages;
		this.preferredPositions = preferredPositions;
	}

	public Player(Integer id, String name, Integer age, String nationality, Integer overallRating,
			FootballTeam currentClub, Double value, Double wages, Set<Position> preferredPositions,
			PhysicalAttributes physicalAttributes, TechnicalAttributes technicalAttributes,
			MentalAttributes mentalAttributes, GoalkeeperAttributes goalkeeperAttributes) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.nationality = nationality;
		this.overallRating = overallRating;
		this.currentClub = currentClub;
		this.value = value;
		this.wages = wages;
		this.preferredPositions = preferredPositions;
		this.physicalAttributes = physicalAttributes;
		this.technicalAttributes = technicalAttributes;
		this.mentalAttributes = mentalAttributes;
		this.goalkeeperAttributes = goalkeeperAttributes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Integer getOverallRating() {
		return overallRating;
	}

	public void setOverallRating(Integer overallRating) {
		this.overallRating = overallRating;
	}

	public FootballTeam getCurrentClub() {
		return currentClub;
	}

	public void setCurrentClub(FootballTeam currentClub) {
		this.currentClub = currentClub;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Double getWages() {
		return wages;
	}

	public void setWages(Double wages) {
		this.wages = wages;
	}

	public Set<Position> getPreferredPositions() {
		return preferredPositions;
	}

	public void setPreferredPositions(Set<Position> preferredPositions) {
		this.preferredPositions = preferredPositions;
	}

	public PhysicalAttributes getPhysicalAttributes() {
		return physicalAttributes;
	}

	public void setPhysicalAttributes(PhysicalAttributes physicalAttributes) {
		this.physicalAttributes = physicalAttributes;
	}

	public TechnicalAttributes getTechnicalAttributes() {
		return technicalAttributes;
	}

	public void setTechnicalAttributes(TechnicalAttributes technicalAttributes) {
		this.technicalAttributes = technicalAttributes;
	}

	public MentalAttributes getMentalAttributes() {
		return mentalAttributes;
	}

	public void setMentalAttributes(MentalAttributes mentalAttributes) {
		this.mentalAttributes = mentalAttributes;
	}

	public GoalkeeperAttributes getGoalkeeperAttributes() {
		return goalkeeperAttributes;
	}

	public void setGoalkeeperAttributes(GoalkeeperAttributes goalkeeperAttributes) {
		this.goalkeeperAttributes = goalkeeperAttributes;
	}

	public Integer getxCoordinate() {
		return xCoordinate;
	}

	public void setxCoordinate(Integer xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public Integer getyCoordinate() {
		return yCoordinate;
	}

	public void setyCoordinate(Integer yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
}
