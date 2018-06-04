package com.elliot.footballmanager.match;

import com.elliot.footballmanager.database.SqliteDatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Elliot
 */
public class MatchEngineDaoImpl implements MatchEngineDao {

    @Override
    public void persistResultToDatabase(MatchResult matchResult) {
        String query = "INSERT INTO MATCH_RESULT (HOME_TEAM_NAME, AWAY_TEAM_NAME, HOME_TEAM_GOALS, AWAY_TEAM_GOALS) VALUES (?, ?, ?, ?)";

        try (Connection conn = SqliteDatabaseConnector.connect();
             Statement stmt = conn.createStatement();
             PreparedStatement pInsert = conn.prepareStatement(query)) {

            pInsert.setString(1, matchResult.getHomeTeam().getTeamName());
            pInsert.setString(2, matchResult.getAwayTeam().getTeamName());

            pInsert.setInt(3, matchResult.getHomeTeamGoals());
            pInsert.setInt(4, matchResult.getAwayTeamGoals());

            pInsert.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
