package com.elliot.footballmanager.country;

import java.util.List;

/**
 * @author Elliot
 * This interface holds the list of methods that enable the connectivity 
 * between the application and the database.
 */
public interface CountryDao {

	/**
	 * @return A list of all countries stored in the database.
	 */
	public List<Country> getAllCountries();
}
