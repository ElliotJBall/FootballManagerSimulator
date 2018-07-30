package com.elliot.footballmanager.match;

import com.elliot.footballmanager.fixture.Fixture;

/**
 * The MatchResult object is created after a FootballMatch has
 * been simulated within the MatchEngine.
 * @author Elliot
 */
public class MatchResult {

    private final Fixture fixture;

    private final FootballTeamMatchStats homeTeamMatchStats;
    private final FootballTeamMatchStats awayTeamMatchStats;

    private final Result result;

    public MatchResult(Fixture fixture, FootballTeamMatchStats homeTeamMatchStats,
                       FootballTeamMatchStats awayTeamMatchStats) {
        this.fixture = fixture;
        this.homeTeamMatchStats = homeTeamMatchStats;
        this.awayTeamMatchStats = awayTeamMatchStats;

        result = determineResultOfMatch();
    }

    public Result determineResultOfMatch() {
        return Result.determineResultOfMatch(this);
    }

    public Fixture getFixture() {
        return fixture;
    }

    public FootballTeamMatchStats getHomeTeamMatchStats() {
        return homeTeamMatchStats;
    }

    public FootballTeamMatchStats getAwayTeamMatchStats() {
        return awayTeamMatchStats;
    }

    public Result getResult() {
        return result;
    }

    public String displayPostMatchResult() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Full Time Result :");
        stringBuilder.append(homeTeamMatchStats.getFootballTeam().getTeamName());
        stringBuilder.append(" ");
        stringBuilder.append("[" + homeTeamMatchStats.getGoals() + "]");
        stringBuilder.append(" ");
        stringBuilder.append(awayTeamMatchStats.getFootballTeam().getTeamName());
        stringBuilder.append(" ");
        stringBuilder.append("[" + awayTeamMatchStats.getGoals() + "]");
        return stringBuilder.toString();
    }
}
