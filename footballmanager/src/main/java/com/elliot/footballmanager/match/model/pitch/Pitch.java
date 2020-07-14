package com.elliot.footballmanager.match.model.pitch;

import com.elliot.footballmanager.entity.Player;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Elliot
 */
public abstract class Pitch implements FootballPitch {

    private Integer xCoordinate;
    private Integer yCoordinate;

    private Set<Player> playersWithinThisTile;

    public Pitch() {

    }

    public Pitch(Integer xCoordinate, Integer yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;

        playersWithinThisTile = new HashSet<Player>();
    }

    public Integer getxCoordinate() {
        return this.xCoordinate;
    }

    public Integer getyCoordinate() {
        return this.yCoordinate;
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
    public Set<Player> getPlayersWithinThisTile() {
        return playersWithinThisTile;
    }

}
