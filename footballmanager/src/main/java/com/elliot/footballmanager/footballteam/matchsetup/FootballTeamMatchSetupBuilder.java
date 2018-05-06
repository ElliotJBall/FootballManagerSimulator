package com.elliot.footballmanager.footballteam.matchsetup;

import com.elliot.footballmanager.footballteam.FootballTeam;

/**
 * Provides a way of generating a FootballTeamMatchSetup object
 * for a given footballTeam.
 * @author elliot
 */
public class FootballTeamMatchSetupBuilder {

    private FootballTeam footballTeam;

    public FootballTeamMatchSetupBuilder() {

    }

    public static FootballTeamMatchSetup buildNewMatchSetup() {
        FootballTeamMatchSetup footballTeamMatchSetup = new FootballTeamMatchSetup();


        }

    private static FootballTeamFormation[] generateFormations() {
        //TODO: Generate primary and secondary formations
        return null;
    }

    public FootballTeam getFootballTeam() {
        return footballTeam;
    }

    public void setFootballTeam(FootballTeam footballTeam) {
        this.footballTeam = footballTeam;
    }
}
