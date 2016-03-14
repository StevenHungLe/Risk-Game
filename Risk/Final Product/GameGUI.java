import javax.swing.JPanel;
import javax.swing.JFrame;
import java.util.Observer;
import java.util.Observable;
import java.awt.event.ActionListener;
import java.awt.CardLayout;
import java.awt.Container;

public class GameGUI extends JFrame implements Observer
{

	//private JPanel currentPanel;
	private Container mainPanel;
	private CardLayout mainLayout;

	// START SCREEN
	private JPanel startScreen;

	// NEW GAME SCREEN
	private JPanel newGameScreen;

	// CREATE PLAYERS SCREEN
    	private JPanel createPlayersScreen;

    	// INITIAL SET-UP SCREEN
    	private InitSetupScreen initSetupScreen;

    	// PLAYER TURN SCREEN
     private PlayerTurnScreen playerTurnScreen;
     
    	private RiskGameEngine gameEngine;
    	private RiskGame game;

	public GameGUI( RiskGameEngine gameEngine )
	{
          this.gameEngine = gameEngine;

	     this.mainPanel = this.getContentPane();
	     
	     mainPanel.setLayout( new CardLayout() );
	     mainLayout = (CardLayout) mainPanel.getLayout();
	
          // START SCREEN
          startScreen = new StartScreen( this.gameEngine );

          // NEW GAME SCREEN 
	     newGameScreen = new NewGameScreen( this.gameEngine );

          // CREATE PLAYERS SCREEN
          createPlayersScreen = new CreatePlayersScreen( this.gameEngine );

          // INITIAL SET-UP SCREEN
          initSetupScreen = new InitSetupScreen( this.gameEngine );

          // PLAYER TURN SCREEN
          playerTurnScreen = new PlayerTurnScreen( this.gameEngine , this);
     
          mainPanel.add( startScreen,"startScreen" );
          mainPanel.add( newGameScreen,"newGameScreen" );
          mainPanel.add( createPlayersScreen,"createPlayersScreen" );
          mainPanel.add( initSetupScreen,"initSetupScreen" );
          mainPanel.add( playerTurnScreen,"playerTurnScreen" );

          mainLayout.first(mainPanel);
	}

     /**
	 * switch subScreens everytime the state of Game Engine changed
	 **/
	public void update( Observable obs, Object obj )
	{
     System.out.println( "In update of GameGUI" );
	String state = gameEngine.getState();

          System.out.println( "New state of game engine: " + state );

        // Update the panel in the JFrame to reflect the current state
	     if( state.equals( "newGameScreen" ) )
		{
		     this.setSize(900,600);
		     this.setLocationRelativeTo(null);
               mainLayout.show(mainPanel,"newGameScreen");
	          mainPanel.remove( this.startScreen );
		}
		
          else if( state.equals( "createPlayersScreen" ) )
          {
               mainLayout.show(mainPanel,"createPlayersScreen");
	          mainPanel.remove( this.newGameScreen );
          }
        
          else if( state.equals( "initSetupScreen" ) )
          {
               this.setExtendedState(MAXIMIZED_BOTH); 
               game = gameEngine.getGame();
               game.addObserver(initSetupScreen);
               initSetupScreen.initialSetup();
			mainLayout.show(mainPanel,"initSetupScreen");
	          mainPanel.remove( this.createPlayersScreen );
          }
          
          else if( state.equals( "playerTurnScreen" ) )
          {
               this.setSize(670,550);
               this.setLocationRelativeTo(null);
               game.setCurrentPlayerIndex(0);
               playerTurnScreen.initialSetup();
               game.addObserver(playerTurnScreen);
			mainLayout.show(mainPanel,"playerTurnScreen");
	          mainPanel.remove( this.initSetupScreen );
          }
          else
          {
               System.out.println( "Shit went wrong" );
          }
	}

}
