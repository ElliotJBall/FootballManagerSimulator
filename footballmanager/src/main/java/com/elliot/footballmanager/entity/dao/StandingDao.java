package com.elliot.footballmanager.entity.dao;

import com.elliot.footballmanager.entity.FootballTeam;

import com.elliot.footballmanager.entity.Standing;
import java.util.List;

/**
 * @author Elliot
 */
public interface StandingDao {

    public void createNewStandingForFootballTeam(FootballTeam footballTeam);

    /**
     * Updates the Standing object stored in the database
     * @param standing Standing object to be updated
     */
    public void updateStandingRecord(Standing standing);

    /**
     *
     * @param footballTeamId The FootballTeam you want to retrieve the Standing object for
     * @return The Standing object
     */
    public Standing getStandingByFootballTeamId(Integer footballTeamId);

    /**
     *
     * @param leagueId The league we want all FootballTeam standings for
     * @return List of standings for given league
     */
    //TODO: Replace List<Standing> with LeagueTable object
    public List<Standing> getOrderedTableByLeagueId(Integer leagueId);
}
