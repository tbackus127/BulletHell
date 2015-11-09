import java.awt.Image;

import javax.swing.ImageIcon;

public class Player {

	public static int PLAYER_DIMENSION = 64;
	public static int FIRE_RATE = 4; // Every X Frames
	
	public int x, y, speed;
	public int dx, dy;
	public Image sprite;
	public ImageIcon ii;
	public boolean isShooting;

	public Player() {
		this.x = GamePanel.PADDING + (GamePanel.WIDTH / 2) - (PLAYER_DIMENSION / 2);
		this.y = GamePanel.HEIGHT - GamePanel.PADDING * 5;
		ii = new ImageIcon(this.getClass().getResource("player.png"));
	}
	
	public void setDX(int dx) {
		this.dx = dx;
	}
	
	public void setDY(int dy) {
		this.dy = dy;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
}
