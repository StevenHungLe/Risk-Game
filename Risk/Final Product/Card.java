/**
 * CSCI 2120 Fall 2014
 * Risk Game Class Card
 * Authors: Hung Le, Jeanne Vu 
 * Date: 11-04-2014
 **
 * Class:	Card specifies the Risk game cards
 * Roles:	trade in to receive armies for the game
 * Composition: contained in Deck object
 **/

public class Card
{
	private String type;
	private String territoryName;

	public Card( String type, String territoryName )
	{
		this.type = type;
		this.territoryName = territoryName;
	}

   	/**
	 * @return the type (infantry, cavalry, artillery) of the card as a String
	 **/
	public String getType()
	{
		return type;
	}
	
	/**
	 * @return a reference to the territory this card is matched to
	 **/
	public String getTerritoryName()
	{
		return territoryName;
	}
} ///////////////////////////////////// end class Card
