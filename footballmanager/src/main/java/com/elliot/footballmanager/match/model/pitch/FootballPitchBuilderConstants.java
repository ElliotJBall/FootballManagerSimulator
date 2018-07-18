package com.elliot.footballmanager.match.model.pitch;

/**
 * Consolidate any constants that are required for the creation
 * and population of new FootballPitch in this class.
 * @author Elliot
 */
public class FootballPitchBuilderConstants {

    public static final Integer[] homeTeamGoalCoOrdinates = new Integer[] {0, 5};
    public static final Integer[] awayTeamGoalCoOrdinates = new Integer[] {14, 5};

    public static final Integer LENGTH_OF_FOOTBALL_PITCH = 15;
    public static final Integer WIDTH_OF_FOOTBALL_PITCH = 11;

    public static final Integer MIDDLE_OF_A_FOOTBALL_PITCH_ROW = 5;
    public static final Integer MIDDLE_OF_A_FOOTBALL_PITCH_COLUMN = 7;

    public static final Integer ROW_FOR_HOME_TEAM_GOALKEEPER = 0;
    public static final Integer ROW_FOR_HOME_TEAM_DEFENDERS = 3;
    public static final Integer ROW_FOR_HOME_TEAM_MIDFIELDERS = 6;
    public static final Integer ROW_FOR_HOME_TEAM_ATTACKERS = 9;

    public static final Integer ROW_FOR_AWAY_TEAM_GOALKEEPER = 14;
    public static final Integer ROW_FOR_AWAY_TEAM_DEFENDERS = 11;
    public static final Integer ROW_FOR_AWAY_TEAM_MIDFIELDERS = 8;
    public static final Integer ROW_FOR_AWAY_TEAM_ATTACKERS = 5;

}
