/**
 * CSCI 2120 Fall 2014
 * Risk Game Class DiceTest
 * Authors: Hung Le, Jeanne Vu 
 * Date: 10-21-2014
 **/

import junit.framework.TestCase;
import java.util.Random;

public class DiceTest extends TestCase
{
	//The objects to test
	private Dice dice;
	//Random generator to test the class in general manner
	Random generator;
	/**
	 * Setup
	 */
	protected void setUp ()
	{
		dice= new Dice();
		generator = new Random();
	} 

	/**
	 * method to be tested: int[] roll ( int attacking, int defending )
	 * Simulates the rolling of a set of dice
	 * @param the number of attacking dices, the number of defending dices
	 * @require Players roll 1-3 attacking dices and 1-2 defending dices
	 * @return an integer array with the results of the rolls
	 **/
	public void testRoll()
	{
		/**
		 * test case: 100 random sessions of rolling dices 
		 * @expected: the returned array contains the correct number of elements with each element falling in the range 1-6
		 */
		 
		//Variables to hold the number of dices to roll
		int attacking = 0;
		int defending = 0;
		
		

		for ( int i=0; i<100; i++)
		{
			// randomly generate the number of attacking dices to roll: 1-3
			attacking = generator.nextInt(3) + 1;
			// randomly generate the number of defending dices to roll: 1-2
			defending = generator.nextInt(2) + 1;

			// call the roll method passing in attacking and defending variables
			int[] results = dice.roll(attacking,defending);
			
			int count=0;
			for(int j=0;j<attacking+defending;j++)
			{
				assertTrue ( results[j] <= 6 && results[j] >= 1 ); // The elements should be an integer in the range 1-6
				count++;
			}

		}

	}

	/**
	 * method to be tested: int roll ()
	 * Simulates the rolling of a single dice
	 * @return an integer as the result of the roll
	 **/
	 public void testRollNoArgs()
	{
		/**
		 * test case: 100 random sessions of rolling 1 dice 
		 * @expected: the returned integer falls in the range 1-6
		 */
		 
		// call the roll method with no parameters
		int result = dice.roll();
		assertTrue ( result <= 6 && result >= 1 ); // The elements should be an integer in the range 1-6

	}

}// end class DiceTest
