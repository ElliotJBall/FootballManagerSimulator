package com.elliot.footballmanager.gamemanager;

/**
 * @author Elliot
 * This interface makes use of the DAO pattern and outlays the operations that 
 * can be performed in the <link>GameManagerDaoImpl</link>
 */
public interface GameManagerDao {

	public void createNewSaveGame(GameManager gameManager);
	
	public void loadSavedGame();
	
}
