package com.elliot.footballmanager.entity.dao.impl;

import com.elliot.footballmanager.entity.dao.MatchEngineDao;
import com.elliot.footballmanager.database.SqliteDatabaseConnector;
import com.elliot.footballmanager.match.MatchResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Elliot
 */
public class MatchEngineDaoImpl implements MatchEngineDao {

    @Override
    public void persistResultToDatabase(MatchResult matchResult) {
        String query = "INSERT INTO MATCH_RESULT (MATCH_RESULT_ID, FIXTURE_ID, HOME_TEAM_GOALS, AWAY_TEAM_GOALS, MATCH_RESULT) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = SqliteDatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setRowId(1, null);
            pstmt.setInt(2, matchResult.getFixture().getFixtureId());
            pstmt.setInt(3, matchResult.getHomeTeamMatchStats().getGoals());
            pstmt.setInt(4, matchResult.getAwayTeamMatchStats().getGoals());
            pstmt.setString(5, matchResult.getResult().getResultString());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
