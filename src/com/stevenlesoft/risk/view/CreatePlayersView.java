package com.stevenlesoft.risk.view;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.util.ArrayList;
import com.stevenlesoft.risk.main.*;
import com.stevenlesoft.risk.model.*;
public class CreatePlayersView extends View{

   
	private ArrayList<Player> playersList;
	private int numPlayers;
	
	private JPanel southPanel;
	private JTextField playersNameJTF;
	private JButton nextButton;

     private RiskGameEngine gameEngine;
     private RiskGame game;
     private ImageComponent imageComponent;
	private Images welcomePic;
	
	
	public CreatePlayersView(RiskGameEngine gameEngine)
	{
		this.gameEngine = gameEngine;

        // the welcome picture
        welcomePic = new Images("src/resources/StartViewPic.jpg");
		imageComponent = new ImageComponent(welcomePic.getImage(900,500));
		imageComponent.setPreferredSize( new Dimension(900,500));

		setLayout( new BorderLayout() );

		// initialize the JPanel
		southPanel = new JPanel();
		southPanel.setLayout(new BoxLayout(southPanel,BoxLayout.Y_AXIS));
		
          // text field to enter player's name
		playersNameJTF = new JTextField("Please enter a name for player 1...");
		playersNameJTF.setHorizontalAlignment(JTextField.CENTER);
		playersNameJTF.setFont( new Font ("Serif", Font.BOLD, 24) );
		playersNameJTF.setMaximumSize(new Dimension(500,100) );
		playersNameJTF.setAlignmentX(CENTER_ALIGNMENT);
		
		// next button for input submission
		nextButton = new JButton("Next");
		nextButton.setPreferredSize(new Dimension(180,35));
		nextButton.setAlignmentX(CENTER_ALIGNMENT);
		nextButton.setFont(new Font("Serif",Font.BOLD, 24));
		//nextButton.setMaximumSize(new Dimension(100,40));

		// add the components to the south panel
		southPanel.add(playersNameJTF);
		southPanel.add(Box.createRigidArea(new Dimension(0,10) ));
		southPanel.add(nextButton);
		
		// add the components to the main panel
        add(imageComponent,BorderLayout.CENTER);
        add(southPanel,BorderLayout.SOUTH);

          // handlers
		TextFieldHandler textHandler= new TextFieldHandler();
		playersNameJTF.addActionListener(textHandler);
		
		nextButton.addActionListener(new NextButtonHandler());
		
		MouseHandler mouseHandler = new MouseHandler();
		playersNameJTF.addMouseListener(mouseHandler);

	}


	// load necessary data used in the view
	@Override
	public void loadData()
	{
		game = gameEngine.getGame();
        playersList = game.getPlayersList();
        numPlayers = game.getNumPlayers();
	}

	// Handler For Mouse Click
	private class MouseHandler extends MouseAdapter
	{
	    	@Override
	    	public void mouseClicked(MouseEvent e)
	    	{
	    		playersNameJTF.setText(""); // reset text when user click on the text field
	    	}
	}

	
	// Handler for TextFields
	private class TextFieldHandler implements ActionListener{

		public void actionPerformed (ActionEvent event)
		{
			handleInputSubmission();
		}
	}
	
	// Handler for Next button
	private class NextButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			handleInputSubmission();
		}
	}
	
	public void handleInputSubmission()
	{
		if ( playersList.size() < numPlayers ) // create new player until reach desired number
		{
			game.addPlayer(playersNameJTF.getText());
			playersNameJTF.setText("Please enter a name for player "+(playersList.size()+1));
			
		}
		if ( playersList.size() == numPlayers )
		{
			playersNameJTF.setText(" Names for all Players are given! ");
			game.setInitArmies(); // set initial armies for placement
			// switch to innitial setup view
            gameEngine.setState("initSetupView");	
		}
	}

} ///////////////////////////////////// end class CreatePlayerView
