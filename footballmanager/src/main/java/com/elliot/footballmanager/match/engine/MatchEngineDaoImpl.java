package com.elliot.footballmanager.match.engine;

import com.elliot.footballmanager.match.MatchResult;

/**
 * @author Elliot
 */
public class MatchEngineDaoImpl implements MatchEngineDao {

    @Override
    public void persistResultToDatabase(MatchResult matchResult) {
        String query = "INSERT INTO MATCH_RESULT (HOME_TEAM_NAME, AWAY_TEAM_NAME, HOME_TEAM_GOALS, AWAY_TEAM_GOALS) VALUES (?, ?, ?, ?)";
    }
}
