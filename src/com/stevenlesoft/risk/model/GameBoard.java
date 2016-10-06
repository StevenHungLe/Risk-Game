/**
  * CSCI 2120 Fall 2014
  * Risk Game Class GameBoard
  * Authors: Hung Le, Jeanne Vu 
  * Date: 11-04-2014
  **
  * Class:	 GameBoard specifies the board used in the game
  * Roles:	 the field on where the game takes place
  * Composition: contained in Game object
  *		 contain Continent objects and Territory objects
  **/
package com.stevenlesoft.risk.model;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;

public class GameBoard
{
   // Instance Variables
	private HashMap<String,Continent> continentsMap;
	private HashMap<String,Territory> territoriesMap;

   // Constructor
	public GameBoard(HashMap<String,Continent> continentsMap, HashMap<String,Territory> territoriesMap)
	{
		this.continentsMap = continentsMap ;
		this.territoriesMap = territoriesMap;
	}
	public GameBoard()
	{
		continentsMap= new HashMap<String,Continent>();
		territoriesMap= new HashMap<String,Territory>();

		HashMap<String, Territory> tempTerrMap = new HashMap<String, Territory>();
		
// NORTH AMERICA		
		tempTerrMap.put("Alaska",new Territory("Alaska","North America",new ArrayList<String>(Arrays.asList("Kamchatka","Northwest Territory","Alberta","Kamchatka"))));
		tempTerrMap.put("Northwest Territory",new Territory("Northwest Territory","North America",new ArrayList<String>(Arrays.asList("Greenland","Alaska","Alberta","Ontario"))));
		tempTerrMap.put("Alberta",new Territory("Alberta","North America",new ArrayList<String>(Arrays.asList("Alaska","Northwest Territory","Ontario","Western United States"))));
		tempTerrMap.put("Ontario",new Territory("Ontario","North America",new ArrayList<String>(Arrays.asList("Quebec","Northwest Territory","Alberta","Greenland","Western United States","Eastern United States"))));
		tempTerrMap.put("Quebec",new Territory("Quebec","North America",new ArrayList<String>(Arrays.asList("Ontario","Greenland","Eastern United States"))));
		tempTerrMap.put("Western United States",new Territory("Western United States","North America",new ArrayList<String>(Arrays.asList("Ontario","Eastern United States","Central America","Alberta"))));
		tempTerrMap.put("Eastern United States",new Territory("Eastern United States","North America",new ArrayList<String>(Arrays.asList("Ontario","Western United States","Quebec"))));
		tempTerrMap.put("Central America",new Territory("Central America","North America",new ArrayList<String>(Arrays.asList("Western United States","Venezuela"))));
		tempTerrMap.put("Greenland",new Territory("Greenland","North America",new ArrayList<String>(Arrays.asList("Quebec","Northwest Territory","Ontario","Iceland"))));
		
		continentsMap.put("North America", new Continent("North America",5,tempTerrMap));
		territoriesMap.putAll( tempTerrMap );
		
// SOUTH AMERICA
		tempTerrMap.clear();

		tempTerrMap.put("Venezuela",new Territory("Venezuela","South America",new ArrayList<String>(Arrays.asList("Central America","Argentina","Brazil"))));
		tempTerrMap.put("Brazil",new Territory("Brazil","South America",new ArrayList<String>(Arrays.asList("Venezuela","Argentina","North Africa"))));
		tempTerrMap.put("Argentina",new Territory("Argentina","South America",new ArrayList<String>(Arrays.asList("Venezuela","Brazil"))));

		continentsMap.put("South America", new Continent("South America",2,tempTerrMap));
		territoriesMap.putAll( tempTerrMap );

// AFRICA
		tempTerrMap.clear();

		tempTerrMap.put("North Africa",new Territory("North Africa","Africa",new ArrayList<String>(Arrays.asList("Brazil","Congo","Egypt", "Western Europe", "Southern Europe"))));
		tempTerrMap.put("Egypt",new Territory("Egypt","Africa",new ArrayList<String>(Arrays.asList("North Africa","Congo","East Africa","Southern Europe","Middle East"))));
		tempTerrMap.put("Congo",new Territory("Congo","Africa",new ArrayList<String>(Arrays.asList("North Africa","Egypt","East Africa","South Africa"))));
		tempTerrMap.put("East Africa",new Territory("East Africa","Africa",new ArrayList<String>(Arrays.asList("Congo","Madagascar","South Africa"))));
		tempTerrMap.put("South Africa",new Territory("South Africa","Africa",new ArrayList<String>(Arrays.asList("Congo","Madagascar","East Africa"))));
		tempTerrMap.put("Madagascar",new Territory("Madagascar","Africa",new ArrayList<String>(Arrays.asList("East Africa","South Africa"))));

		continentsMap.put("Africa", new Continent("Africa",3,tempTerrMap));
		territoriesMap.putAll( tempTerrMap );		

// EUROPE
		tempTerrMap.clear();

		tempTerrMap.put("Iceland",new Territory("Iceland","Europe",new ArrayList<String>(Arrays.asList("Greenland","Great Britain"))));
		tempTerrMap.put("Great Britain",new Territory("Great Britain","Europe",new ArrayList<String>(Arrays.asList("Iceland","Scandinavia","Northern Europe","Western Europe"))));
		tempTerrMap.put("Western Europe",new Territory("Western Europe","Europe",new ArrayList<String>(Arrays.asList("North Africa","Great Britain","Northern Europe","Southern Europe"))));
		tempTerrMap.put("Scandinavia",new Territory("Scandinavia","Europe",new ArrayList<String>(Arrays.asList("Great Britain","Ukraine"))));
		tempTerrMap.put("Northern Europe",new Territory("Northern Europe","Europe",new ArrayList<String>(Arrays.asList("Great Britain","Western Europe","Southern Europe","Ukraine"))));
		tempTerrMap.put("Southern Europe",new Territory("Southern Europe","Europe",new ArrayList<String>(Arrays.asList("Western Europe","Northern Europe", "Ukraine","North Africa","Egypt"))));
		tempTerrMap.put("Ukraine",new Territory("Ukraine","Europe",new ArrayList<String>(Arrays.asList("Northern Europe","Southern Europe","Scandinavia","Ural","Afghanistan","Middle East"))));
		
		continentsMap.put("Europe", new Continent("Europe",5,tempTerrMap));
		territoriesMap.putAll( tempTerrMap );
		
// ASIA
		tempTerrMap.clear();

		tempTerrMap.put("Ural",new Territory("Ural","Asia",new ArrayList<String>(Arrays.asList("Ukraine","Afghanistan","Siberia"))));
		tempTerrMap.put("Afghanistan",new Territory("Afghanistan","Asia",new ArrayList<String>(Arrays.asList("Ukraine","Ural","Siberia","China","India","Middle East"))));
		tempTerrMap.put("Middle East",new Territory("Middle East","Asia",new ArrayList<String>(Arrays.asList("Ukraine","Egypt","Afghanistan","India"))));
		tempTerrMap.put("Siberia",new Territory("Siberia","Asia",new ArrayList<String>(Arrays.asList("Ural","Afghanistan","China","Mongolia","Irkutsk","Yakutsk"))));
		tempTerrMap.put("China",new Territory("China","Asia",new ArrayList<String>(Arrays.asList("Mongolia","Siberia","Afghanistan","India","Siam"))));
		tempTerrMap.put("India",new Territory("India","Asia",new ArrayList<String>(Arrays.asList("Middle East","Afghanistan","China","Siam"))));
		tempTerrMap.put("Yakutsk",new Territory("Yakutsk","Asia",new ArrayList<String>(Arrays.asList("Siberia","Irkutsk","Kamchatka"))));
		tempTerrMap.put("Irkutsk",new Territory("Irkutsk","Asia",new ArrayList<String>(Arrays.asList("Kamchatka","Yakutsk","Siberia","Mongolia"))));
		tempTerrMap.put("Mongolia",new Territory("Mongolia","Asia",new ArrayList<String>(Arrays.asList("Japan","China","Siberia","Irkutsk","Kamchatka"))));
		tempTerrMap.put("Siam",new Territory("Siam","Asia",new ArrayList<String>(Arrays.asList("China","India","Indonesia"))));
		tempTerrMap.put("Kamchatka",new Territory("Kamchatka","Asia",new ArrayList<String>(Arrays.asList("Yakutsk","Irkutsk","Mongolia","Japan","Alaska"))));
		tempTerrMap.put("Japan",new Territory("Japan","Asia",new ArrayList<String>(Arrays.asList("Mongolia","Kamchatka"))));

		continentsMap.put("Asia", new Continent("Asia",7,tempTerrMap));
		territoriesMap.putAll( tempTerrMap );
		
// AUSTRALIA
		tempTerrMap.clear();

		tempTerrMap.put("Indonesia",new Territory("Indonesia","Australia",new ArrayList<String>(Arrays.asList("Siam","New Guinea","Western Australia"))));
		tempTerrMap.put("New Guinea",new Territory("New Guinea","Australia",new ArrayList<String>(Arrays.asList("Indonesia","Western Australia","Eastern Australia"))));
		tempTerrMap.put("Western Australia",new Territory("Western Australia","Australia",new ArrayList<String>(Arrays.asList("Indonesia","New Guinea", "Eastern Australia"))));
		tempTerrMap.put("Eastern Australia",new Territory("Eastern Australia","Australia",new ArrayList<String>(Arrays.asList("New Guinea","Western Australia","LotR"))));
		tempTerrMap.put("LotR",new Territory("LotR","Australia",new ArrayList<String>(Arrays.asList("Eastern Australia"))));

		continentsMap.put("", new Continent("Australia",2,tempTerrMap));
		territoriesMap.putAll( tempTerrMap );
		
	}

   // Method Headers
   	/**
	 * Returns a map of all the continents on the board
	 * @return HashMap of continentName-Continent reference mappings
	 **/
	public HashMap<String,Continent> getContinentsMap()
	{
		return continentsMap;
	}
	
	/**
	 * Returns a map of all the territories on the board
	 * @return HashMap of territoryName-Territory reference mappings
	 **/
	public HashMap<String,Territory> getTerritoriesMap()
	{
		return territoriesMap;
	}

	/**
	 * Returns a map of unoccupied territories on the board
	 * @return HashMap of territoryName-Territory reference mappings
	 **/
	public HashMap<String,Territory> getUnoccupiedTerritories()
	{
		HashMap<String,Territory> unoccupiedTerritories = new HashMap<String,Territory>();
		for ( Territory terr : territoriesMap.values() )
		{
			if (terr.getOccupant() == null )
				unoccupiedTerritories.put(terr.getName(),terr);
		}
		return unoccupiedTerritories;
	}
	
	/**
	 * Retrieves a reference to a territory by name
	 * @param the String containing the name of the territory to get
	 * @return a reference to the specified territory
	 **/
	public Territory getTerritoryByName( String territoryName )
	{
		return territoriesMap.get(territoryName);
	}
	
	/**
	 * Retrieves a reference to a continent by name
	 * @param the String containing the name of the continent to get
	 * @return a reference to the specified continent
	 **/
	public Continent getContinentByName( String continentName )
	{
		return continentsMap.get(continentName);
	}
	
} ///////////////////////////////////// end class GameBoard
