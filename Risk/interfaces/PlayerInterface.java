/**
 * Name: 	Hung Le
 * Professor: 	Mr. Johnathan Redmann
 * Course: 	CSCI2120
 * Project:	Risk-The World Domination Game
 **
 * Interface:	PlayerInterface specifies a player who is playing an active game
 * Roles:	participate in the activities of the game
 * Composition: contained in Game object	
 **/

import java.util.HashMap;

public interface PlayerInterface
{

// Query Methods:

	/**
	 * @return the Player's name
	 **/
	String getName();

	/**
	 * @return integer representing the number of armies the player has that can be placed
	 **/
	int getUnplacedArmies();

	/**
	 * @return a HashMap of the territories the player controls
	 **/
	HashMap<String,Territory> getTerritoriesList();

	/**
	 * @return a HashMap of the continents the player controls
	 *  return an empty map in case the player own no continents 
	 **/
	HashMap<String,Continent> getContinentsList();


// Command Methods:

	/**
	 * Used to set the number of armies the player has that need to be placed
	 * @param newArmies integer representing the number of armies the player have
	 *  at the beginning of the turn
	 **/
	void addArmies( int newArmies );

	/**
	 * Update the map of owned territories as the player win or lose battles
	 * @param:  String: the desired operation ("add" or "remove")
	 *          String: name of the Territory object to add/remove
	 **/
	void updateTerritoriesList(String operation, String territoryName);	

	/**
	 * Update the map of owned continents as the player win or lose battles
	 * @param:  String operation: the desired operation ("add" or "remove")
	 *          String continentName: name of the Continent object to add/remove
	 **/
	void updateContinentsList(String operation, String continentName);


// Turn Operation Methods: might want to cut these off and implement a seperate Turn class

	/** < Fisrt Step >
	 * Used to calculate the number of new armies received
	 * and deploy them into the player's territories
	 * @concept: Use class Territory's method setNumArmies to deploy armies 
	 **/
	void deployArmies();

	/** < Second Step >
	 * Contains a loop to initiate attacks until the player opts to stop
	 **/
	void attack();

	/**
	 * Called by method attack() to initiate an attack
	 * @param: String attacker: the name of the territory the player is attacking from
	 * 	   String defender: the name of the territory that is being attacked
	 **/
	void initiateAttack(String attacker, String defender);

	/** < Third Step >
	 * Used to fortify territories
	 * Move armies from one territory to another 
	 * @param: String departure:   the name of the territory to move armies from
	 *	   String destination: the name of the territory to move armies to
	 * 	   int numArmies:      the number of armies to move 
	 **/
	void moveArmies(String departure,String destination, int numArmies);

}
// end Player interface
