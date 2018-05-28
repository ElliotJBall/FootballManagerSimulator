package com.elliot.footballmanager.season;

import com.elliot.footballmanager.fixture.*;
import com.elliot.footballmanager.footballteam.FootballTeam;
import com.elliot.footballmanager.footballteam.FootballTeamDao;
import com.elliot.footballmanager.footballteam.FootballTeamDaoImpl;
import com.elliot.footballmanager.footballteam.matchsetup.FootballTeamMatchSetupBuilder;
import com.elliot.footballmanager.footballteam.matchsetup.FootballTeamMatchSetupDao;
import com.elliot.footballmanager.footballteam.matchsetup.FootballTeamMatchSetupDaoImpl;
import com.elliot.footballmanager.gamemanager.GameManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to simplify the creation of a new Football
 * season.
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
    }

    private static void setupFixtures() {
        System.out.println("Generating Fixtures...");

        FixtureGeneratorFactory fixtureGeneratorFactory = new FixtureGeneratorFactory();
        FixtureGenerator fixtureGenerator = fixtureGeneratorFactory.getFixtureGenerator(FixtureGeneratorType.ROUND_ROBIN);
        List<String> fixtureCreateStatements = fixtureGenerator.generateFixtureInsertStatements();
        fixtureGenerator.insertFixturesIntoDatabase(fixtureCreateStatements);

        FixtureDao fixtureDao = new FixtureDaoImpl();
        gameManager.setUpcomingFixtures(fixtureDao.getFootballTeamsUpcomingFixtures(gameManager.getCurrentFootballTeam()));
    }

    private static void setupTeamsMatchInfo() {
        System.out.println("Generating FootballTeams Match day data...");

        Integer leagueId = gameManager.getCurrentLeague().getLeagueId();
        FootballTeamDao footballTeamDao = new FootballTeamDaoImpl();
        gameManager.getCurrentLeague().setFootballTeams(new ArrayList<FootballTeam>(footballTeamDao.getAllFootballTeams(leagueId)));

        FootballTeamMatchSetupDao footballTeamMatchSetupDao = new FootballTeamMatchSetupDaoImpl();

        for (FootballTeam footballTeam : gameManager.getCurrentLeague().getFootballTeams()) {
            footballTeam.setMatchSetup(FootballTeamMatchSetupBuilder.buildNewMatchSetup(footballTeam));
            footballTeamMatchSetupDao.persistFootballTeamMatchSetup(footballTeam);
        }
    }
}
