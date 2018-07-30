package com.elliot.footballmanager.match;

/**
 * The outcome of a Football Match.
 * H - Home team won
 * A - Away team won
 * D - Draw
 * @author Elliot
 */
public enum Result {
    H("H"),
    A("A"),
    D("D");

    private String resultString;

    Result(String resultString) {
        this.resultString = resultString;
    }

    public String getResultString() {
        return resultString;
    }

    public void setResultString(String resultString) {
        this.resultString = resultString;
    }

    public static Result determineResultOfMatch(MatchResult matchResult) {
        int homeTeamGoals = matchResult.getHomeTeamMatchStats().getGoals();
        int awayTeamGoals = matchResult.getAwayTeamMatchStats().getGoals();

        if (homeTeamGoals > awayTeamGoals) {
            return H;
        } else if (awayTeamGoals > homeTeamGoals) {
            return A;
        } else {
            return D;
        }
    }
}
