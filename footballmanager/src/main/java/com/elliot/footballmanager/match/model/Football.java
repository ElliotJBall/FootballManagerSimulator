package com.elliot.footballmanager.match.model;

import com.elliot.footballmanager.player.Player;

/**
 * @author Elliot
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
