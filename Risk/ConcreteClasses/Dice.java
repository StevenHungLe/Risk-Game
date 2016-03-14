/**
 * CSCI 2120 Fall 2014
 * Risk Game Class Dice
 * Authors: Hung Le, Jeanne Vu 
 * Date: 11-04-2014
 **
 * Class:	Dice specifies the dice used in the game
 * Roles:  	resolve attacks and initial set-up
 * Composition:	contained in Game object		
 **/
  
import java.util.Random;

public class Dice
{
	private int[] resultsArray;
	private int result;
	private Random generator;


	public Dice()
	{
		this.resultsArray = new int[5];
		this.result = 0;
		generator = new Random();
	}


   	/**
	 * Simulates the rolling of a set of dice to resolve attacks
	 * @param attacking an integer representing how many dice to use for the attack(1-3)
	 * @param defending an integer to represent how many dice to use for the defense(1-2)
	 * @require Players roll 1-3 attacking dices and 1-2 defending dices
	 * @return an integer array with the results of the rolls
	 **/
	public int[] roll( int attacking, int defending )
	{
		for(int i = 0; i < attacking ; i++)
		{
			resultsArray[ i ] = generator.nextInt(6) + 1;
		}

		for(int i = 0; i < defending; i++)
		{
			resultsArray[ attacking + i ] = generator.nextInt(6) + 1;
		}
	
		return resultsArray;
	}

	/**
	 * Simulates the rolling of one dice to resolve initial set-up
	 * @return an integer as the result of the roll
	 **/
	public int roll()
	{
		result = generator.nextInt(6) + 1;
	
		return result;
	}
}
