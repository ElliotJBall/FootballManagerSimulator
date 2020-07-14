package com.elliot.footballmanager;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple FootballManagerApplication.
 */
public class FootballManagerApplicationTest
    extends TestCase {

  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public FootballManagerApplicationTest(String testName) {
    super(testName);
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite() {
    return new TestSuite(FootballManagerApplicationTest.class);
  }

  /**
   * Rigourous Test :-)
   */
  public void testApp() {
    assertTrue(true);
  }
}
