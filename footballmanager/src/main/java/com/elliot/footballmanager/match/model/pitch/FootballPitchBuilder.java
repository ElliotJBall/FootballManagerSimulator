package com.elliot.footballmanager.match.model.pitch;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Helper class to create and return a new FootballPitch object for use
 * within the MatchEngine.
 * @author Elliot
 */
public class FootballPitchBuilder {

    // Integer[] First element = X | Second element = Y
    private static Collection<Integer[]> homeTeamGoalkeepingAreaCoOrdinates = new ArrayList<Integer[]>();
    private static Collection<Integer[]> awayTeamGoalkeepingAreaCoOrdinates = new ArrayList<Integer[]>();

    private static FootballPitch[][] footballPitch;

    private FootballPitchBuilder() {

    }

    public static FootballPitch[][] buildNewFootballPitch() {
        footballPitch = new FootballPitch[FootballPitchBuilderConstants.LENGTH_OF_FOOTBALL_PITCH][FootballPitchBuilderConstants.WIDTH_OF_FOOTBALL_PITCH];

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
        addPitch(FootballPitchBuilderConstants.homeTeamGoalCoOrdinates,
                new GoalPitch(FootballPitchBuilderConstants.homeTeamGoalCoOrdinates[0], FootballPitchBuilderConstants.homeTeamGoalCoOrdinates[1]));
    }

    private static void addAwayTeamGoal() {
        addPitch(FootballPitchBuilderConstants.awayTeamGoalCoOrdinates,
                new GoalPitch(FootballPitchBuilderConstants.awayTeamGoalCoOrdinates[0], FootballPitchBuilderConstants.awayTeamGoalCoOrdinates[1]));
    }

    private static void  addPitch(Integer[] coordinates, FootballPitch pitchToAdd) {
        footballPitch[coordinates[0]][coordinates[1]] = pitchToAdd;
    }
}