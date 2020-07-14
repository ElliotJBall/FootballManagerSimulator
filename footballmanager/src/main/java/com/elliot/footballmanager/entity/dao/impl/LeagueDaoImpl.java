package com.elliot.footballmanager.entity.dao.impl;

import com.elliot.footballmanager.entity.dao.LeagueDao;
import com.elliot.footballmanager.entity.League;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.elliot.footballmanager.database.SqliteDatabaseConnector;

/**
 * @author Elliot
 */
public class LeagueDaoImpl implements LeagueDao {

  @Override
  public List<League> getAllLeagues() {
    String query = "SELECT * FROM LEAGUE";

    try (Connection conn = SqliteDatabaseConnector.connect();
        PreparedStatement pstmt = conn.prepareStatement(query)) {
      ResultSet rs = pstmt.executeQuery();

      List<League> allLeagues = new ArrayList<League>();
      while (rs.next()) {
        League league = new League(rs.getInt("LEAGUE_ID"), rs.getString("LEAGUE_NAME"),
            rs.getInt("COUNTRY_ID"));
        allLeagues.add(league);
      }
      return allLeagues;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Map<Integer, League> getAllLeaguesById(Integer countryId) {
    Map<Integer, League> allLeagues = new HashMap<Integer, League>();
    String query = "SELECT * FROM LEAGUE WHERE COUNTRY_ID = ?";

    try (Connection conn = SqliteDatabaseConnector.connect();
        PreparedStatement pstmt = conn.prepareStatement(query)) {
      pstmt.setInt(1, countryId);
      ResultSet rs = pstmt.executeQuery();

      while (rs.next()) {
        League league = new League(rs.getInt("LEAGUE_ID"), rs.getString("LEAGUE_NAME"),
            rs.getInt("COUNTRY_ID"));
        allLeagues.put(league.getLeagueId(), league);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return allLeagues;
  }

  @Override
  public League getLeagueById(Integer leagueId) {
    String query = "SELECT * FROM LEAGUE WHERE LEAGUE_ID = ?";

    try (Connection conn = SqliteDatabaseConnector.connect();
        PreparedStatement pstmt = conn.prepareStatement(query)) {
      pstmt.setInt(1, leagueId);

      ResultSet rs = pstmt.executeQuery();

      if (rs.isAfterLast()) {
        return null;
      }
      return new League(rs.getInt("LEAGUE_ID"), rs.getString("LEAGUE_NAME"),
          rs.getInt("COUNTRY_ID"));
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
