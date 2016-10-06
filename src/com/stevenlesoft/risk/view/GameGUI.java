package com.stevenlesoft.risk.view;

import com.stevenlesoft.risk.main.*;
import com.stevenlesoft.risk.model.RiskGame;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.util.Observer;
import java.util.Observable;
import java.awt.CardLayout;
import java.awt.Container;

@SuppressWarnings("serial")
public class GameGUI extends JFrame implements Observer
{
	// The main content panel and layout 
	private Container mainPanel;
	private CardLayout mainLayout;

	// The different views used in the game
	private View startView;
	private View newGameView;
    private View createPlayersView;
    private View initSetupView;
    private View playerTurnView;
    
    // The game object and game engine
    private RiskGameEngine gameEngine;
    private RiskGame game;

    /**
     * Constructor for GameGUI
     * create the component views and add them to the main panel
     * @param gameEngine
     */
	public GameGUI( RiskGameEngine gameEngine )
	{
         this.gameEngine = gameEngine;
	     this.mainPanel = this.getContentPane();
	     mainPanel.setLayout( new CardLayout() );
	     mainLayout = (CardLayout) mainPanel.getLayout();
	
         // Initialize the views
         startView 			= new StartView( this.gameEngine );
	     newGameView 		= new NewGameView( this.gameEngine );
         createPlayersView 	= new CreatePlayersView( this.gameEngine );
         initSetupView 		= new InitSetupView( this.gameEngine );
         playerTurnView 	= new PlayerTurnView( this.gameEngine , this);
     
         // add all the views to the main panel
         mainPanel.add( startView,"startView" );
         mainPanel.add( newGameView,"newGameView" );
         mainPanel.add( createPlayersView,"createPlayersView" );
         mainPanel.add( initSetupView,"initSetupView" );
         mainPanel.add( playerTurnView,"playerTurnView" );

         // display the start view
         mainLayout.first(mainPanel);
         
	} // end of constructor

	
    /**
     * overridden update method from interface Observer
     * switch subViews every time the state of Game Engine changed
     **/
	public void update( Observable obs, Object obj )
	{
		// Get the current state of the game
     	String state = gameEngine.getState();

        System.out.println( "New state of game engine: " + state );

        // Update the panel in the JFrame to reflect the current state
	    if( state.equals( "newGameView" ) )
		{
		     this.setSize(900,680);
		     this.setLocationRelativeTo(null);
             mainLayout.show(mainPanel,"newGameView");
	         mainPanel.remove( this.startView );
		}
	    else if( state.equals( "createPlayersView" ) )
	    {
	    	 mainLayout.show(mainPanel,"createPlayersView");
	         mainPanel.remove( this.newGameView );
	         this.createPlayersView.loadData();
	         this.setSize(900,640);
	    }
        else if( state.equals( "initSetupView" ) )
        {
             this.setExtendedState(MAXIMIZED_BOTH); 
             game = gameEngine.getGame();
             game.addObserver((Observer)initSetupView);
             initSetupView.loadData();
             mainLayout.show(mainPanel,"initSetupView");
	         mainPanel.remove( this.createPlayersView );
        }
        else if( state.equals( "playerTurnView" ) )
        {
             this.setSize(670,550);
             this.setLocationRelativeTo(null);
             game.setCurrentPlayerIndex(0);
             playerTurnView.loadData();
             game.addObserver((Observer)playerTurnView);
             mainLayout.show(mainPanel,"playerTurnView");
	         mainPanel.remove( this.initSetupView );
        }
        else
        {
             System.out.println( "Shit went wrong" );
        }
	    
	} // end of update method

} // end of GameGUI class
