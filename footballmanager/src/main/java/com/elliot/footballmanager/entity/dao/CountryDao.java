package com.elliot.footballmanager.entity.dao;

import com.elliot.footballmanager.entity.Country;
import java.util.Map;

/**
 * This interface holds the list of methods that enable the connectivity between the application and
 * the database.
 *
 * @author Elliot
 */
public interface CountryDao {

  /**
   * @return A Map of all the Countires stored in the database. Key : CountryId Value : Country
   * object
   */
  public Map<Integer, Country> getAllCountries();

  /**
   * @param countryId The unique ID of the Country you want to retrieve.
   * @return The corresponding Country for the given Id.
   */
  public Country getCountryById(Integer countryId);
}
