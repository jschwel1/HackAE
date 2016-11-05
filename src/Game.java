import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Game extends JPanel{
	// Global static variables
	public final static int SCREEN_H = 600;
	public final static int SCREEN_W = 900;
	private final static int ROAD_H = 400;
	private final static int TOTAL_DIST = 10000;
	
	// global dynamic variablees
	private int dist;
	
	public Game(){
		dist = 0;
		
				
	}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.black);
		g.fillRect(0, 0, 100, 100);
		
		// paint background
		// organize all enemies on the screen
		
	}
	
}
