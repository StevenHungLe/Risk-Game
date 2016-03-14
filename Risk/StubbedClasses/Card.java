 /**
  * CSCI 2120 Fall 2014
  * Risk Game Class Card
  * Authors: Hung Le, Jeanne Vu 
  * Date: 10-21-2014
  **/

public class Card
{
   // Instance Variables
	private String type;
	private String territoryName;

   // Constructor
	public Card( String type, String territoryName )
	{
		this.type = type;
		this.territoryName = territoryName;
	}

   // Method Headers	
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
}

