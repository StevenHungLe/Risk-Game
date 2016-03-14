/**
 * CSCI 2120 Fall 2014
 * Risk Game Class Game
 * Authors: Hung Le, Jeanne Vu 
 * Date: 11-25-2014
 **
 * Description:	The main body of a game - also the main model
 * Composition: contains all components of a game: the game board, the deck of cards, and the list of players
 **/


import java.util.Observer;
import java.util.Observable;
import java.util.HashMap;
import java.util.ArrayList;



public class RiskGame extends Observable{
	private String gameName;
	private GameBoard gameBoard;
	private Deck deck;
	private ArrayList<Player> playersList;
	private int numPlayers;
	private int currentPlayerIndex;
	private String state;
	
	public RiskGame(String gameName, int numPlayers)
	{
		this.gameName = gameName;
		gameBoard = new GameBoard();
		deck = new Deck();
		playersList = new ArrayList<Player>(numPlayers);
		this.numPlayers = numPlayers;
		currentPlayerIndex = 0;
		//state = "turnInCardScreen";	
		state = "placeArmiesScreen";
	}

	///////////// QUERY METHODS \\\\\\\\\\\\\

     /**
	 * Returns the game board
	 **/
	public String getState()
	{
		return this.state;
	}

	
     /**
	 * Returns the game board
	 **/
	public GameBoard getGameBoard()
	{
		return this.gameBoard;
	}
	
	/**
	 * Returns the player that is currently in turn
	 **/
	public Player getCurrentPlayer()
	{
		return this.playersList.get(currentPlayerIndex);
	}

	/**
	 * Returns the player at the specified index
	 **/
	public Player getPlayer(int index)
	{
		return this.playersList.get(index);
	}

	/**
	 * Returns the list of players
	 **/
	public ArrayList<Player> getPlayersList()
	{
		return this.playersList;
	}

	/**
	 * Returns the index of the current player
	 **/
	public int getCurrentPlayerIndex()
	{
		return this.currentPlayerIndex;
	}

	/**
	 * Returns the number of players
	 **/
	public int getNumPlayers()
	{
		return this.numPlayers ;
	}


	///////////// COMMAND METHODS \\\\\\\\\\\\\


	/**
	 * Sets the state of the game
	 **/
	public void setState(String newState)
	{
		this.state = newState;
		setChanged();
		notifyObservers(state);
	}
	
	public void addPlayer(String name)
	{
		playersList.add(new Player(name));
		setChanged();
		notifyObservers();
	}

	public void nextPlayer()
	{
		currentPlayerIndex++;
		if ( currentPlayerIndex == playersList.size() )
			currentPlayerIndex = 0;
		setChanged();
		notifyObservers();
	}

	public void setCurrentPlayerIndex(int index)
	{
		currentPlayerIndex = index;
		setChanged();
		notifyObservers();
	}

	public void setInitArmies()
	{
	     //35
		int numArmies = 18 - 5*(numPlayers -3);

		for ( Player i: playersList)
			i.updateNumArmies( numArmies );
		setChanged();
		notifyObservers();
	}

	public void calculateNumArmies()
	{
		getCurrentPlayer().updateNumArmies( getCurrentPlayer().getTerritoriesMap().size() / 3 );
		setChanged();
		notifyObservers();
	}


     /*public void attack(int numAttackDices,int numDefendDices)
	{
		getCurrentPlayer().updateNumArmies( getCurrentPlayer().getTerritoriesMap().size() / 3 );
		setChanged();
		notifyObservers();
	}*/
	


// Helper Methods for components to access and make changes to the main Program
	// getter method

	/* get the players list
	public PlayersList getPlayersList()
	{
		return this.playersList;
	}
	*/

	
	
	// Set the name for the game
	public void setGameName(String gameName)
	{
		this.gameName = gameName;
	}

	// Set the number of players and initialzie the list of players
	public void setNumPlayers(int numPlayers)
	{
		this.numPlayers = numPlayers;
	}


	public void notifyChanges()
	{
		setChanged();
		notifyObservers();
	}


	// Switch between screens & Make sure the switch are error-free
	/*public void switchScreen(String screenName)
	{
		if( screenName.equals("Player Turn Screen"))
		{
			System.out.println("Im here in the switch screen method");
			playersList.setCurrentPlayerIndex(0);
			playerTurnScreen.switchScreen("");
			mainLayout.show(mainPanel,"Player Turn Screen");
		}
	}
*/
	
}
