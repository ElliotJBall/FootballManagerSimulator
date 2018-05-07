package com.elliot.footballmanager.footballteam.matchsetup;

import com.elliot.footballmanager.player.Player;

/**
 * This class is used to store a FootballTeam objects current
 * formation and information about the FootballTeam's setup.
 * @author elliot
 */
public class FootballTeamMatchSetup {

    private static final Integer MAXIMUM_STORED_FORMATIONS = 3;

    private FootballTeamFormation selectedFormation;
    private FootballTeamFormation[] availableFormations = new FootballTeamFormation[MAXIMUM_STORED_FORMATIONS];

    private Player freekickTaker;
    private Player penaltyTaker;
    private Player cornerTaker;

    public FootballTeamMatchSetup() {

    }

    public FootballTeamFormation getSelectedFormation() {
        return selectedFormation;
    }

    public void setSelectedFormation(FootballTeamFormation selectedFormation) {
        this.selectedFormation = selectedFormation;
    }

    public FootballTeamFormation[] getAvailableFormations() {
        return availableFormations;
    }

    public void setAvailableFormations(FootballTeamFormation[] availableFormations) {
        this.availableFormations = availableFormations;
    }

    public Player getFreekickTaker() {
        return freekickTaker;
    }

    public void setFreekickTaker(Player freekickTaker) {
        this.freekickTaker = freekickTaker;
    }

    public Player getPenaltyTaker() {
        return penaltyTaker;
    }

    public void setPenaltyTaker(Player penaltyTaker) {
        this.penaltyTaker = penaltyTaker;
    }

    public Player getCornerTaker() {
        return cornerTaker;
    }

    public void setCornerTaker(Player cornerTaker) {
        this.cornerTaker = cornerTaker;
    }
}
