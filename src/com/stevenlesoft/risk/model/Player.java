/**
 * CSCI 2120 Fall 2014
 * Risk Game Class Player
 * Authors: Hung Le, Jeanne Vu 
 * Date: 11-04-2014
 **
 * Class:	Player specifies a player who is playing an active game
 * Roles:	participate in the activities of the game
 * Composition: contained in a Game object
 **/
package com.stevenlesoft.risk.model;
import java.util.HashMap;

public class Player
{
   // Instance Variables
	private String playerName;
	private int numArmies;
	private HashMap<String,Territory> territoriesMap;
	private HashMap<String,Continent> continentsMap;
	private Hand hand;
     

   // Constructor
	public Player( String playerName)
	{
		this.playerName = playerName;
		this.numArmies = 0;
		this.territoriesMap = new HashMap<String,Territory>();
		this.continentsMap = new HashMap<String,Continent>();
		this.hand = new Hand();
	}

   // Query Methods:

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

     /**
	 * @return the Player's Hand of Cards
	 **/
	public Hand getHand()
	{
		return hand;
	}
	
// Command Methods:

	/**
	 * Used to update the number of armies the player has that need to be placed
	 * @param numArmies the integer to add to or subtract from the available armies pool
	 * @modify positive value : add / negative value : subtract
	 **/
	public void updateNumArmies( int numArmies )
	{
		this.numArmies = this.numArmies + numArmies;
	}

	/**
	 * Update the map of owned territories as the player win or lose battles
	 * @param:  operation String:    the desired operation ("add" or "remove")
	 *          territory Territory: reference of the Territory object to add/remove
	 **/
	public void updateTerritoriesMap(String operation, Territory territory)
	{
		if ( operation.equals("add") )
			territoriesMap.put(territory.getName(),territory);
		else if ( operation.equals("remove") )
			territoriesMap.remove(territory.getName());
	}

	/**
	 * Update the map of owned continents as the player win or lose battles
	 * @param:  operation String: the desired operation ("add" or "remove")
	 *          continent Continent: reference of the Continent object to add/remove
	 **/
	public void updateContinentsMap(String operation, Continent continent)
	{
		if ( operation.equals("add")  )
			continentsMap.put(continent.getName(),continent);
		else if ( operation.equals("remove"))
			continentsMap.remove(continent.getName());
	}

// Turn Simulation Methods: simulate the steps taken in a turn of a player

     
	/** < First Step >
	 * Used to deploy armies into territories
	 * @param int numArmies: number of armies to deploy
	 * @param destination Territory: reference of the destination territory 
	 * @modify: call class Territory's method updateNumArmies to deploy armies
	 * @modify: call method updateNumArmies() to decrease the number of available armies
	 **/
	public void deployArmies(int numArmies, Territory destination)
	{
		destination.updateNumArmies(numArmies);
		this.updateNumArmies(-numArmies);
	}

	

	/** < Second Step >
	 * Used to resolve battles
	 * @param: attacker Territory: the reference of the territory the player is attacking from
	 * @param: defender Territory: the reference of the territory that is being attacked
	 * @param: attacking int     : an integer representing how many dice to use for the attack(1-3)
	 * @param: defending int     : an integer to represent how many dice to use for the defense(1-2)
	 * @param: results int[]     : an array of integers holding the results of the rolls
	 * @modify: call method updateNumArmies() of class Territory to decrease the loser's numArmies
	 * @modify: if the defender lost its last army, call method setOccupant() of class Territory to update occupant 
 	 * @modify: call method moveArmies() move armies to the newly captured territory
	 **/
	public int[] battle(Territory attacker, Territory defender, int attacking, int defending, int[] results)
	{
          
		// call helper method sortArray to put results in descending order for easy comparison
		int[] attackingResults = sortArray(results,0,attacking-1);
		int[] defendingResults = sortArray(results,attacking,attacking+defending-1);

		int attackerLost = 0;
		int defenderLost = 0;

		// battle resolve loop, number of times to loop = min(attacking,defending)
		for ( int i=0; i < (attacking<defending?attacking:defending);i++)
		{
		     System.out.println("Attacker Score: "+attackingResults[i]);
		     System.out.println("Defender Score: "+defendingResults[i]);
		     
			if( attackingResults[i] <= defendingResults[i] )// attacker lose
			{
			     System.out.println("Attacker lost this showdown!");
				attacker.updateNumArmies(-1);		// attacker lose 1 army
				attackerLost++;
			}
			else 						// defender lose
			{
			     System.out.println("Defender lost this showdown!");
				defender.updateNumArmies(-1);		// defender lose 1 army
				defenderLost++;
			}
		}

          System.out.println("After Attack, Attacker has "+attacker.getNumArmies());
          System.out.println("After Attack, Defender has "+defender.getNumArmies());
          
		if( defender.getNumArmies() == 0 )			// the last army of defender is lost
		{
		     System.out.println("Defender is "+defender.getName());
		     defender.getOccupant().updateTerritoriesMap("remove",defender); // take ownership from the lost player 
		     System.out.println("After update, Defender is "+defender.getName());
			defender.setOccupant(attacker.getOccupant());	// set new occupant
			
			moveArmies(attacker,defender,attacking);	// move armies

			System.out.println("After Moving, Attacker has "+attacker.getNumArmies());
               System.out.println("After Moving, Defender has "+defender.getNumArmies());
		}

          
          
		return new int[]{attackerLost,defenderLost};

	}


	/**
	 * Helper method: Called by method battle() to sort the result arrays to compare
	 * @param: int[] array: the array of roll results
	 * @param: int startIndex, int endIndex: used to separate attacking and defending dices
	 * @return: int[] : a sorted array of attacking or defending results
	 **/
	private int[] sortArray(int[] array, int startIndex, int endIndex )
	{
		int[] tempArray = new int[endIndex-startIndex+1];
		// outer loop to assign values in an array in descending order
		for(int i=0; i <= (endIndex-startIndex);i++)
		{
			int greatest = array[startIndex];
			int greatestIndex = startIndex;
			// inner loop to find the greatest value
			for( int j = startIndex; j <= endIndex; j++)
			{
				if( greatest < array[j])
				{
					greatest = array[j];
					greatestIndex=j;
				}	
			}
			// set the value at the greatest index = 0 to avoid duplication
			array[greatestIndex]=0;
			// assign greatest value to the array in descenging order
			tempArray[i]=greatest;
		}
		return tempArray;
	}


	
	/** < Third Step >
	 * Used to fortify territories
	 * Move armies from one territory to another 
	 * @param: departure   Territory: the reference of the territory to move armies from
	 *	      destination Territory: the reference of the territory to move armies to
	 * 	      int numArmies:      the number of armies to move 
	 * @modify: increase the numArmies of the destination, 
	 * and decrease that of the departure by a number numArmies
	 **/
	public void moveArmies(Territory departure,Territory destination, int numArmies)
	{
		// do nothing with invalid input
		if ( numArmies < 0 )
			return;
		// Automatically adjust input to leave at least 1 army in the departure
		if ( numArmies >= departure.getNumArmies())
			numArmies = departure.getNumArmies()-1;

		departure.updateNumArmies(-numArmies);
		destination.updateNumArmies(numArmies);
	}
	
} //////////////////// end class Player
