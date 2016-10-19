/**
 * CSCI 2120 Fall 2014
 * Risk Game Class Hand
 * Authors: Hung Le, Jeanne Vu 
 * Date: 11-04-2014
 **
 * Class:	Hand specifies a hand of cards held by a specific player
 * Roles:	accept and trade in cards
 * Composition: contained in Player object
 *		contain Card objects	
 **/
package com.stevenlesoft.risk.model;
import java.util.ArrayList;

public class Hand extends ArrayList<Card>
{
	private ArrayList<Card> turnedInCards;

	public Hand()
	{
		// a hand should start out holding no cards
		super(6);         // 6 is maximum number of cards allowed on hand
		this.turnedInCards = new ArrayList<Card>(3); // 3 is number of cards in a set to be turned in
	}


	/**
	 * Used to receive a Card into a Player's hand
	 * @param newCard the new Card being given to the player's Hand
	 **/
	public void takeACard( Card newCard )
	{
		this.add( newCard );
		System.out.println("Received a new card");
	}
	
	
	/**
	 * Used to remove a set of cards from the player's hand
	 * @param set an array of integers indicating the indices of the cards to turn-in
	 * @return an ArrayList of the Cards being turned-in
	 **/
	public ArrayList<Card> turnInCards( int[] set )
	{
		turnedInCards.clear();

		// add the cards to the list to be turned in
		for( int i : set)
		{
			turnedInCards.add( this.get(i) );
		}

        // remove the turned in cards from the hand
		for( Card toRemove: turnedInCards)
		{
			this.remove(toRemove);
		}
		
		return turnedInCards;
	}
} ///////////////////////////////////// end class Hand
