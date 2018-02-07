/**
 * 
 * @author 		Ryan LaRouche
 */

package org.game.engine;

public class GameLoop extends Thread {
	
	private Game game;
	private GameCanvas canvas;

	public GameLoop(Game game, GameCanvas canvas) {
		this.game = game;
		this.canvas = canvas;
	}
	
	@Override
	// Describe how a game is to be run in the engine
	public void run() {
		// Start the game
		game.init();
		
		// While the game is not over, update and repaint the canvas
		// every getDelay interval
		while(!game.isOver()) {
			game.update();
			canvas.repaint();	
			try {
				Thread.sleep(game.getDelay());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	} // run
}
