package com.elliot.footballmanager.match.engine;

import com.elliot.footballmanager.fixture.Fixture;
import com.elliot.footballmanager.footballteam.FootballTeam;
import com.elliot.footballmanager.footballteam.matchsetup.FootballTeamMatchSetup;
import com.elliot.footballmanager.gamemanager.GameManager;
import com.elliot.footballmanager.match.FootballTeamMatchStats;
import com.elliot.footballmanager.match.MatchResult;
import com.elliot.footballmanager.match.model.pitch.FootballPitch;
import com.elliot.footballmanager.match.model.pitch.FootballPitchHelper;

import java.util.HashMap;
import java.util.Map;

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

    public static FootballTeamMatchSetup homeTeamMatchSetup;
    public static FootballTeamMatchSetup awayTeamMatchSetup;

    private static Map<FootballTeam, FootballTeamMatchStats> footballTeamToMatchStats;

    public static FootballPitch[][] footballPitch;

    // Private Constructor to avoid instantiation of MatchEngine objects
    private MatchEngine() {

    }


    public static MatchResult beginFootballMatchSimulator(GameManager gameManager) {
        beginPreMatchSetup(gameManager);

        buildFootballPitch();
        addPlayersToPitch();

        return null;
    }

    private static void beginPreMatchSetup(GameManager gameManager) {
        initialiseFixtureInformation(gameManager);
        initialiseFootballTeamMatchStatMap();
        initialiseFootballTeamSquads();
    }

    private static void buildFootballPitch() {
        footballPitch = FootballPitchHelper.buildNewFootballPitch();
    }

    private static void addPlayersToPitch() {
        FootballPitchHelper.addPlayersToFootballPitch();
    }

    private static void initialiseFixtureInformation(GameManager gameManager) {
        fixture = gameManager.getUpcomingFixtures().remove();

        homeTeam = fixture.getHomeTeam();
        awayTeam = fixture.getAwayTeam();
    }

      private static void initialiseFootballTeamMatchStatMap() {
        footballTeamToMatchStats = new HashMap<FootballTeam, FootballTeamMatchStats>();
        footballTeamToMatchStats.put(homeTeam, new FootballTeamMatchStats(homeTeam));
        footballTeamToMatchStats.put(awayTeam, new FootballTeamMatchStats(awayTeam));
    }

    private static void initialiseFootballTeamSquads() {
        homeTeamMatchSetup = homeTeam.getMatchSetup();
        awayTeamMatchSetup = awayTeam.getMatchSetup();
    }

    private static void persistMatchResultToDatabase(MatchResult matchResult) {
        MatchEngineDao matchEngineDao = new MatchEngineDaoImpl();
        matchEngineDao.persistResultToDatabase(matchResult);
    }
}
