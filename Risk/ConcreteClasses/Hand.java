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

import java.util.ArrayList;

public class Hand
{

	private ArrayList<Card> cards;
	private ArrayList<Card> turnedInCards;

	public Hand()
	{
		// a hand should start out holding no cards
		this.cards = new ArrayList<Card>(6);         // 6 is maximum number of cards allowed on hand
		this.turnedInCards = new ArrayList<Card>(3); // 3 is number of cards in a set to be turned in
	}

   // Method Headers
	/**
	 * Used to receive a Card into a Player's hand
	 * @param newCard the new Card being given to the player's Hand
	 **/
	public void acceptCard( Card newCard )
	{
		cards.add( newCard );
	}
	
	/**
	 * Used to get a list of the cards currently in the player's hand
	 * @return an ArrayList of the Cards in the player's hand
	 **/
	public ArrayList<Card> getCards()
	{
		return cards;
	}
	
	/**
	 * Used to remove a set of cards from the player's hand
	 * @param set an array of integers indicating the indices of the cards to turn-in
	 * @return an ArrayList of the Cards being turned-in
	 **/
	public ArrayList<Card> turnInSet( int[] set )
	{
		ArrayList<Card> temp = new ArrayList<Card>(3);
		for( int i : set)
		{
			turnedInCards.add( cards.get(i) );
		}

		boolean firstRemove = false;
		for( int i=0 ; i < 3; i++ )
		{
			cards.remove( set[i]-i );
		}
		
		temp = turnedInCards;
		turnedInCards = new ArrayList<Card>(3);
		return temp;
	}
}
