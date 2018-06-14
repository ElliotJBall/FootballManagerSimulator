package com.elliot.footballmanager.match.model.pitch;

import com.elliot.footballmanager.player.Player;

import java.util.Collection;

/**
 * @author Elliot
 */
public abstract class Pitch implements FootballPitch {

    private Integer xCoordinate;
    private Integer yCoordinate;

    private Collection<Player> playersWithinThisTile;

}
