/**
 * CSC190 Pacman Term Project
 * @author Ryan LaRouche
 */

package engine;

// Required package imports for UI and input
import game.PacmanRemastered.Game;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class Engine implements KeyListener, MouseListener, MouseMotionListener {

	protected boolean over = false;         // Track whether the game is over
	protected int delay = 30;		// Track the update delay
	public int width = 500;			// Width of game frame/canvas
	public int height = 500;		// Height of game frame/canvas
	protected String title = "Placeholder Title";	// Name of game
        
	// Assessor methods
	public String getTitle() { return title; }	// Return title of game
	public long getDelay() { return delay; }		// Return length of delay
	public int getWidth()  { return width; }		// Return width of game frame/canvas
	public int getHeight() { return height; }	// Return height of game frame/canvas
	
        // Use constructor to initialize game
	abstract public void update();              // Updates canvas
	abstract public void render(Graphics g);    // Draws canvas
	
        // Builds a new engine game session
        public static void main(String[] args) {
            EngineBuild.build(new Game());  // Build 'Pacman Remastered' game
        }
	// Assessor method to check whether game is over
	public boolean gameOver() {
		return over;
	} // gameOver
	
	// Below is a list of all key and mouse listening events:
        @Override
	public void mouseDragged(MouseEvent event) {
            
	}

        @Override
	public void mouseMoved(MouseEvent event) {
            System.out.print(event.getClickCount());
	}

        @Override
	public void mouseClicked(MouseEvent event) {
	}

        @Override
	public void mouseEntered(MouseEvent event) {
	}

        @Override
	public void mouseExited(MouseEvent event) {
	}

        @Override
	public void mousePressed(MouseEvent event) {
	}

        @Override
	public void mouseReleased(MouseEvent event) {
	}

        @Override
	public void keyPressed(KeyEvent event) {
                System.out.print(event.getKeyChar());
	}

        @Override
	public void keyReleased(KeyEvent event) {
	}

        @Override
	public void keyTyped(KeyEvent event) {
	}
}