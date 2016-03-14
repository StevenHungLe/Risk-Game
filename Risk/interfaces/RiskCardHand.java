/* Name: 	Hung Le
   Professor: 	Mr. Johnathan Redmann
   Course: 	CSCI2120
   Project:	Risk-The World Domination Game
   Interface for class RiskCardHand		*/

import java.util.ArrayList;

public interface RiskCardHand
{
	ArrayList<RiskCard> getCardHand(); //returns a list of risk cards on hand
	void receiveRiskCard();		 // receive a risk card from the deck
	void tradeRiskCard();		 // trade in sets of risk card for armies

}// end interface RiskCardHand
