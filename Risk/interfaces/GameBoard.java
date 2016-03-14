/* Name: 	Hung Le
   Professor: 	Mr. Johnathan Redmann
   Course: 	CSCI2120
   Project:	Risk-The World Domination Game
   Interface for class GameBoard		*/

import java.util.ArrayList;

public interface GameBoard
{
	ArrayList<Continent> getContinentList(); 	// returns a list of the continents on the board
	ArrayList<Territory> getTerritoryList(); 	// returns a list of the territories on the board
	ArrayList<Territory> getUnoccupiedTerritory();  // returns a list of unoccupied territories on the board

}// end interface GameBoard
