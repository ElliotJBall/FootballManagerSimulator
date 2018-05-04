package com.elliot.footballmanager.footballteam;

/**
 * This class is used to store a FootballTeam objects current
 * formation and information about the FootballTeam's setup.
 * @author elliot
 */
public class FootballTeamMatchSetup {

    private Integer MAXIMUM_STORED_FORMATIONS = 3;

    private Formation selectedFormation;
    private Formation[] availableFormations = new Formation[MAXIMUM_STORED_FORMATIONS];

    public FootballTeamMatchSetup() {

    }

    public Formation getSelectedFormation() {
        return selectedFormation;
    }

    public void setSelectedFormation(Formation selectedFormation) {
        this.selectedFormation = selectedFormation;
    }

    /**
     * List of the possible formations a FootballTeam can choose
     * to use.
     */
    private enum Formation {
        //TODO: Add more possible formations
        FOUR_FOUR_TWO, THREE_FIVE_ONE;
    }
}
