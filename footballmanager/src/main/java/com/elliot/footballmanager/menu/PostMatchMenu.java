package com.elliot.footballmanager.menu;

import com.elliot.footballmanager.entity.GameManager;
import com.elliot.footballmanager.match.MatchResult;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Post Simulation menu. Post match analysis / Going back to the main menu.
 *
 * @author Elliot
 */
public class PostMatchMenu implements GameMenu {

  private static Scanner scanner = new Scanner(System.in);
  private GameManager gameManager;
  private List<MatchResult> matchResults;

  private PostMatchMenu() {

  }

  public PostMatchMenu(GameManager gameManager, List<MatchResult> matchResults) {
    this.gameManager = gameManager;
    this.matchResults = matchResults;
  }

  @Override
  public void beginMenuSelectionLoop() {
    displayMenuOptions();

    boolean quit = false;
    do {
      try {
        switch (scanner.nextInt()) {
          case 0:
            this.getGameManager().saveGameAndExit();
            quit = true;
            break;
          case 1:
            displayPostMatchStats();
            displayMenuOptions();
            break;
          case 2:
            break;
          case 3:
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

  //TODO: List additional information for player simulated match (passes etc..)
  private void displayPostMatchStats() {
    for (MatchResult matchResult : getMatchResults()) {
      matchResult.displayPostMatchInfo();
    }
  }

  public List<MatchResult> getMatchResults() {
    return matchResults;
  }

  public GameManager getGameManager() {
    return gameManager;
  }

  @Override
  public void displayMenuOptions() {
    System.out.println("[1] View Post Match stats");
    System.out.println("[2] View league table");
    System.out.println("[3] Back to main menu");
  }
}
