/**
 * CSCI 2120 Fall 2014
 * Risk Game Class Player
 * Authors: Hung Le, Jeanne Vu 
 * Date: 11-04-2014
 **
 * Class:	RiskMain
 * Roles:	The main method to initialize the GUI and the game engine
 **/

 import javax.swing.JFrame;

public class RiskMain
{

     public static void main( String[] args )
     {
          RiskGameEngine gameEngine = new RiskGameEngine();
          GameGUI gui = new GameGUI( gameEngine);

          gameEngine.addObserver( gui );
  
          gui.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
          gui.setSize(900,550);
          gui.setLocationRelativeTo(null);
          gui.setVisible( true );
     }
}
