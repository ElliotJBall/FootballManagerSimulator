package com.elliot.footballmanager.match.model;

import com.elliot.footballmanager.match.FootballTeamMatchStats;
import com.elliot.footballmanager.match.RandomNumberGenerator;
import com.elliot.footballmanager.player.Player;

/**
 * Used within the MatchEngine to determine whether a Player
 * decides to shoot and calculates whether he scores.
 * @author Elliot
 */
public class Shot extends MatchEvent {

    private Player playerTakingShot;
    private Integer numberOfTilesAwayFromGoal;
    private ShotOutcome shotOutcome;

    public Shot() {

    }

    public Shot(Player playerTakingShot) {
        this.playerTakingShot = playerTakingShot;
        numberOfTilesAwayFromGoal = getPlayersDistanceFromGoal();
    }

    private Integer getPlayersDistanceFromGoal() {
        return Math.abs(getPlayerTakingShot().getCurrentXCoordinate() - getPlayerTakingShot().getOpposingTeamsGoal()[0]);
    }

    public boolean doesPlayerDecideToShoot() {
        return RandomNumberGenerator.getRandomNumberBetweenZeroAndOneHundred() < playersProbabilityOfScoringAGoal();
    }

    public void attemptShot(Football football, Player opposingTeamsGoalkeeper, FootballTeamMatchStats matchStats) {
        Integer playersChanceOfScoring = playersProbabilityOfScoringAGoal();
        Integer randomChanceOfScoring = RandomNumberGenerator.getRandomNumberBetweenZeroAndOneHundred();

        if (randomChanceOfScoring < (playersChanceOfScoring / 2)) {
            updateMatchStatsGoalScored(matchStats);
            setShotOutcome(ShotOutcome.GOAL_SCORED);
        } else if (randomChanceOfScoring >= (playersChanceOfScoring / 2)
                && randomChanceOfScoring <= playersChanceOfScoring) {
            updateMatchStatsShotSaved(matchStats);
            setShotOutcome(ShotOutcome.SHOT_SAVED);
        } else {
            updateMatchStatsShotMissed(matchStats);
            setShotOutcome(ShotOutcome.SHOT_MISSED);
        }

        giveGoalKeeperTheFootball(football, opposingTeamsGoalkeeper);

        doesEventNeedToBeLogged();
    }

    private void updateMatchStatsGoalScored(FootballTeamMatchStats matchStats) {
        matchStats.incrementGoalsScoredByOne();
    }

    private void updateMatchStatsShotSaved(FootballTeamMatchStats matchStats) {
        matchStats.incrementShotsOnTargetByOne();
    }

    private void updateMatchStatsShotMissed(FootballTeamMatchStats matchStats) {
        matchStats.incrementShotsByOne();
    }

    private void giveGoalKeeperTheFootball(Football football, Player goalkeeper) {
        football.setPlayerInPossession(goalkeeper);
    }

    //TODO: Continue to flesh this out, E,g use additional attributes determining whether they're off balance etc.
    private Integer playersProbabilityOfScoringAGoal() {
        Integer total = 0;
        total += getPlayerTakingShot().getTechnicalAttributes().getFinishing();
        total += getPlayerTakingShot().getTechnicalAttributes().getShotPower();
        total += getPlayerTakingShot().getTechnicalAttributes().getCurve();
        total += getPlayerTakingShot().getTechnicalAttributes().getCurve();
        total += getPlayerTakingShot().getTechnicalAttributes().getBallControl();

        Integer numberOfAttributesUsedForCalculation = 5;
        total = total / numberOfAttributesUsedForCalculation;

        for (int i = 0; i < getNumberOfTilesAwayFromGoal(); i++) {
            total -= 10;
        }

        return total;
    }

    public Player getPlayerTakingShot() {
        return playerTakingShot;
    }

    public void setPlayerTakingShot(Player playerTakingShot) {
        this.playerTakingShot = playerTakingShot;
    }

    public Integer getNumberOfTilesAwayFromGoal() {
        return numberOfTilesAwayFromGoal;
    }

    public void setNumberOfTilesAwayFromGoal(Integer numberOfTilesAwayFromGoal) {
        this.numberOfTilesAwayFromGoal = numberOfTilesAwayFromGoal;
    }

    public ShotOutcome getShotOutcome() {
        return shotOutcome;
    }

    public void setShotOutcome(ShotOutcome shotOutcome) {
        this.shotOutcome = shotOutcome;
    }

    @Override
    public String buildMatchEventString() {
        StringBuilder message = new StringBuilder();
        message.append(getCurrentGameTime() + " ");
        message.append("[");
        message.append(getPlayerTakingShot().getName() + " ");
        message.append("]");
        message.append(" Shoots ");
        message.append("[");
        message.append(getShotOutcome().getShotOutcomeValue());
        message.append("]");

        return message.toString();
    }

    private enum ShotOutcome {
        SHOT_MISSED("Missed!"),
        SHOT_SAVED("Had his shot saved!"),
        GOAL_SCORED("Scored!!!");

        private String ShotOutcomeValue;

        ShotOutcome(String shotOutcomeValue) {
            this.ShotOutcomeValue = shotOutcomeValue;
        }

        public String getShotOutcomeValue() {
            return ShotOutcomeValue;
        }

        public void setShotOutcomeValue(String shotOutcomeValue) {
            ShotOutcomeValue = shotOutcomeValue;
        }
    }
}
