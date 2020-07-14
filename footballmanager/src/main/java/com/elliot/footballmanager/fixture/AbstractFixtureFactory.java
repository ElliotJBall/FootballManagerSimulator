package com.elliot.footballmanager.fixture;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.elliot.footballmanager.entity.FootballTeam;
import com.elliot.footballmanager.entity.dao.FootballTeamDao;
import com.elliot.footballmanager.entity.dao.impl.FootballTeamDaoImpl;
import com.elliot.footballmanager.entity.League;
import com.elliot.footballmanager.entity.dao.LeagueDao;
import com.elliot.footballmanager.entity.dao.impl.LeagueDaoImpl;

/**
 * Provides all the common information required by all FixtureGenerator's
 *
 * @author Elliot
 */
public abstract class AbstractFixtureFactory {

  private List<League> leaguesForGeneration = new ArrayList<League>();
  private List<String> allFixtures = new ArrayList<String>();

  public AbstractFixtureFactory() {

  }

  protected void prepareLeaguesForFixtureGeneration() {
    LeagueDao leagueDao = new LeagueDaoImpl();
    this.setLeaguesForGeneration(leagueDao.getAllLeagues());

    FootballTeamDao footballTeamDao = new FootballTeamDaoImpl();
    for (League league : this.getLeaguesForGeneration()) {
      league.setFootballTeams(
          new ArrayList<FootballTeam>(footballTeamDao.getAllFootballTeams(league.getLeagueId())));
    }
  }

  protected String createFixtureInsertStatement(FootballTeam homeTeam, FootballTeam awayTeam,
      Date dateOfFixture) {
    return "INSERT INTO FIXTURE (HOME_TEAM, AWAY_TEAM, DATE_OF_MATCH, LEAGUE_ID) "
        + "VALUES ('" + homeTeam.getTeamName() + "', '" + awayTeam.getTeamName() + "'"
        + ", '" + dateOfFixture + "', " + homeTeam.getLeagueId() + ")";
  }

  public List<League> getLeaguesForGeneration() {
    return leaguesForGeneration;
  }

  public void setLeaguesForGeneration(List<League> leaguesForGeneration) {
    this.leaguesForGeneration = leaguesForGeneration;
  }

  public List<String> getAllFixtures() {
    return allFixtures;
  }

  public void setAllFixtures(List<String> allFixtures) {
    this.allFixtures = allFixtures;
  }
}
