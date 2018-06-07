package com.elliot.footballmanager.match;

import com.elliot.footballmanager.fixture.Fixture;
import com.elliot.footballmanager.footballteam.FootballTeam;
import com.elliot.footballmanager.gamemanager.GameManager;

/**
 * The MatchEngine is where a simulation of a football
 * match takes place. Given two FootballTeams a match is
 * simulated and a MatchResult is given.
 * @author Elliot
 */
public class MatchEngine {

    private static Fixture fixture;
    private static FootballTeam homeTeam;
    private static FootballTeam awayTeam;

    // Private Constructor to avoid instantiation of MatchEngine objects
    private MatchEngine() {

    }


    public static MatchResult simulateFootballMatch(GameManager gameManager) {
        initialiseFixtureInformation(gameManager);



        MatchResult matchResult = new MatchResult(homeTeam, awayTeam, 1, 0);
        persistResultToDatabase(matchResult);
        return matchResult;
    }

    private static void initialiseFixtureInformation(GameManager gameManager) {
        fixture = gameManager.getUpcomingFixtures().remove();

        homeTeam = fixture.getHomeTeam();
        awayTeam = fixture.getAwayTeam();
    }

    private static void persistResultToDatabase(MatchResult matchResult) {
        MatchEngineDao matchEngineDao = new MatchEngineDaoImpl();
        matchEngineDao.persistResultToDatabase(matchResult);
    }
}
