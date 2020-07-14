package com.elliot.footballmanager.season;

import com.elliot.footballmanager.entity.FootballTeam;
import com.elliot.footballmanager.entity.GameManager;
import com.elliot.footballmanager.entity.dao.FixtureDao;
import com.elliot.footballmanager.entity.dao.FootballTeamDao;
import com.elliot.footballmanager.entity.dao.FootballTeamMatchSetupDao;
import com.elliot.footballmanager.entity.dao.StandingDao;
import com.elliot.footballmanager.entity.dao.impl.FixtureDaoImpl;
import com.elliot.footballmanager.entity.dao.impl.FootballTeamDaoImpl;
import com.elliot.footballmanager.entity.dao.impl.FootballTeamMatchSetupDaoImpl;

import com.elliot.footballmanager.entity.dao.impl.StandingDaoImpl;
import com.elliot.footballmanager.fixture.FixtureGenerator;
import com.elliot.footballmanager.fixture.FixtureGeneratorFactory;
import com.elliot.footballmanager.fixture.FixtureGeneratorType;
import com.elliot.footballmanager.footballteam.matchsetup.FootballTeamMatchSetupBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to simplify the creation of a new Football season.
 *
 * @author Elliot
 */
public class NewSeasonBuilder {

  private static GameManager gameManager;

  private NewSeasonBuilder() {

  }

  public static void setupNewSeason(GameManager incomingGameManager) {
    gameManager = incomingGameManager;

    setupFixtures();
    setupTeamsMatchInfo();
    setupStandings();
  }

  private static void setupFixtures() {
    System.out.println("Generating Fixtures...");

    FixtureGeneratorFactory fixtureGeneratorFactory = new FixtureGeneratorFactory();
    FixtureGenerator fixtureGenerator = fixtureGeneratorFactory
        .getFixtureGenerator(FixtureGeneratorType.ROUND_ROBIN);
    List<String> fixtureCreateStatements = fixtureGenerator.generateFixtureInsertStatements();
    fixtureGenerator.insertFixturesIntoDatabase(fixtureCreateStatements);

    FixtureDao fixtureDao = new FixtureDaoImpl();
    gameManager.setUpcomingFixtures(
        fixtureDao.getFootballTeamsUpcomingFixtures(gameManager.getCurrentFootballTeam()));
  }

  private static void setupTeamsMatchInfo() {
    System.out.println("Generating FootballTeams Match day data...");

    Integer leagueId = gameManager.getCurrentLeague().getLeagueId();
    FootballTeamDao footballTeamDao = new FootballTeamDaoImpl();
    gameManager.getCurrentLeague().setFootballTeams(
        new ArrayList<FootballTeam>(footballTeamDao.getAllFootballTeams(leagueId)));

    FootballTeamMatchSetupDao footballTeamMatchSetupDao = new FootballTeamMatchSetupDaoImpl();

    for (FootballTeam footballTeam : gameManager.getCurrentLeague().getFootballTeams()) {
      footballTeam.setMatchSetup(FootballTeamMatchSetupBuilder.buildNewMatchSetup(footballTeam));
      footballTeamMatchSetupDao.persistFootballTeamMatchSetup(footballTeam);
    }
  }

  private static void setupStandings() {
    System.out.println("Generating Standings...");

    Integer leagueId = gameManager.getCurrentLeague().getLeagueId();
    FootballTeamDao footballTeamDao = new FootballTeamDaoImpl();
    gameManager.getCurrentLeague().setFootballTeams(
        new ArrayList<FootballTeam>(footballTeamDao.getAllFootballTeams(leagueId)));

    StandingDao standingDao = new StandingDaoImpl();
    for (FootballTeam footballTeam : gameManager.getCurrentLeague().getFootballTeams()) {
      standingDao.createNewStandingForFootballTeam(footballTeam);
    }
  }
}
