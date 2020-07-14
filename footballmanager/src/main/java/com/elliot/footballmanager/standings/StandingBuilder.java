package com.elliot.footballmanager.standings;

import com.elliot.footballmanager.entity.dao.StandingDao;
import com.elliot.footballmanager.entity.dao.impl.StandingDaoImpl;
import com.elliot.footballmanager.entity.Standing;
import com.elliot.footballmanager.match.MatchResult;

import java.util.List;

/**
 * Given a MatchResult object, builds two Standing
 * objects for the two FootballTeams that played
 * @author Elliot
 */
public class StandingBuilder {

    private final Integer POINTS_FOR_WIN = 3;
    private final Integer POINTS_FOR_DRAW = 1;

    private MatchResult matchResult;

    private Standing homeTeamStanding;
    private Standing awayTeamStanding;

    private List<Standing> outdatedTable;

    private StandingBuilder() {

    }

    public StandingBuilder(MatchResult matchResult) {
        this.matchResult = matchResult;

        homeTeamStanding = new Standing();
        awayTeamStanding = new Standing();

        updateFootballTeamIds();
    }

    private void updateFootballTeamIds() {
        homeTeamStanding.setFootballTeamId(matchResult.getHomeTeamMatchStats().getFootballTeam().getFootballTeamId());
        awayTeamStanding.setFootballTeamId(matchResult.getAwayTeamMatchStats().getFootballTeam().getFootballTeamId());
    }

    public void buildStandingsFromMatchResult() {
        getStandingObjectsFromDatabase();

        addLeagueId();
        addFootballTeamIds();
        addGoalsForAndAgainst();
        addGoalDifferences();
        addWinLossDraw();
        addPoints();
        incrementGamesPlayed();
        calculateTablePositions();
    }

    private void getStandingObjectsFromDatabase() {
        StandingDao standingDao = new StandingDaoImpl();

        homeTeamStanding = standingDao.getStandingByFootballTeamId(homeTeamStanding.getFootballTeamId());
        awayTeamStanding = standingDao.getStandingByFootballTeamId(awayTeamStanding.getFootballTeamId());
    }

    public void updateStandingsInDatabase() {
        persistUpdatedTable();
    }

    private void addLeagueId() {
        Integer leagueId = matchResult.getFixture().getLeagueId();
        homeTeamStanding.setLeagueId(leagueId);
        awayTeamStanding.setLeagueId(leagueId);
    }

    private void addFootballTeamIds() {
        homeTeamStanding.setFootballTeamId(matchResult.getHomeTeamMatchStats().getFootballTeam().getFootballTeamId());
        awayTeamStanding.setFootballTeamId(matchResult.getAwayTeamMatchStats().getFootballTeam().getFootballTeamId());
    }

    private void addGoalsForAndAgainst() {
        homeTeamStanding.setGoalsFor(homeTeamStanding.getGoalsFor() + matchResult.getHomeTeamMatchStats().getGoals());
        awayTeamStanding.setGoalsFor(awayTeamStanding.getGoalsFor() + matchResult.getAwayTeamMatchStats().getGoals());

        homeTeamStanding.setGoalsAgainst(homeTeamStanding.getGoalsAgainst() + matchResult.getAwayTeamMatchStats().getGoals());
        awayTeamStanding.setGoalsAgainst(awayTeamStanding.getGoalsAgainst() + matchResult.getHomeTeamMatchStats().getGoals());
    }

    private void addGoalDifferences() {
        homeTeamStanding.setGoalDifference(homeTeamStanding.getGoalsFor() - homeTeamStanding.getGoalsAgainst());
        awayTeamStanding.setGoalDifference(awayTeamStanding.getGoalsFor() - awayTeamStanding.getGoalsAgainst());
    }

    private void addWinLossDraw() {
        switch (matchResult.getResult()) {
            case H:
                homeTeamStanding.setWins(homeTeamStanding.getWins() + 1);
                awayTeamStanding.setLosses(awayTeamStanding.getLosses() + 1);
                break;
            case A:
                homeTeamStanding.setLosses(homeTeamStanding.getLosses() + 1);
                awayTeamStanding.setWins(awayTeamStanding.getWins() + 1);
                break;
            case D:
                homeTeamStanding.setDraws(homeTeamStanding.getDraws() + 1);
                awayTeamStanding.setDraws(awayTeamStanding.getDraws() + 1);
                break;
            default:
                throw new IllegalArgumentException("Invalid Match Result!");
        }
    }

    private void addPoints() {
        switch (matchResult.getResult()) {
            case H:
                homeTeamStanding.setPoints(homeTeamStanding.getPoints() + POINTS_FOR_WIN);
                break;
            case A:
                awayTeamStanding.setPoints(awayTeamStanding.getPoints() + POINTS_FOR_WIN);
                break;
            case D:
                homeTeamStanding.setPoints(homeTeamStanding.getPoints() + POINTS_FOR_DRAW);
                awayTeamStanding.setPoints(awayTeamStanding.getPoints() + POINTS_FOR_DRAW);
                break;
            default:
                throw new IllegalArgumentException("Invalid Match Result!");
        }
    }

    private void incrementGamesPlayed() {
        homeTeamStanding.setGamesPlayed(homeTeamStanding.getGamesPlayed() + 1);
        awayTeamStanding.setGamesPlayed(awayTeamStanding.getGamesPlayed() + 1);
    }

    private void calculateTablePositions() {
        Integer leagueId = homeTeamStanding.getLeagueId();
        StandingDao standingDao = new StandingDaoImpl();
        outdatedTable = standingDao.getOrderedTableByLeagueId(leagueId);

        updateTableStanding(homeTeamStanding);
        updateTableStanding(awayTeamStanding);

        StandingComparator.orderTableByPoints(outdatedTable);
    }

    private void updateTableStanding(Standing standingToUpdate) {
        for (int i = 0; i <= outdatedTable.size(); i++) {
            if (outdatedTable.get(i).getFootballTeamId().equals(standingToUpdate.getFootballTeamId())) {
                outdatedTable.set(i, standingToUpdate);
                return;
            }
        }
    }

    private void persistUpdatedTable() {
        StandingDao standingDao = new StandingDaoImpl();
        for (Standing standing : outdatedTable) {
            standingDao.updateStandingRecord(standing);
        }
    }
}
