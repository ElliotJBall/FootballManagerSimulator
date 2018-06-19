package com.elliot.footballmanager.match.model.pitch;

import com.elliot.footballmanager.player.Player;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Elliot
 */
public abstract class Pitch implements FootballPitch {

    private Integer xCoordinate;
    private Integer yCoordinate;

    private Collection<Player> playersWithinThisTile;

    @Override
    public void addPlayerToTile(Player player) {
        if (playersWithinThisTile == null) {
            playersWithinThisTile = new ArrayList<Player>();
        }
        playersWithinThisTile.add(player);
    }

}
