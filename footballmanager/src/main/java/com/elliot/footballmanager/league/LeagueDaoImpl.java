package com.elliot.footballmanager.league;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.elliot.footballmanager.database.SqliteDatabaseConnector;

/**
 * @author Elliot
 *
 */
public class LeagueDaoImpl implements LeagueDao {

	@Override
	public List<League> getAllLeagues(Integer countryId) {
		List<League> allLeagues = new ArrayList<League>();
		String query = "SELECT * FROM LEAGUE WHERE COUNTRY_ID = ?";
		
		try (Connection conn = SqliteDatabaseConnector.connect();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, countryId);
			ResultSet rs = pstmt.executeQuery();
			
			allLeagues = new ArrayList<League>();
			
			while (rs.next()) {
				allLeagues.add(new League(rs.getInt("LEAGUE_ID"), rs.getString("LEAGUE_NAME"), rs.getInt("COUNTRY_ID")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allLeagues;
	}

}
