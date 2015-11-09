import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class BulletHell {
	
	private boolean running = false;
	private boolean paused = false;
	private int frameCount = 0;
	
	public static int fps = 60;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	protected static GamePanel gamePanel;

	public BulletHell() {
		JFrame gameFrame = new JFrame("Danmaku WIP");
		Container cp = gameFrame.getContentPane();
		cp.setLayout(new BorderLayout());
		
		gamePanel = new GamePanel();
		gamePanel.setLayout(new GridLayout(2, 1));
		
		gameFrame.add(gamePanel, BorderLayout.CENTER);
		gameFrame.setSize(WIDTH, HEIGHT);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setResizable(false);
		gameFrame.setVisible(true);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		runGameLoop();
	}
	
	public static void main (String[] args) {
		BulletHell game = new BulletHell();
	}
	
	public void runGameLoop() {
		System.out.println("Loop Running...");
		Thread loop = new Thread() {
			public void run() {
				gameLoop();
			}
		};
		loop.start();
	}
	
	private void gameLoop() {
		final double GAME_HERTZ = 60.0;
		final double DELTA_TIME = 1000000000 / GAME_HERTZ;
		final int MAX_UPDATES = 5;
		
		double lastUpdateTime = System.nanoTime();
		double lastRenderTime = System.nanoTime();
		
		final double TARGET_FPS = 60;
		final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;
		
		int lastSecondTime = (int) (lastUpdateTime / 1000000000);
		
		while(this.running) {
			double now = System.nanoTime();
			int updateCount = 0;
			
			if(!paused) {
				while(now - lastUpdateTime > DELTA_TIME && updateCount < MAX_UPDATES) {
					gameUpdate();
					lastUpdateTime += DELTA_TIME;
					updateCount++;
				}
				
				// If game updates are hanging
				if(now - lastUpdateTime > DELTA_TIME) {
					lastUpdateTime = now - DELTA_TIME;
				}
				
				float interpolation = Math.min(1.0f, (float) ((now - lastUpdateTime) / DELTA_TIME));
				drawGraphics(interpolation);
				lastRenderTime = now;
				
				int thisSecond = (int) (lastUpdateTime / 1000000000);
	            if (thisSecond > lastSecondTime) {
	               //System.out.println("NEW SECOND " + thisSecond + " " + frameCount);
	               fps = frameCount;
	               frameCount = 0;
	               lastSecondTime = thisSecond;
	            }
	            
	            while ( now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS && now - lastUpdateTime < DELTA_TIME) {
	               Thread.yield();
	            
	               //This stops the app from consuming all your CPU. It makes this slightly less accurate, but is worth it.
	               //You can remove this line and it will still work (better), your CPU just climbs on certain OSes.
	               //FYI on some OS's this can cause pretty bad stuttering. Scroll down and have a look at different peoples' solutions to this.
	               try {Thread.sleep(1);} catch(Exception e) {} 
	            
	               now = System.nanoTime();
	            }
			}
		}
	}
	
	private void gameUpdate() {
		
	}
	
	private void drawGraphics(float interpolation) {
		gamePanel.setInterpolation(interpolation);
		gamePanel.repaint();
	}
}
