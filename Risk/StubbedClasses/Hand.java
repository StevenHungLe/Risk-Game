 /**
  * CSCI 2120 Fall 2014
  * Risk Game Class Hand
  * Authors: Hung Le, Jeanne Vu 
  * Date: 10-21-2014
  **/

import java.util.ArrayList;

public class Hand
{
   // Instance Variables
	private ArrayList<Card> cards;
	private ArrayList<Card> turnedInCards;

   // Constructor
	public Hand()
	{
		// a hand should start out holding no cards
		this.cards = new ArrayList<Card>(6);         // maximum number of cards allowed on hand
		this.turnedInCards = new ArrayList<Card>(3); // number of cards in a set to be turned in
	}

   // Method Headers
	/**
	 * Used to receive a Card into a Player's hand
	 * @param newCard the new Card being given to the player's Hand
	 **/
	public void acceptCard( Card newCard )
	{

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
		return turnedInCards;
	}
}
