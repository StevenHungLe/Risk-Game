 /**
  * CSCI 2120 Fall 2014
  * Risk Game Class Continent
  * Authors: Hung Le, Jeanne Vu 
  * Date: 11-04-2014
  **
  * Class:	 Continent specifies continents on the board
  * Roles:	 the larger entities to be conquered in the game
  * Composition: contained in GameBoard object
  *		 contain Territory objects	
  **/
package com.stevenlesoft.risk.model;
import java.util.HashMap;

public class Continent
{
   // Instance Variables
	private String name;
	private Player occupant;
	private int numBonusArmies;
	private HashMap<String,Territory> territoriesMap;

   // Constructor
	public Continent(String name, int numBonusArmies, HashMap<String,Territory> territoriesMap)
	{
		this.name = name;
		this.occupant = null;
		this.numBonusArmies = numBonusArmies;
		this.territoriesMap = territoriesMap;
	}

   // Method Headers
   	/**
	 * @return the name of the continent as a String
	 **/
	public String getName()
 	{
		return name;
	}

	/**
	 * If no player owns all territories on the continent then this method should 
	 * return null
	 * @return a reference to the Player object that currently owns this continent
	 **/
	public Player getOccupant()
	{
		return occupant;
	}

	/**
	 * @return the number of additional armies a player gets for owning this continent
	 **/
	public int getNumBonusArmies()
	{
		return numBonusArmies;
	}
	
	/**
	 * @return a HashMap of all the territories on the continent
	 **/
	public HashMap<String,Territory> getTerritoriesMap()
	{
		return territoriesMap;
	}

// Command Methods:
	/**
	 * Sets which player owns a continent
	 * add/remove itself to/from new/old occupant'map
	 * @param occupant a reference to the Player object that now owns this continent
	 **/
	public void setOccupant( Player newOccupant )
	{    
	     // if this is not the first time this continent is occupied
	     // remove itself from old occupant's map
	     if ( this.occupant != null ) 
	          this.occupant.updateContinentsMap("remove",this); 

		this.occupant = newOccupant;
		
		// add itself to new occupant's map
		if(this.occupant != null)
			this.occupant.updateContinentsMap("add",this); 
	}

} ///////////////////////////////////// end class Continent

