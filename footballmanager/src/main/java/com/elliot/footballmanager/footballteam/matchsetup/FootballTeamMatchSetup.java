package com.elliot.footballmanager.footballteam.matchsetup;

import com.elliot.footballmanager.player.Player;

import java.io.Serializable;
import java.util.Arrays;

/**
 * This class is used to store a FootballTeam objects current
 * formation and information about the FootballTeam's setup.
 * @author elliot
 */
public class FootballTeamMatchSetup implements Serializable {

    public static final Integer MAXIMUM_STORED_FORMATIONS = 3;

    private MatchDaySquadInformation selectedFormation;
    private MatchDaySquadInformation[] availableFormations = new MatchDaySquadInformation[MAXIMUM_STORED_FORMATIONS];

    private Player freekickTaker;
    private Player penaltyTaker;
    private Player cornerTaker;

    public FootballTeamMatchSetup() {

    }

    public MatchDaySquadInformation getSelectedFormation() {
        return selectedFormation;
    }

    public void setSelectedFormation(MatchDaySquadInformation selectedFormation) {
        this.selectedFormation = selectedFormation;
    }

    public MatchDaySquadInformation[] getAvailableFormations() {
        return availableFormations;
    }

    public void setAvailableFormations(MatchDaySquadInformation[] availableFormations) {
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

    @Override
    public String toString() {
        return "FootballTeamMatchSetup{" +
                "selectedFormation=" + selectedFormation +
                ", availableFormations=" + Arrays.toString(availableFormations) +
                ", freekickTaker=" + freekickTaker +
                ", penaltyTaker=" + penaltyTaker +
                ", cornerTaker=" + cornerTaker +
                '}';
    }
}
