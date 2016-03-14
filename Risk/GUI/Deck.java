/**
 * CSCI 2120 Fall 2014
 * Risk Game Class Deck
 * Authors: Hung Le, Jeanne Vu 
 * Date: 11-04-2014
 **
 * Class:	Deck specifies the deck of undistributed Risk game cardsList
 * Roles:	deal and accept cardsList
 * Composition: contained in Game object; contain Card objects	
 **/
  
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Random;
//import java.util.Queue;

public class Deck extends LinkedList<Card>
{
   /** 
    * Instance Variables
    * Use a LinkedList object to contain cardsList, as to utilize the Queue structure it extends
    * Might change in the future 
    **/
	//private LinkedList<Card> cardsList; // A linkedlist of cardsList in the deck - top card is first index
	private Card aCard;
	private int numCards; // Number of cards currently in the deck
	private Random generator;
	private int numOfSetTurnedIn; // keep track of number of decks turned in
	private int numArmiesRewarded;

	public Deck(GameBoard gameBoard)
	{
	     //aCard = new Card(type,name);
		//this.cardsList = null;
		this.numCards = gameBoard.getTerritoriesMap().size();

		numOfSetTurnedIn = 0;
		numArmiesRewarded = 0;
		int count = 0;
		String type = "";

		// loop through the territory Map
		// assign the name of each territory to a card
		// assign a type to a card in rotation
          for (  Object territoryName : gameBoard.getTerritoriesMap().keySet().toArray() )
          {
               if ( count == 0 )
                    type = "Infantry";
               else if ( count == 1 )
                    type = "Cavalry";
               else if ( count == 2 )
                    type = "Artillery";
              
               this.add( new Card( type , territoryName.toString() ) );

               count++;
               if ( count == 3 )
                    count = 0;
          }

		generator = new Random();
	}

   	/**
	 * Issues one card to be given to a player
	 * @return Card from the front of the deck
	 **/
	public Card deal()
	{
	     numCards--;
		return this.poll();
	}

	/**
	 * Used to add cardsList turned-in by players back to the deck
	 * @param  an ArrayList of Cards turned-in by a player
	 * @return number of armies rewarded
	 **/
	public int acceptCards( ArrayList<Card> set )
	{
	     numCards += 3;
		this.addAll( set );
		System.out.println("Accepted turned-in cards");
		
          numOfSetTurnedIn++;

          // adjust trade-in value accordingly to number of decks turned in
		if ( numOfSetTurnedIn == 1 )
		     numArmiesRewarded = 4;
		else if ( numOfSetTurnedIn == 2 )
		     numArmiesRewarded = 6;
		else if ( numOfSetTurnedIn == 3 )
		     numArmiesRewarded = 8;
		else if ( numOfSetTurnedIn == 4 )
		     numArmiesRewarded = 10;
		else if ( numOfSetTurnedIn == 5 )
		     numArmiesRewarded = 12;
		else if ( numOfSetTurnedIn == 6 )
		     numArmiesRewarded = 15;
		else
		     numArmiesRewarded += 5;

		System.out.println("Number of armies rewarded: "+numArmiesRewarded);
		return numArmiesRewarded;
	}
	
	/**
	 * Shuffles the deck
	 **/
	public void shuffle()
	{
		Card temp;
		int index;
		
		for( int i = 0; i < this.size(); i++)
		{
			index = generator.nextInt( this.size() );
			temp = this.get( index );
			this.set( index, this.get(i) );
			this.set( i, temp );
		}

	}

} ///////////////////////////////////// end class Deck
