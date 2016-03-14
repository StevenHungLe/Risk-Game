/**
 * CSCI 2120 Fall 2014
 * Risk Game Class PlayerTurnScreen
 * Authors: Hung Le, Jeanne Vu 
 * Date: 11-25-2014
 **
 * Class:	PlayerTurnScreen - the screen in which subScreens of a player's turn rotate
 * Composition: contained in RiskGUI
 **/

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.util.Observer;
import java.util.Observable;
import java.awt.Font;


public class PlayerTurnScreen extends JPanel implements Observer{

     private RiskGameEngine gameEngine;
     private GameGUI gameGUI;
     private RiskGame game;
	private GameBoard gameBoard;

     // Panel for Turn Information Bar
     private JPanel topPanel;
     private JPanel playerNamePanel;
     private JLabel playerNameDisplay;
     private JPanel phaseInfoPanel;
     private JLabel phaseInfoDisplay;
     
	// Panel for subScreens
	private JPanel subScreensJP;
	private CardLayout subScreensJPLayout;

     // Instance Variables for Turn-in-Card Screen
	private TurnInCardScreen turnInCardScreen;
	
	// Instance Variables for Place Armies Screen
	private PlaceArmiesScreen placeArmiesScreen;

     // Instance Variables for Attack Screen
	private AttackScreen attackScreen;

	// Instance Variables for Fortify Screen
	private FortifyScreen fortifyScreen;
	
	public PlayerTurnScreen(RiskGameEngine gameEngine, GameGUI gameGUI)
	{

		this.gameEngine = gameEngine;
		this.gameGUI = gameGUI;

		//mapModel = new Images();
		//gameMap = new ImageComponent();
		
		setLayout( new BorderLayout());

          Images riskBoard = new Images("RiskBoard.jpeg");
          Images cardCombinations = new Images("cardCombinations.jpg");

		// Top Panel: display player name and turn info
		topPanel = new JPanel(new BorderLayout());

          
		// SubScreens Panel
		subScreensJP = new JPanel(new CardLayout());
		subScreensJPLayout= (CardLayout) subScreensJP.getLayout();
		
		// Turn-in-Cards Screen
		turnInCardScreen = new TurnInCardScreen(gameEngine,cardCombinations);
		
		// Place Armies Screen
		placeArmiesScreen = new PlaceArmiesScreen(gameEngine,riskBoard);

		// Attack Screen
		attackScreen = new AttackScreen(gameEngine,riskBoard,gameGUI);

		// Fortify Screen
		fortifyScreen = new FortifyScreen(gameEngine,riskBoard);

		subScreensJP.add(turnInCardScreen,"turnInCardScreen");
		subScreensJP.add(placeArmiesScreen,"placeArmiesScreen");
		subScreensJP.add(attackScreen,"attackScreen");
		subScreensJP.add(fortifyScreen,"fortifyScreen");
		
		add( topPanel, BorderLayout.NORTH);
		add( subScreensJP, BorderLayout.CENTER);

	}

     // switch screens accordingly to the state of the game object
     public void update( Observable obs, Object state )
	{
	     if ( state == game.getState())
	     {
               System.out.println( "In update of Player Turn Screen" );
               
	          String subScreenName = game.getState();

               System.out.println( "New state of the game : " + subScreenName );
               
               if (state.equals("turnInCardScreen"))
               {
                    playerNameDisplay.setText("Active Player: "+game.getCurrentPlayer().getName()+".");
                    phaseInfoDisplay.setText("Step 1 < Turn in cards > : Choose a valid combination of three cards to trade for armies.");
                    gameGUI.setSize(670,600);
                    gameGUI.setLocationRelativeTo(null);
	               turnInCardScreen.update();
	          }
	          else if (state.equals("placeArmiesScreen"))
	          {
	               phaseInfoDisplay.setText("Step 2 < Deploy Armies > : Enter number of armies to deploy and the destination territory.");
	               gameGUI.setExtendedState(GameGUI.MAXIMIZED_BOTH); 
	               placeArmiesScreen.update();
	          }
	          else if (state.equals("attackScreen"))
	          {
	               phaseInfoDisplay.setText("Step 3 < Attack! > : Choose attacker,defender,num of dices to attack and num of dices to defend.");
	               attackScreen.update();
	          }
	          else if (state.equals("fortifyScreen"))
	          {
	               phaseInfoDisplay.setText("Step 4 < Fortify > : Choose departure and destination territory and the number of armies to move.");
	               fortifyScreen.update();
	          }
	               
               subScreensJPLayout.show(subScreensJP,subScreenName);
          }
	}

	/**
	 * load the neccessary data for the screen
	 * call method initialSetup of each subScreens to load data for them too
	 * add the player name display and turn Information JLabel
	 **/
	public void initialSetup()
	{
	     this.game = gameEngine.getGame();
	     this.gameBoard = game.getGameBoard();

          turnInCardScreen.initialSetup();
          
          placeArmiesScreen.initialSetup();
          game.addObserver(placeArmiesScreen);

          attackScreen.initialSetup();
          
          fortifyScreen.initialSetup();
          
		// Panel to display player's name
		playerNamePanel = new JPanel();
		playerNameDisplay = new JLabel("Active Player: "+this.game.getCurrentPlayer().getName()+".");
		playerNameDisplay.setFont( new Font ("Serif", Font.BOLD, 14) );
		playerNamePanel.add(playerNameDisplay);

          // Panel to display phase info
          phaseInfoPanel = new JPanel();
          phaseInfoDisplay = new JLabel("");;
          phaseInfoDisplay.setFont( new Font ("Serif", Font.BOLD, 14) );
          phaseInfoPanel.add(phaseInfoDisplay);
          
		topPanel.add(playerNamePanel, BorderLayout.NORTH);
		topPanel.add(phaseInfoPanel , BorderLayout.CENTER);

		phaseInfoDisplay.setText("Step 1 < Turn in cards > : Choose a valid combination of three cards to trade for armies.");
		gameGUI.setSize(670,600);
          gameGUI.setLocationRelativeTo(null);
		game.setState("turnInCardScreen");
	}
}
