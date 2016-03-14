/**
 * CSCI 2120 Fall 2014
 * Risk Game Class RiskGUI
 * Authors: Hung Le, Jeanne Vu 
 * Date: 11-25-2014
 **
 * Class:	RiskGUI - the main JFrame of the game - controls the flow of the game
 * Composition: contains all the screens of the game
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

public class RiskGUI extends JFrame{
	// The main JPanel and its Layout
	private JPanel mainPanel;
	private CardLayout mainLayout;

	// Instance Variables for Start Screen
	private StartScreen startScreen;

	// Instance Variables for New Game Screen
	private NewGameScreen newGameScreen;
	private String gameName = "";
	private int numPlayers = 0;
	private PlayersList playersList;

	// Instance Variables for Players' Name Screen
	private PlayersNameScreen playersNameScreen;
	
	// Instance Variables for Init Setup Screen
	private InitSetupScreen initSetupScreen;
	private GameBoard gameBoard;
	private ArrayList<JComboBox> cbBoxesOfTerritories;
	private JComboBox territoriesListCB;

	// Instance Variables for Player Turn Screen
	private PlayerTurnScreen playerTurnScreen;
	
	// Instance Variables for other Screens
	private JPanel playersTurn;
	private JPanel turnInCard;
	private JPanel placeArmies;
	private JPanel attack;
	private JPanel attackResults;
	private JPanel fortify;
	private JPanel loadSavedGame;

	
	public RiskGUI(){
		super("Risk Game");

		mainPanel = new JPanel( new CardLayout());
		mainLayout = (CardLayout)mainPanel.getLayout();
		
		add(mainPanel);
		
		// Start Screen
		startScreen = new StartScreen(this,mainPanel);

		// New Game Screen
		newGameScreen = new NewGameScreen(this,mainPanel);


		// Players' Name Screen
		playersList = new PlayersList();
		playersNameScreen = new PlayersNameScreen(this,mainPanel,playersList);


		// Initial SetUp Screen
		gameBoard = new GameBoard();
		// initialize the ArrayList that holds the Territories owned by players
		cbBoxesOfTerritories = new ArrayList<JComboBox>();
		initSetupScreen = new InitSetupScreen(this,mainPanel,gameBoard,playersList,cbBoxesOfTerritories);

		// Player Turn Screen
		playerTurnScreen = new PlayerTurnScreen(this,mainPanel,gameBoard,playersList,cbBoxesOfTerritories);


		// add the "cards" to the mainPanel with CardLayout
		mainPanel.add(startScreen, "Start Screen");
		mainPanel.add(newGameScreen, "New Game");
		mainPanel.add(playersNameScreen, "Players' Name");
		mainPanel.add(initSetupScreen, "Init Setup Screen");
		mainPanel.add(playerTurnScreen, "Player Turn Screen");

		
		// Start off showing the Start Screen
		setLayoutGaps(300,500);
		mainLayout.show(mainPanel,"Start Screen");
	}
	
	
	public static void main(String[] args)
	{
		RiskGUI RiskGame = new RiskGUI();
		RiskGame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		//RiskGame.setSize(1000, 700);
		RiskGame.setExtendedState(RiskGame.MAXIMIZED_BOTH);  
		RiskGame.setVisible(true);
	}


// Helper Methods for components to access and make changes to the main Program
	// getter method

	// get the players list
	public PlayersList getPlayersList()
	{
		return this.playersList;
	}

	public int getNumPlayers()
	{
		return this.numPlayers ;
	}
	
	// Set the name for the game
	public void setGameName(String gameName)
	{
		this.gameName = gameName;
	}

	// Set the number of players and initialzie the list of players
	public void setNumPlayers(int numPlayers)
	{
		this.numPlayers = numPlayers;
	}

	// Set the number of players and initialzie the list of players
	public void setLayoutGaps(int Vgap,int Hgap)
	{
		mainLayout.setVgap(Vgap);
		mainLayout.setHgap(Hgap);
	}

	// Switch between screens & Make sure the switch are error-free
	public void switchScreen(String screenName)
	{
		if( screenName.equals("Player Turn Screen"))
		{
			System.out.println("Im here in the switch screen method");
			playersList.setCurrentPlayerIndex(0);
			playerTurnScreen.switchScreen("");
			mainLayout.show(mainPanel,"Player Turn Screen");
		}
	}

	
}
