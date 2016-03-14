/**
 * CSCI 2120 Fall 2014
 * Risk Game Class AttackScreen
 * Authors: Hung Le, Jeanne Vu 
 * Date: 11-25-2014
 **
 * Class:	Fortify Screen - the subScreen in which the player move armies between theirs territories
 * Composition: contained in PlayerTurnScreen
 **/

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComponent;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class FortifyScreen extends JPanel{


	private RiskGameEngine gameEngine;
     private RiskGame game;
	private GameBoard gameBoard;
	
	private ImageComponent imageComponent;

	// Panel for attack selections
	private JPanel fortifySelectionsJP;

	private JComboBox departureCB;
	private JComboBox destinationCB;
	private JComboBox numArmiesToMoveCB;

	private String selectedDeparture;
	private String selectedDestination;
	private Territory departure;
	private Territory destination;
	private ComboBoxHandler boxHandler;

	private int numArmiesToMove = 0;
	
	private JButton moveArmiesButton;
	private JButton endButton;

	
	public FortifyScreen(RiskGameEngine gameEngine, Images riskBoard){

		this.gameEngine = gameEngine;
		this.imageComponent = new ImageComponent(riskBoard.getImage(1050,590));
		
		setLayout( new BorderLayout());


		// Panel for fortify selections
		fortifySelectionsJP = new JPanel();
		fortifySelectionsJP.setLayout(new BoxLayout(fortifySelectionsJP,BoxLayout.Y_AXIS));

          departureCB = new JComboBox();
          destinationCB = new JComboBox();
          numArmiesToMoveCB = new JComboBox();
	
		moveArmiesButton = new JButton("Move Armies");
		endButton = new JButton("End Your Turn");

		fortifySelectionsJP.add(departureCB);
		fortifySelectionsJP.add(destinationCB);
		fortifySelectionsJP.add(numArmiesToMoveCB);
		fortifySelectionsJP.add(moveArmiesButton);
		fortifySelectionsJP.add(endButton);
		
		this.add( fortifySelectionsJP, BorderLayout.WEST);
		this.add( imageComponent, BorderLayout.CENTER);

		// Handlers
		
		ButtonHandler buttonHandler = new ButtonHandler();
		moveArmiesButton.addActionListener( buttonHandler );
		endButton.addActionListener( buttonHandler );

		boxHandler = new ComboBoxHandler();
		departureCB.addActionListener(boxHandler);
		destinationCB.addActionListener(boxHandler);
		numArmiesToMoveCB.addActionListener(boxHandler);


	}

	
	// Handler for Buttons
	private class ButtonHandler implements ActionListener{
		public ButtonHandler(){

		}

		public void actionPerformed (ActionEvent event)
		{
				
			if(event.getSource() == moveArmiesButton && selectedDestination != null)
			{
			     if ( numArmiesToMove == 0 ) // do nothing if num armies is not selected
			          return;
			          
				System.out.println("Before, Departure has "+departure.getNumArmies());
                    System.out.println("Before, Destination has "+destination.getNumArmies());

			     // call method moveArmies from player object
				game.getCurrentPlayer().moveArmies(departure,destination,numArmiesToMove);

				System.out.println("After, Departure has "+departure.getNumArmies());
                    System.out.println("After, Destination has "+destination.getNumArmies());
	               
	               moveArmiesButton.setVisible(false);
				
			}
			else if( event.getSource() == endButton)
			{
			     // switch to next player and come back to turn in card screen
				game.nextPlayer();
				game.setState("turnInCardScreen");
			}
		}
	}

	
	// event handler for JComboBox

	private class ComboBoxHandler implements ActionListener{ 
		public ComboBoxHandler(){

		}

		public void actionPerformed (ActionEvent event){
		     if ( ((JComboBox)event.getSource()).getSelectedItem() == null )
		          return;
		     if ( event.getSource() == departureCB)
		     {
			     selectedDeparture = (String) ((JComboBox)event.getSource()).getSelectedItem();
			     departure = gameBoard.getTerritoryByName(selectedDeparture);

			     if (selectedDeparture != null)
			     {
			          /**
			           * update the destination and num armies to move combobox
			           **/
			          if ( destinationCB.getSelectedIndex() >= 0 )
			               destinationCB.setSelectedIndex(-1);
			          if ( numArmiesToMoveCB.getSelectedIndex() >= 0 )
			               numArmiesToMoveCB.setSelectedIndex(-1);

			          updateDestinationCB(gameBoard.getTerritoryByName(selectedDeparture).getNeighbors().toArray());
			          updateNumArmiesCB(gameBoard.getTerritoryByName(selectedDeparture).getNumArmies());
			               
			     }
			
		     }
		     else if ( event.getSource() == destinationCB )
		     {
			     selectedDestination = (String) ((JComboBox)event.getSource()).getSelectedItem();
			     destination = gameBoard.getTerritoryByName(selectedDestination);
		     }
		     else // numArmiesToMoveCB
		     {
		          numArmiesToMove = (int) ((JComboBox)event.getSource()).getSelectedItem();
		     }
		}
	}

     // loads necessary data for the screen
     public void initialSetup()
	{
          System.out.println("Game is Null");
          this.game = gameEngine.getGame();
          this.gameBoard = game.getGameBoard();

		System.out.println("innitial setup for fortify screen");
	}

     // update each time the screen is loaded
	public void update()
	{
	     System.out.println("updating the fortify screen for the current player");
		updateDepartureCB(game.getCurrentPlayer().getTerritoriesMap().keySet().toArray());
		numArmiesToMove = 0;
		moveArmiesButton.setVisible(true);
		
	}

     /**
	 * update the departure combobox
	 * param  a list of territory name
	 **/
	private void updateDepartureCB(Object[] territoriesList)
	{
	     System.out.println("departure selected index: "+departureCB.getSelectedIndex());
	     departureCB.removeAllItems();
	     for ( Object territoryName : territoriesList )
	     {
	          // only territory with at least 2 armies can be departure
	          if ( gameBoard.getTerritoryByName(territoryName.toString()).getNumArmies() >= 2 ) 
	               departureCB.addItem(territoryName.toString());
	     }
	     //departureCB.setEnabled(true);
	     
	}

     /**
	 * update the destination combobox
	 * param  a list of territory name
	 **/
	private void updateDestinationCB(Object[] territoriesList)
	{

	     System.out.println("destination selected index: "+destinationCB.getSelectedIndex());
	     //destinationCB.setEnabled(true);
	     destinationCB.removeAllItems();
	     System.out.println("Neighbor List:");
	     for ( Object territoryName : territoriesList )
          {
               System.out.println(""+territoryName.toString());
          }
          for ( Object territoryName : territoriesList )
          {
               // only ally territory can be destination
               if ( game.getCurrentPlayer().getTerritoriesMap().containsKey(territoryName.toString()) )
                    destinationCB.addItem(territoryName.toString());
          }

          destinationCB.setEnabled(true);
          System.out.println("destination selected index after: "+destinationCB.getSelectedIndex());
	}

     /**
	 * update the num armies to move combobox
	 * param  a list of territory name
	 **/
	private void updateNumArmiesCB(int numArmies)
	{

	     System.out.println("numArmies selected index: "+numArmiesToMoveCB.getSelectedIndex());

	     numArmiesToMoveCB.removeAllItems();

          System.out.println("num armies of departure: "+numArmies);
          for ( int i=1 ; i < numArmies ; i++)
          {
               numArmiesToMoveCB.addItem(Integer.valueOf(i));
          }

          numArmiesToMoveCB.setEnabled(true);
          System.out.println("numArmies selected index after: "+destinationCB.getSelectedIndex());
	}
	
} /////////////// end class FortifyScreen
