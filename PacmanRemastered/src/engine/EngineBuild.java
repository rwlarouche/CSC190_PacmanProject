/**
 * EngineBuild.java
 * Starts a new game running off of the engine based on the passed game argument
 *      - Constructs a window (height x width dimensions)
 *      - Applies title to window
 *      - Adds a game canvas to window
 *
 * Once the game is built, begins running the game in a loop until closed
 * 
 * CSC190 Pacman Term Project
 * @author Ryan LaRouche
 */

package engine;

import javax.swing.JFrame;
import java.awt.Graphics;

public class EngineBuild {
        
        // no constructor
    
        /** 
        * Function defines the Graphic UI set by the engine when a game starts
        * @param game The game to be started
        */
	static public void build(Engine game) {
            // Frame is a swing UI component referring to the game window
            JFrame frame = new JFrame(game.getTitle());         // Game title
            frame.setSize(game.getWidth(), game.getHeight()); 	// Game window dimensions
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            EngineIO canvas = new EngineIO(game);
            frame.add(canvas);
            frame.setVisible(true);
            
            // Start a new play session for game build
            EngineRun session = new EngineRun(game, canvas);
            session.start();
	} // build
}

/**
 * Runs a game after it has been built by the engine
 * 
 * ** NOTE: This is included inside EngineBuild rather than an external class because it
 * should only be used by build() within EngineBuild once a game has been built
 * by the engine and is ready to run. **
 * 
**/
class EngineRun extends Thread {
    private Engine game;
    private EngineIO canvas;

    // When a game runs, it must have the game properties and canvas to be
    // used when rendering/displaying the game.
    public EngineRun(Engine game, EngineIO canvas) {
        this.game = game;
	this.canvas = canvas;
    }
	
    @Override
    // Runs a game after it has been built by the engine
    public void run() {
        // Start the game
        //game.init();
		
        // While the game is not over, update and repaint the canvas
        // every getDelay interval
        while(!game.gameOver()) {
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