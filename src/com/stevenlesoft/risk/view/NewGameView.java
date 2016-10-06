/**
 * CSCI 2120 Fall 2014
 * Risk Game Class NewGameView
 * Authors: Hung Le, Jeanne Vu 
 * Date: 11-25-2014
 **
 * Class:	NewGameView - the view in which a new game is created
 * Composition: contained in GameGUI
 **/
package com.stevenlesoft.risk.view;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import com.stevenlesoft.risk.main.*;
public class NewGameView extends View{


	// Instance Variables for New Game View
	private JTextField gameNameJTF; // gameName JTextField
	private JTextField numPlayersJTF; // numPlayers JTextField
	private JButton startGameButton; // the button to start a new game
	private String gameName = "";
	private int numPlayers = 0;

	private RiskGameEngine gameEngine;
	private ImageComponent imageComponent;
	private Images welcomePic;
	private JPanel textFieldJP;
	
	private static final String GAME_NAME_PROMPT = "Enter a name for the new game here...";
	private static final String NUM_PLAYERS_PROMPT = "Enter number of players (3-6) here...";
	
	public NewGameView(RiskGameEngine gameEngine){
		this.gameEngine = gameEngine;

        welcomePic = new Images("src/resources/StartViewPic.jpg");
		imageComponent = new ImageComponent(welcomePic.getImage(900,500));

        setLayout( new BorderLayout());

        // the panel to contains the text fields
        textFieldJP = new JPanel();
		textFieldJP.setLayout( new BoxLayout(textFieldJP,BoxLayout.Y_AXIS) );
		
		gameNameJTF = new JTextField(GAME_NAME_PROMPT);
		gameNameJTF.setFont( new Font ("Serif", Font.BOLD, 24) );
		gameNameJTF.setMaximumSize(new Dimension(400,100));
		gameNameJTF.setAlignmentX(CENTER_ALIGNMENT);
		
		numPlayersJTF = new JTextField(NUM_PLAYERS_PROMPT);
		numPlayersJTF.setFont( new Font ("Serif", Font.BOLD, 24) );
		numPlayersJTF.setMaximumSize(new Dimension(400,100));
		numPlayersJTF.setAlignmentX(CENTER_ALIGNMENT);

		startGameButton = new JButton("Start Game");
		startGameButton.setAlignmentX(CENTER_ALIGNMENT);
		startGameButton.setPreferredSize(new Dimension(300,40));
		
		textFieldJP.add(Box.createRigidArea(new Dimension(0,10)));
        textFieldJP.add(gameNameJTF);
		textFieldJP.add(numPlayersJTF);
		textFieldJP.add(Box.createRigidArea(new Dimension(0,10)));
		textFieldJP.add(startGameButton);
		
		// add the two components to the view
        add(imageComponent,BorderLayout.CENTER);
		add(textFieldJP,BorderLayout.SOUTH);

          // handlers
		MouseHandler mouseHandler = new MouseHandler();
		gameNameJTF.addMouseListener(mouseHandler);
		numPlayersJTF.addMouseListener(mouseHandler);
		
		startGameButton.addActionListener(new StartButtonHandler());

	} // end of constructor

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

	
	// handler for start Button
	private class StartButtonHandler implements ActionListener
	{
		public void actionPerformed( ActionEvent event)
		{
			gameName = gameNameJTF.getText();
			
			
			if ( gameName.equals(GAME_NAME_PROMPT) )
			{
				gameNameJTF.setForeground(Color.RED);
				return;
			}
				
			
			try
			{
				numPlayers = Integer.parseInt(numPlayersJTF.getText());
				
				// automatically set the number of players to minimum
				if (numPlayers<3) 
					numPlayers = 3;

				// automatically set the number of players to maximum
				else if(numPlayers>6)
					numPlayers = 6;
			}
			catch(NumberFormatException e)
			{
				System.out.println("please enter the number of players");
				numPlayersJTF.setForeground(Color.RED);
				numPlayersJTF.setText(NUM_PLAYERS_PROMPT);
				return;
			}

			gameEngine.createGame(gameName, numPlayers);
			gameEngine.setState("createPlayersView");

		}
	}

}
