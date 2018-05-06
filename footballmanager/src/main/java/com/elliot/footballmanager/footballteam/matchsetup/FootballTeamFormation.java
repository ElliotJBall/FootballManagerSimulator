package com.elliot.footballmanager.footballteam.matchsetup;


public class FootballTeamFormation {

    private enum Formation {
        //TODO: Add more possible formations
        //TODO: How am I going to limit them to 4 defenders if they play 4-4-2?
        // Parse the selected formation and then build a starting squad + subs
        // E.g. Squad[0] = Choose Starting GK 'Pickford' 'Rooney' ...
        FOUR_FOUR_TWO, THREE_FIVE_ONE;
    }
}
