package com.elliot.footballmanager.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.elliot.footballmanager.database.SqliteDatabaseConnector;

/**
 * @author Elliot
 *
 */
public class ManagerDaoImpl implements ManagerDao {

	@Override
	public void insertIntoManagerTable(Manager manager) {
		String query = "INSERT INTO MANAGER VALUES (?, ?, ?, ?)";
		
		try (Connection conn = SqliteDatabaseConnector.connect();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, 0);
			pstmt.setString(2, manager.getFirstName());
			pstmt.setString(3, manager.getLastName());
			pstmt.setInt(4, manager.getCurrentFootballTeam().getFootballTeamId());
			
			int count = pstmt.executeUpdate();
			
			// If count != 1 the statement did not successfully persist the Manager data into the database
			if (count != 1) {
				throw new SQLException("The Manager was not successfully inserted into the database!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
