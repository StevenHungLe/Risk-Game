/**
 * CSCI 2120 Fall 2014
 * Risk Game Class Hand
 * Authors: Hung Le, Jeanne Vu 
 * Date: 11-04-2014
 **
 * Class:	Hand specifies a hand of cards held by a specific player
 * Roles:	accept and trade in cards
 * Composition: contained in Player object
 *		contain Card objects	
 **/

import java.util.ArrayList;

public class Hand extends ArrayList<Card>
{
	private ArrayList<Card> turnedInCards;

	public Hand()
	{
		// a hand should start out holding no cards
		super(6);         // 6 is maximum number of cards allowed on hand
		this.turnedInCards = new ArrayList<Card>(3); // 3 is number of cards in a set to be turned in
	}

   // Method Headers
	/**
	 * Used to receive a Card into a Player's hand
	 * @param newCard the new Card being given to the player's Hand
	 **/
	public void takeACard( Card newCard )
	{
		this.add( newCard );
		System.out.println("Received a new card");
	}
	
	
	/**
	 * Used to remove a set of cards from the player's hand
	 * @param set an array of integers indicating the indices of the cards to turn-in
	 * @return an ArrayList of the Cards being turned-in
	 **/
	public ArrayList<Card> turnInCards( int[] set )
	{
		turnedInCards.clear();

          // call helper method sortArray to put indices in descending order
          // to easily remove items at desired indices
		set = sortArray( set );

		// add the cards to the list to be turned in
		for( int i : set)
		{
			turnedInCards.add( this.get(i) );
		}

          // remove the turned in cards from the hand
		for( int i=0 ; i < 3; i++ )
		{
			this.remove( set[i] );
		}
		
		return turnedInCards;
	}

	/**
	 * Helper method: Called by method turnInCards() to sort the card indices arrays
	 * @param: int[] array: the array of card indices
	 * @return: int[] : a sorted array of card indices in descending order
	 **/
	private int[] sortArray(int[] array )
	{
		int[] tempArray = new int[3];
		// outer loop to assign values in an array in descending order
		for(int i=0; i <= 2;i++)
		{
			int greatest = array[0];
			int greatestIndex = 0;
			//inner loop to find the greatest value
			for( int j = 0; j <= 2; j++)
			{
				if( greatest < array[j])
				{
					greatest = array[j];
					greatestIndex=j;
				}	
			}
			// set the value at the greatest index = 0 to avoid duplication
			array[greatestIndex]=0;
			// assign greatest value to the array in descenging order
			tempArray[i]=greatest;
		}
		return tempArray;
	}
} ///////////////////////////////////// end class Hand
