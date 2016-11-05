import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.Icon;

public class Enemy {
	
	
	Point speed;
	Point loc;
	Icon img;
	
	public Enemy(Point speed, Point loc, Icon img){
		this.speed = speed;
		this.loc = loc;
		this.img = img;
		
	}
	
	public void move(){
		loc.setLocation(loc.getX()+speed.getX(), loc.getX() + speed.getY());
	}
	
	public void paintEnemy(Component c, Graphics g){
		img.paintIcon(c, g, (int)loc.getX(), (int)loc.getY());
		
	}
}
