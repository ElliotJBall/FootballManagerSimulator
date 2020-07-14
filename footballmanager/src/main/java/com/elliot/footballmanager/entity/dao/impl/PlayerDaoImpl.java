package com.elliot.footballmanager.entity.dao.impl;

import com.elliot.footballmanager.entity.dao.PlayerDao;
import com.elliot.footballmanager.entity.Player;
import com.elliot.footballmanager.match.model.Position;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.elliot.footballmanager.database.SqliteDatabaseConnector;
import com.elliot.footballmanager.entity.FootballTeam;
import com.elliot.footballmanager.entity.attributes.GoalkeeperAttributes;
import com.elliot.footballmanager.entity.attributes.MentalAttributes;
import com.elliot.footballmanager.entity.attributes.PhysicalAttributes;
import com.elliot.footballmanager.entity.attributes.TechnicalAttributes;

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
			
			if (rs.isAfterLast()) {
				return new ArrayList<Player>();
			}
			
			List<Player> squad = new ArrayList<Player>();
			while (rs.next()) {
				Player player = buildBasePlayerObject(rs, footballTeam);
				squad.add(player);
			}
			return squad;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<Player>();
	}
	
	private Player buildBasePlayerObject(ResultSet rs, FootballTeam footballTeam) throws SQLException {
		Set<Position> positions = getPositionsFromString(rs.getString("PREFERRED_POSITIONS"));
		
		Player player = new Player(rs.getInt("PLAYER_ID"), rs.getString("NAME"), rs.getInt("AGE"), rs.getString("NATIONALITY"),
				rs.getInt("OVERALL"), footballTeam, rs.getDouble("VALUE"), rs.getDouble("WAGE"), positions);
		
		player.setGoalkeeperAttributes(buildGoalkeeperAttributes(rs));
		player.setMentalAttributes(buildMentalAttributes(rs));
		player.setPhysicalAttributes(buildPhysicalAttributes(rs));
		player.setTechnicalAttributes(buildTechnicalAttributes(rs));
		
		return player;
	}
	
	private Set<Position> getPositionsFromString(String positionsAsString) {
		String[] parts = positionsAsString.split(" ");
		
		if (parts.length == 0 || parts == null) {
			return new HashSet<Position>();
		}
		
		Set<Position> preferredPositions = new HashSet<Position>();
		for (String string : parts) {
			try {
				preferredPositions.add(Position.getPositionFromString(string));
			} catch (IllegalArgumentException exception) {
				exception.printStackTrace();
				preferredPositions.add(Position.CM);
			}
		}
		return preferredPositions;
	}
	
	private GoalkeeperAttributes buildGoalkeeperAttributes(ResultSet rs) throws SQLException {
		return new GoalkeeperAttributes(rs.getInt("GK_DIVING"), rs.getInt("GK_HANDLING"), rs.getInt("GK_KICKING"),
				rs.getInt("GK_POSITIONING"), rs.getInt("GK_REFLEXES"));
	}
	
	private MentalAttributes buildMentalAttributes(ResultSet rs) throws SQLException {
		return new MentalAttributes(rs.getInt("POSITIONING"), rs.getInt("VISION"), rs.getInt("COMPOSURE"),
				rs.getInt("INTERCEPTIONS"), rs.getInt("AGGRESSION"));
	}
	
	private PhysicalAttributes buildPhysicalAttributes(ResultSet rs) throws SQLException {
		return new PhysicalAttributes(rs.getInt("ACCELERATION"), rs.getInt("SPRINT_SPEED"), rs.getInt("AGILITY"), 
				rs.getInt("BALANCE"), rs.getInt("REACTIONS"), rs.getInt("JUMPING"), 
				rs.getInt("STAMINA"), rs.getInt("STRENGTH"));
	}
	
	private TechnicalAttributes buildTechnicalAttributes(ResultSet rs) throws SQLException {
		return new TechnicalAttributes(rs.getInt("FINISHING"), rs.getInt("LONG_SHOTS"), rs.getInt("PENALTIES"), 
				rs.getInt("SHOT_POWER"), rs.getInt("VOLLEYS"), rs.getInt("CROSSING"), 
				rs.getInt("CURVE"), rs.getInt("FREE_KICK_ACCURACY"), rs.getInt("LONG_PASSING"), 
				rs.getInt("SHORT_PASSING"), rs.getInt("BALL_CONTROL"), rs.getInt("DRIBBLING"), 
				rs.getInt("HEADING_ACCURACY"), rs.getInt("MARKING"), rs.getInt("SLIDING_TACKLE"), 
				rs.getInt("STANDING_TACKLE"));
	}
}
