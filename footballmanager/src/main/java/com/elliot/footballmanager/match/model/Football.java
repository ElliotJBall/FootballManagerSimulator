package com.elliot.footballmanager.match.model;

import com.elliot.footballmanager.player.Player;

/**
 * @author Elliot
 */
public class Football {

    private Integer currentXCoordinate;
    private Integer currentYCoordinate;

    private Player playerInPossession;

    public Football() {

    }

    public Football(Integer currentXCoordinate, Integer currentYCoordinate, Player playerInPossession) {
        this.currentXCoordinate = currentXCoordinate;
        this.currentYCoordinate = currentYCoordinate;
        this.playerInPossession = playerInPossession;
    }

    public Integer getCurrentXCoordinate() {
        return currentXCoordinate;
    }

    public void setCurrentXCoordinate(Integer currentXCoordinate) {
        this.currentXCoordinate = currentXCoordinate;
    }

    public Integer getCurrentYCoordinate() {
        return currentYCoordinate;
    }

    public void setCurrentYCoordinate(Integer currentYCoordinate) {
        this.currentYCoordinate = currentYCoordinate;
    }

    public Player getPlayerInPossession() {
        return playerInPossession;
    }

    public void setPlayerInPossession(Player playerInPossession) {
        this.playerInPossession = playerInPossession;
        this.setCurrentXCoordinate(playerInPossession.getCurrentXCoordinate());
        this.setCurrentYCoordinate(playerInPossession.getCurrentYCoordinate());
    }

    public void removePlayerFromPossession() {
        this.playerInPossession = null;
    }

    public void updatePlayerInPossessionCoordinates() {
        if (playerInPossession != null) {
            this.setCurrentXCoordinate(this.getPlayerInPossession().getCurrentXCoordinate());
            this.setCurrentYCoordinate(this.getPlayerInPossession().getCurrentYCoordinate());
        }
    }
}
