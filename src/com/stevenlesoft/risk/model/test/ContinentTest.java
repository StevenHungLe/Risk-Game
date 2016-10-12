/**
 * CSCI 2120 Fall 2014
 * Risk Game Class ContinentTest
 * Authors: Hung Le, Jeanne Vu 
 * Date: 10-21-2014
 **/

package com.stevenlesoft.risk.model.test;
import com.stevenlesoft.risk.model.*;
import junit.framework.TestCase;
import java.util.HashMap;

public class ContinentTest extends TestCase
{
	//The Continent Objects to test
	private Continent Africa;
	private HashMap<String,Territory> AfricanTerritories;
	
	/**
	 * Setup
	 */
	protected void setUp ()
	{
		// create a sample map of Territories
		Territory SouthAfrica= new Territory("South Africa","Africa",null);
		Territory Nigeria= new Territory("Nigeria","Africa",null);
		AfricanTerritories= new HashMap<String,Territory> ();
		AfricanTerritories.put("South Africa",SouthAfrica);
		AfricanTerritories.put("Nigeria",Nigeria);

		// initialize a sample Continent: name   , numBonusArmies, territoriesMap
		Africa      =      new Continent("Africa", 4             , AfricanTerritories);
	} 

	/**
	 * Test method getName
	 */
	public void testGetName()
	{
		assertEquals ( "Africa",Africa.getName());
	}					


	/**
	 * Test method getOccupant
	 * Creates several sample Player objects; then call setOccupant and pass those objects as parameters 
	 * with assertSame, uses method getOccupant to confirm that the returned reference refers to the expected object
	 */
	public void testGetOccupant()
	{
		assertNull ( Africa.getOccupant()); // should be null
		Player player3= new Player("player3");
		Africa.setOccupant(player3);
		assertSame ( player3, Africa.getOccupant()); 	// these two references should refer to the same object
 
	}			

	/**
	 * Test method getNumBonusArmies
	 */
	public void testGetNumBonusArmies()
	{
		assertTrue( Africa.getNumBonusArmies() == 4 );
	}		 // returns the continent's value of bonus armies


	/**
	 * Test method setOccupant
	 * Creates several sample Player objects; then call setOccupant and pass those objects as parameters 

	 * with assertSame, uses method getOccupant to confirm that the returned reference refers to the expected object
	 */

	 /**
	 * Test method getTerritoriesMap
	 * @require the Continent have at least one territory 
	 */
	public void testGetTerritoriesMap()
	{
		assertSame ( AfricanTerritories, Africa.getTerritoriesMap()); // should refer to the same object		
		// add one more territory to the list 
		Territory Algeria= new Territory("Algeria","Africa",null);
		Africa.getTerritoriesMap().put("Algeria",Algeria);
		assertTrue ( Africa.getTerritoriesMap().size() == 3 ); // should be consistent 
		
	}

	
	public void testSetOccupant()
	{
		Player player1= new Player("player1");			// create a new object of Player to test
		Africa.setOccupant(player1);
		assertSame ( player1, Africa.getOccupant()); 	// these two references should refer to the same object
		
		Player player2= new Player("player2");			// create another object of Player to test
		Africa.setOccupant(player2);
		assertSame ( player2, Africa.getOccupant());	// these two references should refer to the same object

		Africa.setOccupant(null);			// how does it deal with null?
		assertNull ( Africa.getOccupant());		// should be null
		
	}	



}// end class ContinentTest
