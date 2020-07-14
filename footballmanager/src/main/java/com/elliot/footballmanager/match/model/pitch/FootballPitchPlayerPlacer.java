package com.elliot.footballmanager.match.model.pitch;

import com.elliot.footballmanager.footballteam.matchsetup.MatchDaySquad;
import com.elliot.footballmanager.match.engine.MatchEngine;
import com.elliot.footballmanager.entity.Player;
import com.elliot.footballmanager.match.model.Position;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Elliot
 */
public class FootballPitchPlayerPlacer {

    private static FootballPitch[][] footballPitch;
    private static List<Player> alreadyPlacedPlayers = new ArrayList<Player>();

    public static void addPlayersToFootballPitch() {
        footballPitch = MatchEngine.footballPitch;

        addHomeTeamPlayersToPitch();
        addAwayTeamPlayersToPitch();
    }

    private static void addHomeTeamPlayersToPitch() {
        MatchDaySquad homeTeamMatchDaySquad = MatchEngine.homeTeamMatchSetup.getSelectedFormation();

        alreadyPlacedPlayers = new ArrayList<Player>();
        alreadyPlacedPlayers.add(homeTeamMatchDaySquad.getStartingLineup()[0]);

        addTeamGoalkeeperToPitch(homeTeamMatchDaySquad, FootballPitchBuilderConstants.ROW_FOR_HOME_TEAM_GOALKEEPER);
        addTeamDefendersToPitch(homeTeamMatchDaySquad, FootballPitchBuilderConstants.ROW_FOR_HOME_TEAM_DEFENDERS);
        addTeamMidfieldersToPitch(homeTeamMatchDaySquad, FootballPitchBuilderConstants.ROW_FOR_HOME_TEAM_MIDFIELDERS);
        addTeamAttackersToPitch(homeTeamMatchDaySquad, FootballPitchBuilderConstants.ROW_FOR_HOME_TEAM_ATTACKERS);

        addOpposingGoalCoordinatesToPlayers(FootballPitchBuilderConstants.awayTeamGoalCoOrdinates, alreadyPlacedPlayers);
    }

    private static void addAwayTeamPlayersToPitch() {
        MatchDaySquad awayTeamMatchDaySquad = MatchEngine.awayTeamMatchSetup.getSelectedFormation();

        alreadyPlacedPlayers = new ArrayList<Player>();
        alreadyPlacedPlayers.add(awayTeamMatchDaySquad.getStartingLineup()[0]);

        addTeamGoalkeeperToPitch(awayTeamMatchDaySquad, FootballPitchBuilderConstants.ROW_FOR_AWAY_TEAM_GOALKEEPER);
        addTeamDefendersToPitch(awayTeamMatchDaySquad, FootballPitchBuilderConstants.ROW_FOR_AWAY_TEAM_DEFENDERS);
        addTeamMidfieldersToPitch(awayTeamMatchDaySquad, FootballPitchBuilderConstants.ROW_FOR_AWAY_TEAM_MIDFIELDERS);
        addTeamAttackersToPitch(awayTeamMatchDaySquad, FootballPitchBuilderConstants.ROW_FOR_AWAY_TEAM_ATTACKERS);

        addOpposingGoalCoordinatesToPlayers(FootballPitchBuilderConstants.homeTeamGoalCoOrdinates, alreadyPlacedPlayers);
    }

    private static void addTeamGoalkeeperToPitch(MatchDaySquad matchDaySquad, Integer rowToInsertPlayersInto) {
        Player[] startingLineup = matchDaySquad.getStartingLineup();

        List<Player> goalkeeper = new ArrayList<Player>();
        goalkeeper.add(startingLineup[0]);
        placePlayersOntoPitch(goalkeeper, rowToInsertPlayersInto);
    }

    private static void addTeamDefendersToPitch(MatchDaySquad matchDaySquad, Integer rowToInsertPlayersInto) {
        List<Player> defenders = getPlayersByPosition(matchDaySquad, Position.GeneralisedPosition.DEFENDER);
        placePlayersOntoPitch(defenders, rowToInsertPlayersInto);
    }

    private static void addTeamMidfieldersToPitch(MatchDaySquad matchDaySquad, Integer rowToInsertPlayersInto) {
        List<Player> defenders = getPlayersByPosition(matchDaySquad, Position.GeneralisedPosition.MIDFIELDER);
        placePlayersOntoPitch(defenders, rowToInsertPlayersInto);
    }

    private static void addTeamAttackersToPitch(MatchDaySquad matchDaySquad, Integer rowToInsertPlayersInto) {
        List<Player> defenders = getPlayersByPosition(matchDaySquad, Position.GeneralisedPosition.ATTACKER);
        placePlayersOntoPitch(defenders, rowToInsertPlayersInto);
    }

    private static List<Player> getPlayersByPosition(MatchDaySquad matchDaySquad, Position.GeneralisedPosition requiredGeneralPosition) {
        Player[] startingLineup = matchDaySquad.getStartingLineup();
        List<Player> playersForGeneralisedPosition = new ArrayList<Player>();

        int playersRequired = getNumberOfPlayersRequiredForGeneralisedPosition(matchDaySquad, requiredGeneralPosition);

        for (Player player : startingLineup) {
            // Check if we have enough players for the selected formation (Otherwise it can return more players than needed);
            if (playersForGeneralisedPosition.size() >= playersRequired) {
                return playersForGeneralisedPosition;
            }

            for (Position position : player.getPreferredPositions()) {
                if (Position.getGeneralPositionBySpecificPosition(position).equals(requiredGeneralPosition) && !alreadyPlacedPlayers.contains(player)) {
                    alreadyPlacedPlayers.add(player);
                    playersForGeneralisedPosition.add(player);
                    break;
                }
            }
        }
        return playersForGeneralisedPosition;
    }

    //TODO: Need an alternative solution to parsing the Formation string
    private static int getNumberOfPlayersRequiredForGeneralisedPosition(MatchDaySquad matchDaySquad, Position.GeneralisedPosition requiredGeneralPosition) {
        String[] formationInStringArray = matchDaySquad.getFormation().getFormationName().split("-");

        switch (requiredGeneralPosition) {
            case DEFENDER:
                return Integer.parseInt(formationInStringArray[0]);
            case MIDFIELDER:
                return Integer.parseInt(formationInStringArray[1]);
            case ATTACKER:
                return Integer.parseInt(formationInStringArray[2]);
            default:
                return 0;
        }
    }

    private static void placePlayersOntoPitch(List<Player> playersToPlace, Integer rowToInsertPlayersInto) {
        FootballPitch[] rowToInsertInto = footballPitch[rowToInsertPlayersInto];

        for (Player player : playersToPlace) {
            player.setBothPreferredAndCurrentXCoordinate(rowToInsertPlayersInto);
        }

        // Choose appropriate placing strategy and align the players evenly across the FootballPitch
        if (playersToPlace.size() % 2 > 0) {
            addOddNumberOfPlayersToFootballPitch(playersToPlace, rowToInsertInto);
        } else {
            addEvenNumberOfPlayersToFootballPitch(playersToPlace, rowToInsertInto);
        }
    }

    private static void addOddNumberOfPlayersToFootballPitch(List<Player> playersToPlace, FootballPitch[] rowToInsertInto) {
        // Start from the middle of the FootballPitch[], increment and add a player on each side
        Player middlePlayer = playersToPlace.get(0);
        middlePlayer.setBothPreferredAndCurrentYCoordinate(FootballPitchBuilderConstants.MIDDLE_OF_A_FOOTBALL_PITCH_ROW);
        rowToInsertInto[FootballPitchBuilderConstants.MIDDLE_OF_A_FOOTBALL_PITCH_ROW].addPlayerToTile(middlePlayer);
        playersToPlace.remove(0);

        // Increments of two so that a empty tile is left between a player
        int leftSide = FootballPitchBuilderConstants.MIDDLE_OF_A_FOOTBALL_PITCH_ROW - 2;
        int rightSide = FootballPitchBuilderConstants.MIDDLE_OF_A_FOOTBALL_PITCH_ROW + 2;

        addPlayerToGivenFootballRow(playersToPlace, rowToInsertInto, leftSide, rightSide);
    }

    private static void addEvenNumberOfPlayersToFootballPitch(List<Player> playersToPlace, FootballPitch[] rowToInsertInto) {
        // Increments of two so that a empty tile is left between a player
        int leftSide = FootballPitchBuilderConstants.MIDDLE_OF_A_FOOTBALL_PITCH_ROW - 1;
        int rightSide = FootballPitchBuilderConstants.MIDDLE_OF_A_FOOTBALL_PITCH_ROW + 1;

        addPlayerToGivenFootballRow(playersToPlace, rowToInsertInto, leftSide, rightSide);
    }

    private static void addPlayerToGivenFootballRow(List<Player> playersToPlace, FootballPitch[] rowToInsertInto, Integer leftSide, Integer rightSide) {
        while (playersToPlace.size() > 0) {
            Player leftPlayer = playersToPlace.get(0);
            leftPlayer.setBothPreferredAndCurrentYCoordinate(leftSide);
            rowToInsertInto[leftSide].addPlayerToTile(leftPlayer);
            playersToPlace.remove(0);
            leftSide -= 2;

            Player rightPlayer = playersToPlace.get(0);
            rightPlayer.setBothPreferredAndCurrentYCoordinate(rightSide);
            rowToInsertInto[rightSide].addPlayerToTile(rightPlayer);
            playersToPlace.remove(0);
            rightSide += 2;
        }
    }

    private static void addOpposingGoalCoordinatesToPlayers(Integer[] coordinatesOfGoal, Collection<Player> players) {
        for (Player player : players) {
            player.setOpposingTeamsGoal(coordinatesOfGoal);
        }
    }
}
