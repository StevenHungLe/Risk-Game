/**
 * CSCI 2120 Fall 2014
 * Risk Game Class Deck
 * Authors: Hung Le, Jeanne Vu 
 * Date: 11-04-2014
 **
 * Class:	Deck specifies the deck of undistributed Risk game cardsList
 * Roles:	deal and accept cardsList
 * Composition: contained in Game object; contain Card objects	
 * Is-a: LinkedList<Card>: to utilize the queue operation supported by LinkedList
 **/
  package com.stevenlesoft.risk.model;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Random;
//import java.util.Queue;

@SuppressWarnings("serial")
public class Deck extends LinkedList<Card>
{
   /** 
    * Instance Variables
    **/
	private Random generator;
	private int numOfSetTurnedIn; // keep track of number of decks turned in
	private int numArmiesRewarded;

	// build a deck from an arrayList of card
	public Deck(ArrayList<Card> cardList)
	{
		this.addAll(cardList);
		numOfSetTurnedIn = 0;
		numArmiesRewarded = 0;
		generator = new Random();
	}
	
	// build a deck according to the territories on a gameboard
	public Deck(GameBoard gameBoard)
	{
		// number of cards equals number of territories
		numOfSetTurnedIn = 0;
		numArmiesRewarded = 0;
		int count = 0;
		generator = new Random();
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
	}

   	/**
	 * Issues one card to be given to a player
	 * @return Card from the front of the deck
	 **/
	public Card deal()
	{
		return this.poll();
	}

	/**
	 * Used to add cardsList turned-in by players back to the deck
	 * @param  an ArrayList of Cards turned-in by a player
	 * @return number of armies rewarded
	 **/
	public int acceptCards( ArrayList<Card> set )
	{
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
