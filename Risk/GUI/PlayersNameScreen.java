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
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observer;
import java.util.Observable;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.ArrayList;

public class PlayersNameScreen extends JPanel{

	private RiskGUI riskGUI;
	private JPanel mainPanel;
	private CardLayout mainLayout;

	private PlayersList playersList;

	private JTextField playersNameJTF;

	private int numPlayers = 0;
	
	public PlayersNameScreen(RiskGUI riskGUI, JPanel mainPanel, PlayersList playersList)
	{
		this.riskGUI = riskGUI;
		this.mainPanel = mainPanel;
		this.playersList = playersList;
		
		mainLayout = (CardLayout) mainPanel.getLayout();
		
		// Players' Name Screen

		setLayout( new BoxLayout(this,BoxLayout.Y_AXIS) );

		playersNameJTF = new JTextField("Please enter a name for player 1");
		//playersNameJTF.setHorizontalAlignment((int)Component.CENTER_ALIGNMENT);

		add(playersNameJTF);

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
	    		playersNameJTF.setText("");
	    	}
	}

	
	// Handler for TextFields
	private class TextFieldHandler implements ActionListener{
		public TextFieldHandler(){

		}

		public void actionPerformed (ActionEvent event){
			if( numPlayers == 0 )
				numPlayers = riskGUI.getNumPlayers();
				
			if(event.getSource() == playersNameJTF)
			{
				
				if ( playersList.getNumPlayers() < numPlayers )
				{
					System.out.println("named a new player");
					playersList.addPlayer(event.getActionCommand());
					playersNameJTF.setText("Please enter a name for player "+(playersList.getNumPlayers()+1));
					
				}
				if ( playersList.getNumPlayers() == numPlayers )
				{
					playersNameJTF.setText(" Names for all Players are given! ");
					for (Player i: playersList.getPlayersList())
						System.out.println(i.getName()+" ");
		
					playersList.setInitArmies(); // set initial armies for placement

					mainLayout.setVgap(0);
					mainLayout.setHgap(0);
					mainLayout.show(mainPanel,"Init Setup Screen");		
				}
				
			}
			
		}
	}

}
