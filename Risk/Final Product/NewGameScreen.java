/**
 * CSCI 2120 Fall 2014
 * Risk Game Class NewGameScreen
 * Authors: Hung Le, Jeanne Vu 
 * Date: 11-25-2014
 **
 * Class:	NewGameScreen - the screen in which a new game is created
 * Composition: contained in GameGUI
 **/

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class NewGameScreen extends JPanel{


	// Instance Variables for New Game Screen
	private JTextField gameNameJTF; // gameName JTextField
	private JTextField numPlayersJTF; // numPlayers JTextField
	private String gameName = "";
	private int numPlayers = 0;
	//private PlayersList playersList;

	private RiskGameEngine gameEngine;
	private ImageComponent imageComponent;
	private Images welcomePic;
	private JPanel textFieldJP;
	
	public NewGameScreen(RiskGameEngine gameEngine){
		this.gameEngine = gameEngine;

          welcomePic = new Images("StartScreenPic.jpg");
		imageComponent = new ImageComponent(welcomePic.getImage(900,500));

          

          setLayout( new BorderLayout());

          // the panel to contains the text fields
          textFieldJP = new JPanel();
		textFieldJP.setLayout( new BoxLayout(textFieldJP,BoxLayout.Y_AXIS) );
		
		gameNameJTF = new JTextField("Enter a name for the new game here...");
		gameNameJTF.setFont( new Font ("Serif", Font.BOLD, 24) );
		gameNameJTF.setHorizontalAlignment(JTextField.CENTER);
		
		numPlayersJTF = new JTextField("Enter number of players (3-6) here ...");
		numPlayersJTF.setFont( new Font ("Serif", Font.BOLD, 24) );
		numPlayersJTF.setHorizontalAlignment(JTextField.CENTER);

          textFieldJP.add(gameNameJTF);
		textFieldJP.add(numPlayersJTF);

		// add the two components to the screen
          add(imageComponent,BorderLayout.CENTER);
		add(textFieldJP,BorderLayout.SOUTH);

          // handlers
		MouseHandler mouseHandler = new MouseHandler();
		gameNameJTF.addMouseListener(mouseHandler);
		numPlayersJTF.addMouseListener(mouseHandler);

		TextFieldHandler textHandler= new TextFieldHandler();
		gameNameJTF.addActionListener(textHandler);
		numPlayersJTF.addActionListener(textHandler);


	}


	// Handler For Mouse Click
	private class MouseHandler extends MouseAdapter
	{
	    	@Override
	    	public void mouseClicked(MouseEvent e)
	    	{
	    	// reset text when clicked on text field
	    	if ( e.getSource() == gameNameJTF )
	    		gameNameJTF.setText("");
	    	else
	        	numPlayersJTF.setText("");
	    	}
	}


	// Handler for TextFields
	private class TextFieldHandler implements ActionListener
	{
		public TextFieldHandler()
		{
		}

		public void actionPerformed (ActionEvent event)
		{
			if(event.getSource() == gameNameJTF)
			{
				gameName = event.getActionCommand();
				System.out.println("game name is '"+event.getActionCommand()+"'");

				//riskGUI.setGameName( gameName );
				
			}
			
			if(event.getSource() == numPlayersJTF)
			{
				numPlayers = Integer.parseInt(event.getActionCommand());

				// automatically set the number of players to minimum
				if (numPlayers<3) 
					numPlayers = 3;

				// automatically set the number of players to maximum
				else if(numPlayers>6)
					numPlayers = 6;
					
				System.out.println("number of players is "+numPlayers);
				
				//riskGUI.setNumPlayers( numPlayers );
				
			}	
			// switch to next screen when game name and number of players are entered
			if(!(gameName.equals("")) && numPlayers != 0 )
			{
				//riskGUI.setLayoutGaps(330,550);
				gameEngine.createGame(gameName,numPlayers);
				gameEngine.setState("createPlayersScreen");
			}
		}
	}

}
