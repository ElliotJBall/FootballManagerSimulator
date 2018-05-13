package com.elliot.footballmanager.footballteam.matchsetup;

import com.elliot.footballmanager.database.SqliteDatabaseConnector;
import com.elliot.footballmanager.footballteam.FootballTeam;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FootballTeamMatchSetupDaoImpl implements FootballTeamMatchSetupDao {

    @Override
    public FootballTeamMatchSetup getFootballTeamMatchSetup(Integer footballTeamId) {
        return null;
    }

    @Override
    public void persistFootballTeamMatchSetup(FootballTeam footballTeam) {
        String query = "INSERT INTO MATCH_DAY_SQUAD_INFO (FOOTBALL_TEAM_ID, MATCH_DAY_INFO) VALUES (?, ?)";

        try (Connection conn = SqliteDatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(footballTeam.getMatchSetup());

            byte[] footballTeamMatchSetupAsBytes = byteArrayOutputStream.toByteArray();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(footballTeamMatchSetupAsBytes);
            pstmt.setInt(1, footballTeam.getFootballTeamId());
            pstmt.setBinaryStream(2, byteArrayInputStream, footballTeamMatchSetupAsBytes.length);

           pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
