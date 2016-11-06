import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.ImageIcon;

public class Player extends Character{

	Point loc;
	ImageIcon img;
	
	public Player(Point loc){
		super(loc, new ImageIcon(Game.class.getResource("/Dude.png")), new Point(3, 1));
		
		loc = new Point(super.getXLoc(), super.getYLoc());
	}
	

	
	public void paint(Component c, Graphics g){
		img.paintIcon(c, g, (int)loc.getX(), (int)loc.getY());
	}
	
	
}
