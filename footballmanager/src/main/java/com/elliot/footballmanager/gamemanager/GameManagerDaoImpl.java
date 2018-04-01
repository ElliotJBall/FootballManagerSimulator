package com.elliot.footballmanager.gamemanager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.elliot.footballmanager.country.Country;
import com.elliot.footballmanager.country.CountryDao;
import com.elliot.footballmanager.country.CountryDaoImpl;
import com.elliot.footballmanager.database.SqliteDatabaseConnector;
import com.elliot.footballmanager.footballteam.FootballTeam;
import com.elliot.footballmanager.footballteam.FootballTeamDao;
import com.elliot.footballmanager.footballteam.FootballTeamDaoImpl;
import com.elliot.footballmanager.league.League;
import com.elliot.footballmanager.league.LeagueDao;
import com.elliot.footballmanager.league.LeagueDaoImpl;
import com.elliot.footballmanager.manager.Manager;
import com.elliot.footballmanager.manager.ManagerDao;
import com.elliot.footballmanager.manager.ManagerDaoImpl;

/**
 * @author Elliot
 * This class makes use of the DAO pattern to perform CRUD operations on the 
 * FootballManager database.
 */
public class GameManagerDaoImpl implements GameManagerDao {

	@Override
	public void saveGame(GameManager gameManager) {
		//TODO: Confirm whether we only want one Save game available at a time or not.
		String deletePreviousSave = "DELETE FROM GAME_MANAGER";
		String insertNewSave = "INSERT INTO GAME_MANAGER VALUES (?, ?, ?, ?, ?, ?)";
		
		try (Connection conn = SqliteDatabaseConnector.connect();
				Statement stmt = conn.createStatement();
				PreparedStatement pDelete = conn.prepareStatement(deletePreviousSave)) {
			
			pDelete.executeUpdate();
			
			PreparedStatement pInsert = conn.prepareStatement(insertNewSave);
			pInsert.setInt(1, 1); // GAME_MANAGER_ID
			pInsert.setInt(2, gameManager.getCurrentCountry().getCountryId()); // SELECTED_COUNTRY_ID
			pInsert.setInt(3, gameManager.getCurrentLeague().getLeagueId()); // SELECTED_LEAGUE_ID
			pInsert.setInt(4, gameManager.getCurrentFootballTeam().getFootballTeamId()); // SELECTED_FOOTBALL_TEAM_ID
			pInsert.setInt(5, 1); // MANAGER_ID
			pInsert.setDate(6, (java.sql.Date) gameManager.getCurrentDate()); // CURRENT_DATE
			
			// If count != 1 the statement did not successfully persist the GameManager data into the database
			if (pInsert.executeUpdate() != 1) {
				throw new SQLException("The Game has not successfully been saved! Please try again.");
			} else {
				System.out.println("Created successfully!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public GameManager loadSavedGame() {
		String query = "SELECT * FROM GAME_MANAGER WHERE GAME_MANAGER_ID = 1";
		
		try (Connection conn = SqliteDatabaseConnector.connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			//TODO: Provide the dialog to create a new game from here
			// No results found
			if (!rs.next()) {
				System.out.println("No save game found! Please create a new game first.");
				return null;
			}
			
			CountryDao countryDao = new CountryDaoImpl();
			Country country = countryDao.getCountryById(rs.getInt("SELECTED_COUNTRY_ID"));
			
			LeagueDao leagueDao = new LeagueDaoImpl();
			League league = leagueDao.getLeagueById(rs.getInt("SELECTED_LEAGUE_ID"));
			
			FootballTeamDao footballTeamDao = new FootballTeamDaoImpl();
			FootballTeam footballTeam = footballTeamDao.getFootballTeamById(rs.getInt("SELECTED_FOOTBALL_TEAM_ID"));
			
			ManagerDao managerDao = new ManagerDaoImpl();
			Manager manager = managerDao.getManagerById(rs.getInt("MANAGER_ID"));
			
			Date date = rs.getDate("CURRENT_DATE");
			
			return new GameManager(country, league, footballTeam, manager, date);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
