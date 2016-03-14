/**
 * CSCI 2120 Fall 2014
 * Risk Game Class Game
 * Authors: Hung Le, Jeanne Vu 
 * Date: 11-25-2014
 **
 * Description:	The main body of a game - also the main model - holds a game state to switch subScreens
 * Composition: contains all components of a game: the game board, the deck of cards, and the list of players
 * Observed by multiple JPanel Screens from the GUI
 **/


import java.util.Observer;
import java.util.Observable;
import java.util.HashMap;
import java.util.ArrayList;

public class RiskGame extends Observable{
	private String gameName;
	private GameBoard gameBoard;
	private Deck deck;
	private Dice dice;
	private ArrayList<Player> playersList;
	private int numPlayers;
	private int currentPlayerIndex;
	private String state;
	
	
	public RiskGame(String gameName, int numPlayers)
	{
		this.gameName = gameName;
		gameBoard = new GameBoard();
		
		deck = new Deck(gameBoard);
		deck.shuffle(); // suhffle the deck
		
		dice = new Dice();
		
		playersList = new ArrayList<Player>(numPlayers);
		this.numPlayers = numPlayers;
		currentPlayerIndex = 0;
		state = "turnInCardScreen";	
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

     /**
	 * Returns the results of a roll
	 **/
	public int[] getRollResults(int attacking, int defending)
	{
		return this.dice.roll(attacking,defending) ;
	}

	/**
	 * Returns the deck of cards
	 **/
	public Deck getDeck()
	{
		return this.deck ;
	}

	///////////// COMMAND METHODS \\\\\\\\\\\\\

     /**
	 * call method turnInCard for the current player
	 * param  an array that holds the indices of the card to be turned in
	 **/
     public void turnInCards(int[] selectedIndices)
     {
          int rewardedArmies = this.deck.acceptCards( getCurrentPlayer().getHand().turnInCards( selectedIndices ) );
          this.getCurrentPlayer().updateNumArmies( rewardedArmies );
          // bonus armies
        
     }
     
	/**
	 * Sets the state of the game
	 **/
	public void setState(String newState)
	{
		this.state = newState;
		setChanged();
		notifyObservers(state);
	}

	// add a new player
	// param : the name of the new player
	public void addPlayer(String name)
	{
		playersList.add(new Player(name));
		setChanged();
		notifyObservers();
	}

     // remove a player from the list
     // param : the player object to be removed
	public void removePlayer(Player lostPlayer)
	{
		playersList.remove(lostPlayer);
		numPlayers--;
		setChanged();
		notifyObservers();
	}

     // switch to the next player by increment the currentPlayerIndex
     // automatically rotate the players in the list
	public void nextPlayer()
	{
		currentPlayerIndex++;
		if ( currentPlayerIndex == playersList.size() )
			currentPlayerIndex = 0;
		setChanged();
		notifyObservers();
	}

     // set the CurrentPlayerIndex
	public void setCurrentPlayerIndex(int index)
	{
		currentPlayerIndex = index;
		setChanged();
		notifyObservers();
	}

     // set the initial armies for placement
	public void setInitArmies()
	{
	     //35
		int numArmies = 35 - 5*(numPlayers -3);

		for ( Player i: playersList)
			i.updateNumArmies( numArmies );
		setChanged();
		notifyObservers();
	}

     // calculate the number of armies received for the current player
	public void calculateNumArmies()
	{
	     int newArmies = 0;
	     int newArmiesFromTerr = 0;

	     newArmiesFromTerr = getCurrentPlayer().getTerritoriesMap().size() / 3 ;
	     if ( newArmiesFromTerr < 3)
	          newArmiesFromTerr = 3; // always receive at least 3 armies
	     
	     newArmies += newArmiesFromTerr ;
	     
	     for ( Continent c : getCurrentPlayer().getContinentsMap().values() )
	          newArmies += c.getNumBonusArmies(); // bonus armies from owned continent
	          
		getCurrentPlayer().updateNumArmies( newArmies );
		setChanged();
		notifyObservers();
	}

     // setChanged and notifyObservers
	public void notifyChanges()
	{
		setChanged();
		notifyObservers();
	}

	
} //////////////////////// end class RiskGame
