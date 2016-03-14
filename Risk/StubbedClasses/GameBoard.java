 /**
  * CSCI 2120 Fall 2014
  * Risk Game Class GameBoard
  * Authors: Hung Le, Jeanne Vu 
  * Date: 10-21-2014
  **/

import java.util.HashMap;

public class GameBoard
{
   // Instance Variables
	private HashMap<String,Continent> continentsMap;
	private HashMap<String,Territory> territoriesMap;

   // Constructor
	public GameBoard(HashMap<String,Continent> continentsMap, HashMap<String,Territory> territoriesMap)
	{
		this.continentsMap = continentsMap ;
		this.territoriesMap = territoriesMap;
	}

   // Method Headers
   	/**
	 * Returns a map of all the continents on the board
	 * @return HashMap of continentName-Continent reference mappings
	 **/
	public HashMap<String,Continent> getContinentsMap()
	{
		return continentsMap;
	}
	
	/**
	 * Returns a map of all the territories on the board
	 * @return HashMap of territoryName-Territory reference mappings
	 **/
	public HashMap<String,Territory> getTerritoriesMap()
	{
		return territoriesMap;
	}

	/**
	 * Returns a map of unoccupied territories on the board
	 * @return HashMap of territoryName-Territory reference mappings
	 **/
	public HashMap<String,Territory> getUnoccupiedTerritories()
	{
		HashMap<String,Territory> unoccupiedTerritories = new HashMap<String,Territory>();
		return unoccupiedTerritories;
	}
	
	/**
	 * Retrieves a reference to a territory by name
	 * @param the String containing the name of the territory to get
	 * @return a reference to the specified territory
	 **/
	public Territory getTerritoryByName( String territoryName )
	{
		return territoriesMap.get(territoryName);
	}
	
	/**
	 * Retrieves a reference to a continent by name
	 * @param the String containing the name of the continent to get
	 * @return a reference to the specified continent
	 **/
	public Continent getContinentByName( String continentName )
	{
		return continentsMap.get(continentName);
	}
}