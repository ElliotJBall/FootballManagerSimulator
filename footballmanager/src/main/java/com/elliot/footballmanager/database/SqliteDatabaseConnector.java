package com.elliot.footballmanager.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * @author Elliot
 * Creates a new Connection (Session) to the SQLite3 database. The SqliteDatabaseConnector class
 * will be used throughout the application when data is required regarding the Football Manager Simulation. 
 */
public class SqliteDatabaseConnector {
	
	private static String CONNECTION_URL;

	/**
	 * Creates a new connection to the SQLite3 Database. If the CONNECTION_URL variable is null
	 * the variable will be set to the files location.
	 * @return A connection object to the SQLite3 Database
	 */
	public static Connection connect() {
		if (CONNECTION_URL == null) {
			getDatabaseFileLocation();
		}
		
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection(CONNECTION_URL);
		} catch (SQLException e) {
			System.out.println("Connection Failure!");
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.out.println("Failure to close the connection!");
					e.printStackTrace();
				}
			}
		}
		return connection;
	}
	
	/**
	 * Stores the SQLites Database path location as a String variable so that the 
	 * SqliteDatabaseConnector can connect to the Football Manager's database. 
	 * This has been done to remove any hard coded value for the database connection string.
	 */
	private static void getDatabaseFileLocation() {
		 CONNECTION_URL = "jdbc:sqlite:" + new File(System.getProperty("user.dir")).getParentFile().toString() + "\\FootballManagerDatabase";
	}
}
