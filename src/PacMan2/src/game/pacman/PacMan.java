package game.pacman;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.game.engine.Game;
import org.game.engine.GameApplication;

public class PacMan extends Game {
	
	public static void main(String[] args) {
		// Start new game
		GameApplication.start(new PacMan());	
	} // main

	// Movement speed of pacman
	final int STEP = 2;
	
	BufferedImage pacman; // Will point to pacman sprite-sheet
	int frame; // Track frame within sprite-sheets
	int dir; // Corresponds to direction pacman is moving
	int x, y;
	
	// Initialize game (title, frame dimensions, images)
	@Override
	public void init() {
		title = "PacMan";
		frame = 0;
		dir = KeyEvent.VK_RIGHT;
		x = 300;
		y = 200;
//		width = height = 400;
		
		try {
			pacman = ImageIO.read(new File("images/packman.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // init
	
	@Override
	public void update() {
		// Frame is used by draw to parse through sprite-sheet
		frame++;
		if (frame > 5) {
			frame = 0;
		}
		switch(dir) {
		case KeyEvent.VK_LEFT:	// 37
			x -= STEP;
			break;
		case KeyEvent.VK_UP:		// 38
			y -= STEP;
			break;
		case KeyEvent.VK_RIGHT:	// 39
			x += STEP;
			break;
		case KeyEvent.VK_DOWN:	// 40
			y += STEP;
			break;
		}
		if (x < 0) { // Limit x bounds from moving out of frame
			x = 0;
		} else if (x > width - 28 - STEP) {
			x = width - 28 - STEP;
		}
		if (y < 0) {
			y = 0;
		} else if (y > height - 28 - STEP) {
			y = width - 28 - STEP;
		}
	} // update

	@Override
	public void draw(Graphics g) {
		// Break sprite-sheet into single-sprite components
		// (frame/2) because frame goes to 6 (5) for smoother animations
		g.drawImage(pacman.getSubimage((frame/2)*30, (dir-37)*30, 28, 28), x, y, null);
	} // draw
	
	// User input controls
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("Press");
		dir = e.getKeyCode();
	} // keyPressed
}
