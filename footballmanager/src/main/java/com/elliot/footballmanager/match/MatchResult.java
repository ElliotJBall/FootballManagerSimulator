package com.elliot.footballmanager.match;

import com.elliot.footballmanager.footballteam.FootballTeam;

/**
 * The MatchResult object is created after a FootballMatch has
 * been simulated within the MatchEngine.
 * @author Elliot
 */
public class MatchResult {

    //TODO: Expand the amount of information that is captured in a MatchResult
    private final FootballTeam homeTeam;
    private final FootballTeam awayTeam;

    private final Integer homeTeamGoals;
    private final Integer awayTeamGoals;

    public MatchResult(FootballTeam homeTeam, FootballTeam awayTeam,
                       Integer homeTeamGoals, Integer awayTeamGoals) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
    }

    public FootballTeam getHomeTeam() {
        return homeTeam;
    }

    public FootballTeam getAwayTeam() {
        return awayTeam;
    }

    public Integer getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public Integer getAwayTeamGoals() {
        return awayTeamGoals;
    }
}
