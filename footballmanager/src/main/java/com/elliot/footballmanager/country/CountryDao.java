package com.elliot.footballmanager.country;

import java.util.Map;

/**
 * @author Elliot
 * This interface holds the list of methods that enable the connectivity 
 * between the application and the database.
 */
public interface CountryDao {


	/**
	 * @return A Map of all the Countires stored in the database.
	 * Key : CountryId
	 * Value : Country object
	 */
	public Map<Integer, Country> getAllCountries();
}
