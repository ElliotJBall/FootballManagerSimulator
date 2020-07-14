package com.elliot.footballmanager.menu;

import java.util.*;

import com.elliot.footballmanager.entity.Country;
import com.elliot.footballmanager.entity.dao.CountryDao;
import com.elliot.footballmanager.entity.dao.impl.CountryDaoImpl;
import com.elliot.footballmanager.database.SqliteDatabaseConnector;
import com.elliot.footballmanager.entity.FootballTeam;
import com.elliot.footballmanager.entity.dao.FootballTeamDao;
import com.elliot.footballmanager.entity.dao.impl.FootballTeamDaoImpl;
import com.elliot.footballmanager.entity.GameManager;
import com.elliot.footballmanager.entity.League;
import com.elliot.footballmanager.entity.dao.LeagueDao;
import com.elliot.footballmanager.entity.dao.impl.LeagueDaoImpl;
import com.elliot.footballmanager.entity.Manager;
import com.elliot.footballmanager.entity.dao.ManagerDao;
import com.elliot.footballmanager.entity.dao.impl.ManagerDaoImpl;
import com.elliot.footballmanager.season.NewSeasonBuilder;

/**
 * The MainMenu class is used to create a new Menu object that displays all the options that area
 * available to the user.
 *
 * @author Elliot
 */
public class StartMenu implements GameMenu {

  private GameManager gameManager;
  private static Scanner scanner = new Scanner(System.in);
  private boolean quit = false;
  private Date newGameStartDate = new GregorianCalendar(2018, Calendar.JULY, 1)
      .getTime(); // (01/07/2018)

  public StartMenu() {
    beginMenuSelectionLoop();
  }

  public void beginMenuSelectionLoop() {
    displayMenuOptions();

    boolean quit = false;
    do {
      try {
        switch (scanner.nextInt()) {
          case 0: // [0] Exit Game
            System.out.println("Thanks for playing!");
            quit = true;
            break;
          case 1: // [1] Start New Game
            gameManager = new GameManager();
            clearPreviousSaveData();
            createNewGame();
            quit = true;
            break;
          case 2: // [2] Continue Saved Game
            gameManager = new GameManager();
            gameManager.loadSavedGame();
            quit = true;
            break;
          default:
            System.out.println("Invalid option, please try again.");
        }
      } catch (InputMismatchException e) {
        System.out.println("Invalid selection! Please try again.");
        scanner.next();
      }

    } while (!quit);
    scanner.close();
  }

  public void displayMenuOptions() {
    System.out.println("------------------------------------------------------------");
    System.out.println("Elliots Java Football Manager!");
    System.out.println("------------------------------------------------------------");
    System.out.println("Please choose one of the following options:");
    System.out.println("[0] Exit Game! (Please note you can hit 0 at any point to exit the game!");
    System.out.println("[1] Start New Game!");
    System.out.println("[2] Continue Saved Game!");
  }

  /**
   * Remove any artifacts from the database that reference the save game that is being deleted.
   */
  private void clearPreviousSaveData() {
    SqliteDatabaseConnector.deleteSavedGameArtifacts();
  }

  /**
   * Calls the required methods in order to successfully instantiate a new FootballManager game. The
   * new details are persisted into the database via the <link>GameManager</link> class.
   */
  private void createNewGame() {
    chooseCountry();
    chooseLeague();
    chooseTeam();
    createNewManager();

    NewSeasonBuilder.setupNewSeason(gameManager);

    gameManager.setCurrentDate(newGameStartDate);
    gameManager.saveGame();
  }

  /**
   * Gets a Map of all available Countries from the database and the <link>Country</link> selected
   * by the user is added to the <link>GameManager</link> object
   */
  private void chooseCountry() {
    System.out.println("Please select the country that you would like to play in:");

    CountryDao countryDao = new CountryDaoImpl();
    Map<Integer, Country> allCountries = countryDao.getAllCountries();

    for (Country country : allCountries.values()) {
      System.out.println(country.printCountryMenuInfo());
    }

    quit = false;
    do {
      int choice = scanner.nextInt();

      if (allCountries.get(choice) != null) {
        Country country = allCountries.get(choice);
        gameManager.setCurrentCountry(country);

        System.out.println("You have selected : " + country.printCountryMenuInfo());
        quit = true;
      } else if (choice == 0) {
        quit = true;
      } else {
        System.out.println("Invalid option, please try again.");
      }

    } while (!quit);
  }

  /**
   * Gets a Map of all available Leagues for the selected </link>Country</link>. The selected
   * </link>League</link> is added to the <link>GameManager</link> object.
   */
  private void chooseLeague() {
    System.out.println("Please select the league that you would like to play in:");

    LeagueDao leagueDao = new LeagueDaoImpl();
    Map<Integer, League> leagues = leagueDao
        .getAllLeaguesById(gameManager.getCurrentCountry().getCountryId());

    for (League league : leagues.values()) {
      System.out.println(league.printLeagueMenuInfo());
    }

    quit = false;
    do {
      int choice = scanner.nextInt();

      if (leagues.get(choice) != null) {
        League league = leagues.get(choice);
        gameManager.setCurrentLeague(league);

        System.out.println("You have selected : " + league.printLeagueMenuInfo());
        quit = true;
      } else if (choice == 0) {
        quit = true;
      } else {
        System.out.println("Invalid option, please try again.");
      }
    } while (!quit);
  }


  /**
   * Gets a Map of all available FootballTeams for the selected <link>Country</link>. The selected
   * <link>FootballTeam</link> is added to the <link>GameManager</link> object.
   */
  private void chooseTeam() {
    System.out.println("Please select the FootballTeam that you would like to play as:");

    FootballTeamDao footballTeamDao = new FootballTeamDaoImpl();
    Map<Integer, FootballTeam> footballTeams = MenuHelper.buildFootballTeamMapDisplay(gameManager);

    for (FootballTeam footballTeam : footballTeams.values()) {
      System.out.println(footballTeam.printFootballTeamMenuInfo());
    }

    quit = false;
    do {
      int choice = scanner.nextInt();

      if (footballTeams.get(choice) != null) {
        FootballTeam footballTeam = footballTeams.get(choice);
        gameManager.setCurrentFootballTeam(footballTeam);

        System.out.println("You have selected : " + footballTeam.printFootballTeamMenuInfo());
        quit = true;
      } else if (choice == 0) {
        quit = true;
      } else {
        System.out.println("Invalid option, please try again.");
      }
    } while (!quit);
  }

  /**
   * Given information from the user a new <link>Manager</link> object is created. This is persisted
   * into the database and added to the <link>GameManager</link> object.
   */
  private void createNewManager() {
    System.out.println("Please enter the managers first name:");

    String firstName, lastName;
    quit = false;

    do {
      firstName = scanner.next();
      System.out.println("Is " + firstName + " correct? (Y/N)");

      if (scanner.next().toUpperCase().equals("Y")) {
        quit = true;
      } else {
        System.out.println("ERROR. Please enter the managers first name:");
      }
    } while (!quit);

    System.out.println("Please enter the managers last name:");
    quit = false;

    do {
      lastName = scanner.next();
      System.out.println("Is " + lastName + " correct? (Y/N)");

      if (scanner.next().toUpperCase().equals("Y")) {
        quit = true;
      } else {
        System.out.println("ERROR: Please enter the managers last name:");
      }
    } while (!quit);

    // Only allow one manager at the moment - This will need expanding when multiple save games are enabled
    Manager manager = new Manager(1, firstName, lastName, gameManager.getCurrentFootballTeam());

    ManagerDao managerDao = new ManagerDaoImpl();
    managerDao.insertIntoManagerTable(manager);

    gameManager.setManager(manager);
  }
}
