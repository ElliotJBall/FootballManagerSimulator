package com.elliot.footballmanager.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.elliot.footballmanager.database.SqliteDatabaseConnector;
import com.elliot.footballmanager.footballteam.FootballTeam;
import com.elliot.footballmanager.footballteam.FootballTeamDao;
import com.elliot.footballmanager.footballteam.FootballTeamDaoImpl;

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
			pstmt.setString(2, manager.getFirstName());
			pstmt.setString(3, manager.getLastName());
			pstmt.setInt(4, manager.getCurrentFootballTeam().getFootballTeamId());
			
			// If count != 1 the statement did not successfully persist the Manager data into the database
			if (pstmt.executeUpdate() != 1) {
				throw new SQLException("The Manager was not successfully inserted into the database!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Manager getManagerById(Integer managerId) {
		String query = "SELECT * FROM MANAGER WHERE MANAGER_ID = ?";

		try (Connection conn = SqliteDatabaseConnector.connect();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, managerId);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.isAfterLast()) {
				return null;
			}
			
			FootballTeamDao footballTeamDao = new FootballTeamDaoImpl();
			FootballTeam footballTeam = footballTeamDao.getFootballTeamById(rs.getInt("ASSIGNED_FOOTBALL_CLUB"));
			
			return new Manager(rs.getInt("MANAGER_ID"), rs.getString("FIRST_NAME"), rs.getString("LAST_NAME"),
					footballTeam);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
