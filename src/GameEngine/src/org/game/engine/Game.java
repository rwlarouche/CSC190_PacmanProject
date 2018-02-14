/**
 * 
 * @author 		Ryan LaRouche
 */

package org.game.engine;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class Game implements KeyListener, MouseListener, MouseMotionListener {

	protected boolean over;				// Track whether the game is over
	protected int delay = 30;			// Track the update delay
	protected int width = 300;			// Width of game frame/canvas
	protected int height = 300;			// Height of game frame/canvas
	protected String title = "My Game";	// Name of game
	
	
	// Assessor methods
	public String getTitle() { return title; }	// Return title of game
	public long getDelay() { return delay; }		// Return length of delay
	public int getWidth()  { return width; }		// Return width of game frame/canvas
	public int getHeight() { return height; }	// Return height of game frame/canvas
	
	abstract public void init();				// Initialize game
	abstract public void update(); 			// Updates canvas
	abstract public void draw(Graphics g);	// Draws canvas
	
	// Assessor method to check whether game is over
	public boolean isOver() {
		return over;
	} // isOver
	
	// Below is a list of all key and mouse listening events:	
	public void mouseDragged(MouseEvent arg0) {
	}

	public void mouseMoved(MouseEvent arg0) {
	}

	public void mouseClicked(MouseEvent arg0) {
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent arg0) {
	}

	public void mouseReleased(MouseEvent arg0) {
	}

	public void keyPressed(KeyEvent arg0) {
	}

	public void keyReleased(KeyEvent arg0) {
	}

	public void keyTyped(KeyEvent arg0) {
	}

}
