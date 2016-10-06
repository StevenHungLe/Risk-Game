/**
 * CSCI 2120 Fall 2014
 * Risk Game Class PlayerTurnView
 * Authors: Hung Le, Jeanne Vu 
 * Date: 11-25-2014
 **
 * Class:	PlayerTurnView - the view in which subViews of a player's turn rotate
 * Composition: contained in RiskGUI
 **/
package com.stevenlesoft.risk.view;
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
import com.stevenlesoft.risk.main.*;
import com.stevenlesoft.risk.model.*;
public class PlayerTurnView extends View implements Observer{

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
     
	// Panel for subViews
	private JPanel subViewsJP;
	private CardLayout subViewsJPLayout;

     // Instance Variables for Turn-in-Card View
	private TurnInCardView turnInCardView;
	
	// Instance Variables for Place Armies View
	private PlaceArmiesView placeArmiesView;

     // Instance Variables for Attack View
	private AttackView attackView;

	// Instance Variables for Fortify View
	private FortifyView fortifyView;
	
	public PlayerTurnView(RiskGameEngine gameEngine, GameGUI gameGUI)
	{

		this.gameEngine = gameEngine;
		this.gameGUI = gameGUI;

		//mapModel = new Images();
		//gameMap = new ImageComponent();
		
		setLayout( new BorderLayout());

          Images riskBoard = new Images("src/resources/RiskBoard.jpg");
          Images cardCombinations = new Images("src/resources/cardCombinations.jpg");

		// Top Panel: display player name and turn info
		topPanel = new JPanel(new BorderLayout());

          
		// SubViews Panel
		subViewsJP = new JPanel(new CardLayout());
		subViewsJPLayout= (CardLayout) subViewsJP.getLayout();
		
		// Turn-in-Cards View
		turnInCardView = new TurnInCardView(gameEngine,cardCombinations);
		
		// Place Armies View
		placeArmiesView = new PlaceArmiesView(gameEngine,riskBoard);

		// Attack View
		attackView = new AttackView(gameEngine,riskBoard,gameGUI);

		// Fortify View
		fortifyView = new FortifyView(gameEngine,riskBoard);

		subViewsJP.add(turnInCardView,"turnInCardView");
		subViewsJP.add(placeArmiesView,"placeArmiesView");
		subViewsJP.add(attackView,"attackView");
		subViewsJP.add(fortifyView,"fortifyView");
		
		add( topPanel, BorderLayout.NORTH);
		add( subViewsJP, BorderLayout.CENTER);

	}

     // switch views accordingly to the state of the game object
     public void update( Observable obs, Object state )
	{
	     if ( state == game.getState())
	     {
               System.out.println( "In update of Player Turn View" );
               
	          String subViewName = game.getState();

               System.out.println( "New state of the game : " + subViewName );
               
               if (state.equals("turnInCardView"))
               {
                    playerNameDisplay.setText("Active Player: "+game.getCurrentPlayer().getName()+".");
                    phaseInfoDisplay.setText("Step 1 < Turn in cards > : Choose a valid combination of three cards to trade for armies.");
                    gameGUI.setSize(670,600);
                    gameGUI.setLocationRelativeTo(null);
	               turnInCardView.update();
	          }
	          else if (state.equals("placeArmiesView"))
	          {
	               phaseInfoDisplay.setText("Step 2 < Deploy Armies > : Enter number of armies to deploy and the destination territory.");
	               gameGUI.setExtendedState(GameGUI.MAXIMIZED_BOTH); 
	               placeArmiesView.update();
	          }
	          else if (state.equals("attackView"))
	          {
	               phaseInfoDisplay.setText("Step 3 < Attack! > : Choose attacker,defender,num of dices to attack and num of dices to defend.");
	               attackView.update();
	          }
	          else if (state.equals("fortifyView"))
	          {
	               phaseInfoDisplay.setText("Step 4 < Fortify > : Choose departure and destination territory and the number of armies to move.");
	               fortifyView.update();
	          }
	               
               subViewsJPLayout.show(subViewsJP,subViewName);
          }
	}

	/**
	 * load the neccessary data for the view
	 * call method loadData of each subViews to load data for them too
	 * add the player name display and turn Information JLabel
	 **/
    @Override
	public void loadData()
	{
	     this.game = gameEngine.getGame();
	     this.gameBoard = game.getGameBoard();

          turnInCardView.loadData();
          
          placeArmiesView.loadData();
          game.addObserver(placeArmiesView);

          attackView.loadData();
          
          fortifyView.loadData();
          
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
		game.setState("turnInCardView");
	}
}
