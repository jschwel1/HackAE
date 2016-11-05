import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
	public static void main(String args[]){
		JFrame frame = new JFrame("Get to the Polls");
		Container window = frame.getContentPane();
		Game g = new Game();
		
		window.add(g);
		
		frame.setSize(Game.SCREEN_W, Game.SCREEN_H);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.revalidate();
	}
}
