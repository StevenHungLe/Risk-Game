/**
 * Name: 	Hung Le
 * Professor: 	Mr. Johnathan Redmann
 * Course: 	CSCI2120
 * Project:	Risk-The World Domination Game
 **
 * Interface:	HandInterface specifies a hand of cards held by a specific player
 * Roles:	accept and trade in cards
 * Composition: contained in Player object
 *		contain Card objects		
 **/

import java.util.ArrayList;

public interface HandInterface
{

	/**
	 * Used to receive a Card into a Player's hand
	 * @param newCard the new Card being given to the player's Hand
	 **/
	void acceptCard( Card newCard );
	
	/**
	 * Used to get a list of the cards currently in the player's hand
	 * @return an ArrayList of the Cards in the player's hand
	 **/
	ArrayList<Card> getCards();
	
	/**
	 * Used to remove a set of cards from the player's hand
	 * @param set an array of integers indicating the indices of the cards to turn-in
	 * @return an ArrayList of the Cards being turned-in
	 **/
	ArrayList<Card> turnInSet( int[] set );

}
// end Hand interface
