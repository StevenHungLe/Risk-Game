/**
 * CSCI 2120 Fall 2014
 * Risk Game Class PlayerList
 * Authors: Hung Le, Jeanne Vu 
 * Date: 11-25-2014
 **
 * Class:	PlayerList - the structure that control the queue of Players, also acts as a Model to many views
 * Composition: contains an ArrayList of Players
 **/
import java.util.Observable;
import java.util.ArrayList;

public class PlayersList extends Observable
{
	
	private ArrayList<Player> playersList;
	private int currentPlayerIndex;

	public PlayersList()
	{
		this.playersList = new ArrayList<Player>();
		currentPlayerIndex=0;
	}

	public Player getPlayer()
	{
		return this.playersList.get(currentPlayerIndex);
	}
	
	public Player getPlayer(int index)
	{
		return this.playersList.get(index);
	}

	public ArrayList<Player> getPlayersList()
	{
		return this.playersList;
	}

	public int getCurrentPlayerIndex()
	{
		return this.currentPlayerIndex;
	}

	public int getNumPlayers()
	{
		return this.playersList.size();
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
		int numArmies = 35 - 5*(playersList.size() -3);

		for ( Player i: playersList)
			i.updateNumArmies( numArmies );
		setChanged();
		notifyObservers();
	}

	public void calculateNumArmies()
	{
		getPlayer().updateNumArmies( getPlayer().getTerritoriesMap().size() / 3 );
		setChanged();
		notifyObservers();
	}

	public void refresh()
	{
		setChanged();
		notifyObservers();
	}
	
}
