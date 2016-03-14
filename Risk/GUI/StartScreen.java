/**
 * CSCI 2120 Fall 2014
 * Risk Game Class StartScreen
 * Authors: Hung Le, Jeanne Vu 
 * Date: 11-25-2014
 **
 * Class:	StartScreen - the first screen of the game-decides new game or load saved game
 * Composition: contained in RiskGUI
 **/

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFileChooser;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Image;
import javax.swing.JComponent;
import java.awt.Graphics;

public class StartScreen extends JPanel{
	private JPanel buttonPanel;
	private JButton newGameButton;
	private JButton loadSavedGameButton;
	private JPanel mainPanel;
	private RiskGameEngine gameEngine;
	private ImageComponent imageComponent;
	private Images welcomePic;

	public StartScreen(RiskGameEngine gameEngine){
		//Layout manager for the mainPanel in GUI
		this.gameEngine = gameEngine;

		welcomePic = new Images("StartScreenPic.jpg");
		imageComponent = new ImageComponent(welcomePic.getImage(900,500));
	
		
		// BorderLayout for the Start Screen
		setLayout( new BorderLayout());
		// FlowLayout for the ButtonPanel
		buttonPanel = new JPanel( new FlowLayout());
		
		newGameButton = new JButton("New Game");
		buttonPanel.add(newGameButton);
		
		loadSavedGameButton = new JButton("Load Saved Game");
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
				gameEngine.setState("newGameScreen");
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


	/*private class ImageComponent extends JComponent
	{
		private Image image;
		public ImageComponent(){
			image = welcomePic.getImage();
			repaint();
		}

		public void paintComponent(Graphics g){
			g.drawImage(image, 0, 0, this);
		}
	}*/

}
