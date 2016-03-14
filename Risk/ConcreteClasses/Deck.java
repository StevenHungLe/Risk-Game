/**
 * CSCI 2120 Fall 2014
 * Risk Game Class Deck
 * Authors: Hung Le, Jeanne Vu 
 * Date: 11-04-2014
 **
 * Class:	Deck specifies the deck of undistributed Risk game cards
 * Roles:	deal and accept cards
 * Composition: contained in Game object; contain Card objects	
 **/
  
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Random;

public class Deck
{
   /** 
    * Instance Variables
    * Use a LinkedList object to contain cards, as to utilize the Queue structure it extends
    * Might change in the future 
    **/
	private LinkedList<Card> cards; // A linkedlist of cards in the deck - top card is first index
	private int numCards; // Number of cards currently in the deck
	private Random generator;

	public Deck( ArrayList<Card> cards)
	{
		this.cards = new LinkedList<Card>( cards );
		this.numCards = 54;

		generator = new Random();
	}

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
		cards.addAll( set );
	}
	
	/**
	 * Shuffles the deck
	 **/
	public void shuffle()
	{
		Card temp;
		int index;
		
		for( int i = 0; i < cards.size(); i++)
		{
			index = generator.nextInt( cards.size() );
			temp = cards.get( index );
			cards.set( index, cards.get(i) );
			cards.set( i, temp );
		}
	}
}
