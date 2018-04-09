package game.PacmanRemastered;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author csc190
 */
public class GamePacman {
        // Class properties
	final int STEP = 2;     // Movement speed of pacman	
	BufferedImage pacman;   // Will point to pacman sprite-sheet
        int frame;              // Track frame within sprite-sheets
	int dir;                // Corresponds to direction pacman is moving
	int x, y;               // Tracks x,y location/coordinates of Pacman
        
        int width;
        int height;
        
        public GamePacman(Game game) {
            frame = 0;                  // Initial frame
            x = y = 128;
            dir = KeyEvent.VK_RIGHT;    // Initial control direction
		
		try {
			pacman = ImageIO.read(new File("images/packman.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
            if (pacman == null) throw new NullPointerException();
            
            width = game.width;
            height = game.height;
        }
        
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
        
        public void render(Graphics g) {
            // Break sprite-sheet into single-sprite components
            // (frame/2) because frame goes to 6 (5) for smoother animations
            g.drawImage(pacman.getSubimage((frame/2)*30, (dir-37)*30, 28, 28), x, y, null);
        } // render
        
        // User input controls for pacman
	public void keyPressed(KeyEvent e) {
		dir = e.getKeyCode();
	} // keyPressed
}
