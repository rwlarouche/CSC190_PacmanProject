/**
 * 
 * @author 		Ryan LaRouche
 */

package org.game.engine;

import java.awt.Graphics;
import javax.swing.JComponent;

public class GameCanvas extends JComponent {
	private final Game game;
	
	public GameCanvas(Game game) {
		this.game = game;
		addKeyListener(this.game);
		addMouseListener(this.game);
		addMouseMotionListener(this.game);
		requestFocus();
	} // GameCanvas
	
	@Override
	public void paintComponent(Graphics g) {
		game.draw(g);
	} // paintComponent
}
