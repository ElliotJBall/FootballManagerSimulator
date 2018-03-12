package com.elliot.footballmanager.league;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.elliot.footballmanager.database.SqliteDatabaseConnector;

/**
 * @author Elliot
 *
 */
public class LeagueDaoImpl implements LeagueDao {

	@Override
	public Map<Integer, League> getAllLeagues(Integer countryId) {
		Map<Integer, League> allLeagues = new HashMap<Integer, League>();
		String query = "SELECT * FROM LEAGUE WHERE COUNTRY_ID = ?";
		
		try (Connection conn = SqliteDatabaseConnector.connect();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, countryId);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				League league = new League(rs.getInt("LEAGUE_ID"), rs.getString("LEAGUE_NAME"), rs.getInt("COUNTRY_ID"));
				allLeagues.put(league.getLeagueId(), league);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allLeagues;
	}

}
