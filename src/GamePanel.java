import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public static final int PADDING = 32;
	
	public static final int WIDTH = 480;
	public static final int HEIGHT = BulletHell.HEIGHT - PADDING * 2;
	
	private float interpolation;
	private int playerX, playerY;
	
	protected Image playerSprite;
	
	public GamePanel() {
		
		setSize(WIDTH, HEIGHT);
		requestFocus();
		
		Player player = new Player();
		
		playerX = player.getX();
		playerY = player.getY();
		
		this.playerSprite = player.ii.getImage();
		
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher(new Dispatch());
	}
	
	public void setInterpolation(float interp) {
		interpolation = interp;
	}
	
	public void update() {
		
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		g.setColor(Color.BLACK);
		g.fillRect(PADDING, PADDING, WIDTH, HEIGHT - PADDING);
		
		g2d.drawImage(playerSprite, playerX, playerY, null);
		g.drawString("FPS: " + BulletHell.fps, 5, 14);
	}
	
	private class Dispatch implements KeyEventDispatcher {

		// 37 - 40: LEFT, UP, RIGHT, DOWN
		
		@Override
		public boolean dispatchKeyEvent(KeyEvent e) {
			if(e.getKeyCode() == 37) { 				// LEFT, Takes priority
				System.out.println("LEFT");
			} else if (e.getKeyCode() == 39) { 		// RIGHT
				System.out.println("RIGHT");
			}
			
			if(e.getKeyCode() == 38) { 				// UP, Takes priority
				System.out.println("UP");
			} else if (e.getKeyCode() == 40) { 		// DOWN
				System.out.println("DOWN");
			}
			
			return false;
		}
		
	}
}
