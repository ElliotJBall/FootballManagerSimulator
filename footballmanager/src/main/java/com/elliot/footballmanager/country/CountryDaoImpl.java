package com.elliot.footballmanager.country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.elliot.footballmanager.database.SqliteDatabaseConnector;

/**
 * Implementation of the <link>CountryDao</link> class.
 * @author Elliot
 */
public class CountryDaoImpl implements CountryDao {
	
	public Map<Integer, Country> getAllCountries() {
		Map<Integer, Country> allCountries = new HashMap<Integer, Country>();
		String query = "SELECT * FROM COUNTRY";
		
		try (Connection conn = SqliteDatabaseConnector.connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			
			while (rs.next()) {
				Country country = new Country(rs.getInt("COUNTRY_ID"), rs.getString("COUNTRY_NAME"));
				allCountries.put(country.getCountryId(), country);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allCountries;
	}

	@Override
	public Country getCountryById(Integer countryId) {
		String query = "SELECT * FROM COUNTRY WHERE COUNTRY_ID = ?";
		
		try (Connection conn = SqliteDatabaseConnector.connect();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, countryId);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (!rs.next()) {
				return null;
			}
			
			return new Country(rs.getInt("COUNTRY_ID"), rs.getString("COUNTRY_NAME"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
