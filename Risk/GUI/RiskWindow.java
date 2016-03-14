import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComponent;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Observer;
import java.util.Observable;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;

public class RiskWindow extends JFrame{

	private JPanel mainPanel;
	private CardLayout mainLayout;
	private JPanel startScreen;
	private JPanel startScreenButtonPanel;
	private JButton newGameButton;
	private JButton loadSavedGameButton;
	//private JLabel gameTitle;

	//New Game Screen
	private JPanel newGame;
	private JTextField gameNameJTF; // gameName JTextField
	private JTextField numPlayersJTF; // numPlayers JTextField
	private String gameName = "";
	private int numPlayers = 0;
	private PlayersList playersList;

	// Players' Name Screen
	private JPanel playersNameScreen;
	private JLabel namePrompt;
	private JTextField playersNameJTF;
	
	//Init Setup Screen
	private JPanel initSetupScreen;
	private GameBoard gameBoard;
	private ImageComponent gameMap;
	private Images mapModel;
	private String[] arraysOfTerritoryNames = new String[9];
	private JButton initPlaceArmiesButton;
	private PlayerNameDisplay playerNameDisplay;
	private NumArmiesDisplay numArmiesDisplay;
	private JComboBox TerritoriesListCB; // ComboBox
	private String selectedTerritory;
	
	private JPanel playersTurn;
	private JPanel turnInCard;
	private JPanel placeArmies;
	private JPanel attack;
	private JPanel attackResults;
	private JPanel fortify;
	private JPanel loadSavedGame;

	/*nope
	private JButton humanButton;
	private JButton baboonButton;
	private JButton chimpButton;
	private Images myModel;
	private ImageComponent imageComponent;
	*/
	
	public RiskWindow(){
		super("Risk Game");
		setLayout( new BorderLayout());
		//gameTitle = new JLabel("< Risk - The World Domination Game >");
		mainPanel = new JPanel( new CardLayout());
		mainPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
		add(mainPanel,BorderLayout.CENTER);
		BorderLayout mainL = (BorderLayout) this.getLayout();
		mainL.setVgap(500);
		//add(gameTitle,BorderLayout.PAGE_START);

		// Initialization for startScreen Panel
		startScreen = new JPanel( new BorderLayout());

		startScreenButtonPanel = new JPanel( new FlowLayout());
		
		newGameButton = new JButton("New Game");
		startScreenButtonPanel.add(newGameButton);
		newGameButton.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		loadSavedGameButton = new JButton("Load Saved Game");
		startScreenButtonPanel.add(loadSavedGameButton);
		loadSavedGameButton.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		startScreenButtonPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

		startScreen.add(startScreenButtonPanel,BorderLayout.CENTER);
		startScreen.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		ButtonHandler buttonHandler = new ButtonHandler();
		newGameButton.addActionListener( buttonHandler );
		loadSavedGameButton.addActionListener( buttonHandler );



		// New Game Screen

		newGame= new JPanel();
		newGame.setLayout( new BoxLayout(newGame,BoxLayout.Y_AXIS) );
		
		gameNameJTF = new JTextField("Enter a name for the new game here");
		gameNameJTF.setHorizontalAlignment((int)Component.CENTER_ALIGNMENT);
		newGame.add(gameNameJTF);

		numPlayersJTF = new JTextField("Enter number of players here");
		numPlayersJTF.setHorizontalAlignment((int)Component.CENTER_ALIGNMENT);
		newGame.add(numPlayersJTF);

		TextFieldHandler textHandler= new TextFieldHandler();
		gameNameJTF.addActionListener(textHandler);
		numPlayersJTF.addActionListener(textHandler);


		// Players' Name Screen

		playersNameScreen = new JPanel();
		playersNameScreen.setLayout( new BoxLayout(playersNameScreen,BoxLayout.Y_AXIS) );

		//playersList = new PlayersList(numPlayers);
		playersNameJTF = new JTextField("Please enter a name for player 1");
		playersNameJTF.setHorizontalAlignment((int)Component.CENTER_ALIGNMENT);
		playersNameScreen.add(playersNameJTF);

		playersNameJTF.addActionListener(textHandler);
		
		
		
		// initial SetUp Screen
		gameBoard = new GameBoard();
		mapModel = new Images();
		gameMap = new ImageComponent();
		
		
		initSetupScreen = new JPanel();
		initSetupScreen.setLayout( new BorderLayout());

		initPlaceArmiesButton = new JButton("Init Place Armies");
		initSetupScreen.add(initPlaceArmiesButton , BorderLayout.SOUTH);
		initPlaceArmiesButton.addActionListener( buttonHandler );

		
		initSetupScreen.add(gameMap, BorderLayout.CENTER);

		

		//String[] ownedTerritoriesArray = playersList.getPlayer().getTerritoriesMap()
		arraysOfTerritoryNames = gameBoard.getUnoccupiedTerritories().keySet().toArray(arraysOfTerritoryNames);
		TerritoriesListCB = new JComboBox ( arraysOfTerritoryNames );

		//initSetupScreen.add( TerritoriesListCB, BorderLayout.WEST);
		JPanel TerritoriesListPanel = new JPanel(new BorderLayout());
		TerritoriesListPanel.add(TerritoriesListCB,BorderLayout.NORTH);
		

		
		initSetupScreen.add( TerritoriesListPanel, BorderLayout.WEST);

		ComboBoxHandler boxHandler = new ComboBoxHandler();
		TerritoriesListCB.addActionListener( boxHandler );



		mainPanel.add(startScreen, "Start Screen");
		mainPanel.add(newGame, "New Game");
		mainPanel.add(playersNameScreen, "Players' Name");
		mainPanel.add(initSetupScreen, "Init Setup Screen");
		mainLayout = (CardLayout)mainPanel.getLayout();
		mainLayout.setVgap(300);
		mainLayout.setHgap(500);
		//mainLayout.addLayoutComponent(startScreen,"Start Screen");
		mainLayout.show(mainPanel,"Start Screen");
	}

	/*nope
	public ImageComponent getImageComponent(){
		return imageComponent;
	}*/

	// Handler for Buttons
	private class ButtonHandler implements ActionListener{
		public ButtonHandler(){

		}

		public void actionPerformed (ActionEvent event){
			if(event.getActionCommand().equals("New Game")){
				mainLayout.show(mainPanel,"New Game");
			}
			if(event.getActionCommand().equals("Load Saved Game")){

				JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showOpenDialog(chooser.getParent());
				if(returnVal == JFileChooser.APPROVE_OPTION) 
				{
					System.out.println("You chose to open this file: " +chooser.getSelectedFile().getName());
				}
			}
			if(event.getActionCommand().equals("Init Place Armies") && selectedTerritory != null)
			{
				playersList.getPlayer().updateNumArmies(-1);
				gameBoard.getTerritoryByName(selectedTerritory).updateNumArmies(1);
					
				playersList.nextPlayer();
				
				
				if ( !(gameBoard.getUnoccupiedTerritories().isEmpty()))
				{
					gameBoard.getTerritoryByName(selectedTerritory).setOccupant(playersList.getPlayer());
					TerritoriesListCB.removeItem(selectedTerritory);
				}	
				if ( gameBoard.getUnoccupiedTerritories().isEmpty())
				{
					TerritoriesListCB.removeAllItems();
					for( String i : (String[])playersList.getPlayer().getTerritoriesMap().keySet().toArray())
						TerritoriesListCB.addItem(i);
				}
			}
		}
	}


	// Handler for TextFields
	private class TextFieldHandler implements ActionListener{
		public TextFieldHandler(){

		}

		public void actionPerformed (ActionEvent event){
			if(event.getSource() == gameNameJTF){
				//set startScreen JPanel invisible 
				//newGame JPanel visible
				gameName = event.getActionCommand();
				System.out.println("game name is '"+gameName+"'");
				//mainLayout.show(mainPanel,"New Game");
				if(!(gameName.equals("")) && numPlayers != 0 )
					mainLayout.show(mainPanel,"Players' Name");
			}
			if(event.getSource() == numPlayersJTF){
				//startScreen JPanel invisible
				//JFileChooser visible
				numPlayers = Integer.parseInt(event.getActionCommand());
				if (numPlayers<3)
					numPlayers = 3;
				else if(numPlayers>6)
					numPlayers = 6;
				System.out.println("number of players is "+numPlayers);

				playersList = new PlayersList(numPlayers);// create a players list with the number of players specified
				
				
				if(!(gameName.equals("")) && numPlayers != 0 ) // switch to next screen when game name and number of players are entered
					mainLayout.show(mainPanel,"Players' Name");
			}
			if(event.getSource() == playersNameJTF){
				if ( playersList.getPlayer() == null )
				{
					System.out.println("named a new player");
					playersList.createPlayer(event.getActionCommand());
					playersList.nextPlayer();
					if (playersList.getPlayer() != null)
					{
						playersNameJTF.setText(" Names for all Players are given! ");
						for (Player i: playersList.getPlayersList())
							System.out.println(i.getName()+" ");

						JPanel nameAndNumArmiesJP = new JPanel(new FlowLayout());
						playerNameDisplay = new PlayerNameDisplay(playersList);//ready to display names
						playersList.addObserver(playerNameDisplay);
						playerNameDisplay.setHorizontalAlignment((int)Component.CENTER_ALIGNMENT);
						nameAndNumArmiesJP.add(playerNameDisplay);

						playersList.setInitArmies(numPlayers); // set initial armies for placement

						numArmiesDisplay = new NumArmiesDisplay(playersList);
						playersList.addObserver(numArmiesDisplay);
						//numArmiesDisplay.setHorizontalAlignment((int)Component.CENTER_ALIGNMENT);
						//numArmiesDisplay.setVerticalAlignment((int)Component.CENTER_ALIGNMENT);
						nameAndNumArmiesJP.add(numArmiesDisplay);
						initSetupScreen.add(nameAndNumArmiesJP, BorderLayout.NORTH);

						mainLayout.setVgap(0);
						mainLayout.setHgap(0);
						mainLayout.show(mainPanel,"Init Setup Screen");
						
					}
					else
						playersNameJTF.setText("Please enter a name for player "+(playersList.getCurrentPlayerIndex()+1));
					
				}
			}
			
		}
	}


	// event handler for JComboBox

	private class ComboBoxHandler implements ActionListener{ 
		public ComboBoxHandler(){

		}

		public void actionPerformed (ActionEvent event){
			
			if(event.getSource() == TerritoriesListCB ){
        			selectedTerritory = (String) ((JComboBox)event.getSource()).getSelectedItem();
        			System.out.println(selectedTerritory+" chosen");
			}
		}
	}

	private class ImageComponent extends JComponent
	{
		private Image image;
		public ImageComponent(){
			image = mapModel.getImage();
			repaint();
		}

		public void paintComponent(Graphics g){
			g.drawImage(image, 0, 0, this); 
			//g.setColor(Color.BLUE);
			//g.drawString("FOUR TROOPS", 450, 150);
		}
	}

	public static void main(String[] args)
	{
		/*
		Images myModel = new Images();
		ImageGUI myGUI = new ImageGUI(myModel);
		myModel.addObserver( myGUI.getImageComponent() );
		*/
		RiskWindow RiskGame = new RiskWindow();
		RiskGame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		//RiskGame.setSize(1000, 700);
		RiskGame.setExtendedState(RiskGame.MAXIMIZED_BOTH);  
		RiskGame.setVisible(true);
	}
}
