/**
 * Name: 	Hung Le
 * Professor: 	Mr. Johnathan Redmann
 * Course: 	CSCI2120
 * Project:	Risk-The World Domination Game
 **
 * Interface:	CardInterface specifies the Risk game cards
 * Roles:	trade in to receive armies for the game
 * Composition: contained in Deck object
 **/



public interface CardInterface
{

	/**
	 * @return the type (infantry, cavalry, artillery) of the card as a String
	 **/
	String getType();
	
	/**
	 * @return a reference to the territory this card is matched to
	 **/
	Territory getTerritory();

}
// end Card interface
