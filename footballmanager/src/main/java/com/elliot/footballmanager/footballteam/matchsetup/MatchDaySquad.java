package com.elliot.footballmanager.footballteam.matchsetup;

import com.elliot.footballmanager.player.Player;

import java.io.Serializable;
import java.util.Arrays;

public class MatchDaySquad implements Serializable {

    public static final Integer MATCH_DAY_SQUAD = 11;
    public static final Integer MATCH_DAY_SUBSTITUTIONS = 7;

    private Player[] startingLineup;
    private Player[] substitutions;
    private FootballTeamFormation formation;

    public MatchDaySquad() {

    }

    public MatchDaySquad(Player[] startingLineup, Player[] substitutions, FootballTeamFormation formation) {
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

    @Override
    public String toString() {
        return "MatchDaySquad{" +
                "startingLineup=" + Arrays.toString(startingLineup) +
                ", substitutions=" + Arrays.toString(substitutions) +
                ", formation=" + formation +
                '}';
    }
}

