/**
 * CSCI 2120 Fall 2014
 * Risk Game Class AttackView
 * Authors: Hung Le, Jeanne Vu 
 * Date: 11-25-2014
 **
 * Class:	Fortify View - the subView in which the player move armies between theirs territories
 * Composition: contained in PlayerTurnView
 **/
package com.stevenlesoft.risk.view;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComponent;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JList;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import com.stevenlesoft.risk.main.*;
import com.stevenlesoft.risk.model.*;
public class TurnInCardView extends View
{  
     private RiskGameEngine gameEngine;
     private RiskGame game;

     private JPanel imagePanel;
     private ImageComponent imageComponent;
     
	private JList<Object> cardsList;

	private JPanel buttonsPanel;
	private JButton turnInCardsButton;
	private JButton skipButton;

	private JPanel msgPanel;
	private JLabel messageDisplay;
	private JButton gotItButton;
	
	private Hand playersHand;
	private ArrayList<String> typeAndNameList; // the list of types and names of the cards at hand
	private int[] selectedIndices; 

	private JListListener jListListener;


	public TurnInCardView(RiskGameEngine gameEngine, Images cardCombinations)
	{
		setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );
		
		this.gameEngine = gameEngine;
		
		this.imageComponent = new ImageComponent(cardCombinations.getImage(650,250));
		imagePanel = new JPanel( new BorderLayout());
		imagePanel.add ( imageComponent );

          buttonsPanel = new JPanel ( new FlowLayout() );
		turnInCardsButton = new JButton( "Turn in cards" );
		skipButton = new JButton( "Skip to next step" );
		gotItButton = new JButton( "Alright! I got it!" );
		gotItButton.setVisible(false);
		
	     
		buttonsPanel.add(turnInCardsButton);
		buttonsPanel.add(skipButton);
		buttonsPanel.add(gotItButton);
		

          ButtonHandler buttonHandler = new ButtonHandler();
		turnInCardsButton.addActionListener( buttonHandler );
		skipButton.addActionListener( buttonHandler );
		gotItButton.addActionListener( buttonHandler );

		// the Panel to display messages
          msgPanel = new JPanel ( new FlowLayout() );
	     messageDisplay = new JLabel("");
	     msgPanel.add(messageDisplay);

          // the listener for Jlist
          jListListener = new JListListener();
          
		this.add(imagePanel);
		this.add(buttonsPanel);
		this.add(msgPanel);
	}


	// load necessary data for the view
	@Override
     public void loadData()
	{
          this.game = gameEngine.getGame();
		System.out.println("innitial setup for turn-in-card view");

          typeAndNameList = new ArrayList<String>();
          selectedIndices = new int[3];
          
          this.update();

	}

     // update the info in the view everytime the view is loaded
	public void update()
	{
	     System.out.println("updating the turn-in-card view for the current player");

          if ( this.playersHand == game.getCurrentPlayer().getHand() )
               return;
               
	     this.playersHand = game.getCurrentPlayer().getHand();
	     
		if (playersHand.isEmpty())
		{
		     if ( cardsList != null )
		          cardsList.setVisible(false); // hide the cardList if the player has no cards
               return;
          }
               
          if ( !typeAndNameList.isEmpty() )
          {
               typeAndNameList.clear(); // reset the list
               this.remove(cardsList);
          }

          // make a list of types and names of the cards at hands
          for ( Card i : playersHand )
               typeAndNameList.add( String.format("%s%d%s%-11s%s%-22s%s%n","Index ",playersHand.indexOf(i)," : [ ",i.getType(),", ",i.getTerritoryName(),"]" ) );

          // make a new JList out of the list above     
		cardsList = new JList<Object>( typeAndNameList.toArray() );
		
		cardsList.addListSelectionListener ( jListListener );
		cardsList.setVisible(true);
		this.add( cardsList );

          clearSelectedIndices();

		updateMessage(messageDisplay);

		gotItButton.setVisible(false);
	}

	
	private class ButtonHandler implements ActionListener
	{
	     
		public void actionPerformed( ActionEvent event )
		{
		     
		     if ( event.getSource() == turnInCardsButton )
		     {
		          if ( cardsList == null )
		               return;

		          // if three cards are selected and the combination is valid
                    if ( selectedThreeCards() && isCombinationValid())
                    {
                         System.out.println("Turning in cards");
                         game.turnInCards( selectedIndices );
                         game.setState("placeArmiesView");
                    }
                    // if three cards are not selected, show error message
                    else if ( !selectedThreeCards() )
                    {
                         messageDisplay.setText("You have to select exactly three cards to turn in!!!");
                         gotItButton.setVisible(true);
                         clearSelectedIndices();
                    }
                    // if the combination is invalid, show error message
                    else
                    {
                         messageDisplay.setText("Combination invalid! please refer to the trade-in guide above and choose a valid combination!!!");
                         gotItButton.setVisible(true);
                         clearSelectedIndices();
                    }
                    
			}
			
			else if( event.getSource() == skipButton ) // skip button
			{
			     // if the player has 5 cards or more, trade-in is required
			     if ( playersHand.size() >= 5 )
			     {
			          messageDisplay.setText("You have too many cards!!! Please trade in at least one set!");
                         gotItButton.setVisible(true);
                         clearSelectedIndices();
                    }
                    else
			          game.setState("placeArmiesView");
			}
			// dismiss the error message
			else // "Alright! I got it!"
			{
			     updateMessage(messageDisplay);
			     gotItButton.setVisible(false);
			}
		}
	}

     // Listtener for JList
	private class JListListener implements ListSelectionListener
	{
	     private int count = 0;
	     private int selectedIndex = 0;
		public void valueChanged( ListSelectionEvent event )
		{    
		     if ( count == 3 )
		          count = 0;
		          
		     if ( event.getValueIsAdjusting()) // avoid duplicated selection
		          return;

		     selectedIndex = cardsList.getSelectedIndex();
		     
		     // check if the player choose a card twice, show error message
		     if ( isDuplicated(selectedIndex) )
		     {
		          messageDisplay.setText("You cannot choose the same card twice!!! Please choose another card!");
		          gotItButton.setVisible(true);
		          return;
		     }
		     
			selectedIndices[count] = selectedIndex;

			updateMessage(messageDisplay);

               System.out.println("Index "+selectedIndex+" selected");
			count++;
			
		}

		// reset the count
		public void resetCount()
		{
		     count = 0;
		}
	}

     // reset the array of selected Indices
     private void clearSelectedIndices()
     {
          for (int i=0;i<3;i++)
		     selectedIndices[i] = -1;
     }

     // check if three cards are selected
     private boolean selectedThreeCards()
     {
          for (int i=0;i<3;i++)
          {
               if ( selectedIndices[i] == -1 )
                    return false;
          }
          return true;
	}

     // check if the combination is valid
     private boolean isCombinationValid()
     {
          String[] types = new String[3];
          
          for ( int i=0; i < 3; i++)
          {
               types[i] = playersHand.get(selectedIndices[i]).getType();
          }

          if ( types[0].equals(types[1]) && types[1].equals(types[2]) )
               return true; // three of a type: valid

          if ( !(types[0].equals(types[1])) && !(types[1].equals(types[2])) && !(types[0].equals(types[2])) )
               return true; // three different types: valid

          return false; // any other case: invalid
          
     }

     // check if the player chose the same card twice
     // param : the newly selected index 
	private boolean isDuplicated(int newSelection)
	{
	     for ( int i : selectedIndices )
	     {
	          if ( i == newSelection )
	               return true;
	     }
	     return false;
	}

     // update the message that shows selected Indices
     // param : the JLabel that displays the message
	private void updateMessage(JLabel message)
	{
	     message.setText("Selected Indices: ["+selectedIndices[0]+"] ["+selectedIndices[1]+"] ["+selectedIndices[2]+"]");
	}
}
