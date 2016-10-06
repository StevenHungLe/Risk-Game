package com.stevenlesoft.risk.view;
/**
 * CSCI 2120 Fall 2014
 * Risk Game Class AttackScreen
 * Authors: Hung Le, Jeanne Vu 
 * Date: 11-25-2014
 **
 * Class:	ImageComponent : the Jcomponent that contains an image
 * Composition: contained in PlayerTurnScreen
 **/
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
