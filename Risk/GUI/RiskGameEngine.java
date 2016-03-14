import java.util.Observable;

public class RiskGameEngine extends Observable
{
     private RiskGame game;
     private String state;

     public RiskGameEngine()
     {
          this.state = "start";
          this.game = null;
     }

     public void createNewGame()
     {
          System.out.println( "In createNewGame of RiskGameEngine" );
          this.state = "createNewGame";
          this.setChanged();
          this.notifyObservers();
     }



     public void loadSavedGame()
     {
          this.state = "loadSavedGame";
     }

    

     public RiskGame getGame()
     {
          return this.game;
     }

     public String getState()
     {
          return this.state;
     }

     public void loadGame( String gameFileName )
     {
          // Read & De-Serialize a game object
          // and set this.game to the de-serialized object
     }
    
     public void createGame( String gameName, int numPlayers )
     {
          this.game = new RiskGame( gameName , numPlayers );
     }
    
     public void setState(String newState)
     {
          this.state = newState;
          this.setChanged();
          this.notifyObservers();
     }

}
