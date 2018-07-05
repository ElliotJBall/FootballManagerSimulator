package com.elliot.footballmanager.match.model.pitch;

import com.elliot.footballmanager.footballteam.matchsetup.FootballTeamMatchSetup;
import com.elliot.footballmanager.footballteam.matchsetup.MatchDaySquad;
import com.elliot.footballmanager.match.engine.MatchEngine;
import com.elliot.footballmanager.player.Player;
import com.elliot.footballmanager.player.Position;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Helper class to create and return a new FootballPitch object for use
 * within the MatchEngine.
 * @author Elliot
 */
public class FootballPitchHelper {

    // Integer[] First element = X | Second element = Y
    private static Collection<Integer[]> homeTeamGoalkeepingAreaCoOrdinates = new ArrayList<Integer[]>();
    private static Collection<Integer[]> awayTeamGoalkeepingAreaCoOrdinates = new ArrayList<Integer[]>();

    private static FootballPitch[][] footballPitch;

    private static List<Player> alreadyPlacedPlayers = new ArrayList<Player>();

    private FootballPitchHelper() {

    }

    public static FootballPitch[][] buildNewFootballPitch() {
        footballPitch = new FootballPitch[FootballPitchHelperConstants.LENGTH_OF_FOOTBALL_PITCH][FootballPitchHelperConstants.WIDTH_OF_FOOTBALL_PITCH];

        setupHomeTeamGoalkeepingAreaCoOrdinates();
        setupAwayTeamGoalkeepingAreaCoOrdinates();

        addOutfieldToPitch();

        addHomeTeamGoalkeepingArea();
        addAwayTeamGoalkeepingArea();

        addHomeTeamGoal();
        addAwayTeamGoal();

        return footballPitch;
    }

    private static void setupHomeTeamGoalkeepingAreaCoOrdinates() {
        homeTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {0, 3});
        homeTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {0, 4});
        homeTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {0, 6});
        homeTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {0, 7});
        homeTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {1, 3});
        homeTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {1, 4});
        homeTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {1, 5});
        homeTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {1, 6});
        homeTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {1, 7});
    }

    private static void setupAwayTeamGoalkeepingAreaCoOrdinates() {
        awayTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {14, 3});
        awayTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {14, 4});
        awayTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {14, 6});
        awayTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {14, 7});
        awayTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {13, 3});
        awayTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {13, 4});
        awayTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {13, 5});
        awayTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {13, 6});
        awayTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {13, 7});
    }

    private static void addOutfieldToPitch() {
        for (int i = 0; i < footballPitch.length; i++) {
            for (int j = 0; j < footballPitch[i].length; j++) {
                footballPitch[i][j] = new OutFieldPitch(i, j);
            }
        }
    }

    private static void addHomeTeamGoalkeepingArea() {
        for (Integer[] coordinates : homeTeamGoalkeepingAreaCoOrdinates) {
            addPitch(coordinates, new GoalkeepingPitch(coordinates[0], coordinates[1]));
        }
    }

    private static void addAwayTeamGoalkeepingArea() {
        for (Integer[] coordinates : awayTeamGoalkeepingAreaCoOrdinates) {
            addPitch(coordinates, new GoalkeepingPitch(coordinates[0], coordinates[1]));
        }
    }

    private static void addHomeTeamGoal() {
        addPitch(FootballPitchHelperConstants.homeTeamGoalCoOrdinates,
                new GoalPitch(FootballPitchHelperConstants.homeTeamGoalCoOrdinates[0], FootballPitchHelperConstants.homeTeamGoalCoOrdinates[1]));
    }

    private static void addAwayTeamGoal() {
        addPitch(FootballPitchHelperConstants.awayTeamGoalCoOrdinates,
                new GoalPitch(FootballPitchHelperConstants.awayTeamGoalCoOrdinates[0], FootballPitchHelperConstants.awayTeamGoalCoOrdinates[1]));
    }

    private static void  addPitch(Integer[] coordinates, FootballPitch pitchToAdd) {
        footballPitch[coordinates[0]][coordinates[1]] = pitchToAdd;
    }

    public static void addPlayersToFootballPitch() {
        footballPitch = MatchEngine.footballPitch;

        addHomeTeamPlayersToPitch();
        addAwayTeamPlayersToPitch();
    }

    private static void addHomeTeamPlayersToPitch() {
        MatchDaySquad homeTeamMatchDaySquad = MatchEngine.homeTeamMatchSetup.getSelectedFormation();

        alreadyPlacedPlayers = new ArrayList<Player>();

        addTeamGoalkeeperToPitch(homeTeamMatchDaySquad, FootballPitchHelperConstants.ROW_FOR_HOME_TEAM_GOALKEEPER);
        addTeamDefendersToPitch(homeTeamMatchDaySquad, FootballPitchHelperConstants.ROW_FOR_HOME_TEAM_DEFENDERS);
        addTeamMidfieldersToPitch(homeTeamMatchDaySquad, FootballPitchHelperConstants.ROW_FOR_HOME_TEAM_MIDFIELDERS);
        addTeamAttackersToPitch(homeTeamMatchDaySquad, FootballPitchHelperConstants.ROW_FOR_HOME_TEAM_ATTACKERS);
    }

    private static void addAwayTeamPlayersToPitch() {
        MatchDaySquad awayTeamMatchDaySquad = MatchEngine.awayTeamMatchSetup.getSelectedFormation();

        alreadyPlacedPlayers = new ArrayList<Player>();

        addTeamGoalkeeperToPitch(awayTeamMatchDaySquad, FootballPitchHelperConstants.ROW_FOR_AWAY_TEAM_GOALKEEPER);
        addTeamDefendersToPitch(awayTeamMatchDaySquad, FootballPitchHelperConstants.ROW_FOR_AWAY_TEAM_DEFENDERS);
        addTeamMidfieldersToPitch(awayTeamMatchDaySquad, FootballPitchHelperConstants.ROW_FOR_AWAY_TEAM_MIDFIELDERS);
        addTeamAttackersToPitch(awayTeamMatchDaySquad, FootballPitchHelperConstants.ROW_FOR_AWAY_TEAM_ATTACKERS);
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
            player.setxCoordinate(rowToInsertPlayersInto);
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
        middlePlayer.setyCoordinate(FootballPitchHelperConstants.MIDDLE_OF_A_FOOTBALL_PITCH_ROW);
        rowToInsertInto[FootballPitchHelperConstants.MIDDLE_OF_A_FOOTBALL_PITCH_ROW].addPlayerToTile(middlePlayer);
        playersToPlace.remove(0);

        // Increments of two so that a empty tile is left between a player
        int leftSide = FootballPitchHelperConstants.MIDDLE_OF_A_FOOTBALL_PITCH_ROW - 2;
        int rightSide = FootballPitchHelperConstants.MIDDLE_OF_A_FOOTBALL_PITCH_ROW + 2;

        addPlayerToGivenFootballRow(playersToPlace, rowToInsertInto, leftSide, rightSide);
    }

    private static void addEvenNumberOfPlayersToFootballPitch(List<Player> playersToPlace, FootballPitch[] rowToInsertInto) {
        // Increments of two so that a empty tile is left between a player
        int leftSide = FootballPitchHelperConstants.MIDDLE_OF_A_FOOTBALL_PITCH_ROW - 1;
        int rightSide = FootballPitchHelperConstants.MIDDLE_OF_A_FOOTBALL_PITCH_ROW + 1;

        addPlayerToGivenFootballRow(playersToPlace, rowToInsertInto, leftSide, rightSide);
    }

    private static void addPlayerToGivenFootballRow(List<Player> playersToPlace, FootballPitch[] rowToInsertInto, Integer leftSide, Integer rightSide) {
        while (playersToPlace.size() > 0) {
            Player leftPlayer = playersToPlace.get(0);
            leftPlayer.setyCoordinate(leftSide);
            rowToInsertInto[leftSide].addPlayerToTile(leftPlayer);
            playersToPlace.remove(0);
            leftSide -= 2;

            Player rightPlayer = playersToPlace.get(0);
            rightPlayer.setyCoordinate(rightSide);
            rowToInsertInto[rightSide].addPlayerToTile(rightPlayer);
            playersToPlace.remove(0);
            rightSide += 2;
        }
    }
}