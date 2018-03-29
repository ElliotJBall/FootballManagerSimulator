package com.elliot.footballmanager.player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.PreferencesFactory;

import com.elliot.footballmanager.database.SqliteDatabaseConnector;
import com.elliot.footballmanager.footballteam.FootballTeam;
import com.elliot.footballmanager.footballteam.FootballTeamDao;
import com.elliot.footballmanager.footballteam.FootballTeamDaoImpl;

/**
 * @author Elliot
 * 
 */
public class PlayerDaoImpl implements PlayerDao {

	@Override
	public List<Player> getAllPlayersForFootballTeam(FootballTeam footballTeam) {
		String query = "SELECT * FROM PLAYER WHERE CLUB LIKE ?";
		
		try (Connection conn = SqliteDatabaseConnector.connect();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, footballTeam.getTeamName());
			
			ResultSet rs = pstmt.executeQuery();
			
			if (!rs.next()) {
				return new ArrayList<Player>();
			}
			
			List<Player> squad = new ArrayList<Player>();
			while (rs.next()) {
				Player player = buildBasePlayerObject(rs);
				//TODO: Add attributes objects
				
				squad.add(player);
			}
			return squad;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<Player>();
	}
	
	private Player buildBasePlayerObject(ResultSet rs) throws SQLException {
		FootballTeamDao footballTeamDao = new FootballTeamDaoImpl();
		FootballTeam footballTeam = footballTeamDao.getFootballTeamByName(rs.getString("CLUB"));
		
		List<Position> positions = getPositionsFromString(rs.getString("PREFERRED POSITIONS"));
		
		return new Player(rs.getInt("PLAYER_ID"), rs.getString("NAME"), rs.getInt("AGE"), rs.getString("NATIONALITY"),
				rs.getInt("OVERALL"), footballTeam, rs.getDouble("VALUE"), rs.getDouble("WAGE"), positions);
	}
	
	private List<Position> getPositionsFromString(String positionsAsString) {
		String[] parts = positionsAsString.split(" ");
		
		if (parts.length == 0 || parts == null) {
			return new ArrayList<Position>();
		}
		
		List<Position> preferredPositions = new ArrayList<Position>();
		for (String string : parts) {
			preferredPositions.add(Position.getPositionFromString(string));
		}
		return preferredPositions;
	}
}
