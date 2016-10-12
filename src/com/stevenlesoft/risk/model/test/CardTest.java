/**
 * CSCI 2120 Fall 2014
 * Risk Game Class CardTest
 * Authors: Hung Le, Jeanne Vu 
 * Date: 10-21-2014
 **/

package com.stevenlesoft.risk.model.test;
import com.stevenlesoft.risk.model.*;
import junit.framework.TestCase;


public class CardTest extends TestCase
{
	// Objects to test
	private Card regularCard;
	private Card wildCard;


	/**
	 * Setup
	 */
	protected void setUp ()
	{
		regularCard= new Card("infantry","Vietnam");
		wildCard= new Card("wild",null);
	}

	/**
	 * Method to be tested: getType()
	 *@return the type(infantry, cavalry, artillery) of the card as a String
	 *@interface: String getType();
	 */
	
	public void testGetType()
	{
		assertEquals( "infantry", regularCard.getType() );
		assertEquals( "wild", wildCard.getType() );
	}

	/**
	 * Method to be tested: getTerritoryName()
	 *@return the name of the territory marked on the card as a String
	 *@interface: String getTerritoryName();
	 */
	public void testgetTerritoryName()
	{
		assertEquals( "Vietnam", regularCard.getTerritoryName() );
		assertNull( wildCard.getTerritoryName() ); // a wild card does not contain a territory and thus should return null
	}

}// end class CardTest
