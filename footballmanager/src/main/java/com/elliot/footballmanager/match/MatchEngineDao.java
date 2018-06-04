package com.elliot.footballmanager.match;

/**
 * Outlays the interactivity with the database
 * @author Elliot
 */
public interface MatchEngineDao {

    public void persistResultToDatabase(MatchResult matchResult);

}
