package com.elliot.footballmanager.match.model.pitch;

import com.elliot.footballmanager.player.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * @author Elliot
 */
public abstract class Pitch implements FootballPitch {

    private Integer xCoordinate;
    private Integer yCoordinate;

    private Collection<Player> playersWithinThisTile;

    public Pitch() {

    }

    public Pitch(Integer xCoordinate, Integer yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;

        playersWithinThisTile = new HashSet<Player>();
    }

    @Override
    public void addPlayerToTile(Player player) {
        if (playersWithinThisTile == null) {
            playersWithinThisTile = new HashSet<Player>();
        }
        playersWithinThisTile.add(player);
    }

    @Override
    public void removePlayerFromTile(Player player) {
        playersWithinThisTile.remove(player);
    }

    @Override
    public Collection<Player> getPlayersWithinThisTile() {
        return playersWithinThisTile;
    }

}
