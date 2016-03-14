/**
 * Name: 	Hung Le
 * Professor: 	Mr. Johnathan Redmann
 * Course: 	CSCI2120
 * Project:	Risk-The World Domination Game
 **
 * Interface:	TerritoryInterface specifies territories on the board
 * Roles:	the base entities to be conquered in the game
 * Composition: contained in Continent object	
 **/

import java.util.HashMap;

public interface TerritoryInterface
{

// Query Methods

	/**
	 * @return the name of the territory
	 **/
	String getName();

	/**
	 * @return a reference to the Continent that contains this territory
	 * serves the purpose of updating a Continent's isOccupied status
	 * without having to scan through Continents'list of Territories
	 **/
	Continent getContinent();
	
	/**
	 * @return a HashMap of all territories it shares a border with
	 **/
	HashMap<String,Territory> getNeighbors();

	/**
	 * @return a reference to the player that currently owns this territory
	 **/
	Player getOccupant();

	/**
	 * @return the number of armies the occupying player has in the territory
	 **/
	int getNumArmies();


// Command Methods:

	/**
	 * Used to set the new occupying player of a territory
	 * @param occupant reference to the Player object who now occupies the territory
	 **/
	void setOccupant( Player occupant );
	
	/**
	 * Used by the occupying player to add armies to a territory
	 * @param numArmies the number of armies to be placed into the territory
	 **/
	void setNumArmies( int numArmies );  

}
// end Territory interface
