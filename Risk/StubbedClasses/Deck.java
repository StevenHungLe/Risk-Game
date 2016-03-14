 /**
  * CSCI 2120 Fall 2014
  * Risk Game Class Deck
  * Authors: Hung Le, Jeanne Vu 
  * Date: 10-21-2014
  **/
  
import java.util.LinkedList;
import java.util.ArrayList;

public class Deck
{
   /** 
    * Instance Variables
    * Use a LinkedList object to contain cards, as to utilize the Queue structure it extends
    * Might change in the future 
    **/
	private LinkedList<Card> cards;

   // Constructor
	public Deck( ArrayList<Card> cards)
	{
		this.cards = new LinkedList<Card>( cards );
	}

   // Method Headers
   	/**
	 * Issues one card to be given to a player
	 * @return Card from the front of the deck
	 **/
	public Card deal()
	{
		return this.cards.poll();
	}

	/**
	 * Used to add cards turned-in by players back to the deck
	 * @param set an ArrayList of Cards turned-in by a player
	 **/
	public void acceptCards( ArrayList<Card> set )
	{

	}
	
	/**
	 * Shuffles the deck
	 **/
	public void shuffle()
	{

	}
}

