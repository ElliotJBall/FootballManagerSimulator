package com.elliot.footballmanager.match.engine;

import com.elliot.footballmanager.match.model.pitch.FootballPitchBuilderConstants;

import java.util.Random;

/**
 * Random number generator for use within the MatchEngine.
 * @author Elliot
 */
public class RandomNumberGenerator {

    private static Random random = new Random();

    public RandomNumberGenerator() {

    }

    public static Integer getRandomNumberBetweenZeroAndOneHundred() {
        return random.nextInt(100);
    }

    public static Double getRandomNumberBetweenZeroAndOne() {
        return random.nextDouble();
    }

    public static Integer getRandomNumberBetweenZeroAndWidthOfFootballPitch() {
        return random.nextInt(FootballPitchBuilderConstants.WIDTH_OF_FOOTBALL_PITCH + 1);
    }

    public static Integer getRandomNumberBetweenZeroAnGivenNumber(int highestNumber) {
        return random.nextInt(highestNumber);
    }
}
