/**
 * CSCI 2120 Fall 2014
 * Risk Game Class DeckTest
 * Authors: Hung Le, Jeanne Vu 
 * Date: 10-21-2014
 **/

package com.stevenlesoft.risk.model.test;
import com.stevenlesoft.risk.model.*;
import junit.framework.TestCase;
import java.util.ArrayList;

public class DeckTest extends TestCase
{
	// Objects to test
	private Deck deck;
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
		card0 = new Card("type0","terr0");
		card1 = new Card("type1","terr1");
		card2 = new Card("type2","terr2");
		card3 = new Card("type3","terr3");
		card4 = new Card("type4","terr4");

		ArrayList<Card> tempIni = new ArrayList<Card>();
		tempIni.add(card0);
		tempIni.add(card1);
		
		//initialize a sample deck: @param: an ArrayList of cards
		deck  = new Deck(new GameBoard());
	} 


	/**
	 * Method to be tested: void acceptCards( ArrayList<Card> set)
	 * @param 	an ArrayList of cards to be traded in
	 * @modify 	add the traded-in set of cards to the back of the deck,i.e. the lasts in queue
	 */
	public void testacceptCards()
	{
		ArrayList<Card> tempAccept = new ArrayList<Card>();
		tempAccept.add(card2);
		tempAccept.add(card3);
		tempAccept.add(card4);
		deck.acceptCards( tempAccept );// the deck should now contains cards in this order: card0,card1,card2,card3,card4
		
		// deal from the front of the deck,i.e the first in queue to confirm the order of cards
		assertSame( card0,deck.deal() );
		assertSame( card1,deck.deal() );
		assertSame( card2,deck.deal() );
		assertSame( card3,deck.deal() );
		assertSame( card4,deck.deal() );
	}

	/**
	 * Method to be tested: Card deal()
	 * @return a reference of a card from the front of the deck
	 * @require the deck is not empty( which is not possible according to the game mechanism)
	 */
	public void testDeal()
	{
		// deal from the front of the deck,i.e. the first in queue, to confirm the order of cards
		assertSame( card0, deck.deal() );
		assertSame( card1, deck.deal() );
	}

	/**
	 * Method to be tested: void shuffle()
	 * @modify the order of the cards randomly
	 * @ensure the deck contains all of the cards, only in different order
	 */
	public void testShuffle()
	{
		// Add some cards to the deck to test
		ArrayList<Card> tempAccept = new ArrayList<Card>();
		tempAccept.add(card2);
		tempAccept.add(card3);
		tempAccept.add(card4);
		deck.acceptCards( tempAccept );// the deck should now contains cards in this order: card0,card1,card2,card3,card4
		
		deck.shuffle(); // shuffle the deck

		ArrayList<Card> testDeck = new ArrayList<Card> (); // create a intermediate ArrayList to contain the cards to be tested

		Card card= new Card("","");
		for(int i=0; i<5; i++)
		{
			card= deck.deal();
			testDeck.add(card); // deal all the cards to the intermediate testDeck
		}

		assertTrue ( testDeck.size() == 5 ); // confirm: the number of cards in the deck should remain the same after shuffling 

		// confirm: the composition of the deck should remain the same after shuffling 
		assertTrue ( testDeck.contains(card0) );
		assertTrue ( testDeck.contains(card1) );
		assertTrue ( testDeck.contains(card2) );
		assertTrue ( testDeck.contains(card3) );
		assertTrue ( testDeck.contains(card4) );

	}
}// end class DeckTest
