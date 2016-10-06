/**
 * CSCI 2120 Fall 2014
 * Risk Game Class AttackView
 * Authors: Hung Le, Jeanne Vu 
 * Date: 11-25-2014
 **
 * Class:	AttackView - the subView in which the player initiate attacks
 * Composition: contained in PlayerTurnView
 **/
package com.stevenlesoft.risk.view;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import com.stevenlesoft.risk.main.*;
import com.stevenlesoft.risk.model.*;

public class AttackView extends View{


	private GameGUI gameGUI;
	private RiskGameEngine gameEngine;
    private RiskGame game;
    private Player defendingPlayer;
	private GameBoard gameBoard;
	
	private ImageComponent imageComponent;

	// Panel for attack selections
	private JPanel attackSelectionsJP;

	private JComboBox attackerCB;
	private JComboBox defenderCB;

	private String selectedAttacker;
	private String selectedDefender;
	private Territory attacker;
	private Territory defender;

	private JComboBox numAttackDicesCB;
	private JComboBox numDefendDicesCB;
	private int numAttackDices;
	private int numDefendDices;

	private ComboBoxHandler boxHandler;
	
	private JButton attackButton;
	private JButton skipButton;

	private int[] battleResults;// holds the result of battles
	private int[] rollResults;  // holds the result of rolls
	
     // Panel for subViews
	private JPanel subViewsJP;
	private CardLayout subViewsJPLayout;
	
	// Panel for battle result
	private JPanel battleResultJP;
	private JLabel attackerLostLB;
	private JLabel defenderLostLB;
	private JLabel territoryConqueredLB;
	private boolean conqueredATerr; 
	private JButton continueAttack;

	
	public AttackView(RiskGameEngine gameEngine, Images riskBoard, GameGUI gameGUI)
	{

		this.gameEngine = gameEngine;
		this.gameGUI = gameGUI;
		this.imageComponent = new ImageComponent(riskBoard.getImage(1050,590));
		
		setLayout( new BorderLayout());

		// Panel for attack selections
		attackSelectionsJP = new JPanel();
		attackSelectionsJP.setLayout(new BoxLayout(attackSelectionsJP,BoxLayout.Y_AXIS));

          // ComboBoxes for attack/defender
          attackerCB = new JComboBox();
          defenderCB = new JComboBox();

          // ComboBoxes for number of dices to attack/defend
		numAttackDicesCB = new JComboBox( new Integer[]{1,2,3});
		numDefendDicesCB = new JComboBox( new Integer[] {1,2});

	     // Buttons
		attackButton = new JButton("ATACKKKK!");
		skipButton = new JButton("Skip Attack");

          // Add all components to the attack selections JPanel
		attackSelectionsJP.add(attackerCB);
		attackSelectionsJP.add(defenderCB);
		attackSelectionsJP.add(numAttackDicesCB);
		attackSelectionsJP.add(numDefendDicesCB);
		attackSelectionsJP.add(attackButton);
		attackSelectionsJP.add(skipButton);

		
		// Panel for battle results
		battleResultJP = new JPanel();
		battleResultJP.setLayout(new BoxLayout(battleResultJP,BoxLayout.Y_AXIS));

          // How many armies attacker lost
		attackerLostLB = new JLabel();
		attackerLostLB.setFont( new Font ("Serif", Font.BOLD, 18) );
		// How many armies defender lost
	     defenderLostLB = new JLabel();
	     defenderLostLB.setFont( new Font ("Serif", Font.BOLD, 18) );
	     // Did attecker sucessfully conquer defender?
	     territoryConqueredLB = new JLabel();
	     territoryConqueredLB.setFont( new Font ("Serif", Font.BOLD, 18) );
	     conqueredATerr = false;
	     // Button to continue attack
	     continueAttack = new JButton("Continue Attacking!");
	     
          // Add all components to the attack results JPanel
	     battleResultJP.add(attackerLostLB);
	     battleResultJP.add(defenderLostLB);
	     battleResultJP.add(territoryConqueredLB);
	     battleResultJP.add(continueAttack);


		// SubViews Panel: contains gameMap and battle result panel
		subViewsJP = new JPanel(new CardLayout());
		subViewsJPLayout= (CardLayout) subViewsJP.getLayout();

		subViewsJP.add(imageComponent,"gameMap");
		subViewsJP.add(battleResultJP,"battleResultJP");
		subViewsJPLayout.show(subViewsJP,"gameMap");


		// Add the two main panel to the attack view
		this.add( attackSelectionsJP, BorderLayout.WEST);
		this.add( subViewsJP, BorderLayout.CENTER);

		// Handlers
		ButtonHandler buttonHandler = new ButtonHandler();
		attackButton.addActionListener( buttonHandler );
		skipButton.addActionListener( buttonHandler );
		continueAttack.addActionListener( buttonHandler );
		
		boxHandler = new ComboBoxHandler();
		attackerCB.addActionListener(boxHandler);
		defenderCB.addActionListener(boxHandler);
		numAttackDicesCB.addActionListener(boxHandler);
		numDefendDicesCB.addActionListener(boxHandler);


	}


	// Handler for Buttons
	private class ButtonHandler implements ActionListener{
		public ButtonHandler(){

		}

		public void actionPerformed (ActionEvent event)
		{
				
			if(event.getSource() == attackButton && selectedDefender != null) // attack button
			{
			     // Print info to view for monitoring purpose
				System.out.println("Before, Attacker has "+attacker.getNumArmies());
                    System.out.println("Before, Defender has "+defender.getNumArmies());

                    // Call method from class game to roll the dices
                    rollResults = game.getRollResults(numAttackDices,numDefendDices);

                    // Print the roll result to view for monitoring purpose
                    for ( int k: rollResults )
			          System.out.print("["+k+"]");
			     System.out.println();

			     defendingPlayer = defender.getOccupant(); // to later determine if the player is defeated

			     // call method battle from object Player to resolve battle
			     // the results are stored in an array of size 2 
				battleResults = game.getCurrentPlayer().battle(attacker,defender,numAttackDices,numDefendDices,rollResults);

				// set text to display the result
				attackerLostLB.setText("Attacker lost "+battleResults[0]+" armies!");
	               defenderLostLB.setText("Defender lost "+battleResults[1]+" armies!");

                    // if the attacker has conquered the territory
	               if ( game.getCurrentPlayer().getTerritoriesMap().containsKey(selectedDefender)) 
	               {
	                    conqueredATerr = true ; // set flag to receive a card later
	                    
	                    // set text to declare the result of the battle
	                    territoryConqueredLB.setText("Attacker has conquered "+selectedDefender+"!!!");

	                    // call private method to update the attacker ComboBox
	                    // since the player's territory list has changed
	                    updateAttackerCB(game.getCurrentPlayer().getTerritoriesMap().keySet().toArray());

                         // if the player has conquered the last territory in the continent
                         // set new occupant for the continent
	                    if ( isContinentConquered(defender) ) 
	                         gameBoard.getContinentByName(defender.getContinentName()).setOccupant( game.getCurrentPlayer() );

	                    // if the defending player is defeated
	                    // remove the player from the game's playersList     
	                    if ( isPlayerDefeated(defendingPlayer) )
	                         game.removePlayer(defendingPlayer);

                         // if all other players are eliminated
	                    if ( game.getPlayersList().size() == 1 ) 
	                    {
	                         // Print the name of the winner and terminate the program
	                         System.out.println("Game Over! The Winner is: "+game.getCurrentPlayer().getName()); 
	                         System.exit(0);
	                    }
    
	               }

                    // display the result
	               subViewsJPLayout.show(subViewsJP,"battleResultJP");
	               gameGUI.setSize(650,500);
	               gameGUI.setLocationRelativeTo(null);
				
			}
			
			else if( event.getSource() == skipButton) // skip button
			{
                    
			     if ( conqueredATerr == true ) // if the player conquered a territory
			     {
			          // get a new card from the deck
			          game.getCurrentPlayer().getHand().takeACard( game.getDeck().deal() );
			     }

			     // switch to fortifyView after readjust frame size
			     gameGUI.setExtendedState(GameGUI.MAXIMIZED_BOTH); 
			     game.setState("fortifyView");
			}
			
			else if( event.getSource() == continueAttack ) // continue attack
			{
			     // reset the territory-conquered declaration
			     territoryConqueredLB.setText("");
			     // update the attacker ComboBox
			     updateAttackerCB(game.getCurrentPlayer().getTerritoriesMap().keySet().toArray());
			     // display the game map
				subViewsJPLayout.show(subViewsJP,"gameMap");
				gameGUI.setExtendedState(GameGUI.MAXIMIZED_BOTH); 
			}
		}
	}

	
	// event handler for JComboBox

	private class ComboBoxHandler implements ActionListener{ 
		public ComboBoxHandler(){

		}

		public void actionPerformed (ActionEvent event){
		     // if nothing is selected, do nothing
		     if ( ((JComboBox)event.getSource()).getSelectedItem() == null )
		          return;

		     
		     if ( event.getSource() == attackerCB) // attacker combobox
		     {
			     selectedAttacker = (String) ((JComboBox)event.getSource()).getSelectedItem();
			     attacker = gameBoard.getTerritoryByName(selectedAttacker);
			     
			     if (selectedAttacker != null)
			     {
			          // reset the defender combobox selected index
			          if ( defenderCB.getSelectedIndex() >= 0 )
			               defenderCB.setSelectedIndex(-1);
                         // update the defender combobox accordingly to the attacker
			          updateDefenderCB(gameBoard.getTerritoryByName(selectedAttacker).getNeighbors().toArray());

			          // reset num dices comboboxes
			          resetNumDicesCB();

			          // update num dices to attack combobox
			          while ( numAttackDicesCB.getItemCount() >= attacker.getNumArmies() )
			                numAttackDicesCB.removeItemAt( numAttackDicesCB.getItemCount() - 1 );
			          numAttackDicesCB.setSelectedIndex(0);
			               
			     }
			
		     }
		     
		     else if ( event.getSource() == defenderCB ) // defender combobox
		     {
			     selectedDefender = (String) ((JComboBox)event.getSource()).getSelectedItem();
			     defender = gameBoard.getTerritoryByName(selectedDefender);
			     
			     if (selectedDefender != null)
			     {
			          resetNumDicesCB();

			          // update the num dices to defend comboboxes
			          if ( defender.getNumArmies() < 2)
			                numDefendDicesCB.removeItemAt(1);
			          numDefendDicesCB.setSelectedIndex(0);
			     }
			     
			    
		     }
		     else if ( event.getSource() == numAttackDicesCB ) // num dices to attack
		     {
			     numAttackDices = (int) ((JComboBox)event.getSource()).getSelectedItem();
			     System.out.println(numAttackDices+" dices chosen to attack");
		     }
		     else if ( event.getSource() == numDefendDicesCB ) // num dices to defend
		     {
			     numDefendDices = (int) ((JComboBox)event.getSource()).getSelectedItem();
			     System.out.println(numDefendDices+" dices chosen to defend");
		     }
		}
	}

     /**
	 * loads the necessary data for class attack view
	 **/
	@Override
     public void loadData()
	{
        this.game = gameEngine.getGame();
        this.gameBoard = game.getGameBoard();
		System.out.println("innitial setup for attack view");
	}

     /**
	 * update every time the view is switched to
	 **/
	public void update()
	{
	     System.out.println("updating the attack view for the current player");
		updateAttackerCB(game.getCurrentPlayer().getTerritoriesMap().keySet().toArray());
		conqueredATerr = false;
	}

     /**
	 * Checks if the player has conquered a continent
	 * param  the territory that the player has just conquered
	 * return a boolean value true or false
	 **/
     public boolean isContinentConquered( Territory territory)
	{
	     Continent continent = gameBoard.getContinentByName(territory.getContinentName());

	     for ( Territory terr : continent.getTerritoriesMap().values() )
	     {    
	          // if there is any territory the played has not owned, return false
	          if ( !(game.getCurrentPlayer().getTerritoriesMap().containsValue( terr ) ) )
	               return false;
	     }
          // the player owns every territory in the continent
	     return true;
	}
	
     /**
	 * Checks if this player is defeated
	 * param  the player object to be checked
	 **/
	public boolean isPlayerDefeated( Player lostPlayer)
	{
	     // checks if the player has any territory left
	     if ( lostPlayer.getTerritoriesMap().isEmpty() )
	          return true;
	     else
	          return false;
	}

	/**
	 * reset the number of dices combobox
	 **/
     private void resetNumDicesCB()
     {
          while ( numAttackDicesCB.getItemCount() < 3 ) // reset the numAttackDices JComboBox
		     numAttackDicesCB.addItem( Integer.valueOf(numAttackDicesCB.getItemCount()+1) );
		if ( numDefendDicesCB.getItemCount() == 1 ) // reset the numAttackDices JComboBox
		     numDefendDicesCB.addItem( Integer.valueOf(2) );    
     }

     /**
	 * update the attacker combobox
	 * param  a list of territory name
	 **/
	private void updateAttackerCB(Object[] territoriesList)
	{
	     attackerCB.removeAllItems();
	     
	     for ( Object territoryName : territoriesList )
	     {
	          // only territory with at least 2 armies can attack
	          if ( gameBoard.getTerritoryByName(territoryName.toString()).getNumArmies() >= 2 ) 
	               attackerCB.addItem(territoryName.toString());
	     }
	     
	}

     /**
	 * update the defender combobox
	 * param  a list of territory name
	 **/
	private void updateDefenderCB(Object[] territoriesList)
	{

	     defenderCB.removeAllItems();
	     
          for ( Object territoryName : territoriesList )
          {
               // add all the neighbors that is not an ally
               if ( !(game.getCurrentPlayer().getTerritoriesMap().containsKey(territoryName.toString())) )
                    defenderCB.addItem(territoryName.toString());
          }
          defenderCB.setEnabled(true);
	}
	
} ///////////////////////////////////// end class AttackView
