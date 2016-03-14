/**
 * CSCI 2120 Fall 2014
 * Risk Game Class Player
 * Authors: Hung Le, Jeanne Vu 
 * Date: 10-21-2014
 **/

import java.util.HashMap;

public class Player
{
   // Instance Variables
	private String playerName;
	private int numArmies;
	private HashMap<String,Territory> territoriesMap;
	private HashMap<String,Continent> continentsMap;

   // Constructor
	public Player( String playerName)
	{
		this.playerName = playerName;
		this.numArmies = 0;
		this.territoriesMap = new HashMap<String,Territory>();
		this.continentsMap = new HashMap<String,Continent>();
	}

   // Method Headers
   	/**
	 * @return the Player's name
	 **/
	public String getName()
	{
		return playerName;
	}

	/**
	 * @return integer representing the number of armies the player has that can be placed
	 **/
	public int getNumArmies()
	{
		return numArmies;
	}

	/**
	 * @return a HashMap of the territories the player controls
	 **/
	public HashMap<String,Territory> getTerritoriesMap()
	{
		return territoriesMap;
	}

	/**
	 * @return a HashMap of the continents the player controls
	 *  return an empty map in case the player own no continents 
	 **/
	public HashMap<String,Continent> getContinentsMap()
	{
		return continentsMap;
	}

// Command Methods:

	/**
	 * Used to update the number of armies the player has that need to be placed
	 * @param numArmies the integer to add to or subtract from the available armies pool
	 * @modify positive value : add / negative value : subtract
	 **/
	public void updateNumArmies( int numArmies )
	{

	}

	/**
	 * Update the map of owned territories as the player win or lose battles
	 * @param:  String: the desired operation ("add" or "remove")
	 *          String: name of the Territory object to add/remove
	 **/
	public void updateTerritoriesMap(String operation, String territoryName)
	{

	}

	/**
	 * Update the map of owned continents as the player win or lose battles
	 * @param:  String operation: the desired operation ("add" or "remove")
	 *          String continentName: name of the Continent object to add/remove
	 **/
	public void updateContinentsMap(String operation, String continentName)
	{

	}

// Turn Simulation Methods: simulate the steps taken in a turn of a player
// Might want to cut these off and implement a seperate Turn class in the future

	/** < First Step >
	 * Used to calculate the number of new armies received
	 * and deploy them into the player's territories using a loop
	 * @Interaction: promt players to enter decisions at runtime
	 * @modify call method updateNumArmies() to add to the number of available armies
	 * @operation call method deployArmies to deploy armies to a specified territory
	 **/
	public void getAndPlaceArmies()
	{

	}

	/**
	 * called by method getAndPlaceArmies()
	 * @param number of armies to deploy
	 * @param name(String) of the destination territory 
	 * @modify: call class Territory's method updateNumArmies to deploy armies
	 * @modify: call method updateNumArmies() to decrease the number of available armies
	 **/
	public void deployArmies(int numArmies, String destination)
	{
	
	}

	
	/** < Second Step >
	 * Contains a loop to initiate battles until the player opts to stop
	 * @Interaction: promt players to enter decisions at runtime
	 * @operation: call method roll(int attacking,int defending) of class Dice to simulate the rolls
	 * @operation: call method battle to resolve battles
	 * @operation: if the player captured a territory, draw one and only one card from the deck
	 **/
	public void attack()
	{

	}
	

	/**
	 * Called by method attack() to resolve a battle
	 * @param: String attacker: the name of the territory the player is attacking from
	 * @param: String defender: the name of the territory that is being attacked
	 * @param attacking an integer representing how many dice to use for the attack(1-3)
	 * @param defending an integer to represent how many dice to use for the defense(1-2)
	 * @param an array of integers holding the results of the rolls
	 * @modify: call method updateNumArmies() of class Territory to decrease the loser's numArmies
	 * @modify: if the defender lost its last army, call method setOccupant() of class Territory to update occupant 
 	 * @modify: call method moveArmies() move armies to the newly captured territory
	 **/
	public void battle(String attacker, String defender, int attacking, int defending, int[] results)
	{

	}


	/** < Third Step >
	 * Used to fortify territories
	 * @Interaction: promt players to enter decisions at runtime
	 * @operation: call method moveArmies() to move armies from one territory to another
	 **/
	public void fortify()
	{

	}
	
	/**
	 * Called by method fortify 
	 * Move armies from one territory to another 
	 * @param: String departure:   the name of the territory to move armies from
	 *	   String destination: the name of the territory to move armies to
	 * 	   int numArmies:      the number of armies to move 
	 * @modify: increase the numArmies of the destination and decrease that of the departure by a number numArmies
	 **/
	public void moveArmies(String departure,String destination, int numArmies)
	{

	}
}
