package com.elliot.footballmanager.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates a new Connection (Session) to the SQLite3 database. The SqliteDatabaseConnector class
 * will be used throughout the application when data is required regarding the Football Manager
 * Simulation.
 *
 * @author Elliot
 */
public class SqliteDatabaseConnector {

  private static String CONNECTION_URL;

  /**
   * Creates a new connection to the SQLite3 Database. If the CONNECTION_URL variable is null the
   * variable will be set to the files location.
   *
   * @return A connection object to the SQLite3 Database
   */
  public static Connection connect() {
    if (CONNECTION_URL == null) {
      getDatabaseFileLocation();
    }

    Connection connection = null;

    try {
      Class.forName("org.sqlite.JDBC");
      connection = DriverManager.getConnection(CONNECTION_URL);
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return connection;
  }

  public static void closeConnection(Connection connection) {
    try {
      if (connection != null) {
        connection.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Runs a series of SQL statements that remove artifacts so that a new saved game can be created.
   * Note: Deletes vital information about a save game, not reversible.
   */
  public static void deleteSavedGameArtifacts() {
    List<String> deleteStatements = new ArrayList<String>();
    deleteStatements.add("DELETE FROM GAME_MANAGER");
    deleteStatements.add("DELETE FROM MANAGER");
    deleteStatements.add("DELETE FROM FIXTURE");
    deleteStatements.add("DELETE FROM FOOTBALL_TEAM_MATCH_SETUP");
    deleteStatements.add("DELETE FROM STANDING");

    try (Connection conn = SqliteDatabaseConnector.connect();
        Statement stmt = conn.createStatement()) {

      for (String statement : deleteStatements) {
        int result = stmt.executeUpdate(statement);
        if (result > 0) {
          System.out.println(statement + " Run successfully.");
        }
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  /**
   * Create the path to the location of the SQLite database file.
   */
  private static void getDatabaseFileLocation() {
    if (System.getProperty("os.name").toLowerCase().startsWith("windows")) {
      CONNECTION_URL =
          "jdbc:sqlite:" + new File(System.getProperty("user.dir")).getParentFile().toString()
              + "\\FootballManagerDatabase";
    } else {
      CONNECTION_URL =
          "jdbc:sqlite:" + new File(System.getProperty("user.dir")).getParentFile().toString()
              + "/FootballManagerDatabase";
    }
  }
}
