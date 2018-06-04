package com.elliot.footballmanager.match;

import com.elliot.footballmanager.footballteam.FootballTeam;

/**
 * The MatchEngine is where a simulation of a football
 * match takes place. Given two FootballTeams a match is
 * simulated and a MatchResult is given.
 * @author Elliot
 */
public class MatchEngine {

    private static FootballTeam homeTeam;
    private static FootballTeam awayTeam;

    // Private Constructor to avoid instantiation of MatchEngine objects
    private MatchEngine() {

    }


    public static MatchResult simulateFootballMatch(FootballTeam incomingHomeTeam, FootballTeam incomingAwayTeam) {
        setFootballTeams(incomingHomeTeam, incomingAwayTeam);



        MatchResult matchResult = new MatchResult(homeTeam, awayTeam, 1, 0);
        persistResultToDatabase(matchResult);
        return matchResult;
    }

    private static void persistResultToDatabase(MatchResult matchResult) {
        MatchEngineDao matchEngineDao = new MatchEngineDaoImpl();
        matchEngineDao.persistResultToDatabase(matchResult);
    }

    private static void setFootballTeams(FootballTeam incomingHomeTeam, FootballTeam incomingAwayTeam) {
        homeTeam = incomingHomeTeam;
        awayTeam = incomingAwayTeam;
    }
}
