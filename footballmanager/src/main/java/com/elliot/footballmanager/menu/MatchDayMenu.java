package com.elliot.footballmanager.menu;

import com.elliot.footballmanager.DateUtils;
import com.elliot.footballmanager.entity.Fixture;
import com.elliot.footballmanager.entity.dao.FixtureDao;
import com.elliot.footballmanager.entity.dao.impl.FixtureDaoImpl;
import com.elliot.footballmanager.entity.GameManager;
import com.elliot.footballmanager.match.MatchResult;
import com.elliot.footballmanager.match.engine.MatchEngine;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * This class provides the list of options that are provided to the user prior to the start of a
 * Football Match. These options are usually presented when the currentDate in
 * <link>GameManager</link> is equal to an upcomingFixture.
 *
 * @author Elliot
 */
public class MatchDayMenu implements GameMenu {

  private static Scanner scanner = new Scanner(System.in);
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
          case 1:
            List<MatchResult> matchResults = simulateCurrentDatesFixtures();
            displayPostMatchMenu(matchResults);
            break;
          case 2:
            displaySquadOptions();
            break;
          case 3:
            GameMenu mainMenu = new MainMenu(gameManager);
            mainMenu.beginMenuSelectionLoop();
            break;
          default:
            System.out.println("Invalid selection! Please try again.");
            break;
        }
      } catch (InputMismatchException e) {
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
    System.out.println(
        gameManager.getCurrentFootballTeam().getMatchSetup().getSelectedFormation().toString());
  }

  public void displayMenuOptions() {
    System.out.println("[1] Start Match");
    System.out.println("[2] View / Edit team formation");
    System.out.println("[3] Back to main menu");
  }

  private List<MatchResult> simulateCurrentDatesFixtures() {
    List<MatchResult> matchResults = new ArrayList<MatchResult>();

    MatchEngine.setIsLoggingGameEvents(true);
    Fixture currentPlayersFixture = gameManager.getUpcomingFixtures().remove();

    MatchResult playersMatchResult = MatchEngine.beginFootballMatchSimulator(currentPlayersFixture);

    matchResults = simulateRemainingFixtures(currentPlayersFixture);
    matchResults.add(0, playersMatchResult);

    return matchResults;
  }

  private List<MatchResult> simulateRemainingFixtures(Fixture currentPlayersFixture) {
    FixtureDao fixtureDao = new FixtureDaoImpl();
    List<MatchResult> matchResults = new ArrayList<MatchResult>();
    MatchEngine.setIsLoggingGameEvents(false);

    for (Fixture fixture : fixtureDao.getFixturesForGivenDate(gameManager.getCurrentDate())) {
      if (!currentPlayersFixture.equals(fixture)) {
        MatchResult matchResult = MatchEngine.beginFootballMatchSimulator(fixture);
        matchResults.add(matchResult);
      }
    }
    return matchResults;
  }

  private void displayPostMatchMenu(List<MatchResult> matchResults) {
    PostMatchMenu postMatchMenu = new PostMatchMenu(gameManager, matchResults);
    postMatchMenu.beginMenuSelectionLoop();
  }

  private GameManager getGameManager() {
    return gameManager;
  }
}
