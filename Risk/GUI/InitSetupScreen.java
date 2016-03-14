/**
 * CSCI 2120 Fall 2014
 * Risk Game Class InitSetupScreen
 * Authors: Hung Le, Jeanne Vu 
 * Date: 11-25-2014
 **
 * Class:	InitSetupScreen - the screen in which innitial set up of the game takes place
 * Composition: contained in RiskGUI
 **/

 import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComponent;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observer;
import java.util.Observable;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.ArrayList;

public class InitSetupScreen extends JPanel implements Observer{

     private RiskGameEngine gameEngine;
     private RiskGame game;
     private ArrayList<Player> playersList;
	private GameBoard gameBoard;
	
	private ImageComponent imageComponent;
	private Images riskBoard;

	private JPanel territoriesListPanel;
	private CardLayout terrListPaneLayout;
	private JComboBox emptyTerrListCB;
	private ArrayList<JComboBox> cbBoxesOfTerritories;
	private String[] arraysOfTerritoryNames;
	private String selectedTerritory;
	private ComboBoxHandler boxHandler;

     private JPanel topPanel;
     private JLabel screenTitleBar;
	private JPanel nameAndNumArmiesJP;
	private JPanel titleBarJP;
	
	private JButton initPlaceArmiesButton;
	
	private JLabel playerNameDisplay;
	private JLabel numArmiesDisplay;

	
	public InitSetupScreen(RiskGameEngine gameEngine){

		this.gameEngine = gameEngine;
		this.cbBoxesOfTerritories = new ArrayList<JComboBox>();

          riskBoard = new Images("RiskBoard.jpeg");
		imageComponent = new ImageComponent(riskBoard.getImage(1100,620));
		
		setLayout( new BorderLayout());

		initPlaceArmiesButton = new JButton("Claim Ownership!");
		//initPlaceArmiesButton.setSize(200,40);

		ButtonHandler buttonHandler = new ButtonHandler();
		initPlaceArmiesButton.addActionListener( buttonHandler );

		territoriesListPanel = new JPanel(new CardLayout());
		terrListPaneLayout = (CardLayout) territoriesListPanel.getLayout();
		terrListPaneLayout.setVgap(295);

		topPanel = new JPanel();
		topPanel.setLayout(  new BoxLayout(topPanel,BoxLayout.Y_AXIS) );
		nameAndNumArmiesJP = new JPanel(new FlowLayout());
		titleBarJP = new JPanel();

		topPanel.add(titleBarJP);
		topPanel.add(nameAndNumArmiesJP);
	
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

		public void actionPerformed (ActionEvent event){
				
			if(selectedTerritory != null)
			{
				
				game.getCurrentPlayer().updateNumArmies(-1);
				gameBoard.getTerritoryByName(selectedTerritory).updateNumArmies(1);
				
				
				if ( !(gameBoard.getUnoccupiedTerritories().isEmpty()))
				{
					gameBoard.getTerritoryByName(selectedTerritory).setOccupant(game.getCurrentPlayer());
					
					
					if( gameBoard.getUnoccupiedTerritories().size()<1)
					{
						//terrListPaneLayout.removeLayoutComponent( emptyTerrListCB );
						initPlaceArmiesButton.setText("Add Armies!");
						screenTitleBar.setText("Initial Set-up <Phase 2> : Choose an owned territory to add more armies!");
						territoriesListPanel.remove (emptyTerrListCB);
						for (int i=0; i < game.getNumPlayers();i++)
						{
							System.out.println("I am here and I am at index i = "+i);

							//emptyTerrListCB = new JComboBox ( gameBoard.getUnoccupiedTerritories().keySet().toArray() );
						     JComboBox temp =  (JComboBox) new JComboBox( game.getPlayer(i).getTerritoriesMap().keySet().toArray() );
					          cbBoxesOfTerritories.add(temp);
					          System.out.println("Size of the arrayList is "+cbBoxesOfTerritories.size());
							cbBoxesOfTerritories.get(i).addActionListener(boxHandler);
							cbBoxesOfTerritories.get(i).setActionCommand("Choose A Territory To Place Armies" );
							
							territoriesListPanel.add( cbBoxesOfTerritories.get(i), Integer.toString(i));
						}	
						//terrListPaneLayout.show(territoriesListPanel,Integer.toString(playersList.getCurrentPlayerIndex()));
					}
					emptyTerrListCB.removeItem(selectedTerritory);
					//emptyTerrListCB.setSelectedItem(null);
				}	

				game.nextPlayer();

				if ( game.getCurrentPlayer().getNumArmies() <= 0 )
				{
					//riskGUI.synchronize();
					System.out.println("Im here in the switch screen if clause");
					gameEngine.setState("playerTurnScreen");	
					//mainLayout.show(mainPanel,"Players' Name");
				}
				if ( gameBoard.getUnoccupiedTerritories().isEmpty())
				{
					terrListPaneLayout.show(territoriesListPanel,Integer.toString(game.getCurrentPlayerIndex()));
					//terrListPaneLayout.next(territoriesListPanel);
				}

				
				
			}
		}
	}


	// event handler for JComboBox

	private class ComboBoxHandler implements ActionListener{ 
		public ComboBoxHandler(){

		}

		public void actionPerformed (ActionEvent event){
			
			if(event.getActionCommand().equals("Choose A Territory To Place Armies" )){
        			selectedTerritory = (String) ((JComboBox)event.getSource()).getSelectedItem();
        			System.out.println(selectedTerritory+" chosen");
        		}
			JComboBox source = (JComboBox) event.getSource();
        		if(source.getItemCount() == 1 )
        		{
        			terrListPaneLayout.removeLayoutComponent( source);
			}
		}
	}

     public void update( Observable obs, Object obj)
	{
		this.playerNameDisplay.setText( "Player: "+this.game.getCurrentPlayer().getName()+".");
		this.numArmiesDisplay.setText( "Number of armies available: "+Integer.toString(game.getCurrentPlayer().getNumArmies())+".");
	}
	
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

          // JComboBox to display the list of territories available to occupy
          emptyTerrListCB = new JComboBox ( gameBoard.getUnoccupiedTerritories().keySet().toArray() );
		territoriesListPanel.add(emptyTerrListCB,"unOccupied Territories");
		terrListPaneLayout.show(territoriesListPanel,"unOccupied Territories");
		emptyTerrListCB.addActionListener( boxHandler );
		emptyTerrListCB.setActionCommand("Choose A Territory To Place Armies" );

		game.notifyChanges();
	}
/*
	private class ImageComponent extends JComponent
	{
		private Image image;
		public ImageComponent(){
			image = mapModel.getImage();
			repaint();
		}

		public void paintComponent(Graphics g){
			g.drawImage(image, 0, 0, this);
		}
	}
*/	
}
