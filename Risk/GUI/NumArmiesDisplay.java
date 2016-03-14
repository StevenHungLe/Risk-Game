import javax.swing.JLabel;
import java.util.Observer;
import java.util.Observable;

public class NumArmiesDisplay extends JLabel implements Observer
{
	PlayersList playersList;
	
	public NumArmiesDisplay(PlayersList playersList)
	{
		//super("has "+Integer.toString(playersList.getPlayer().getNumArmies())+" armies left.");
		super("");
		this.playersList = playersList;
		
	}

	public void update( Observable obs, Object obj)
	{
		this.setText( "Number of armies available: "+Integer.toString(playersList.getPlayer().getNumArmies())+".");
	}	
}
