import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements KeyListener, ActionListener{
	// Global static variables
	public final static int SCREEN_H = 600;
	public final static int SCREEN_W = 900;
	private final static int ROAD_H = 470;
	private final static int HOUSES_H = 100;
	private final static int TOTAL_DIST = 5000;
	private final static int NUM_ENEMIES = 20;
	private final static int FPS = 30;	
	private final static ImageIcon road = new ImageIcon("src/BACKGROUND.png");
	
	// global dynamic variablees
	private int dist;
	private boolean forward;
	private boolean backward;
	private boolean up;
	private boolean down;
	private Player player;
	private ArrayList<Enemy> enemyList;
//	private ArrayList<Character> things;
	public boolean gameOver, win;
	private Timer clock;
	

	/****************************************************************************************************
	 								CONSTRUCTOR
	 ***************************************************************************************************/
	public Game(){
		// Add the Key Listener to this class to read from this classes
		this.addKeyListener(this);
		
		// set defaults:
		dist = 0;
		forward = false;
		backward = false;
//		things = new ArrayList<Character>();
		enemyList = new ArrayList<Enemy>();
		gameOver = false;
		win = false;
		player = new Player(new Point(50, 300));
		clock = new Timer((int)(100*(1.0/FPS)), this);
		
		// randomly generate enemies
		for (int i = 0; i < NUM_ENEMIES; i++){
			// first and last screens are safe
			int x = (int)(Math.random()*(this.TOTAL_DIST-SCREEN_W) + 2*SCREEN_W);
			int y = (int)(Math.random()*(SCREEN_H-150))+100; //
			
			enemyList.add(new Enemy(new Point(x,y)));
		}
		enemyList = sort(enemyList);
		
		System.out.println("Created Enemies");
		this.repaint();
				
	}

	/****************************************************************************************************
	 								RESTART
	 ***************************************************************************************************/
	public void restart(){
		dist = 0;
//		things = new ArrayList<Character>();
		enemyList = new ArrayList<Enemy>();
		gameOver = false;
		win = false;
		player = new Player(new Point(50, 300));
		clock = new Timer((int)(100*(1.0/FPS)), this);
		
		// randomly generate enemies
		enemyList.clear();
		for (int i = 0; i < NUM_ENEMIES; i++){
			// first and last screens are safe
			int x = (int)(Math.random()*(this.TOTAL_DIST-SCREEN_W) + 2*SCREEN_W);
			int y = (int)(Math.random()*(SCREEN_H-150))+100; //
			
			enemyList.add(new Enemy(new Point(x,y)));
		}
		System.out.println("Created Enemies");
		this.repaint();
		
	}

	/****************************************************************************************************
	 								CHECK LOSE/WIN
	 ***************************************************************************************************/
	public void checkLose(){
		if (gameOver || win) return;
		
		if (player.getXLoc() < dist) gameOver = true;
		if (player.getXLoc() >= TOTAL_DIST) win = true;
		
		// check for collisions
		Rectangle pr = new Rectangle(player.getXLoc(), player.getYLoc(), player.getImage().getIconWidth(), player.getImage().getIconHeight());
		for(Enemy en: enemyList){
			Rectangle er = new Rectangle(en.getXLoc(), en.getYLoc(), en.getImage().getIconWidth(), en.getImage().getIconHeight());
			
			if (pr.intersects(er)){
				gameOver = true;
				return;
			}
		}
		
	}

	/****************************************************************************************************
	 								PLAY
	 ***************************************************************************************************/
	public void play(){
		repaint();
		clock.stop();
		while(!forward){System.out.println("Waiting");}
		clock.start();
	}


	/****************************************************************************************************
	 								PAINT COMPONENT
	 ***************************************************************************************************/
	public void paintComponent(Graphics g){
		g.setColor(Color.white);
		g.fillRect(0, 0, SCREEN_W, SCREEN_H);
		
		if (gameOver){
			g.setFont(new Font(Font.SERIF, Font.BOLD, 100));
			g.setColor(Color.green);
			g.drawString("GAME OVER!", 100, 300);
			return;
		}
		if (win){
			g.setFont(new Font(Font.SERIF, Font.BOLD, 100));
			g.setColor(Color.green);
			g.drawString("Congrats!", 100, 300);
			return;
		}
		
		// paint background
		road.paintIcon(null, g, SCREEN_W - (dist%SCREEN_W), 0);
		road.paintIcon(null, g, SCREEN_W - (dist%SCREEN_W)-road.getIconWidth(), 0);
		// paint all enemies on the screen
//		System.out.println("Attempting to paint " + enemyList.size() + " things");
		for (Character c: enemyList){
			if (c.getXLoc() > dist-100 && c.getXLoc() < (dist + SCREEN_W + 100)){
				
				c.getImage().paintIcon(null, g, c.getXLoc() - dist, c.getYLoc());
				
			}
		}
		player.getImage().paintIcon(null, g, player.getXLoc() - dist, player.getYLoc());
		
		if (TOTAL_DIST - dist < SCREEN_W*2){
			g.setColor(Color.black);
			g.fillRect(TOTAL_DIST-dist, 0, SCREEN_W, SCREEN_H);
		}
		
		
		// completion bar
		g.setColor(Color.black);
		g.fillRect(0, SCREEN_H-20, SCREEN_W, 20);
		g.setColor(Color.cyan);
		g.fillRect(1, SCREEN_H-19, (int)((SCREEN_W-1)*((double)player.getXLoc()/(double)TOTAL_DIST)), 18);
	}

	

	/****************************************************************************************************
	 								KEY PRESSED/RELEASED
	 ***************************************************************************************************/
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
//		System.out.println("Key Typed");
		if (code == KeyEvent.VK_RIGHT){
			forward = true;
		}
		if (code == KeyEvent.VK_LEFT){
			backward = true;
		}
		if (code == KeyEvent.VK_UP){
			up = true;
		}
		if (code == KeyEvent.VK_DOWN){
			down = true;
		}
		
		if (code == KeyEvent.VK_SPACE && (gameOver || win)){
			restart();

		}
		
	}

	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_RIGHT){
			forward = false;
		}
		if (code == KeyEvent.VK_LEFT){
			backward = false;
		}
		if (code == KeyEvent.VK_UP){
			up = false;
		}
		if (code == KeyEvent.VK_DOWN){
			down = false;
		}

	}
	

	/****************************************************************************************************
	 								SORT
	 ***************************************************************************************************/
	public ArrayList<Enemy> sort(ArrayList<Enemy> list){
		ArrayList<Enemy> sList = new ArrayList<Enemy>();
		sList.add(list.get(0));
		list.remove(0);
		while (list.size() > 0){
			
			boolean added = false;
			for(int i = 0; i < sList.size(); i++){
				if (list.get(0).getYLoc() < sList.get(0).getYLoc()) {
					added = true;
					sList.add(i, list.get(0));
					list.remove(0);
				}
				
			}
			if (!added){
				sList.add(list.get(0));
				list.remove(0);
			}
		}
		
		return sList;
	}
	
	
	public void keyTyped(KeyEvent e) {
		
	}
	/****************************************************************************************************
	 								ACTION PERFORMED
	 ***************************************************************************************************/
	public void actionPerformed(ActionEvent e) {
		// Move enemies
		for (Enemy en: enemyList){
			// if it's on the screen (or near the edges
			if (en.getXLoc() > dist-100 && en.getXLoc() < (dist + SCREEN_W + 100)){
				en.move(new Point(1,1));
			}
			
			// get rid of it if it no longer exists
			//if (en.getXLoc() < dist-100) enemyList.remove(en);
			
		}
//		System.out.println(enemyList.get(0).toString() + "  Dist:" + dist);		
		// move player	
		int x, y;
		if (forward == backward) x = 0;
		else if (forward) x = 1;
		else x = -1;
		
		if (up == down) y = 0;
		else if (up) y = -1;
		else y = 1;
		
		// prevent player from moving out of bounds
		if (player.getYLoc()+player.getImage().getIconHeight() > (HOUSES_H + ROAD_H) && y > 0) y = 0;
		else if (player.getYLoc() < HOUSES_H-player.getImage().getIconHeight()/2 && y <0) y = 0;
		
		player.move(new Point(x,y));
//		System.out.println(player.toString() + " x -> " + x + ", y-> " +y);
					
		// update distance
		dist++;
						
		this.repaint();
		
		checkLose();
		if (gameOver || win) {
			//clock.stop();
			repaint();
		}
		
	}
}
