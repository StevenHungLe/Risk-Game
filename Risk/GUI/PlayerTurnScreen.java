/**
 * CSCI 2120 Fall 2014
 * Risk Game Class PlayerTurnScreen
 * Authors: Hung Le, Jeanne Vu 
 * Date: 11-25-2014
 **
 * Class:	PlayerTurnScreen - the screen in which subScreens of a player's turn rotate
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

public class PlayerTurnScreen extends JPanel implements Observer{

     private RiskGameEngine gameEngine;
     private RiskGame game;
     private ArrayList<Player> playersList;
	private GameBoard gameBoard;

	private Images riskBoard;

     // Panel for Turn Information Bar
     private JPanel topPanel;
     private JLabel playerNameDisplay;
     
	// Panel for subScreens
	private JPanel subScreensJP;
	private CardLayout subScreensJPLayout;

	// Instance Variables for Place Armies Screen
	private PlaceArmiesScreen placeArmiesScreen;

     // Instance Variables for Attack Screen
	private AttackScreen attackScreen;
	
	public PlayerTurnScreen(RiskGameEngine gameEngine)
	{

		this.gameEngine = gameEngine;

		//mapModel = new Images();
		//gameMap = new ImageComponent();
		
		setLayout( new BorderLayout());

          riskBoard = new Images("RiskBoard.jpeg");

		// Player Name Label Panel
		topPanel = new JPanel(new FlowLayout());

		// SubScreens Panel
		subScreensJP = new JPanel(new CardLayout());
		subScreensJPLayout= (CardLayout) subScreensJP.getLayout();
		
		// Place Armies Screen
		placeArmiesScreen = new PlaceArmiesScreen(gameEngine,riskBoard);

		// Attack Screen
		attackScreen = new AttackScreen(gameEngine,riskBoard);
		
		subScreensJP.add(placeArmiesScreen,"placeArmiesScreen");
		subScreensJP.add(attackScreen,"attackScreen");
		
		add( topPanel, BorderLayout.NORTH);
		add( subScreensJP, BorderLayout.CENTER);

		subScreensJPLayout.first(subScreensJP);

	}

     public void update( Observable obs, Object state )
	{
	     if ( state == game.getState())
	     {
               System.out.println( "In update of Player Turn Screen" );
	          String subScreenName = game.getState();

               System.out.println( "New state of the game : " + subScreenName );

	          if (state.equals("placeArmiesScreen"))
	               placeArmiesScreen.update();
	          else if (state.equals("attackScreen"))
	               attackScreen.update();
	               
               subScreensJPLayout.show(subScreensJP,subScreenName);

               playerNameDisplay.setText("Active Player: "+game.getCurrentPlayer().getName()+".");
          }
          
	}

	
	public void initialSetup()
	{
	     this.game = gameEngine.getGame();
	     this.gameBoard = game.getGameBoard();
		this.playersList = game.getPlayersList();

          placeArmiesScreen.initialSetup();
          game.addObserver(placeArmiesScreen);

          attackScreen.initialSetup();
          game.addObserver(attackScreen);
          
		// JLabel to display player's name
		playerNameDisplay = new JLabel("Active Player: "+this.game.getCurrentPlayer().getName()+".");
		playerNameDisplay.setFont( new Font ("Serif", Font.BOLD, 16) );
		//playerNameDisplay.setHorizontalAlignment((int)Component.CENTER_ALIGNMENT);
		topPanel.add(playerNameDisplay);

	}






     /*
	private class NumArmiesDisplay extends JLabel implements Observer
	{
		PlayersList playersList;
	
		public NumArmiesDisplay(PlayersList playersList)
		{
			//super("has "+Integer.toString(playersList.getPlayer().getNumArmies())+" armies left.");
			super("");
			this.playersList = playersList;
		
		}

		public void update( Observable obs, Object obj)
		{
			this.setText( "Number of armies available: "+Integer.toString(playersList.getPlayer().getNumArmies()));
		}	
	}
	*/
	
}
