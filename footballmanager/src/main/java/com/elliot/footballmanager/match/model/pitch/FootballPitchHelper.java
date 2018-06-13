package com.elliot.footballmanager.match.model.pitch;

import com.elliot.footballmanager.match.engine.MatchEngineConstants;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Helper class to create and return a new FootballPitch object for use
 * within the MatchEngine.
 * @author Elliot
 */
public class FootballPitchHelper {

    // Integer[] First element = X | Second element = Y
    private static Collection<Integer[]> homeTeamGoalkeepingAreaCoOrdinates = new ArrayList<Integer[]>();
    private static Collection<Integer[]> awayTeamGoalkeepingAreaCoOrdinates = new ArrayList<Integer[]>();

    private static Integer[] homeTeamGoalCoOrdinates = new Integer[] {0, 3};
    private static Integer[] awayTeamGoalCoOrdinates = new Integer[] {8, 3};

    private static FootballPitch[][] footballPitch;

    private FootballPitchHelper() {

    }

    public static FootballPitch[][] buildNewFootballPitch() {
        footballPitch = new FootballPitch[MatchEngineConstants.LENGTH_OF_FOOTBALL_PITCH][MatchEngineConstants.WIDTH_OF_FOOTBALL_PITCH];

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
        homeTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {1, 3});
        homeTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {1, 4});
        homeTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {0, 4});
    }

    private static void setupAwayTeamGoalkeepingAreaCoOrdinates() {
        awayTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {8, 2});
        awayTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {7, 2});
        awayTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {7, 3});
        awayTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {7, 4});
        awayTeamGoalkeepingAreaCoOrdinates.add(new Integer[] {8, 4});
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
        addPitch(homeTeamGoalCoOrdinates, new GoalPitch());
    }

    private static void addAwayTeamGoal() {
        addPitch(awayTeamGoalCoOrdinates, new GoalPitch());
    }

    private static void addPitch(Integer[] coordinates, FootballPitch pitchToAdd) {
        for (int i = 0; i < footballPitch.length; i++) {
            for (int j = 0; j < footballPitch[i].length; j++) {
                if (i == coordinates[0] && j == coordinates[1]) {
                    footballPitch[i][j] = pitchToAdd;
                }
            }
        }
    }
}
