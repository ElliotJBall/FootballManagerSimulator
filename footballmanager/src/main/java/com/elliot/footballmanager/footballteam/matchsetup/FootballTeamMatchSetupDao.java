package com.elliot.footballmanager.footballteam.matchsetup;

import com.elliot.footballmanager.footballteam.FootballTeam;

/**
 * Outlines the database interactivity options available.
 * @author Elliot
 */
public interface FootballTeamMatchSetupDao {

    public FootballTeamMatchSetup getFootballTeamMatchSetup(Integer footballTeamId);

    public void persistFootballTeamMatchSetup(FootballTeam footballTeam);
}
