/**
 * CSCI 2120 Fall 2014
 * Risk Game Class GameBoardTest
 * Authors: Hung Le, Jeanne Vu 
 * Date: 10-21-2014
 **/

package com.stevenlesoft.risk.model.test;
import com.stevenlesoft.risk.model.*;
import junit.framework.TestCase;
import java.util.HashMap;

public class GameBoardTest extends TestCase
{
	//The objects to test
	private GameBoard board;
	private HashMap<String,Continent> continentsMap;
	private HashMap<String,Territory> territoriesMap;

	/**
	 * Setup
	 */
	protected void setUp ()
	{
		//Initialize a sample territoriesMap
		Territory terr1 = new Territory("terr1","cont1",null);
		territoriesMap= new HashMap<String,Territory>();
		territoriesMap.put("terr1",terr1);

		//Initialize a sample continentsMap
		Continent cont1 = new Continent("cont1",10,territoriesMap);
		continentsMap= new HashMap<String,Continent>();
		continentsMap.put("cont1",cont1);

		//initialize a sample GameBoard: continentsMap, territoriesMap
		board      =      new GameBoard (continentsMap, territoriesMap);
	} 


	/**
	 * Test method getContinentsMap
	 */
	public void testGetContinentsMap()
	{
		assertSame( continentsMap, board.getContinentsMap() ); // the two references should refer to the same object
	}

	/**
	 * Test method getTerritoriesMap
	 */
	public void testGetTerritoriesMap()
	{
		assertSame( territoriesMap, board.getTerritoriesMap() ); // the two references should refer to the same object
	}

	/**
	 * Test method getUnoccupiedTerritories
	 */
	public void testGetUnoccupiedTerritories()
	{
		assertTrue( board.getUnoccupiedTerritories().size()== 1 ); // currently there is 1 empty territory on the board	
		assertTrue( board.getUnoccupiedTerritories().containsKey("terr1")); // the empty territory is terr1	
	}

	/**
	 * Test method getTerritoryByName
	 */
	public void testGetTerritoryByName()
	{
		assertEquals( "terr1", board.getTerritoryByName("terr1").getName() );      // test case: successful retrievement
		assertNull  ( board.getTerritoryByName("TerritoryThatDoesNotEvenExist") ); // test case: null when no territory with such name is found
	}

	/**
	 * Test method getContinentByName
	 */
	public void testGetContinentByName()
	{
		assertEquals( "cont1", board.getContinentByName("cont1").getName() );      // test case: successful retrievement
		assertNull  ( board.getContinentByName("ContinentThatDoesNotEvenExist") ); // test case: null when no continent with such name is found
	}

}// end class GameBoardTest
