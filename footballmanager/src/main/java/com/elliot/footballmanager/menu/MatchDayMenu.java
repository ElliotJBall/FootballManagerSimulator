package com.elliot.footballmanager.menu;

import com.elliot.footballmanager.DateUtils;
import com.elliot.footballmanager.fixture.Fixture;
import com.elliot.footballmanager.fixture.FixtureDao;
import com.elliot.footballmanager.fixture.FixtureDaoImpl;
import com.elliot.footballmanager.gamemanager.GameManager;
import com.elliot.footballmanager.match.MatchResult;
import com.elliot.footballmanager.match.engine.MatchEngine;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
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
            try {
                switch (scanner.nextInt()) {
                    case 6:
                        simulateCurrentDatesFixtures();
                        //TODO: Continue with simulation, show post game stats then continue into the main menu / post game conference
                        break;
                    case 7:
                        displaySquadOptions();
                        break;
                    case 8:
                        GameMenu mainMenu = new MainMenu(gameManager);
                        mainMenu.beginMenuSelectionLoop();
                        break;
                    default:
                        System.out.println("Invalid selection! Please try again.");
                        break;
                }
            } catch (InputMismatchException e ) {
                System.out.println("Invalid selection! Please try again.");
                scanner.next();
            }
        } while (!quit);
        scanner.close();
    }

    private void displayMatchDayMenuScreen() {
        Fixture fixture = this.getGameManager().getUpcomingFixtures().peek();
        System.out.println("M A T C H D A Y");
        System.out.println(DateUtils.FIXTURE_DATE_DISPLAY_FORMAT.format(fixture.getDateOfFixture())
            + " " + "|" + " "
            + fixture.getHomeTeam().getTeamName()
            + " " + "VS" + " "
            + fixture.getAwayTeam().getTeamName());
        System.out.println(fixture.getHomeTeam().getStadium());
    }

    private void displaySquadOptions() {
        System.out.println(gameManager.getCurrentFootballTeam().getMatchSetup().getSelectedFormation().toString());
    }

    public void displayMenuOptions() {
        System.out.println("[6] Start Match");
        System.out.println("[7] View / Edit team formation");
        System.out.println("[8] Back to main menu");
    }

    private void simulateCurrentDatesFixtures() {
        MatchEngine.setIsLoggingGameEvents(true);
        Fixture currentPlayersFixture = gameManager.getUpcomingFixtures().remove();

        MatchResult matchResult = MatchEngine.beginFootballMatchSimulator(currentPlayersFixture);
        matchResult.displayPostMatchInfo();

        simulateRemainingFixtures(currentPlayersFixture);
    }

    private void simulateRemainingFixtures(Fixture currentPlayersFixture) {
        FixtureDao fixtureDao = new FixtureDaoImpl();
        List<MatchResult> matchResults = new ArrayList<MatchResult>();
        MatchEngine.setIsLoggingGameEvents(false);

        for (Fixture fixture : fixtureDao.getFixturesForGivenDate(gameManager.getCurrentDate())) {
            if (!currentPlayersFixture.equals(fixture)) {
                MatchResult matchResult = MatchEngine.beginFootballMatchSimulator(fixture);
                matchResult.displayPostMatchInfo();
                matchResults.add(matchResult);
            }
        }
    }

    private GameManager getGameManager() {
        return gameManager;
    }
}
