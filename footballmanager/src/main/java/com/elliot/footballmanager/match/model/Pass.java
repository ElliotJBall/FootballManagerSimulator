package com.elliot.footballmanager.match.model;

import com.elliot.footballmanager.match.RandomNumberGenerator;
import com.elliot.footballmanager.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * The pass object is used to select a passing strategy (Short / Long range). This strategy is used
 * to determine the next player to be assigned possession of the Football within the MatchEngine.
 *
 * @author Elliot
 */
public class Pass extends MatchEvent {

  private static Integer SHORT_RANGE_PASSING_DISTANCE = 2;
  private static String SHORT_PASSING_RANGE = "SHORT_RANGE";
  private static String LONG_PASSING_RANGE = "LONG_RANGE";

  private Football football;
  private List<Player> squadCurrentlyInPossession;
  private Player playerSelectedForPass;

  public Pass() {

  }

  public Pass(List<Player> squadCurrentlyInPossession, Football football) {
    this.squadCurrentlyInPossession = squadCurrentlyInPossession;
    this.football = football;
  }

  public Player getPlayerTheBallIsBeingPassedTo() {
    List<Player> playersAvailableToPassTo;
    if (RandomNumberGenerator.getRandomNumberBetweenZeroAndOneHundred() < 60) {
      playersAvailableToPassTo = getPlayersWithinSpecifiedPassingRange(SHORT_PASSING_RANGE);
    } else {
      playersAvailableToPassTo = getPlayersWithinSpecifiedPassingRange(LONG_PASSING_RANGE);
    }

    setPlayerSelectedForPass(playersAvailableToPassTo.get(RandomNumberGenerator
        .getRandomNumberBetweenZeroAnGivenNumber(playersAvailableToPassTo.size())));

    doesEventNeedToBeLogged();
    return getPlayerSelectedForPass();
  }

  private List<Player> getPlayersWithinSpecifiedPassingRange(String passingRange) {
    List<Player> playersInShortRange = new ArrayList<Player>();
    List<Player> playersInLongRange = new ArrayList<Player>();

    for (Player player : squadCurrentlyInPossession) {
      if (player.equals(football.getPlayerInPossession())) {
        continue;
      }

      if (isWithinShortRangePassingDistance(player)) {
        playersInShortRange.add(player);
      } else {
        playersInLongRange.add(player);
      }
    }

    if (passingRange.equals(SHORT_PASSING_RANGE)) {
      return playersInShortRange.size() != 0 ? playersInShortRange : playersInLongRange;
    } else {
      return playersInLongRange.size() != 0 ? playersInLongRange : playersInShortRange;
    }
  }

  private boolean isWithinShortRangePassingDistance(Player player) {
    return this.football.getCurrentXCoordinate() - player.getCurrentXCoordinate()
        <= SHORT_RANGE_PASSING_DISTANCE
        && this.football.getCurrentYCoordinate() - player.getCurrentYCoordinate()
        <= SHORT_RANGE_PASSING_DISTANCE;
  }

  public Player getPlayerSelectedForPass() {
    return playerSelectedForPass;
  }

  public void setPlayerSelectedForPass(Player playerSelectedForPass) {
    this.playerSelectedForPass = playerSelectedForPass;
  }

  @Override
  protected String buildMatchEventString() {
    StringBuilder message = new StringBuilder();
    message.append(getCurrentGameTime() + " ");
    message.append("[");
    message.append(football.getPlayerInPossession().getName());
    message.append("]");
    message.append(" Passes To ");
    message.append("[");
    message.append(getPlayerSelectedForPass().getName());
    message.append("]");

    return message.toString();
  }
}
