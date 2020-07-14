package com.elliot.footballmanager.menu;

import com.elliot.footballmanager.entity.GameManager;

import java.util.Scanner;

/**
 * Context menu providing options to the user to modify their
 * FootballTeam formation to be used in a Match.
 * @author Elliot
 */
public class FormationMenu {

    private static Scanner scanner  = new Scanner(System.in);
    private GameManager gameManager;

    public FormationMenu() {

    }

    public FormationMenu(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }
}
