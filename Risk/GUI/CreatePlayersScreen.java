import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.util.ArrayList;

public class CreatePlayersScreen extends JPanel{

   
	private ArrayList<Player> playersList;
	private int numPlayers;
	
	
	private JTextField playersNameJTF;

     private RiskGameEngine gameEngine;
     private RiskGame game;
     private ImageComponent imageComponent;
	private Images welcomePic;
	
	
	public CreatePlayersScreen(RiskGameEngine gameEngine)
	{
		this.gameEngine = gameEngine;

          // the welcome picture
          welcomePic = new Images("StartScreenPic.jpg");
		imageComponent = new ImageComponent(welcomePic.getImage(900,500));

		setLayout( new BorderLayout() );

          // text field to enter player's name
		playersNameJTF = new JTextField("Please enter a name for player 1...");
		playersNameJTF.setHorizontalAlignment(JTextField.CENTER);
		playersNameJTF.setFont( new Font ("Serif", Font.BOLD, 24) );

          add(imageComponent,BorderLayout.CENTER);
		add(playersNameJTF,BorderLayout.SOUTH);

          // handlers
		TextFieldHandler textHandler= new TextFieldHandler();
		playersNameJTF.addActionListener(textHandler);
		
		MouseHandler mouseHandler = new MouseHandler();
		playersNameJTF.addMouseListener(mouseHandler);

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
		public TextFieldHandler(){

		}

		public void actionPerformed (ActionEvent event)
		{
			if ( game == null ) // load necessary data
			{
			     game = gameEngine.getGame();
                    playersList = game.getPlayersList();
		          numPlayers = game.getNumPlayers();
			}	
			if ( playersList.size() < numPlayers ) // create new player until reach desired number
			{
				System.out.println("named a new player");
				game.addPlayer(event.getActionCommand());
				playersNameJTF.setText("Please enter a name for player "+(playersList.size()+1));
				
			}
			if ( playersList.size() == numPlayers )
			{
				playersNameJTF.setText(" Names for all Players are given! ");
				game.setInitArmies(); // set initial armies for placement
				// switch to innitial setup screen
                    gameEngine.setState("initSetupScreen");	
			}
		
		}
	}

} ///////////////////////////////////// end class CreatePlayerScreen
