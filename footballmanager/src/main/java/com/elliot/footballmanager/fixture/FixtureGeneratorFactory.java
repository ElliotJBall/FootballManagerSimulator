package com.elliot.footballmanager.fixture;

/**
 * Factory pattern to enable the ability to generate Fixture's using various different algorithms
 * (E.g. Round Robin, Group draw..)
 *
 * @author Elliot
 */
public class FixtureGeneratorFactory {

  public FixtureGeneratorFactory() {

  }

  public FixtureGenerator getFixtureGenerator(FixtureGeneratorType fixtureGeneratorType) {
    if (fixtureGeneratorType == null) {
      return null;
    }

    if (FixtureGeneratorType.ROUND_ROBIN.equals(fixtureGeneratorType)) {
      return new RoundRobinFixtureGenerator();
    } else if (FixtureGeneratorType.GROUP_TOURNAMENT.equals(fixtureGeneratorType)) {
      return new GroupTouramentFixtureGenerator();
    } else {
      return null;
    }
  }

}
