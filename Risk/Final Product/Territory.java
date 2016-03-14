 /**
  * CSCI 2120 Fall 2014
  * Risk Game Class Territory
  * Authors: Hung Le, Jeanne Vu 
  * Date: 11-04-2014
  **
  * Class:	Territory specifies territories on the board
  * Roles:	the base entities to be conquered in the game
  * Composition: contained in Continent objects and GameBoard object
  **/

import java.util.HashMap;
import java.util.ArrayList;

public class Territory
{
   // Instance Variables
	private String name;
	private String continentName;
	private ArrayList<String> neighbors;
	private Player occupant;
	private int numArmies;

   // Constructor
	public Territory( String name, String continentName, ArrayList<String> neighbors )
	{
		this.name = name;
		this.continentName = continentName;
		this.neighbors = neighbors;
		this.occupant = null;
		this.numArmies = 0;
	}

   // Method Headers
   	/**
	 * @return the name of the territory
	 **/
	public String getName()
	{
		return name;
	}

	/**
	 * @return a reference to the Continent that contains this territory
	 * serves the purpose of updating a Continent's isOccupied status
	 * without having to scan through Continents'list of Territories
	 **/
	public String getContinentName()
	{
		return continentName;
	}
	
	/**
	 * @return a HashMap of all territories it shares a border with
	 **/
	public ArrayList<String> getNeighbors()
	{
		return neighbors;
	}

	/**
	 * @return a reference to the player that currently owns this territory
	 **/
	public Player getOccupant()
	{
		return occupant;
	}
	
	/**
	 * @return the number of armies the occupying player has in the territory
	 **/
	public int getNumArmies()
	{
		return numArmies;
	}

// Command Methods:
	/**
	 * Used to set the new occupying player of a territory
	 * also add itself to the new occupant's map
	 * @param occupant reference to the Player object who now occupies the territory
	 **/
	public void setOccupant( Player occupant )
	{
		this.occupant = occupant;
		occupant.updateTerritoriesMap("add",this);
	}

	/**
	 * Used to update the number of armies in a territory
	 * @param numArmies the integer to add to or subtract from the number of armies in the terrtory
	 * @modify positive value : add / negative value : subtract
	 **/
	public void updateNumArmies( int numArmies )
	{
		this.numArmies = this.numArmies + numArmies;
	}
	
}////////////// end class Territory
