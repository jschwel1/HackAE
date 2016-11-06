import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.ImageIcon;

public class Character {
	
	private Point loc;
	private Point speed;
	private ImageIcon img;
	
	public Character(Point loc, ImageIcon i, Point speed){
		this.loc = loc;
		this.speed = speed;
		this.img = i;
	}
	
	public String toString(){
		return "L(" + this.getXLoc() + ", " + this.getYLoc() + ") -> S(" + speed.getX() + ", " + speed.getY() + ")";
	}
	
	public ImageIcon getImage(){
		return img;
	}
	
	public int getYLoc(){
		return (int)loc.getY();
	}
	public int getXLoc(){
		return (int)loc.getX();
	}
	
	/**
	 * 
	 * @param direction - Multiplier in either direction. Use -1 to move backwards/up or 1 to move down/forward
	 */
	public void move(Point direction){
		//System.out.println(super.toString());
		loc = new Point((int)(loc.getX() +speed.getX()*direction.getX()), (int)(loc.getY() + speed.getY()*direction.getY()));
	}
	
	public void paint(Component c, Graphics g){
		img.paintIcon(c, g, (int)loc.getX(), (int)loc.getY());
	}
	
	
}
