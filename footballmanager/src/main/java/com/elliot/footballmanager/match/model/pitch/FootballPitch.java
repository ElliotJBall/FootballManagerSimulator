package com.elliot.footballmanager.match.model.pitch;

import com.elliot.footballmanager.entity.Player;

import java.util.Set;

/**
 * @author Elliot
 */
public interface FootballPitch {

  public void addPlayerToTile(Player player);

  public void removePlayerFromTile(Player player);

  public Set<Player> getPlayersWithinThisTile();

}
