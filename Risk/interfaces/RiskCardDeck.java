/* Name: 	Hung Le
   Professor: 	Mr. Johnathan Redmann
   Course: 	CSCI2120
   Project:	Risk-The World Domination Game
   Interface for class RiskCardDeck		*/



public interface RiskCardDeck
{
	void acceptCard();  // accepts sets of cards traded in
	void giveCard();    // gives a card to a player
	void shuffle();	    // randomly alters the order of the cards in the deck

}// end interface RiskCardDeck
