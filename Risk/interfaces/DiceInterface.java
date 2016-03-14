/**
 * Name: 	Hung Le
 * Professor: 	Mr. Johnathan Redmann
 * Course: 	CSCI2120
 * Project:	Risk-The World Domination Game
 **
 * Interface:	DiceInterface specifies the dice used in the game
 * Uses:  	resolve attacks and initial set-up
 * Composition:	contained in Game object		
 **/



public interface DiceInterface
{

	/**
	 * Simulates the rolling of a set of dice to resolve attacks
	 * @param attacking an integer representing how many dice to use for the attack(1-3)
	 * @param defending and integer to represent how many dice to use for the defense(1-2)
	 * @return an integer array with the results of the rolls
	 **/
	int[] roll( int attacking, int defending );

	/**
	 * Simulates the rolling of a dice to resolve innitial set-up
	 * @return an integer as the result of the roll
	 **/
	int roll();

}// end Dice interface 
