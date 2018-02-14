/**
 * 
 * @author 		Ryan LaRouche
 */

package org.game.engine;

import javax.swing.JFrame;

public class GameApplication {

	// Function defines the Graphic UI set by the
	// engine when a game starts
	static public void start(Game game) {
		// Frame is a swing UI component referring to the game window
		JFrame frame = new JFrame(game.getTitle());
		frame.setSize(game.getWidth(), game.getHeight()); 	// Window dimensions
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GameCanvas canvas = new GameCanvas(game);
		frame.add(canvas);
		frame.setVisible(true);
		GameLoop loop = new GameLoop(game, canvas);
		loop.start();
	} // start
}
