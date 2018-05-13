package com.elliot.footballmanager.footballteam.matchsetup;

import com.elliot.footballmanager.footballteam.FootballTeam;
import com.elliot.footballmanager.player.Player;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Provides a way of generating a FootballTeamMatchSetup object
 * for a given footballTeam.
 * @author elliot
 */
public class FootballTeamMatchSetupBuilder {

    private static String FORMATION_SPLIT_CHARACTER = "-";

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

        MatchDaySquadInformation[] matchDaySquads = generateFormations(footballTeamMatchSetup, footballTeam);

        footballTeamMatchSetup.setSelectedFormation(matchDaySquads[0]);
        footballTeamMatchSetup.setAvailableFormations(matchDaySquads);

        footballTeamMatchSetup.setFreekickTaker(getBestFreekickTaker(footballTeam.getSquad()));
        footballTeamMatchSetup.setPenaltyTaker(getBestPenaltyTaker(footballTeam.getSquad()));
        footballTeamMatchSetup.setCornerTaker(getBestCornerTaker(footballTeam.getSquad()));

        return footballTeamMatchSetup;
    }

    /**
     * For a given FootballTeam this method will generate a list of Formations (The size is limited by
     * MAXIMUM_STORED_FORMATIONS within the FootballTeamMatchSetup class). One will be randomly chosen
     * and then set as the selected formation.
     * @param footballTeamMatchSetup
     * @param footballTeam
     * @return
     */
    private static MatchDaySquadInformation[] generateFormations(FootballTeamMatchSetup footballTeamMatchSetup,
                                                               FootballTeam footballTeam) {
        Set<FootballTeamFormation> uniqueFormations = new HashSet<FootballTeamFormation>();
        MatchDaySquadInformation[] matchDaySquads = new MatchDaySquadInformation[FootballTeamMatchSetup.MAXIMUM_STORED_FORMATIONS];

        for (int i = 0; i < FootballTeamMatchSetup.MAXIMUM_STORED_FORMATIONS; i++) {
            MatchDaySquadInformation matchDaySquadInformation = new MatchDaySquadInformation();
            // Need to get a random FootballTeamFormation and then work backwards
            // Adding the right amount of players to each 'Position (E.g. 4-4-2 4 Defenders, Midfielders, 2 Attackers)
            FootballTeamFormation formation = getUniqueFormation(uniqueFormations);

            matchDaySquadInformation.setFormation(formation);
            // Parse the FootballTeamFormation String value and add the required number of players from that position
            matchDaySquadInformation.setStartingLineup(buildStartingLineup(footballTeam, formation));

            matchDaySquads[i] = matchDaySquadInformation;
        }
        return matchDaySquads;
    }

    private static FootballTeamFormation getUniqueFormation(Set<FootballTeamFormation> uniqueFormations) {

        FootballTeamFormation footballTeamFormation;
        footballTeamFormation = FootballTeamFormation.getRandomFormation();

        if (uniqueFormations.contains(footballTeamFormation)) {
            return getUniqueFormation(uniqueFormations);
        } else {
            uniqueFormations.add(footballTeamFormation);
            return footballTeamFormation;
        }
    }

    private static Player[] buildStartingLineup(FootballTeam footballTeam, FootballTeamFormation footballTeamFormation) {
        //TODO: Find an alternative that will work with more complex formations (E.g. 4-1-2-1-2)
        //TODO: Change Squad variable inside FootballTeam into its own separate class (Can add HashMap for Defender - SubList of Squad | Attacker - SubList)
        Player[] startingLineup = new Player[MatchDaySquadInformation.MATCH_DAY_SQUAD];
        Player[] substitutes = new Player[MatchDaySquadInformation.MATCH_DAY_SUBSTITUTIONS];
        String[] formationParts = footballTeamFormation.getFormationName().split(FORMATION_SPLIT_CHARACTER);

        addBestGoalKeeper(startingLineup, footballTeam.getSquad());
        addBestDefenders(startingLineup, footballTeam.getSquad(), Integer.valueOf(formationParts[0]));
        addBestMidfielders(startingLineup, footballTeam.getSquad(), Integer.valueOf(formationParts[1]));
        addBestStrikers(startingLineup, footballTeam.getSquad(), Integer.valueOf(formationParts[2]));

        return startingLineup;
    }

    private static void addBestGoalKeeper(Player[] startingLineup, List<Player> footballTeamSquad) {

    }

    private static void addBestDefenders(Player[] startingLineup, List<Player> footballTeamSquad, Integer numberOfDefenders) {

    }

    private static void addBestMidfielders(Player[] startingLineup, List<Player> footballTeamSquad, Integer numberOfMidfielders) {

    }

    private static void addBestStrikers(Player[] startingLineup, List<Player> footballTeamSquad, Integer numberOfStrikers) {

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
