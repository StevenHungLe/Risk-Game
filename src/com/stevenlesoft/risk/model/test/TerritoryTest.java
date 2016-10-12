/**
 * CSCI 2120 Fall 2014
 * Risk Game Class TerritoryTest
 * Authors: Hung Le, Jeanne Vu 
 * Date: 10-21-2014
 **/

package com.stevenlesoft.risk.model.test;
import com.stevenlesoft.risk.model.*;
import junit.framework.TestCase;

import java.util.ArrayList;

public class TerritoryTest extends TestCase
{
	//The Territory Objects to test
	private Territory Russia; 
	private Territory Australia;
	private ArrayList<String> russiaNeighbors;
	/**
	 * Setup
	 */
	protected void setUp ()
	{
		russiaNeighbors = new ArrayList<String>();
		//initialize 2 sample Territories:name      , continent    , Neighbors
		Russia        =    new Territory("Russia"   , "Eurasia"    , russiaNeighbors);
		Australia     =    new Territory("Australia", "Australia"   , null );
	} 

	/**
	 * Test method getName
	 */
	public void testGetName()
	{
		assertEquals("Russia",Russia.getName());
		assertEquals("Australia",Australia.getName());
	}

	/**
	 * Test method getOccupant
	 */
	public void testGetOccupant()
	{
		assertNull(Russia.getOccupant()); // should return null
		
		Player player1= new Player("player1");
		Russia.setOccupant(player1);
		assertSame( player1, Russia.getOccupant()); //should refer to the same object
	}				

	/**
	 * Test method setOccupant
	 */
	public void testSetOccupant()
	{
		Player player2= new Player("player2");			// create a new object of Player to test
		Russia.setOccupant(player2);
		assertSame ( player2, Russia.getOccupant()); 	// these two references should refer to the same object
		
		Player player3= new Player("player3");			// create another object of Player to test
		Russia.setOccupant(player3);
		assertSame ( player3, Russia.getOccupant());	// these two references should refer to the same object

	}				
 
	/**
	 * Test method getNumArmies
	 */
	public void testGetNumArmies()
	{
		assertTrue(Russia.getNumArmies()== 0);
		assertTrue(Australia.getNumArmies()== 0);
	}				

	/**
	 * Test method updateNumArmies
	 * Used to update the number of armies in a territory
	 * @param numArmies the integer to add to or subtract from the number of armies in the terrtory
	 * @modify: positive value : add / negative value : subtract
	 */
	public void testUpdateNumArmies()
	{
		Russia.updateNumArmies(123);			// test case: positive
		assertTrue ( Russia.getNumArmies()== 123 );
		
		Russia.updateNumArmies(0);			// test case: 0
		assertTrue ( Russia.getNumArmies()== 123 );

		Russia.updateNumArmies(-10);			// test case: negative
		assertTrue ( Russia.getNumArmies()== 113 );	// the setNumArmies method should reject negative value, thus this case should return 0 
	}				
	
	/**
	 * Test method getNeighbors
	 */	 
	public void testGetNeighbors()
	{
		assertSame( russiaNeighbors, Russia.getNeighbors() ); 	// test case: simple
		assertNull( Australia.getNeighbors() ); 		// test case: null
	
	}	



}// end class TerritoryTest
