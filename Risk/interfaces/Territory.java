/* Name: 	Hung Le
   Professor: 	Mr. Johnathan Redmann
   Course: 	CSCI2120
   Project:	Risk-The World Domination Game
   Interface for class Territory		*/

import java.util.ArrayList;

public interface Territory
{
	void setOccupant();				// sets the occupant of the Territory 
	Player getOccupant();				// returns the occupant of the Territory

	void setNumArmies();				// sets the number of armies in the territorry	 
	int getNumArmies();				// returns the number of armies in the territorry
		 
	ArrayList<Territory> getAdjacentTerritories();	// returns a list of territories that share border with the current territory	 

}// end interface Territory
