package com.elliot.footballmanager.match.model;

import com.elliot.footballmanager.match.engine.RandomNumberGenerator;
import com.elliot.footballmanager.match.model.pitch.FootballPitchBuilderConstants;
import com.elliot.footballmanager.player.Player;

/**
 * Provides a way of moving a Player from one tile to another within
 * the MatchEngine FootballPitch.
 * @author Elliot
 */
public class Movement {

    public Movement() {

    }

    public void movePlayerToNewTile(Player player) {
        Direction directionToMovePlayerIn = getRandomDirection();

        while (willMovePlayerOutsideFootballPitch(player, directionToMovePlayerIn) != false) {
            directionToMovePlayerIn = getRandomDirection();
        }

        player.addDirectionBackToPrefferedCoordinates(getOppositeDirectionForGivenDirection(directionToMovePlayerIn));
        updatePlayerWithNewCoordinates(player, directionToMovePlayerIn);
    }

    private Direction getRandomDirection() {
        return Direction.values()[RandomNumberGenerator.getRandomNumberBetweenZeroAnGivenNumber(Direction.values().length)];
    }

    private boolean willMovePlayerOutsideFootballPitch(Player player, Direction direction) {
        return (player.getCurrentXCoordinate() + direction.getXValue()) <= 0
                || (player.getCurrentXCoordinate() + direction.getXValue()) >= (FootballPitchBuilderConstants.LENGTH_OF_FOOTBALL_PITCH - 1)
                || (player.getCurrentYCoordinate() + direction.getYValue()) <= 0
                || (player.getCurrentYCoordinate() + direction.getYValue()) >= (FootballPitchBuilderConstants.WIDTH_OF_FOOTBALL_PITCH - 1);
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
                player.setCurrentYCoordinate(player.getCurrentYCoordinate() + directionToMovePlayerIn.getYValue());
                break;
            case DOWN:
                player.setCurrentYCoordinate(player.getCurrentYCoordinate() + directionToMovePlayerIn.getYValue());
                break;
            case LEFT:
                player.setCurrentXCoordinate(player.getCurrentXCoordinate() + directionToMovePlayerIn.getXValue());
                break;
            case RIGHT:
                player.setCurrentXCoordinate(player.getCurrentXCoordinate() + directionToMovePlayerIn.getXValue());
                break;
            case DIAGONAL_LEFT_UP:
                player.setCurrentXCoordinate(player.getCurrentXCoordinate() + directionToMovePlayerIn.getXValue());
                player.setCurrentYCoordinate(player.getCurrentYCoordinate() + directionToMovePlayerIn.getYValue());
                break;
            case DIAGONAL_LEFT_DOWN:
                player.setCurrentXCoordinate(player.getCurrentXCoordinate() + directionToMovePlayerIn.getXValue());
                player.setCurrentYCoordinate(player.getCurrentYCoordinate() + directionToMovePlayerIn.getYValue());
                break;
            case DIAGONAL_RIGHT_UP:
                player.setCurrentXCoordinate(player.getCurrentXCoordinate() + directionToMovePlayerIn.getXValue());
                player.setCurrentYCoordinate(player.getCurrentYCoordinate() + directionToMovePlayerIn.getYValue());
                break;
            case DIAGONAL_RIGHT_DOWN:
                player.setCurrentXCoordinate(player.getCurrentXCoordinate() + directionToMovePlayerIn.getXValue());
                player.setCurrentYCoordinate(player.getCurrentYCoordinate() + directionToMovePlayerIn.getYValue());
                break;
            case NO_DIRECTION:
                break;
            default:
                throw new IllegalArgumentException("Cannot move player when the direction has not been specified");
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
                throw new IllegalArgumentException("Cannot move player when the direction has not been specified");
        }
    }

    /**
     * The possible directions that a player can move in
     * on the FootballPitch.
     * @author Elliot
     */
    public enum Direction {
        UP(0, -1),
        DOWN(0, 1),
        LEFT(-1, 0),
        RIGHT(1, 0),
        DIAGONAL_LEFT_UP(-1, -1),
        DIAGONAL_LEFT_DOWN(-1, 1),
        DIAGONAL_RIGHT_UP(1, -1),
        DIAGONAL_RIGHT_DOWN(1, 1),
        NO_DIRECTION(0, 0);

        private Integer xValue;
        private Integer yValue;

        Direction(Integer xValue, Integer yValue) {
            this.setXValue(xValue);
            this.setYValue(yValue);
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
    }

}
