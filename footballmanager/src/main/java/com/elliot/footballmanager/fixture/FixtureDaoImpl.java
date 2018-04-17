package com.elliot.footballmanager.fixture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.elliot.footballmanager.database.SqliteDatabaseConnector;
import com.elliot.footballmanager.footballteam.FootballTeam;
import com.elliot.footballmanager.footballteam.FootballTeamDao;
import com.elliot.footballmanager.footballteam.FootballTeamDaoImpl;

public class FixtureDaoImpl implements FixtureDao {
	
	@Override
	public void insertFixturesIntoDatabase(List<String> allFixtures) {
		try (Connection conn = SqliteDatabaseConnector.connect();
				Statement stmt = conn.createStatement()) {
			for (String fixture : allFixtures) {
				stmt.executeUpdate(fixture);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Fixture> getFootballTeamsUpcomingFixtures(FootballTeam footballTeam) {
		String query = "SELECT * FROM FIXTURE WHERE HOME_TEAM = ? OR AWAY_TEAM = ?";
		
		try (Connection conn = SqliteDatabaseConnector.connect();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			if (footballTeam == null) {
				return new ArrayList<Fixture>();
			}
			
			pstmt.setString(1, footballTeam.getTeamName());
			pstmt.setString(2, footballTeam.getTeamName());
			
			ResultSet rs = pstmt.executeQuery();
			
			if (!rs.next()) {
				return new ArrayList<Fixture>();
			}
			
			List<Fixture> upcomingFixtures = new ArrayList<Fixture>();
			FootballTeamDao footballTeamDao = new FootballTeamDaoImpl();
			while (rs.next()) {
				FootballTeam homeTeam = footballTeamDao.getFootballTeamByName(rs.getString("HOME_TEAM"));
				FootballTeam awayTeam = footballTeamDao.getFootballTeamByName(rs.getString("AWAY_TEAM"));
				
				upcomingFixtures.add(new Fixture(homeTeam, awayTeam,
						rs.getDate("DATE_OF_MATCH"), rs.getInt("LEAGUE_ID")));
			}
			return upcomingFixtures;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<Fixture>();
	}
}
