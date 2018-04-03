package com.elliot.footballmanager.manager;

/**
 * @author Elliot
 * 
 */
public interface ManagerDao {

	/**
	 * @param manager The manager object that is going to be persisted 
	 * into the database.
	 */
	public void insertIntoManagerTable(Manager manager);
	
	public Manager getManagerById(Integer managerId);
}
