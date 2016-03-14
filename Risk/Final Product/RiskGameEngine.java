/**
 * CSCI 2120 Fall 2014
 * Risk Game Class AttackScreen
 * Authors: Hung Le, Jeanne Vu 
 * Date: 11-25-2014
 **
 * Class:	RiskGameEngine - the engine of the game- in charge of initialize the game
 * and holds a state to switch between screens
 * Composition: contain a game
 * Observed by the GameGUI
 **/
 import java.util.Observable;

public class RiskGameEngine extends Observable
{
     private RiskGame game;
     private String state;

     public RiskGameEngine()
     {
          this.state = "startScreen";
          this.game = null;
     }

     // create a new game
     // param : the game name and number of players
     public void createGame( String gameName, int numPlayers )
     {
          this.game = new RiskGame( gameName , numPlayers );
     }

    
     // get the game object
     public RiskGame getGame()
     {
          return this.game;
     }

     // get the current state
     public String getState()
     {
          return this.state;
     }

     public void loadGame( String gameFileName )
     {
          // Read & De-Serialize a game object
          // and set this.game to the de-serialized object
     }
    
    // set new state for the game engine
     public void setState(String newState)
     {
          this.state = newState;
          this.setChanged();
          this.notifyObservers();
     }

} // end class RiskGameEngine
