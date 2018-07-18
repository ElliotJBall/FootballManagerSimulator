package com.elliot.footballmanager.match.engine;

import com.elliot.footballmanager.fixture.Fixture;
import com.elliot.footballmanager.footballteam.FootballTeam;
import com.elliot.footballmanager.footballteam.matchsetup.FootballTeamMatchSetup;
import com.elliot.footballmanager.gamemanager.GameManager;
import com.elliot.footballmanager.match.FootballTeamMatchStats;
import com.elliot.footballmanager.match.MatchResult;
import com.elliot.footballmanager.match.model.Football;
import com.elliot.footballmanager.match.model.pitch.FootballPitch;
import com.elliot.footballmanager.match.model.pitch.FootballPitchBuilder;
import com.elliot.footballmanager.match.model.pitch.FootballPitchBuilderConstants;
import com.elliot.footballmanager.match.model.pitch.FootballPitchPlayerPlacer;
import com.elliot.footballmanager.player.Player;

import java.util.*;

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
    private static Football football;

    private static FootballTeamMatchStats matchStats;


    // Private Constructor to avoid instantiation of MatchEngine objects
    private MatchEngine() {

    }

    public static MatchResult beginFootballMatchSimulator(GameManager gameManager) {
        beginPreMatchSetup(gameManager);

        buildFootballPitch();
        addPlayersToPitch();

        giveATeamTheFootball();

        simulateOneHalfOfFootball();
        simulateOneHalfOfFootball();

        return null;
    }

    private static void beginPreMatchSetup(GameManager gameManager) {
        initialiseFixtureInformation(gameManager);
        initialiseFootballTeamMatchStats();
        initialiseFootballTeamSquads();
    }

    private static void initialiseFixtureInformation(GameManager gameManager) {
        fixture = gameManager.getUpcomingFixtures().remove();

        homeTeam = fixture.getHomeTeam();
        awayTeam = fixture.getAwayTeam();
    }

      private static void initialiseFootballTeamMatchStats() {
        footballTeamToMatchStats = new HashMap<FootballTeam, FootballTeamMatchStats>();
        footballTeamToMatchStats.put(homeTeam, new FootballTeamMatchStats(homeTeam));
        footballTeamToMatchStats.put(awayTeam, new FootballTeamMatchStats(awayTeam));
    }

    private static void initialiseFootballTeamSquads() {
        homeTeamMatchSetup = homeTeam.getMatchSetup();
        awayTeamMatchSetup = awayTeam.getMatchSetup();
    }

    private static void buildFootballPitch() {
        footballPitch = FootballPitchBuilder.buildNewFootballPitch();
    }

    private static void addPlayersToPitch() {
        FootballPitchPlayerPlacer.addPlayersToFootballPitch();
    }

    private static void giveATeamTheFootball() {
        if (football == null) {
            Player[] players = homeTeamMatchSetup.getSelectedFormation().getStartingLineup();
            Player playerToKickOffGame = players[6];

            football = new Football(playerToKickOffGame.getxCoordinate(), playerToKickOffGame.getyCoordinate(),
                    playerToKickOffGame);
            return;
        }

        if (football.getPlayerInPossession().getCurrentClub().equals(homeTeam)) {
            Player[] players = homeTeamMatchSetup.getSelectedFormation().getStartingLineup();
            football.setPlayerInPossession(players[6]);
        } else {
            Player[] players = awayTeamMatchSetup.getSelectedFormation().getStartingLineup();
            football.setPlayerInPossession(players[6]);
        }

    }

    private static void simulateOneHalfOfFootball() {
        double timeRemainingInHalf = MatchEngineConstants.MINUTES_REMAINING_IN_HALF;

        // TODO: Look into alternative for Double due to precision issues, BigDecimal suitable replacement?
        while (timeRemainingInHalf > 0D) {
            determineNextGameAction();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(football.getPlayerInPossession().getName() + " " + football.getCurrentXCoordinate() + " " + football.getCurrentYCoordinate());

            timeRemainingInHalf -= 0.10D;
        }
    }

    private static void determineNextGameAction() {
        passToAnotherTeamMate();
    }

    private static void passToAnotherTeamMate() {
        List<Player> playersAvailableToPassTo = new ArrayList<Player>();
        if (RandomNumberGenerator.getRandomNumberBetweenZeroAndOneHundred() < 60) {
            playersAvailableToPassTo = getPlayersWithinSpecifiedPassingRange(MatchEngineConstants.SHORT_PASSING_RANGE);
        } else {
            playersAvailableToPassTo = getPlayersWithinSpecifiedPassingRange(MatchEngineConstants.LONG_PASSING_RANGE);
        }

        football.setPlayerInPossession(playersAvailableToPassTo.get(RandomNumberGenerator.getRandomNumberBetweenZeroAnGivenNumber(playersAvailableToPassTo.size())));
    }

    private static List<Player> getPlayersWithinSpecifiedPassingRange(String passingRange) {
        List<Player> playersInShortRange = new ArrayList<Player>();
        List<Player> playersInLongRange = new ArrayList<Player>();
        for (Player player : squadCurrentlyInPossession()) {
            if (player.equals(football.getPlayerInPossession())) {
                continue;
            }

            if (isWithinShortRangePassingDistance(player)) {
                playersInShortRange.add(player);
            } else {
                playersInLongRange.add(player);
            }
        }

        if (passingRange.equals(MatchEngineConstants.SHORT_PASSING_RANGE)) {
            return playersInShortRange;
        } else {
            return playersInLongRange;
        }

    }

    private static List<Player> squadCurrentlyInPossession() {
        if (football.getPlayerInPossession().getCurrentClub().getTeamName().equals(homeTeam.getTeamName())) {
            return Arrays.asList(homeTeamMatchSetup.getSelectedFormation().getStartingLineup());
        } else {
            return Arrays.asList(awayTeamMatchSetup.getSelectedFormation().getStartingLineup());
        }
    }

    private static boolean isWithinShortRangePassingDistance(Player player) {
        return football.getCurrentXCoordinate() - player.getxCoordinate() <= MatchEngineConstants.SHORT_RANGE_PASSING_DISTANCE
                && football.getCurrentYCoordinate() - player.getyCoordinate() <= MatchEngineConstants.SHORT_RANGE_PASSING_DISTANCE;
    }

    private static void persistMatchResultToDatabase(MatchResult matchResult) {
        MatchEngineDao matchEngineDao = new MatchEngineDaoImpl();
        matchEngineDao.persistResultToDatabase(matchResult);
    }
}
