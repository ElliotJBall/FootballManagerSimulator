package com.elliot.footballmanager.match.model;

import com.elliot.footballmanager.match.RandomNumberGenerator;
import com.elliot.footballmanager.match.model.pitch.FootballPitchBuilderConstants;
import com.elliot.footballmanager.entity.Player;

/**
 * Provides a way of moving a Player from one tile to another within the MatchEngine FootballPitch.
 *
 * @author Elliot
 */
public class Movement extends MatchEvent {

  private Player playerInPossession;
  private Direction directionToMovePlayerIn;

  public Movement() {

  }

  public void movePlayerToNewTile(Player player) {
    playerInPossession = player;
    directionToMovePlayerIn = getRandomDirection();

    while (willMovePlayerOutsideFootballPitch(player, getDirectionToMovePlayerIn()) != false) {
      setDirectionToMovePlayerIn(getRandomDirection());
    }

    player.addDirectionBackToPrefferedCoordinates(
        getOppositeDirectionForGivenDirection(getDirectionToMovePlayerIn()));
    updatePlayerWithNewCoordinates(player, getDirectionToMovePlayerIn());

    doesEventNeedToBeLogged();
  }

  private Direction getRandomDirection() {
    return Direction.values()[RandomNumberGenerator
        .getRandomNumberBetweenZeroAnGivenNumber(Direction.values().length)];
  }

  private boolean willMovePlayerOutsideFootballPitch(Player player, Direction direction) {
    return (player.getCurrentXCoordinate() + direction.getXValue()) <= 0
        || (player.getCurrentXCoordinate() + direction.getXValue()) >= (
        FootballPitchBuilderConstants.LENGTH_OF_FOOTBALL_PITCH - 1)
        || (player.getCurrentYCoordinate() + direction.getYValue()) <= 0
        || (player.getCurrentYCoordinate() + direction.getYValue()) >= (
        FootballPitchBuilderConstants.WIDTH_OF_FOOTBALL_PITCH - 1);
  }

  public void movePlayerNotInPossessionBackToPreferredPositions(Player player) {
    if (player.getDirectionsBackToPreferredCoordinates().size() > 0) {
      Direction directionToMoveIn = player.getDirectionsBackToPreferredCoordinates().pop();
      updatePlayerWithNewCoordinates(player, directionToMoveIn);
    }
  }

  private void updatePlayerWithNewCoordinates(Player player, Direction directionToMovePlayerIn) {
    switch (directionToMovePlayerIn) {
      case UP:
        player.setCurrentYCoordinate(
            player.getCurrentYCoordinate() + directionToMovePlayerIn.getYValue());
        break;
      case DOWN:
        player.setCurrentYCoordinate(
            player.getCurrentYCoordinate() + directionToMovePlayerIn.getYValue());
        break;
      case LEFT:
        player.setCurrentXCoordinate(
            player.getCurrentXCoordinate() + directionToMovePlayerIn.getXValue());
        break;
      case RIGHT:
        player.setCurrentXCoordinate(
            player.getCurrentXCoordinate() + directionToMovePlayerIn.getXValue());
        break;
      case DIAGONAL_LEFT_UP:
        player.setCurrentXCoordinate(
            player.getCurrentXCoordinate() + directionToMovePlayerIn.getXValue());
        player.setCurrentYCoordinate(
            player.getCurrentYCoordinate() + directionToMovePlayerIn.getYValue());
        break;
      case DIAGONAL_LEFT_DOWN:
        player.setCurrentXCoordinate(
            player.getCurrentXCoordinate() + directionToMovePlayerIn.getXValue());
        player.setCurrentYCoordinate(
            player.getCurrentYCoordinate() + directionToMovePlayerIn.getYValue());
        break;
      case DIAGONAL_RIGHT_UP:
        player.setCurrentXCoordinate(
            player.getCurrentXCoordinate() + directionToMovePlayerIn.getXValue());
        player.setCurrentYCoordinate(
            player.getCurrentYCoordinate() + directionToMovePlayerIn.getYValue());
        break;
      case DIAGONAL_RIGHT_DOWN:
        player.setCurrentXCoordinate(
            player.getCurrentXCoordinate() + directionToMovePlayerIn.getXValue());
        player.setCurrentYCoordinate(
            player.getCurrentYCoordinate() + directionToMovePlayerIn.getYValue());
        break;
      case NO_DIRECTION:
        break;
      default:
        throw new IllegalArgumentException(
            "Cannot move player when the direction has not been specified");
    }
  }

  private Direction getOppositeDirectionForGivenDirection(Direction direction) {
    switch (direction) {
      case UP:
        return Direction.DOWN;
      case DOWN:
        return Direction.UP;
      case LEFT:
        return Direction.RIGHT;
      case RIGHT:
        return Direction.LEFT;
      case DIAGONAL_LEFT_UP:
        return Direction.DIAGONAL_RIGHT_DOWN;
      case DIAGONAL_LEFT_DOWN:
        return Direction.DIAGONAL_RIGHT_UP;
      case DIAGONAL_RIGHT_UP:
        return Direction.DIAGONAL_LEFT_DOWN;
      case DIAGONAL_RIGHT_DOWN:
        return Direction.DIAGONAL_LEFT_UP;
      case NO_DIRECTION:
        return Direction.NO_DIRECTION;
      default:
        throw new IllegalArgumentException(
            "Cannot move player when the direction has not been specified");
    }
  }

  public Player getPlayerInPossession() {
    return playerInPossession;
  }

  public Direction getDirectionToMovePlayerIn() {
    return directionToMovePlayerIn;
  }

  public void setDirectionToMovePlayerIn(Direction directionToMovePlayerIn) {
    this.directionToMovePlayerIn = directionToMovePlayerIn;
  }

  @Override
  protected String buildMatchEventString() {
    StringBuilder message = new StringBuilder();
    message.append(getCurrentGameTime() + " ");
    message.append("[");
    message.append(getPlayerInPossession().getName());
    message.append("]");
    message.append(" Moved ");
    message.append("[");
    message.append(getDirectionToMovePlayerIn().getDirectionValueAsString());
    message.append("]");

    return message.toString();
  }

  /**
   * The possible directions that a player can move in on the FootballPitch.
   *
   * @author Elliot
   */
  public enum Direction {
    UP(0, -1, "Up"),
    DOWN(0, 1, "Down"),
    LEFT(-1, 0, "Left"),
    RIGHT(1, 0, "Right"),
    DIAGONAL_LEFT_UP(-1, -1, "Diagonally Left Up"),
    DIAGONAL_LEFT_DOWN(-1, 1, "Diagonally Left Down"),
    DIAGONAL_RIGHT_UP(1, -1, "Diagonally Right Up"),
    DIAGONAL_RIGHT_DOWN(1, 1, "Diagonally Right Down"),
    NO_DIRECTION(0, 0, "No Movement");

    private Integer xValue;
    private Integer yValue;
    private String directionValueAsString;

    Direction(Integer xValue, Integer yValue, String directionValueAsString) {
      this.setXValue(xValue);
      this.setYValue(yValue);
      this.directionValueAsString = directionValueAsString;
    }

    public Integer getXValue() {
      return xValue;
    }

    public void setXValue(Integer xValue) {
      this.xValue = xValue;
    }

    public Integer getYValue() {
      return yValue;
    }

    public void setYValue(Integer yValue) {
      this.yValue = yValue;
    }

    public String getDirectionValueAsString() {
      return directionValueAsString;
    }

    public void setDirectionValueAsString(String directionValueAsString) {
      this.directionValueAsString = directionValueAsString;
    }
  }

}
