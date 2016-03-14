/* Name: 	Hung Le
   Professor: 	Mr. Johnathan Redmann
   Course: 	CSCI2120
   Project:	Risk-The World Domination Game
   Interface for class Continent		*/

import java.util.ArrayList;

public interface Continent
{
	void setOccupant();			 // sets the occupant of the continent 
	Player getOccupant();			 // returns the occupant of the continent
	int getBonusArmyValue();		 // returns the continent's value of bonus armies
	ArrayList<Territory> getTerritoryList(); // returns the list of territories that comprise the continent

}// end interface Continent
