package com.elliot.footballmanager.footballteam.matchsetup;

import com.elliot.footballmanager.footballteam.FootballTeam;
import com.elliot.footballmanager.player.Player;

import java.util.List;

/**
 * Provides a way of generating a FootballTeamMatchSetup object
 * for a given footballTeam.
 * @author elliot
 */
public class FootballTeamMatchSetupBuilder {

    public FootballTeamMatchSetupBuilder() {

    }

    /**
     * Main method that goes through the FootballTeamMatchSetup creating all
     * the required variables.
     * @param footballTeam The FootballTeam to generate the FootballTeamMatchSetup
     *                     object for.
     * @return A newly populated FootballTeamMatchObject.
     */
    public static FootballTeamMatchSetup buildNewMatchSetup(FootballTeam footballTeam) {
        FootballTeamMatchSetup footballTeamMatchSetup = new FootballTeamMatchSetup();
        footballTeamMatchSetup.setSelectedFormation(generateFormations(footballTeamMatchSetup,
                footballTeam));

        footballTeamMatchSetup.setFreekickTaker(getBestFreekickTaker(footballTeam.getSquad()));
        footballTeamMatchSetup.setPenaltyTaker(getBestPenaltyTaker(footballTeam.getSquad()));
        footballTeamMatchSetup.setCornerTaker(getBestCornerTaker(footballTeam.getSquad()));

        return footballTeamMatchSetup;
    }

    private static FootballTeamFormation generateFormations(FootballTeamMatchSetup footballTeamMatchSetup,
                                                              FootballTeam footballTeam) {
        //TODO: Generate primary and secondary formations
       return FootballTeamFormation.FOUR_FOUR_TWO;
    }

    private static Player getBestFreekickTaker(List<Player> squad) {
        Player bestFreekickAbility = squad != null ? squad.get(0) : new Player();
        for (Player player : squad) {
            if (player.getTechnicalAttributes().getFreeKick() > bestFreekickAbility.getTechnicalAttributes().getFreeKick()) {
                bestFreekickAbility = player;
            }
        }
        return bestFreekickAbility;
    }

    private static Player getBestPenaltyTaker(List<Player> squad) {
        Player bestPenaltyAbility = squad != null ? squad.get(0) : new Player();
        for (Player player : squad) {
            if (player.getTechnicalAttributes().getPenalties() > bestPenaltyAbility.getTechnicalAttributes().getPenalties()) {
                bestPenaltyAbility = player;
            }
        }
        return bestPenaltyAbility;
    }

    private static Player getBestCornerTaker(List<Player> squad) {
        Player bestCornerTaker = squad != null ? squad.get(0) : new Player();
        for (Player player : squad) {
            if (player.getTechnicalAttributes().getCrossing() > bestCornerTaker.getTechnicalAttributes().getCrossing()) {
                bestCornerTaker = player;
            }
        }
        return bestCornerTaker;
    }
}
