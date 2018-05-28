package com.elliot.footballmanager.footballteam.matchsetup;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum FootballTeamFormation {
    FOUR_FOUR_TWO("4-4-2"),
    FOUR_THREE_THREE("4-3-3"),
    THREE_FIVE_TWO("3-5-2");

    private String formationName;

    FootballTeamFormation(String formationName) {
        this.formationName = formationName;
    }

    private static final List<FootballTeamFormation> ALL_FORMATIONS = Collections.unmodifiableList(Arrays.asList(FootballTeamFormation.values()));
    private static final Integer NUMBER_OF_FORMATIONS_IN_ENUM = ALL_FORMATIONS.size();
    private static final Random random = new Random();

    public static FootballTeamFormation getRandomFormation() {
        return ALL_FORMATIONS.get(random.nextInt(NUMBER_OF_FORMATIONS_IN_ENUM));
    }

    public String getFormationName() {
        return formationName;
    }
}
