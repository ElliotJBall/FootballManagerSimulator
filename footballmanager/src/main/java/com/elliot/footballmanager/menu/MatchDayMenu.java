package com.elliot.footballmanager.menu;

import com.elliot.footballmanager.fixture.Fixture;
import com.elliot.footballmanager.gamemanager.GameManager;

import java.util.Scanner;

/**
 * This class provides the list of options that are provided to the user
 * prior to the start of a Football Match. These options are usually presented
 * when the currentDate in <link>GameManager</link> is equal to an upcomingFixture.
 * @author Elliot
 */
public class MatchDayMenu implements GameMenu {

    private static Scanner scanner  = new Scanner(System.in);
    private GameManager gameManager;

    public MatchDayMenu() {

    }

    public MatchDayMenu(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void beginMenuSelectionLoop() {
        displayMatchDayMenuScreen();
        displayMenuOptions();

        boolean quit = false;
        do {
            switch (scanner.nextInt()) {
                case 6:

                    break;
                case 7:
                    //TODO: Add the ability to change the formation / team setup
                    break;
                case 8:
                    GameMenu mainMenu = new MainMenu(gameManager);
                    mainMenu.beginMenuSelectionLoop();
                    break;
            }
        } while (!quit);
    }

    private void displayMatchDayMenuScreen() {
        Fixture fixture = this.getGameManager().getUpcomingFixtures().get(0);
        System.out.println("M A T C H D A Y");
        System.out.println(fixture.getHomeTeam().getTeamName()
            + " " + "VS" + " "
            + fixture.getAwayTeam().getTeamName());
    }

    public void displayMenuOptions() {
        System.out.println("[6] Start Match");
        System.out.println("[7] View / Edit team formation");
        System.out.println("[8] Back to main menu");
    }

    private GameManager getGameManager() {
        return gameManager;
    }
}
