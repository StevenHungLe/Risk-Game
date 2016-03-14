/**
 * Name: 	Hung Le
 * Professor: 	Mr. Johnathan Redmann
 * Course: 	CSCI2120
 * Project:	Risk-The World Domination Game
 **
 * Interface:	ContinentInterface specifies continents on the board
 * Roles:	the larger entities to be conquered in the game
 * Composition: contained in GameBoard object
 *		contain Territory objects	
 **/

import java.util.HashMap;

public interface ContinentInterface
{

// Query Methods:

	/**
	 * @return the name of the continent as a String
	 **/
	String getName();

	/**
	 * If no player owns all territories on the continent then this method should 
	 * return null
	 * @return a reference to the Player object that currently owns this continent
	 **/
	Player getOccupant();
	
	/**
	 * @return the number of additional armies a player gets for owning this continent
	 **/
	int getNumBonusArmies();
	
	/**
	 * @return true of there is a player that owns all the territories on this continent
	 **/
	boolean isOccupied();
	
	/**
	 * @return a HashMap of all the territories on the continent
	 **/
	HashMap<String,Territory> getTerritories();


// Command Methods:

	/**
	 * Sets which player owns a continent, if any
	 * @param occupant a reference to the Player object that now owns this continent
	 **/
	void setOccupant( Player occupant );


}
// end Continent interface
