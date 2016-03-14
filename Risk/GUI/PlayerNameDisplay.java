import javax.swing.JLabel;
import java.util.Observer;
import java.util.Observable;

public class PlayerNameDisplay extends JLabel implements Observer
{
	PlayersList playersList;
	
	public PlayerNameDisplay(PlayersList playersList)
	{
		//super("Player "+playersList.getPlayer().getName());
		super("");
		this.playersList = playersList;
		
	}

	public void update( Observable obs, Object obj)
	{
		this.setText( "Player: "+this.playersList.getPlayer().getName()+".");
	}	
}
