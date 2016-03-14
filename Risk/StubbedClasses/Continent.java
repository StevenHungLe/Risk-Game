 /**
  * CSCI 2120 Fall 2014
  * Risk Game Class Continent
  * Authors: Hung Le, Jeanne Vu 
  * Date: 10-21-2014
  **/

import java.util.HashMap;

public class Continent
{
   // Instance Variables
	private String name;
	private Player occupant;
	private int numBonusArmies;
	private boolean isOccupied;
	private HashMap<String,Territory> territoriesMap;

   // Constructor
	public Continent(String name, int numBonusArmies, HashMap<String,Territory> territoriesMap)
	{
		this.name = name;
		this.occupant = null;
		this.numBonusArmies = numBonusArmies;
		this.isOccupied = false;
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
	 * @return true of there is a player that owns all the territories on this continent
	 **/
	public boolean isOccupied()
	{
		return false; 
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
	 * Sets which player owns a continent, if any
	 * @param occupant a reference to the Player object that now owns this continent
	 **/
	public void setOccupant( Player occupant )
	{

	}

	/**
	 * Toggle the boolean value of isOccupied between true and false 
	 **/
	public void toggleIsOccupied()
	{

	}
}

