/**
 * CSCI 2120 Fall 2014
 * Risk Game Class StartView
 * Authors: Hung Le, Jeanne Vu 
 * Date: 11-25-2014
 **
 * Class:	StartView - the first view of the game-decides new game or load saved game
 * Composition: contained in RiskGUI
 **/
package com.stevenlesoft.risk.view;
import javax.swing.JFrame;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFileChooser;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.stevenlesoft.risk.main.*;

public class StartView extends View{
	private JPanel buttonPanel;
	private JButton newGameButton;
	private JButton loadSavedGameButton;
	private JPanel mainPanel;
	private RiskGameEngine gameEngine;
	private ImageComponent imageComponent;
	private Images welcomePic;

	public StartView(RiskGameEngine gameEngine){

		this.gameEngine = gameEngine;

		welcomePic = new Images("src/resources/StartViewPic.jpg");
		imageComponent = new ImageComponent(welcomePic.getImage(900,500));
		
		// BorderLayout for the Start View
		setLayout( new BorderLayout());
		
		// FlowLayout for the ButtonPanel
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		
		newGameButton = new JButton("New Game");
		newGameButton.setPreferredSize(new Dimension(150,40));
		buttonPanel.add(newGameButton);
		buttonPanel.add( Box.createRigidArea(new Dimension(10,0)));
		loadSavedGameButton = new JButton("Load Saved Game");
		loadSavedGameButton.setPreferredSize(new Dimension(150,40));
		buttonPanel.add(loadSavedGameButton);

		add(buttonPanel,BorderLayout.SOUTH);
		add(imageComponent,BorderLayout.CENTER);

		EventHandler eventHandler = new EventHandler();
		newGameButton.addActionListener( eventHandler );
		loadSavedGameButton.addActionListener( eventHandler );

	}


	// EventHandler
	private class EventHandler implements ActionListener{
	
		public void actionPerformed (ActionEvent event){
		
			if( event.getActionCommand().equals("New Game") )
			{
				gameEngine.setState("newGameView");
			}
			else if (event.getActionCommand().equals("Load Saved Game")){

				JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showOpenDialog(chooser.getParent());
				if(returnVal == JFileChooser.APPROVE_OPTION) 
				{
					System.out.println("You chose to open this file: " +chooser.getSelectedFile().getName());
				}
			}
				
		}
	}

}
