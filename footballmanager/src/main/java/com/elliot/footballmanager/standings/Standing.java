package com.elliot.footballmanager.standings;

/**
 * Represents a FootballTeams current standing
 * in a league table.
 * @author Elliot
 */
public class Standing {

    private Integer standingId;
    private Integer leagueId;
    private Integer footballTeamId;
    private Integer wins;
    private Integer losses;
    private Integer draws;
    private Integer goalsFor;
    private Integer goalsAgainst;
    private Integer goalDifference;
    private Integer points;
    private Integer tablePosition;
    private Integer gamesPlayed;

    public Standing() {

    }

    public Standing(Integer standingId, Integer leagueId, Integer footballTeamId, Integer wins,
                    Integer losses, Integer draws, Integer goalsFor, Integer goalsAgainst,
                    Integer goalDifference, Integer points, Integer tablePosition, Integer gamesPlayed) {
        this.standingId = standingId;
        this.leagueId = leagueId;
        this.footballTeamId = footballTeamId;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
        this.goalDifference = goalDifference;
        this.points = points;
        this.tablePosition = tablePosition;
        this.gamesPlayed = gamesPlayed;
    }

    public Integer getStandingId() {
        return standingId;
    }

    public void setStandingId(Integer standingId) {
        this.standingId = standingId;
    }

    public Integer getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Integer leagueId) {
        this.leagueId = leagueId;
    }

    public Integer getFootballTeamId() {
        return footballTeamId;
    }

    public void setFootballTeamId(Integer footballTeamId) {
        this.footballTeamId = footballTeamId;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getLosses() {
        return losses;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    public Integer getDraws() {
        return draws;
    }

    public void setDraws(Integer draws) {
        this.draws = draws;
    }

    public Integer getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(Integer goalsFor) {
        this.goalsFor = goalsFor;
    }

    public Integer getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(Integer goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public Integer getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference(Integer goalDifference) {
        this.goalDifference = goalDifference;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getTablePosition() {
        return tablePosition;
    }

    public void setTablePosition(Integer tablePosition) {
        this.tablePosition = tablePosition;
    }

    public Integer getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(Integer gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }
}
