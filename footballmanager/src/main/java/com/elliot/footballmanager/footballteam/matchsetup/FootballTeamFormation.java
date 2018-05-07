package com.elliot.footballmanager.footballteam.matchsetup;

//TODO: Change to a class and have the enum as a inner class
//TODO: Add the ability to set players in a certain position
public enum FootballTeamFormation {
    //TODO: Add more possible formations
    //TODO: How am I going to limit them to 4 defenders if they play 4-4-2?
    // Parse the selected formation and then build a starting squad + subs
    // E.g. Squad[0] = Choose Starting GK 'Pickford' 'Rooney' ...
    FOUR_FOUR_TWO,
    FOUR_THREE_THREE,
    THREE_FIVE_ONE;
}

