package com.elliot.footballmanager.fixture;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.elliot.footballmanager.database.SqliteDatabaseConnector;
import com.elliot.footballmanager.footballteam.FootballTeam;

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
		// TODO Auto-generated method stub
		return null;
	}

}
