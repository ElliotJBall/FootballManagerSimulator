package com.elliot.footballmanager.match;

import com.elliot.footballmanager.fixture.Fixture;
import com.elliot.footballmanager.footballteam.FootballTeam;

/**
 * The MatchResult object is created after a FootballMatch has
 * been simulated within the MatchEngine.
 * @author Elliot
 */
public class MatchResult {

    private final Fixture fixture;

    private final FootballTeamMatchStats homeTeamMatchStats;
    private final FootballTeamMatchStats awayTeamMatchStats;

    public MatchResult(Fixture fixture, FootballTeamMatchStats homeTeamMatchStats,
                       FootballTeamMatchStats awayTeamMatchStats) {
        this.fixture = fixture;
        this.homeTeamMatchStats = homeTeamMatchStats;
        this.awayTeamMatchStats = awayTeamMatchStats;
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
}
