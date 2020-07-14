package com.elliot.footballmanager.entity;

import java.util.Date;
import java.util.Objects;

/**
 * The Fixture class provides information about an upcoming Football match. Information about the
 * two FootballTeams, competition and date are stored.
 *
 * @author Elliot
 */
public class Fixture {

  private Integer fixtureId;
  private FootballTeam homeTeam;
  private FootballTeam awayTeam;
  private Date dateOfFixture;
  private Integer leagueId;

  public Fixture() {

  }

  public Fixture(Integer fixtureId, FootballTeam homeTeam, FootballTeam awayTeam,
      Date dateOfFixture, Integer leagueId) {
    this.fixtureId = fixtureId;
    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
    this.dateOfFixture = dateOfFixture;
    this.leagueId = leagueId;
  }

  public Integer getFixtureId() {
    return fixtureId;
  }

  public void setFixtureId(Integer fixtureId) {
    this.fixtureId = fixtureId;
  }

  public FootballTeam getHomeTeam() {
    return homeTeam;
  }

  public void setHomeTeam(FootballTeam homeTeam) {
    this.homeTeam = homeTeam;
  }

  public FootballTeam getAwayTeam() {
    return awayTeam;
  }

  public void setAwayTeam(FootballTeam awayTeam) {
    this.awayTeam = awayTeam;
  }

  public Date getDateOfFixture() {
    return dateOfFixture;
  }

  public void setDateOfFixture(Date dateOfFixture) {
    this.dateOfFixture = dateOfFixture;
  }

  public Integer getLeagueId() {
    return leagueId;
  }

  public void setLeagueId(Integer leagueId) {
    this.leagueId = leagueId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Fixture fixture = (Fixture) o;
    return Objects.equals(fixtureId, fixture.fixtureId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fixtureId, homeTeam, awayTeam, dateOfFixture, leagueId);
  }

  @Override
  public String toString() {
    return "Fixture: [Home Team: " + homeTeam + ", Away Team:" + awayTeam + ", Fixture Date="
        + dateOfFixture + "]";
  }
}
