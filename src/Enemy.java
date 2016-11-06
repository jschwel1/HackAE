import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.ImageIcon;

public class Enemy extends Character{
	
	
	Point speed;
	Point loc;
	ImageIcon img;
	
	public Enemy(Point loc){
		super(loc, new ImageIcon("src/ENEMY.png"), new Point(-1, 0));
	}
	

	
	public void paintEnemy(Component c, Graphics g){
		img.paintIcon(c, g, (int)loc.getX(), (int)loc.getY());
		
	}
}
