import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.File;
import java.util.Observable;
import javax.imageio.ImageIO;
import java.io.IOException;
import javax.swing.JComponent;

public class ImageComponent extends JComponent
{
	private Image image;
	public ImageComponent(Image image){
		this.image = image;
		repaint();
	}

	public void paintComponent(Graphics g){
		g.drawImage(image, 0, 0, this);
	}
}
