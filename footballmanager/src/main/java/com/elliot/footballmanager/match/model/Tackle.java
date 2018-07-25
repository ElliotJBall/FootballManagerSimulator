package com.elliot.footballmanager.match.model;

import com.elliot.footballmanager.match.RandomNumberGenerator;
import com.elliot.footballmanager.player.Player;

/**
 * Provides a way of challenging the Player in possession
 * for the ball.
 * @author Elliot
 */
public class Tackle extends MatchEvent {

    private Player playerAttemptingChallenge;
    private Player playerBeingChallenged;
    private Football football;

    private TacklingMethod tacklingMethod;

    public Tackle() {

    }

    public Tackle(Player playerAttemptingChallenge, Football football) {
        this.playerAttemptingChallenge = playerAttemptingChallenge;
        this.playerBeingChallenged = football.getPlayerInPossession();
        this.football = football;
    }

    public void attemptTackleOnPlayerInPossession() {
        Integer challengerTacklingRating = determineTacklingStrategy();
        Integer challengedStrengthRating = playerBeingChallenged.getPhysicalAttributes().getStrength();

        double chanceOfSuccessfulTackle = ((double) challengerTacklingRating / (double) challengedStrengthRating) / (double) 2;

        if (RandomNumberGenerator.getRandomNumberBetweenZeroAndOne() <= chanceOfSuccessfulTackle) {
            playerBeingChallenged.setGameTicksUntilRecoveredFromTackle(tacklingMethod.getGameTickRecoveryTime());
            football.setPlayerInPossession(playerAttemptingChallenge);
        }
    }

    private Integer determineTacklingStrategy() {
        if (RandomNumberGenerator.getRandomNumberBetweenZeroAndOneHundred() < 50) {
            tacklingMethod = TacklingMethod.STANDING_TACKLE;
            return playerAttemptingChallenge.getTechnicalAttributes().getStandingTackle();
        } else {
            tacklingMethod = TacklingMethod.SLIDING_TACKLE;
            return playerAttemptingChallenge.getTechnicalAttributes().getSlidingTackle();
        }
    }

    public Player getPlayerAttemptingChallenge() {
        return playerAttemptingChallenge;
    }

    public Player getPlayerBeingChallenged() {
        return playerBeingChallenged;
    }

    @Override
    protected String buildMatchEventString() {
        StringBuilder message = new StringBuilder();
        message.append(getCurrentGameTime() + " ");
        message.append(getPlayerBeingChallenged().getName() + " ");
        message.append("Is being challenged by ");
        message.append(getPlayerAttemptingChallenge().getName());

        return message.toString();
    }

    /**
     * The different ways a player can attempt to tackle the
     * player in Possession with.
     * @author Elliot
     */
    private enum TacklingMethod {
        SLIDING_TACKLE(1),
        STANDING_TACKLE(2);

        private Integer gameTickRecoveryTime;

        TacklingMethod(Integer gameTickRecoveryTime) {
            this.gameTickRecoveryTime = gameTickRecoveryTime;
        }

        public Integer getGameTickRecoveryTime() {
            return gameTickRecoveryTime;
        }

        public void setGameTickRecoveryTime(Integer gameTickRecoveryTime) {
            this.gameTickRecoveryTime = gameTickRecoveryTime;
        }
    }
}
