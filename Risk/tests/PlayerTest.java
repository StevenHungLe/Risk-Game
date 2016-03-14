/**
 * CSCI 2120 Fall 2014
 * Risk Game Class PlayerTest
 * Authors: Hung Le, Jeanne Vu 
 * Date: 10-21-2014
 **/

import junit.framework.TestCase;
import java.util.HashMap;

public class PlayerTest extends TestCase
{
	// Objects to test
	private Player player1;
	private Player player2;
	private Territory terr0;
	private Territory terr1;
	private Continent cont0;
	private Continent cont1;
	public GameBoard gameBoard;
	
	/**
	 * Setup
	 */
	protected void setUp ()
	{
		//initialize some sample Territory and Continent objects to be tested
		terr0= new Territory("terr0","cont3",null);
		terr1= new Territory("terr1","cont4",null);
		cont0= new Continent("cont0",5,null);
		cont1= new Continent("cont1",10,null);

		// Initialize a GameBoard object to be used in tests
		HashMap<String,Territory> temp= new HashMap<String,Territory>();
		temp.put("terr0",terr0);
		temp.put("terr1",terr1);
		gameBoard = new GameBoard(null,temp);

		// initiziale sample Player objects to be tested
		player1= new Player("Hung Le");
		player2= new Player("Jeanne Vu");
	} 


// Test Query Methods

	/**
	 * Method to be tested: String getName()
	 * return the Player's name
	 */
	public void testGetName()
	{
		assertEquals("Hung Le",player1.getName() );
	}


	/**
	 * Method to be tested: int getNumArmies()
	 * return: the number of armies to be placed
	 */
	public void testGetNumArmies()
	{
		player1.updateNumArmies(7);
		assertTrue( player1.getNumArmies()== 7 );

	}


	/**
	 * Method to be tested: HashMap<String,Territory> getTerritoriesMap()
	 * @return: an HashMap that contains the map of the territories under control
	 */
	public void testGetTerritoriesMap()
	{
		// add some territory to test
		player1.updateTerritoriesMap("add",terr0);
		player1.updateTerritoriesMap("add",terr1);

		assertTrue( player1.getTerritoriesMap().size() == 2);
		assertSame( terr0, player1.getTerritoriesMap().get("terr0"));
		assertSame( terr1, player1.getTerritoriesMap().get("terr1"));
		
	}

	/**
	 * Method to be tested: HashMap<String,Continent> getContinentsMap()
	 * @return: an HashMap that contains the map of the Continents under control
	 */
	public void testGetContinentsMap()
	{
		// add some continent to test
		player1.updateContinentsMap("add",cont0);
		player1.updateContinentsMap("add",cont1);

		assertTrue( player1.getContinentsMap().size() == 2);
		assertSame( cont0, player1.getContinentsMap().get("cont0"));
		assertSame( cont1, player1.getContinentsMap().get("cont1"));
		
	}


// Test Command Methods

	/**
	 * Method to be tested: void updateNumArmies(int newArmies)
	 * @param numArmies the integer to add to or subtract from the available armies pool
	 * @modify positive value : add / negative value : subtract
	 */
	public void testUpdateNumArmies()
	{
		// test case: positive value
		player1.updateNumArmies(8);
		assertTrue( player1.getNumArmies()== 8 );

		
		// test case: negative value
		player1.updateNumArmies(-4);
		assertTrue( player1.getNumArmies()== 4 );
	}

	
	/**
	 * Method to be tested: void updateTerrittoriesMap(String operation, String territoryName)
	 * @param: a String of the desired operation("add" or "remove")
	 * @param: the Territory object on which to carry out the specified operation
	 * @modify: add the specified Territory object to the TerritoriesMap, OR...
	 * @modify: remove the specified Territory object from the TerritoriesMap 
	 */

	public void testUpdateTerritoriesMap()
	{
		/**
		 * Test case: add a territory to the map
		 * confirm: size of the map increases by 1 and the new territory should be found on the map
		 */
		player1.updateTerritoriesMap("add",terr0);
		assertSame( terr0, player1.getTerritoriesMap().get("terr0"));
		assertTrue( player1.getTerritoriesMap().size() == 1);
		
		player1.updateTerritoriesMap("add",terr1);
		assertSame( terr1, player1.getTerritoriesMap().get("terr1"));
		assertTrue( player1.getTerritoriesMap().size() == 2);

		/**
		 * Test case: remove a territory from the map
		 * confirm: size of the map decreases by 1 and the removed territory should not be found on the map
		 */
		player1.updateTerritoriesMap("remove",terr0);
		assertTrue( player1.getTerritoriesMap().size() == 1);
		assertFalse( player1.getTerritoriesMap().containsKey("terr0"));

	}


	/**
	 * Method to be tested: void updateTerrittoryList(String operation, Continent continent)
	 * @param: a String of the desired operation("add" or "remove")
	 * @param: the Continent object on which to carry out the specified operation
	 * @modify: add the specified Continent object to the ContinentsMap,OR...
	 * @modify: remove the specified Continent object from the ContinentsMap 
	 */
	public void testUpdateContinentsMap()
	{
		/**
		 * Test case: add a continent to the map
		 * confirm: size of the map increases by 1 and the new continent should be found on the map
		 */
		player1.updateContinentsMap("add",cont0);
		assertSame( cont0, player1.getContinentsMap().get("cont0"));
		assertTrue( player1.getContinentsMap().size() == 1);

		player1.updateContinentsMap("add",cont1);
		assertSame( cont1, player1.getContinentsMap().get("cont1"));
		assertTrue( player1.getContinentsMap().size() == 2);

		/**
		 * Test case: remove a continent from the map
		 * confirm: size of the map decreases by 1 and the removed continent should not be found on the map
		 */
		player1.updateContinentsMap("remove",cont0);
		assertTrue( player1.getContinentsMap().size() == 1);
		assertFalse( player1.getContinentsMap().containsKey("cont0"));

	}



// Turn Simulation Methods: simulate the steps taken in a turn of a player
// Might want to cut these off and implement a seperate Turn class in the future


	/** < First Step >
	 * Method to be tested: void deployArmies(int numArmies, String destination)
	 * used to deploy armies into territories
	 * @param int numArmies: number of armies to deploy
	 * @param destination Territory: reference of the destination territory 
	 * @modify: call class Territory's method updateNumArmies to deploy armies
	 * @modify: call method updateNumArmies() to decrease the number of available armies
	 **/
	public void testDeployArmies()
	{
		// add some territories to test
		player1.updateTerritoriesMap("add",terr0);
		player1.updateTerritoriesMap("add",terr1);

		// set the available number of armies to deploy
		player1.updateNumArmies(20);
		
		// call deployArmies on terr0
		player1.deployArmies(5,terr0);
		// terr0 should now have 5 armies
		assertTrue( gameBoard.getTerritoryByName("terr0").getNumArmies() == 5 );
		// player1 has 15 armies left
		assertTrue( player1.getNumArmies() == 15 );
		
		// call deployArmies on terr0 again
		player1.deployArmies(5,terr0);
		// terr0 should now have 10 armies
		assertTrue( gameBoard.getTerritoryByName("terr0").getNumArmies() == 10 );
		// player1 has 10 armies left
		assertTrue( player1.getNumArmies() == 10 );
	}
	
	

	/** < Second Step >
	 * Method: void battle(String attacker, String defender, int attacking, int defending, int[] results)
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
	public void testBattle()
	{
		// prepare the territories to simulate a real battle
		player1.updateTerritoriesMap("add",terr0);
		terr0.setOccupant(player1);
		player2.updateTerritoriesMap("add",terr1);
		terr1.setOccupant(player2);
		
		player1.deployArmies(3,terr0);// attacker: 3 armies
		player2.deployArmies(2,terr1);// defender: 2 armies

	 	// battle !!!
	 	player1.battle(terr0,terr1,2,1,new int[]{4,6,5});

	 	// since max(4,6)=6 > 5, the attacker wins, defender lost 1 armies
	 	assertTrue( gameBoard.getTerritoryByName("terr1").getNumArmies() == 1) ;

	 	// battle !!!
	 	player1.battle(terr0,terr1,2,1,new int[]{5,3,5});

		// since max(5,3)=5 == 5, the defender wins, attacker lost 1 armies
	 	assertTrue( gameBoard.getTerritoryByName("terr0").getNumArmies() == 2) ;

	 	// battle !!!
	 	player1.battle(terr0,terr1,1,1,new int[]{6,1});

	 	// since 6 > 1, the attacker wins, defender lost the territory
	 	// attacker move 1 armies in their new territory
	 	assertTrue( gameBoard.getTerritoryByName("terr1").getNumArmies() == 1) ;
	 	// attacker(player1) captured defender(player2)'s territory
	 	assertSame( player1, gameBoard.getTerritoryByName("terr1").getOccupant() );
	 	// attacking territory now has 1 army left
	 	assertTrue( gameBoard.getTerritoryByName("terr0").getNumArmies() == 1) ;
	}


	
	/** < Third Step >
	 * Method: void moveArmies(String departure,String destination, int numArmies)
	 * Used to fortify territories
	 * Move armies from one territory to another 
	 * @param: departure   Territory: the reference of the territory to move armies from
	 *	   destination Territory: the reference of the territory to move armies to
	 * 	   int numArmies:      the number of armies to move 
	 * @modify: increase the numArmies of the destination, 
	 * and decrease that of the departure by a number numArmies
	 **/
	public void testMoveArmies()
	{
		// prepare some territories to test
		player1.updateTerritoriesMap("add",terr0);
		player1.updateTerritoriesMap("add",terr1);

		player1.deployArmies(10,terr0);// departure: 10 armies
		player1.deployArmies(5,terr1);// destination: 5 armies

		//Test Case 1: move 5 armies
		player1.moveArmies(terr0,terr1,5);

		assertTrue(gameBoard.getTerritoryByName("terr0").getNumArmies() == 5);
		assertTrue(gameBoard.getTerritoryByName("terr1").getNumArmies() == 10);


		//Test Case 2: move 1000 armies
		player1.moveArmies(terr0,terr1,1000);

		/**
		 * The game mechanism require territories to keep a minimum of 1 army
		 * The method should automatically move the largest possible number of armies, leaving 1 behind
		 **/
		assertTrue(gameBoard.getTerritoryByName("terr0").getNumArmies() == 1);
		assertTrue(gameBoard.getTerritoryByName("terr1").getNumArmies() == 14);

		//Test Case 3: negative value
		player1.moveArmies(terr0,terr1,-999);

		/**
		 * Negative value should not be allowed in this method
		 * The method should leave everything as is
		 **/
		assertTrue(gameBoard.getTerritoryByName("terr0").getNumArmies() == 1);
		assertTrue(gameBoard.getTerritoryByName("terr1").getNumArmies() == 14);
	}


}// end class PlayerTest
