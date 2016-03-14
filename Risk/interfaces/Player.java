/* Name: 	Hung Le
   Professor: 	Mr. Johnathan Redmann
   Course: 	CSCI2120
   Project:	Risk-The World Domination Game
   Interface for class Player			*/

import java.util.ArrayList;

public interface Player
{
	void setNumArmies();		// sets the number of armies available for placement
	int getNumArmies();		// gets the number of armies available for placement

	void setNumTerritories();	// sets the number of territories occupied ( for calculation of armies received each turn )
	int getNumTerritories();	// gets the number of territories occupied ( for calculation of armies received each turn )

	void updateTerrittoryList();			// updates the list of occupied territories ( for attack/armies recruit purpose )
	ArrayList<Territory> getTerrittoryList();	// gets the list of occupied territories ( for attack/armies recruit purpose )

	void updateContinentList();			// updates the list of occupied continents ( for calculation of armies received each turn )
	ArrayList<Continent> getContinentList();	// gets the list of occupied continents ( for calculation of armies received each turn )

	void deployArmies();		// calculates the number of armies received and deploy them into the player's territories 
	void attack();			// contains attack and win/lose decision mechanism
	void rollDice();		// rolls the dice
	void fortify();			// moves armies between the player's territories 

}// end interface Player
