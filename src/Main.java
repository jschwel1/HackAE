import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Main {
	public static void main(String args[]){
		JFrame frame = new JFrame("Get to the Polls");
		Container window = frame.getContentPane();
		Game g = new Game();
		window.addKeyListener(g);
		frame.addKeyListener(g);
		window.add(g);
		
		frame.setSize(Game.SCREEN_W, Game.SCREEN_H+29);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.revalidate();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		
		System.out.println("It's a go at main");
		
		g.play();
	}
}
