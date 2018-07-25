package com.elliot.footballmanager.match.engine;

import com.elliot.footballmanager.fixture.Fixture;
import com.elliot.footballmanager.footballteam.FootballTeam;
import com.elliot.footballmanager.footballteam.matchsetup.FootballTeamMatchSetup;
import com.elliot.footballmanager.gamemanager.GameManager;
import com.elliot.footballmanager.match.FootballTeamMatchStats;
import com.elliot.footballmanager.match.MatchResult;
import com.elliot.footballmanager.match.RandomNumberGenerator;
import com.elliot.footballmanager.match.model.*;
import com.elliot.footballmanager.match.model.pitch.FootballPitch;
import com.elliot.footballmanager.match.model.pitch.FootballPitchBuilder;
import com.elliot.footballmanager.match.model.pitch.FootballPitchPlayerPlacer;
import com.elliot.footballmanager.player.Player;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * The MatchEngine is where a simulation of a football
 * match takes place. Given two FootballTeams a match is
 * simulated and a MatchResult is given.
 * @author Elliot
 */
public class MatchEngine {

    private static boolean logGameEvents = false;

    private static Fixture fixture;

    private static FootballTeam homeTeam;
    private static FootballTeam awayTeam;

    public static FootballTeamMatchSetup homeTeamMatchSetup;
    public static FootballTeamMatchSetup awayTeamMatchSetup;

    private static Map<String, FootballTeamMatchStats> footballTeamToMatchStats;

    public static FootballPitch[][] footballPitch;
    private static Football football;

    private static double currentTimeInGame = 0.0d;

    // Private Constructor to avoid instantiation of MatchEngine objects
    private MatchEngine() {

    }

    public static MatchResult beginFootballMatchSimulator(GameManager gameManager) {
        beginPreMatchSetup(gameManager);

        buildFootballPitch();
        addPlayersToPitch();

        giveATeamTheFootball();

        MatchEngine.logGameEvents = true;

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
        footballTeamToMatchStats = new HashMap<String, FootballTeamMatchStats>();
        footballTeamToMatchStats.put(homeTeam.getTeamName(), new FootballTeamMatchStats(homeTeam));
        footballTeamToMatchStats.put(awayTeam.getTeamName(), new FootballTeamMatchStats(awayTeam));
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

            football = new Football(playerToKickOffGame.getCurrentXCoordinate(), playerToKickOffGame.getCurrentYCoordinate(),
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
        double simulationTime = MatchEngine.getCurrentTimeInGame() <= MatchEngineConstants.MINUTES_IN_FIRST_HALF ?
                MatchEngineConstants.MINUTES_IN_FIRST_HALF : MatchEngineConstants.MINUTES_IN_SECOND_HALF;

        while (MatchEngine.getCurrentTimeInGame() <= simulationTime) {
            determineNextGameAction();
            MatchEngine.setCurrentTimeInGame(MatchEngine.getCurrentTimeInGame() + 0.10D);

            isDelayForUserRequired();
        }
    }

    private static void determineNextGameAction() {
        if (RandomNumberGenerator.getRandomNumberBetweenZeroAndOneHundred() < 50) {
            passToAnotherTeamMate();
        } else {
            movePlayerInPossessionToNewTile();
        }

        // After a GameAction has happened begin moving all out of position players back to preferred Coordinates
        checkIfAPlayerCanAttemptATackle();

        checkIfPlayerAttemptsShotAtGoal();

        moveNonPossessionPlayersToPreferredCoordinates();
        updatePlayersTackledRecovery();
    }

    private static void passToAnotherTeamMate() {
        Pass pass = new Pass(getSquadCurrentlyInPossession(), football);
        Player newPlayerInPossession = pass.getPlayerTheBallIsBeingPassedTo();
        football.setPlayerInPossession(newPlayerInPossession);
    }

    private static void movePlayerInPossessionToNewTile() {
        Player playerInPossession = football.getPlayerInPossession();
        removePlayerFromOldTile(playerInPossession);

        Movement movement = new Movement();
        movement.movePlayerToNewTile(playerInPossession);

        addPlayerToNewTile(playerInPossession);
        football.updatePlayerInPossessionCoordinates();
    }

    private static void checkIfAPlayerCanAttemptATackle() {
        List<Player> players = getSquadNotCurrentlyInPossession().stream()
                .filter(player -> player.getGameTicksUntilRecoveredFromTackle().equals(0)
                        && football.getCurrentXCoordinate().equals(player.getCurrentXCoordinate() + 1)
                        || football.getCurrentXCoordinate().equals(player.getCurrentXCoordinate() - 1)
                        || football.getCurrentYCoordinate().equals(player.getCurrentYCoordinate() + 1)
                        || football.getCurrentYCoordinate().equals(player.getCurrentYCoordinate() - 1))
                .collect(Collectors.toList());

        if (players.size() > 0) {
            Player playerToChallengeForPossession = players.get(RandomNumberGenerator.getRandomNumberBetweenZeroAnGivenNumber(players.size()));

            Tackle tackle = new Tackle(playerToChallengeForPossession, football);
            tackle.attemptTackleOnPlayerInPossession();
        }
    }

    private static void checkIfPlayerAttemptsShotAtGoal() {
        Shot shot = new Shot(football.getPlayerInPossession());

        if (shot.doesPlayerDecideToShoot()) {
            Player opposingTeamsGoalKeeper = getSquadNotCurrentlyInPossession().get(0);
            FootballTeamMatchStats matchStats = footballTeamToMatchStats.get(football.getPlayerInPossession().getCurrentClub().getTeamName());

            shot.attemptShot(football, opposingTeamsGoalKeeper, matchStats);
        }
    }

    private static void updatePlayersTackledRecovery() {
        for (Player player : getAllPlayersFromBothSquads()) {
            if (player.getGameTicksUntilRecoveredFromTackle() > 0) {
                player.setGameTicksUntilRecoveredFromTackle(player.getGameTicksUntilRecoveredFromTackle() - 1);
            }
        }
    }

    private static void moveNonPossessionPlayersToPreferredCoordinates() {
        for (Player player : getAllPlayersFromBothSquads()) {
            if (football.getPlayerInPossession().equals(player)) {
                continue;
            }

            if (!player.getCurrentXCoordinate().equals(player.getPreferredXCoordinate())
                    || !player.getCurrentYCoordinate().equals(player.getPreferredYCoordinate())) {
                Movement movement = new Movement();
                movement.movePlayerNotInPossessionBackToPreferredPositions(player);
            }
        }
    }

    private static void removePlayerFromOldTile(Player player) {
        footballPitch[player.getCurrentXCoordinate()][player.getCurrentYCoordinate()].removePlayerFromTile(player);
    }

    private static void addPlayerToNewTile(Player player) {
        footballPitch[player.getCurrentXCoordinate()][player.getCurrentYCoordinate()].addPlayerToTile(player);
    }

    private static List<Player> getSquadCurrentlyInPossession() {
        if (football.getPlayerInPossession().getCurrentClub().getTeamName().equals(homeTeam.getTeamName())) {
            return Arrays.asList(homeTeamMatchSetup.getSelectedFormation().getStartingLineup());
        } else {
            return Arrays.asList(awayTeamMatchSetup.getSelectedFormation().getStartingLineup());
        }
    }

    private static List<Player> getSquadNotCurrentlyInPossession() {
        if (football.getPlayerInPossession().getCurrentClub().getTeamName().equals(homeTeam.getTeamName())) {
            return Arrays.asList(awayTeamMatchSetup.getSelectedFormation().getStartingLineup());
        } else {
            return Arrays.asList(homeTeamMatchSetup.getSelectedFormation().getStartingLineup());
        }
    }

    private static List<Player> getAllPlayersFromBothSquads() {
        List<Player> allPlayersOnFootballPitch = new ArrayList<Player>();
        allPlayersOnFootballPitch.addAll(Arrays.asList(homeTeamMatchSetup.getSelectedFormation().getStartingLineup()));
        allPlayersOnFootballPitch.addAll(Arrays.asList(awayTeamMatchSetup.getSelectedFormation().getStartingLineup()));
        return allPlayersOnFootballPitch;
    }

    private static void persistMatchResultToDatabase(MatchResult matchResult) {
        MatchEngineDao matchEngineDao = new MatchEngineDaoImpl();
        matchEngineDao.persistResultToDatabase(matchResult);
    }

    public static boolean isLoggingGameEvents() {
        return logGameEvents;
    }

    public static void setIsLoggingGameEvents(boolean isLoggingGameEvents) {
        MatchEngine.logGameEvents = isLoggingGameEvents;
    }

    private static void isDelayForUserRequired() {
        // If the logGameEvents flag is true, Add small delay slowing number of Events displayed
        try {
            if (MatchEngine.isLoggingGameEvents()) {
                TimeUnit.MILLISECONDS.sleep(400);
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public static double getCurrentTimeInGame() {
        return currentTimeInGame;
    }

    private static void setCurrentTimeInGame(double currentTimeInGame) {
        MatchEngine.currentTimeInGame = BigDecimal.valueOf(currentTimeInGame)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
