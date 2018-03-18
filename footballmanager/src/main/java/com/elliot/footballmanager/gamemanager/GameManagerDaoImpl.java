package com.elliot.footballmanager.gamemanager;

/**
 * @author Elliot
 * This class makes use of the DAO pattern to perform CRUD operations on the 
 * FootballManager database.
 */
public class GameManagerDaoImpl implements GameManagerDao {

	@Override
	public void createNewSaveGame(GameManager gameManager) {
		String query = "INSERT INTO GAME_MANAGER VALUES (?, ?, ?, ?)";
	}

	@Override
	public void loadSavedGame() {

	}
	
}
