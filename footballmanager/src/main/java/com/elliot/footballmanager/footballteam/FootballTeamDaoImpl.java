package com.elliot.footballmanager.footballteam;

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
public class FootballTeamDaoImpl implements FootballTeamDao {

	@Override
	public Map<Integer, FootballTeam> getAllFootballTeams(Integer leagueId) {
		Map<Integer, FootballTeam> footballTeams = new HashMap<Integer, FootballTeam>();
		String query = "SELECT * FROM FOOTBALL_TEAM WHERE LEAGUE_ID = ?";
		
		try (Connection conn = SqliteDatabaseConnector.connect();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			
			pstmt.setInt(1, leagueId);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				FootballTeam footballTeam = new FootballTeam(rs.getInt("FOOTBALL_TEAM_ID"), rs.getInt("LEAGUE_ID"),
						rs.getString("TEAM_NAME"));
				footballTeams.put(footballTeam.getFootballTeamId(), footballTeam);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return footballTeams;
	}

	@Override
	public FootballTeam getFootballTeamById(Integer footballTeamId) {
		String query = "SELECT *FROM FOOTBALL_TEAM WHERE FOOTBALL_TEAM_ID = ?";
		
		try (Connection conn = SqliteDatabaseConnector.connect();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, footballTeamId);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (!rs.next()) {
				return null;
			}
			return new FootballTeam(rs.getInt("FOOTBALL_TEAM_ID"), rs.getInt("LEAGUE_ID"),
					rs.getString("TEAM_NAME")); 			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
