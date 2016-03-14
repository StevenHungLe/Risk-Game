/**
 * Name: 	Hung Le
 * Professor: 	Mr. Johnathan Redmann
 * Course: 	CSCI2120
 * Project:	Risk-The World Domination Game
 **
 * Interface:	DeckInterface specifies the deck of undistributed Risk game cards
 * Roles:	deal and accept cards
 * Composition: contained in Game object; contain Card objects		
 **/



public interface DeckInterface
{
	/**
	 * Issues one card to be given to a player
	 * @return Card from the front of the deck
	 **/
	Card deal();
	
	/**
	 * Used to add cards turned-in by players back to the deck
	 * @param set an ArrayList of Cards turned-in by a player
	 **/
	void acceptCards( ArrayList<Card> set );
	
	/**
	 * Shuffles the deck
	 **/
	void shuffle();

}
// end Deck interface
