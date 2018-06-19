package com.elliot.footballmanager.match.model.pitch;

import com.elliot.footballmanager.match.engine.MatchEngine;
import com.elliot.footballmanager.player.Player;
import com.elliot.footballmanager.player.Position;

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
        homeTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {0, 2});
        homeTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {1, 2});
        homeTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {0, 3});
        homeTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {1, 3});
        homeTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {1, 4});
        homeTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {0, 5});
        homeTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {1, 5});
        homeTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {0, 6});
        homeTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {1, 6});
    }

    private static void setupAwayTeamGoalkeepingAreaCoOrdinates() {
        awayTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {14, 2});
        awayTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {13, 2});
        awayTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {14, 3});
        awayTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {13, 3});
        awayTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {13, 4});
        awayTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {14, 5});
        awayTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {13, 5});
        awayTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {14, 6});
        awayTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {13, 6});
    }

    private static void addOutfieldToPitch() {
        for (int i = 0; i < footballPitch.length; i++) {
            for (int j = 0; j < footballPitch[i].length; j++) {
                footballPitch[i][j] = new OutFieldPitch();
            }
        }
    }

    private static void addHomeTeamGoalkeepingArea() {
        for (Integer[] coordinates : homeTeamGoalkeepingAreaCoOrdinates) {
            addPitch(coordinates, new GoalkeepingPitch());
        }
    }

    private static void addAwayTeamGoalkeepingArea() {
        for (Integer[] coordinates : awayTeamGoalkeepingAreaCoOrdinates) {
            addPitch(coordinates, new GoalkeepingPitch());
        }
    }

    private static void addHomeTeamGoal() {
        addPitch(FootballPitchHelperConstants.homeTeamGoalCoOrdinates, new GoalPitch());
    }

    private static void addAwayTeamGoal() {
        addPitch(FootballPitchHelperConstants.awayTeamGoalCoOrdinates, new GoalPitch());
    }

    private static void  addPitch(Integer[] coordinates, FootballPitch pitchToAdd) {
        footballPitch[coordinates[0]][coordinates[1]] = pitchToAdd;
    }

    public static void addPlayersToFootballPitch() {
        footballPitch = MatchEngine.footballPitch;

        addHomeTeamPlayersToPitch();
    }

    private static void addHomeTeamPlayersToPitch() {
        Player[] homeTeamStartingLineup = MatchEngine.homeTeamMatchSetup.getSelectedFormation().getStartingLineup();

        addHomeTeamGoalkeeperToPitch(homeTeamStartingLineup);
        addHomeTeamDefendersToPitch(homeTeamStartingLineup);
    }

    private static void addHomeTeamGoalkeeperToPitch(Player[] startingLineup) {
        footballPitch[0][1].addPlayerToTile(startingLineup[0]);
    }

    private static void addHomeTeamDefendersToPitch(Player[] startingLineup) {
        List<Player> defenders = getPlayersByPosition(startingLineup, Position.GeneralisedPosition.DEFENDER);
        placePlayersOntoPitch(defenders, FootballPitchHelperConstants.ROW_FOR_HOME_TEAM_DEFENDERS);
    }

    private static List<Player> getPlayersByPosition(Player[] startingLineup, Position.GeneralisedPosition requiredGeneralPosition) {
        List<Player> playersForGeneralisedPosition = new ArrayList<Player>();

        for (Player player : startingLineup) {
            for (Position position : player.getPreferredPositions()) {
                if (Position.getGeneralPositionBySpecificPosition(position).equals(requiredGeneralPosition)) {
                    playersForGeneralisedPosition.add(player);
                }
            }
        }
        return playersForGeneralisedPosition;
    }

    private static void placePlayersOntoPitch(List<Player> playersToPlace, Integer rowToInsertPlayersInto) {
        FootballPitch[] rowToInsertInto = footballPitch[rowToInsertPlayersInto];

        // Edge case: Insert into the middle of the row
        if (playersToPlace.size() == 1) {
            rowToInsertInto[FootballPitchHelperConstants.MIDDLE_OF_A_FOOTBALL_PITCH_ROW].addPlayerToTile(playersToPlace.get(0));
        }

        // If players mod 2 == 0 then have low, high variables and add players there, incrementing each time
        // else do addition to tile differently
        if (playersToPlace.size() % 2 > 0) {

        } else {

        }


    }

}