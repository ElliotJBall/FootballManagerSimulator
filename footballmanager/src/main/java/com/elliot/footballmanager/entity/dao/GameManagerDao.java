package com.elliot.footballmanager.entity.dao;

import com.elliot.footballmanager.entity.GameManager;

/**
 * This interface makes use of the DAO pattern and outlays the operations that 
 * can be performed in the <link>GameManagerDaoImpl</link>.
 * @author Elliot
 */
public interface GameManagerDao {

	public void saveGame(GameManager gameManager);
	
	public void loadSavedGame(GameManager gameManager);
}
