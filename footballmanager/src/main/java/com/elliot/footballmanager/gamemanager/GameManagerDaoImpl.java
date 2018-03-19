package com.elliot.footballmanager.gamemanager;

import java.sql.Connection;
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
	public void createNewSaveGame(GameManager gameManager) {
		//TODO: Confirm whether we only want one Save game available at a time or not.
		String checkForSaveGame = "SELECT COUNT(*) FROM GAME_MANAGER";
		String insertNewSave = "INSERT INTO GAME_MANAGER VALUES (?, ?, ?, ?, ?)";
		
		try (Connection conn = SqliteDatabaseConnector.connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(checkForSaveGame)) {
			
			// If there are any resuls there is a game saved in the GAME_MANAGER table
			if (rs.getInt(1) > 0) {
				//TODO: Provide the option to delete the saved game here and then let them retry with the saved information
				System.out.println("There is already a saved game. Please delete this and then try again.");
				return;
			}
			
			PreparedStatement pstmt = conn.prepareStatement(insertNewSave);
			pstmt.setInt(1, 1); // GAME_MANAGER_ID
			pstmt.setInt(2, gameManager.getCurrentCountry().getCountryId()); // SELECTED_COUNTRY_ID
			pstmt.setInt(3, gameManager.getCurrentLeague().getLeagueId()); // SELECTED_LEAGUE_ID
			pstmt.setInt(4, gameManager.getCurrentFootballTeam().getFootballTeamId()); // SELECTED_FOOTBALL_TEAM_ID
			pstmt.setInt(5, 1); // MANAGER_ID
			
			// If count != 1 the statement did not successfully persist the GameManager data into the database
			if (pstmt.executeUpdate() != 1) {
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
			
			return new GameManager(country, league, footballTeam, manager);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
