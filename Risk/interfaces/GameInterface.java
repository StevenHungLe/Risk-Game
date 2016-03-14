/**
 * Name: 	Hung Le
 * Professor: 	Mr. Johnathan Redmann
 * Course: 	CSCI2120
 * Project:	Risk-The World Domination Game
 **
 * Interface:	GameInterface specifies the Game model
 * Roles:	execute and control the various activities of the game
 * Composition: contain Player,GameBoard, Deck, and Dice objects
 **/



public interface GameInterface
{
	/** 
	 * Starts a new game and will call private methods to initialize the new game.
	 * @param gameName the name used to save the game into a file
	 **/
	void newGame( String gameName );

	/**
	 * Loads a saved game from serialized objects stored in a file
	 * @param gameName the name of the file containing the saved game
	 **/
	void loadSavedGame( String gameName );
	
	/**
	 * Starts the game loop that will begin actual game play
	 **/
	void playGame();
	
	/**
	 * Save the state of the game to a file
	 **/
	void saveGame();
}
// end Game interface
