package com.elliot.footballmanager.match.model.pitch;

import com.elliot.footballmanager.player.Player;

import java.util.Collection;

/**
 * @author Elliot
 */
public interface FootballPitch {

    public void addPlayerToTile(Player player);

    public void removePlayerFromTile(Player player);

    public Collection<Player> getPlayersWithinThisTile();

}
