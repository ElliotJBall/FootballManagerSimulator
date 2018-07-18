package com.elliot.footballmanager.player;

import com.elliot.footballmanager.match.engine.MatchEngine;
import com.elliot.footballmanager.match.model.Football;

/**
 * Provides a way of moving a Player from one tile to another within
 * the MatchEngine FootballPitch.
 * @author Elliot
 */
public class Movement {

    private Movement() {

    }

    public static void movePlayerInDirectionSpecified(Player player, Direction directionToMovePlayerIn) {
        removePlayerFromOldTile(player);

         switch (directionToMovePlayerIn) {
             case UP:
                 player.setyCoordinate(player.getyCoordinate() - 1);
                 break;
             case DOWN:
                 player.setyCoordinate(player.getyCoordinate() + 1);
                 break;
             case LEFT:
                 player.setxCoordinate(player.getxCoordinate() - 1);
                 break;
             case RIGHT:
                 player.setxCoordinate(player.getxCoordinate() + 1);
                 break;
             case DIAGONAL_LEFT_UP:
                 player.setxCoordinate(player.getxCoordinate() - 1);
                 player.setyCoordinate(player.getyCoordinate() - 1);
                 break;
             case DIAGONAL_LEFT_DOWN:
                 player.setxCoordinate(player.getxCoordinate() - 1);
                 player.setyCoordinate(player.getyCoordinate() + 1);
                 break;
             case DIAGONAL_RIGHT_UP:
                 player.setxCoordinate(player.getxCoordinate() + 1);
                 player.setyCoordinate(player.getyCoordinate() - 1);
                 break;
             case DIAGONAL_RIGHT_DOWN:
                 player.setxCoordinate(player.getxCoordinate() + 1);
                 player.setyCoordinate(player.getyCoordinate() + 1);
                 break;
             case NO_DIRECTION:
                 break;
             default:
                 throw new IllegalArgumentException("Cannot move player when the direction has not been specified");
         }

         addPlayerToNewTile(player);
    }

    public static Direction determineDirectionToMoveTowardsTheFootball(Player player, Football football) {
        int playerX = player.getxCoordinate().intValue();
        int playerY = player.getyCoordinate().intValue();

        int footballX = football.getCurrentXCoordinate().intValue();
        int footballY = football.getCurrentYCoordinate().intValue();

        if (playerX == footballX
                && playerY > footballY) {
            return Direction.UP;
        } else if (playerX == footballX
                && playerY < footballY) {
            return Direction.DOWN;
        }  else if (playerY == footballY
                && playerX > footballX) {
            return Direction.LEFT;
        } else if (playerY == footballY
                && playerX < footballX) {
            return Direction.RIGHT;
        } else if (playerX > footballX
                && playerY > footballY) {
            return Direction.DIAGONAL_LEFT_UP;
        } else if (playerX < footballX
                && playerY < footballY) {
            return Direction.DIAGONAL_LEFT_DOWN;
        } else if (playerX < footballX
                && playerY > footballY) {
            return Direction.DIAGONAL_RIGHT_UP;
        } else if (playerX < footballX
                && playerY < footballY) {
            return Direction.DIAGONAL_RIGHT_DOWN;
        } else {
            return Direction.NO_DIRECTION;
        }

    }

    private static void removePlayerFromOldTile(Player player) {
        MatchEngine.footballPitch[player.getxCoordinate()][player.getyCoordinate()].removePlayerFromTile(player);
    }

    private static void addPlayerToNewTile(Player player) {
        MatchEngine.footballPitch[player.getxCoordinate()][player.getyCoordinate()].addPlayerToTile(player);
    }

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        DIAGONAL_LEFT_UP,
        DIAGONAL_LEFT_DOWN,
        DIAGONAL_RIGHT_UP,
        DIAGONAL_RIGHT_DOWN,
        NO_DIRECTION;
    }

}
