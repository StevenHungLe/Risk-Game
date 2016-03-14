import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.File;
import java.util.Observable;
import javax.imageio.ImageIO;
import java.io.IOException;
import javax.swing.JComponent;


public class GameMap extends JComponent
{
	private Image image;
	
	public GameMap()
	{
	     try
	     {
		     image = ImageIO.read(new File("RiskBoard.jpeg"));
		     System.out.println("Image loaded!: ");
	     }
	     catch (IOException e)
	     {
		     System.out.println("Image not loaded!: " + e);
	     }
		//image = mapModel.getImage();
		image = image.getScaledInstance(1100,620,Image.SCALE_SMOOTH);
		repaint();
	}

	public void paintComponent(Graphics g)
	{
		g.drawImage(image, 0, 0, this);
	}

/*	
     private class Images
     {
	     private BufferedImage image;
	
	     public Images()
	     {
		     try{
			     image = ImageIO.read(new File("RiskBoard.jpeg"));
			     System.out.println("Image loaded!: ");
		     }
		     catch (IOException e){
			     System.out.println("Image not loaded!: " + e);
		     }
	     }

	     public Image getImage(){
		     return image.getScaledInstance(1100,620,Image.SCALE_SMOOTH);
	     }
	}
*/
}
