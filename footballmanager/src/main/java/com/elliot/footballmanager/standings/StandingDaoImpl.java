package com.elliot.footballmanager.standings;

import com.elliot.footballmanager.database.SqliteDatabaseConnector;
import com.elliot.footballmanager.footballteam.FootballTeam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Elliot
 */
public class StandingDaoImpl implements StandingDao {

    @Override
    public void createNewStandingForFootballTeam(FootballTeam footballTeam) {
        String query = "INSERT INTO STANDING (LEAGUE_ID, FOOTBALL_TEAM_ID) VALUES (? ,?)";

        try (Connection conn = SqliteDatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, footballTeam.getLeagueId());
            pstmt.setInt(2, footballTeam.getFootballTeamId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStandingRecord(Standing standing) {
        String query = "UPDATE STANDING SET LEAGUE_ID = ?, FOOTBALL_TEAM_ID = ?, WINS = ?, LOSSES = ?," +
                " DRAWS = ?, GOALS_FOR = ?, GOALS_AGAINST = ?, GOAL_DIFFERENCE = ?," +
                " POINTS = ?, TABLE_POSITION = ?, GAMES_PLAYED = ? WHERE FOOTBALL_TEAM_ID = ?";

        try (Connection conn = SqliteDatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, standing.getLeagueId());
            pstmt.setInt(2, standing.getFootballTeamId());
            pstmt.setInt(3, standing.getWins());
            pstmt.setInt(4, standing.getLosses());
            pstmt.setInt(5, standing.getDraws());
            pstmt.setInt(6, standing.getGoalsFor());
            pstmt.setInt(7, standing.getGoalsAgainst());
            pstmt.setInt(8, standing.getGoalDifference());
            pstmt.setInt(9, standing.getPoints());
            pstmt.setInt(10, standing.getTablePosition());
            pstmt.setInt(11, standing.getGamesPlayed());

            pstmt.setInt(12, standing.getFootballTeamId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Standing getStandingByFootballTeamId(Integer footballTeamId) {
        String query = "SELECT * FROM STANDING WHERE FOOTBALL_TEAM_ID = ?";

        try (Connection conn = SqliteDatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, footballTeamId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.isAfterLast()) {
                return null;
            }

            while (rs.next()) {
                return buildStandingObject(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public LeagueTable getOrderedTableByLeagueId(Integer leagueId) {
        LeagueTable leagueTable = new LeagueTable();
        String query = "SELECT * FROM STANDING WHERE LEAGUE_ID = ?";

        try (Connection conn = SqliteDatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, leagueId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.isAfterLast()) {
                return new LeagueTable();
            }

            while (rs.next()) {
                leagueTable.addStandingToTable(buildStandingObject(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leagueTable;
    }

    private Standing buildStandingObject(ResultSet rs) throws SQLException {
        return new Standing(rs.getInt("STANDING_ID"), rs.getInt("LEAGUE_ID"), rs.getInt("FOOTBALL_TEAM_ID"), rs.getInt("WINS"),
                rs.getInt("LOSSES"), rs.getInt("DRAWS"), rs.getInt("GOALS_FOR"), rs.getInt("GOALS_AGAINST"),
                rs.getInt("GOAL_DIFFERENCE"), rs.getInt("POINTS"), rs.getInt("TABLE_POSITION"), rs.getInt("GAMES_PLAYED"));
    }

    private List<Standing> orderTableByPoints(List<Standing> table) {
        return StandingComparator.orderTableByPoints(table);
    }
}
