/**
 * CSCI 2120 Fall 2014
 * Risk Game Class InitSetupScreen
 * Authors: Hung Le, Jeanne Vu 
 * Date: 11-25-2014
 **
 * Class:	InitSetupScreen - the screen in which innitial set up of the game takes place
 * Composition: contained in GameGUI
 **/

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Observer;
import java.util.Observable;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

public class InitSetupScreen extends JPanel implements Observer{

     private RiskGameEngine gameEngine;
     private RiskGame game;
     private ArrayList<Player> playersList;
	private GameBoard gameBoard;
	
	private ImageComponent imageComponent;
	private Images riskBoard;

     // territory list panel
	private JPanel territoriesListPanel;
	private CardLayout terrListPaneLayout;
	private JComboBox emptyTerrListCB;
	private ArrayList<JComboBox> cbBoxesOfTerritories;
	private String[] arraysOfTerritoryNames;
	private String selectedTerritory;
	private ComboBoxHandler boxHandler;

     // top panel that shows players name and num armies available
     private JPanel topPanel;
     private JPanel titleBarJP;
     private JLabel screenTitleBar;
	private JPanel nameAndNumArmiesJP;
	private JLabel playerNameDisplay;
	private JLabel numArmiesDisplay;
	
	private JButton initPlaceArmiesButton;
	
	

	
	public InitSetupScreen(RiskGameEngine gameEngine){

		this.gameEngine = gameEngine;
		this.cbBoxesOfTerritories = new ArrayList<JComboBox>();

          // the game map
          riskBoard = new Images("RiskBoard.jpeg");
		imageComponent = new ImageComponent(riskBoard.getImage(1100,620));
		
		setLayout( new BorderLayout());

		initPlaceArmiesButton = new JButton("Claim Ownership!");
		//initPlaceArmiesButton.setSize(200,40);

		ButtonHandler buttonHandler = new ButtonHandler();
		initPlaceArmiesButton.addActionListener( buttonHandler );

          // the panel to contain combobox of territory
		territoriesListPanel = new JPanel(new CardLayout());
		terrListPaneLayout = (CardLayout) territoriesListPanel.getLayout();
		terrListPaneLayout.setVgap(295);

          // top panel that shows players name and num armies available
		topPanel = new JPanel();
		topPanel.setLayout(  new BoxLayout(topPanel,BoxLayout.Y_AXIS) );
		nameAndNumArmiesJP = new JPanel(new FlowLayout());
		titleBarJP = new JPanel();

		topPanel.add(titleBarJP);
		topPanel.add(nameAndNumArmiesJP);

	     // add them all to the screen
		add( topPanel, BorderLayout.NORTH);
		add( initPlaceArmiesButton , BorderLayout.SOUTH);
		add( imageComponent, BorderLayout.CENTER);
		add( territoriesListPanel, BorderLayout.WEST);

		boxHandler = new ComboBoxHandler();

	}
	

	// Handler for Buttons
	private class ButtonHandler implements ActionListener{
		public ButtonHandler(){

		}
          
		public void actionPerformed (ActionEvent event)
		{
			if(selectedTerritory != null)
			{
			     // call method deploy armies from player object
				game.getCurrentPlayer().deployArmies(1,gameBoard.getTerritoryByName(selectedTerritory));
				

				// when there are empty territories to occupy
				if ( !(gameBoard.getUnoccupiedTerritories().isEmpty()))
				{
					gameBoard.getTerritoryByName(selectedTerritory).setOccupant(game.getCurrentPlayer());
					

					// IF there is no more empty territory to occupy
					// set up phase 2: add armies to owned territory
					if( gameBoard.getUnoccupiedTerritories().size()<1)
					{
					     // change the text of the button to fit the new phase
						initPlaceArmiesButton.setText("Add Armies!");
						screenTitleBar.setText("Initial Set-up <Phase 2> : Choose an owned territory to add more armies!");

						// remove the empty territory combobox
						territoriesListPanel.remove (emptyTerrListCB);

						/**
						 * in a loop, initialize an ArrayList of ComboBoxes
						 * each of which contains the list of territory of each player
						 */
						for (int i=0; i < game.getNumPlayers();i++)
						{
                                   // create a temporary ComboBox
						     JComboBox temp =  (JComboBox) new JComboBox( game.getPlayer(i).getTerritoriesMap().keySet().toArray() );

						     // add it to the array list
					          cbBoxesOfTerritories.add(temp);

					          // add actionlistener for the newly created ComboBox
							cbBoxesOfTerritories.get(i).addActionListener(boxHandler);
							cbBoxesOfTerritories.get(i).setActionCommand("Choose A Territory To Place Armies" );

							// add it to the Panel
							territoriesListPanel.add( cbBoxesOfTerritories.get(i), Integer.toString(i));
						}	

					}
					
					// remove the occupied territory from the empty Terr ComboBox
					emptyTerrListCB.removeItem(selectedTerritory);
				}	

                    // switch to next player
				game.nextPlayer();

                    // if everybody is out of available armies to place
				if ( game.getCurrentPlayer().getNumArmies() <= 0 )
				{
				     // switch to the next screen
					gameEngine.setState("playerTurnScreen");
				}

				// if there is no empty territory to occupy ( now theres a list of territory for each player )
				// shows the ComboBox that fits the current player
				if ( gameBoard.getUnoccupiedTerritories().isEmpty())
				{
					terrListPaneLayout.show(territoriesListPanel,Integer.toString(game.getCurrentPlayerIndex()));
					cbBoxesOfTerritories.get(game.getCurrentPlayerIndex()).setSelectedIndex(0);
				}
				
			}
		}
	}


	// event handler for JComboBox

	private class ComboBoxHandler implements ActionListener{ 
		public ComboBoxHandler(){

		}

		public void actionPerformed (ActionEvent event){
			
			if(event.getActionCommand().equals("Choose A Territory To Place Armies" ))
			{
        			selectedTerritory = (String) ((JComboBox)event.getSource()).getSelectedItem();
        			System.out.println(selectedTerritory+" chosen");
        		}
		}
	}

     // update the player name and num armies to fits the current player
     public void update( Observable obs, Object obj)
	{
		this.playerNameDisplay.setText( "Player: "+this.game.getCurrentPlayer().getName()+".");
		this.numArmiesDisplay.setText( "Number of armies available: "+Integer.toString(game.getCurrentPlayer().getNumArmies())+".");
	}

	/**
	 * load necessary info for the screen
	 * add the player and numArmies display
	 * add the emptyTerrList ComboBox
	 **/
	public void initialSetup()
	{
	     this.game = gameEngine.getGame();
	     this.gameBoard = game.getGameBoard();
		this.playersList = game.getPlayersList();

          screenTitleBar = new JLabel ("Initial Set-up <Phase 1> : Choose an empty territory to claim ownership!");
          screenTitleBar.setFont( new Font ("Serif", Font.BOLD, 18) );
          titleBarJP.add(screenTitleBar);
		
          // JLabel to display player's name
		playerNameDisplay = new JLabel("Player: "+this.game.getCurrentPlayer().getName()+".");
		nameAndNumArmiesJP.add(playerNameDisplay);
		
          // JLabel to display the number or armies available for the current player
          
		numArmiesDisplay = new JLabel("Number of armies available: "+game.getCurrentPlayer().getNumArmies()+".");
		nameAndNumArmiesJP.add(numArmiesDisplay);

          // JComboBox to display the list of empty territories available to occupy
          emptyTerrListCB = new JComboBox ( gameBoard.getUnoccupiedTerritories().keySet().toArray() );
		territoriesListPanel.add(emptyTerrListCB,"unOccupied Territories");
		terrListPaneLayout.show(territoriesListPanel,"unOccupied Territories");
		emptyTerrListCB.addActionListener( boxHandler );
		emptyTerrListCB.setActionCommand("Choose A Territory To Place Armies" );

		game.notifyChanges();

	}
	
} // end class InitSetupScreen
