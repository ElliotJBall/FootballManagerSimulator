package com.elliot.footballmanager.fixture;

import java.util.List;


/**
 * Top level interface providing methods outlining the 
 * common functionality between various FixtureGenerator's.
 * @author Elliot
 *
 */
public interface FixtureGenerator {

	public List<String> generateFixtureInsertStatements();
	
	public void insertFixturesIntoDatabase(List<String> fixtures);
	
}
