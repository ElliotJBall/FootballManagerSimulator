package com.elliot.footballmanager.footballteam.matchsetup;

import com.elliot.footballmanager.player.Player;

import java.io.Serializable;

//TODO: Change to a class and have the enum as a inner class
//TODO: Add the ability to set players in a certain position
public class MatchDaySquadInformation implements Serializable {

    public static final Integer MATCH_DAY_SQUAD = 11;
    public static final Integer MATCH_DAY_SUBSTITUTIONS = 7;

    private Player[] startingLineup;
    private Player[] substitutions;
    private FootballTeamFormation formation;

    public MatchDaySquadInformation() {

    }

    public MatchDaySquadInformation(Player[] startingLineup, Player[] substitutions, FootballTeamFormation formation) {
        this.startingLineup = startingLineup;
        this.substitutions = substitutions;
        this.formation = formation;
    }

    public Player[] getStartingLineup() {
        return startingLineup;
    }

    public void setStartingLineup(Player[] startingLineup) {
        this.startingLineup = startingLineup;
    }

    public Player[] getSubstitutions() {
        return substitutions;
    }

    public void setSubstitutions(Player[] substitutions) {
        this.substitutions = substitutions;
    }

    public FootballTeamFormation getFormation() {
        return formation;
    }

    public void setFormation(FootballTeamFormation formation) {
        this.formation = formation;
    }
}

