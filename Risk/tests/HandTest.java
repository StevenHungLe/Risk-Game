/**
 * CSCI 2120 Fall 2014
 * Risk Game Class HandTest
 * Authors: Hung Le, Jeanne Vu 
 * Date: 10-21-2014
 **/

import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.Arrays;

public class HandTest extends TestCase
{
	// Objects to test
	private	Hand hand1;
	private Hand hand0;
	private Card card0;
	private Card card1;
	private Card card2;
	private Card card3;
	private Card card4;

	/**
	 * Setup
	 */
	protected void setUp ()
	{
		card0 = new Card("infantry","terr0");
		card1 = new Card("artillery","terr1");
		card2 = new Card("cavalry","terr2");
		card3 = new Card("infantry","terr3");
		card4 = new Card("wild",null);
		
		//initialize two sample hands:one with 4 cards and one empty
		hand1  = new Hand();
		hand1.acceptCard(card0);
		hand1.acceptCard(card1);
		hand1.acceptCard(card2);
		hand1.acceptCard(card3);
		
		hand0 = new Hand();
	} 



	/**
	 * Method to be tested: ArrayList<Card> getCards()
	 * Used to get a list of the cards currently in the player's hand
	 * @return an ArrayList of the Cards in the player's hand
	 **/
	public void testGetCards()
	{
		/**
		 * test case: a simple hand: hand1
		 * confirm: the method returns an ArrayList of the cards in the hand in exact order
		 **/

		assertSame( card0, hand1.getCards().get(0) );
		assertSame( card1, hand1.getCards().get(1) );
		assertSame( card2, hand1.getCards().get(2) );
		assertSame( card3, hand1.getCards().get(3) );


		/**
		 * test case: a hand with no cards: hand0
		 * confirm: the method returns an empty ArrayList
		 **/
		assertTrue( hand0.getCards().isEmpty());

		
	}


	/**
	 * Method to be tested: void acceptCard( Card newCard)
	 * Used to accept a Card into a Player's hand
	 * @param newCard the new Card being given to the player's Hand
	 * @ensure the new Card is added to the end of the hand
	 **/
	public void testAcceptCard()
	{
		/**
		 * test case: a simple hand: hand1
		 * confirm: the new Card is added to the end of the hand
		 **/
	
		hand1.acceptCard( card4 );
		assertSame( card4, hand1.getCards().get(4) );


		/**
		 * test case: a hand with no cards: hand0
		 * confirm: the new Card is added to the front of the hand
		 **/

		hand0.acceptCard( card0 );
		assertSame( card0, hand0.getCards().get(0) );
	}


	/**
 	 * Method to be tested: ArrayList<Card> turnInSet( int[] set )
	 * Used to remove a set of cards from the player's hand
	 * @param set an array of integers indicating the indices of the cards to turn-in
	 * @return an ArrayList of the Cards being turned-in
	 * @ensure the method returns the set of cards to be traded in as an ArrayList
	 * @ensure the set of cards to be traded in is removed from the hand
	 * @ensure the trade of invalid combination is unauthorized 
	 **/
	public void testTurnInSet()
	{
		hand1.acceptCard( card4 );      // add a wild card to the hand for testing
		ArrayList<Card> tradeSet = null;// a temporary ArrayList to contains turned in cards
		int arraySet[];			// array of card indexes to turn in

		/** type of the cards to test
		* card0 = "infantry"
		* card1 = "artillery"
		* card2 = "cavalry"
		* card3 = "infantry"
		* card4 = "wild"
		**/


		/**
		 * test case: two cards with the same type and a wild card (0,3,4)
		 **/
		arraySet= new int[] {0,3,4};
		tradeSet= hand1.turnInSet(arraySet);

		// confirm: the set of cards to be traded in is removed from the hand
		assertFalse ( hand1.getCards().contains(card0));
		assertFalse ( hand1.getCards().contains(card3));
		assertFalse ( hand1.getCards().contains(card4));
		
		// confirm: the method returns the set of cards to be traded in as an ArrayList
		assertSame( card0, tradeSet.get(0));
		assertSame( card3, tradeSet.get(1));
		assertSame( card4, tradeSet.get(2));

			
	}

}// end class HandTest
