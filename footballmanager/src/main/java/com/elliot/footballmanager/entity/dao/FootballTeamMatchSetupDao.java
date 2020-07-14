package com.elliot.footballmanager.entity.dao;

import com.elliot.footballmanager.entity.FootballTeam;
import com.elliot.footballmanager.footballteam.matchsetup.FootballTeamMatchSetup;

/**
 * Outlines the database interactivity options available.
 *
 * @author Elliot
 */
public interface FootballTeamMatchSetupDao {

  public FootballTeamMatchSetup getFootballTeamMatchSetup(Integer footballTeamId);

  public void persistFootballTeamMatchSetup(FootballTeam footballTeam);
}
