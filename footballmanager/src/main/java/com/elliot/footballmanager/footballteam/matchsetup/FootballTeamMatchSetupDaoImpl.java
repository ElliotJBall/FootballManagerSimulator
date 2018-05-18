package com.elliot.footballmanager.footballteam.matchsetup;

import com.elliot.footballmanager.database.SqliteDatabaseConnector;
import com.elliot.footballmanager.footballteam.FootballTeam;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FootballTeamMatchSetupDaoImpl implements FootballTeamMatchSetupDao {

    @Override
    public FootballTeamMatchSetup getFootballTeamMatchSetup(Integer footballTeamId) {
        String query = "SELECT * FROM FOOTBALL_TEAM_MATCH_SETUP WHERE FOOTBALL_TEAM_ID = ?";

        try (Connection conn = SqliteDatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, footballTeamId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Object object = rs.getObject("FOOTBALL_TEAM_MATCH_SETUP_CLASS");
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream((byte[])object);
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                return (FootballTeamMatchSetup) objectInputStream.readObject();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void persistFootballTeamMatchSetup(FootballTeam footballTeam) {
        String query = "INSERT INTO FOOTBALL_TEAM_MATCH_SETUP (FOOTBALL_TEAM_ID, FOOTBALL_TEAM_MATCH_SETUP_CLASS) VALUES (?, ?)";

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
