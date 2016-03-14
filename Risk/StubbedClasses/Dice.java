 /**
  * CSCI 2120 Fall 2014
  * Risk Game Class Dice
  * Authors: Hung Le, Jeanne Vu 
  * Date: 10-21-2014
  **/
  
import java.util.Random;

public class Dice
{
   // Instance Variables
	private int[] resultsArray;
	private int result;
	private Random generator;

   // Constructor
	public Dice()
	{
		this.resultsArray = new int[5];
		this.result = 0;
		generator = new Random();
	}

   // Method Headers
   	/**
	 * Simulates the rolling of a set of dice to resolve attacks
	 * @param attacking an integer representing how many dice to use for the attack(1-3)
	 * @param defending an integer to represent how many dice to use for the defense(1-2)
	 * @require Players roll 1-3 attacking dices and 1-2 defending dices
	 * @return an integer array with the results of the rolls
	 **/
	public int[] roll( int attacking, int defending )
	{
		return resultsArray;
	}

	/**
	 * Simulates the rolling of a dice to resolve initial set-up
	 * @return an integer as the result of the roll
	 **/
	public int roll()
	{
		return result;
	}
}
