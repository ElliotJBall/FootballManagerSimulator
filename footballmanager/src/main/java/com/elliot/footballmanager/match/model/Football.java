package com.elliot.footballmanager.match.model;

import com.elliot.footballmanager.player.Player;

/**
 * The Football object tracks who is currently in possession of
 * of the ball and any players close by.
 */
public class Football {
    private Player playerInPossession;

    public Football() {

    }

    public Player getPlayerInPossession() {
        return playerInPossession;
    }

    public void setPlayerInPossession(Player playerInPossession) {
        this.playerInPossession = playerInPossession;
    }
}
