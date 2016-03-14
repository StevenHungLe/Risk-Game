/**
 * Name: 	Hung Le
 * Professor: 	Mr. Johnathan Redmann
 * Course: 	CSCI2120
 * Project:	Risk-The World Domination Game
 **
 * Interface:	GameBoardInterface specifies the board used in the game
 * Roles:	the field on where the game takes place
 * Composition: contained in Game object
 *		contain Continent objects	
 **/

import java.util.HashMap;

public interface GameBoardInterface
{
	/**
	 * Returns a map of all the continents on the board
	 * @return HashMap of continentName-Continent reference mappings
	 **/
	HashMap<String,Continent> getContinentsMap();

	/**
	 * Returns a map of all the territories on the board
	 * @return HashMap of territoryName-Territory reference mappings
	 **/
	HashMap<String,Territory> getTerritoriesMap();

	/**
	 * Returns a map of unoccupied territories on the board
	 * @return HashMap of territoryName-Territory reference mappings
	 **/
	HashMap<String,Territory> getUnoccupiedTerritories();

	/**
	 * Retrieves a reference to a territory by name
	 * @param the String containing the name of the territory to get
	 * @return a reference to the specified territory
	 **/
	Territory getTerritoryByName( String territoryName );
	
	/**
	 * Retrieves a reference to a continent by name
	 * @param the String containing the name of the continent to get
	 * @return a reference to the specified continent
	 **/
	Continent getContinentByName( String continentName );

}// end GameBoard interface
