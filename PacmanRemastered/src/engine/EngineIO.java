package engine;

/**
 * EnginIO.java
 * Implements IO listeners
 * 
 * CSC190 Pacman Term Project
 * @author Ryan LaRouche
 */

import java.awt.Graphics;
import javax.swing.JComponent;

class EngineIO extends JComponent {
	private final Engine game;
	
	public EngineIO(Engine game) {
            this.game = game;
            addListeners(game);
            focus();
        } // constructor
	
        /**
         * Add needed listeners to game
         *  - Key listener  [ex.Key press]
         *  - Mouse Listener [ex.Mouse clicks]
         *  - Mouse Motion Listener [ex.Mouse movement]    
         * @param game 
         */
        public void addListeners(Engine game) {
            addKeyListener(game);
            addMouseListener(game);
            addMouseMotionListener(game);
        }
        
        /** 
         * Request window focus
         */
        public void focus() {
            requestFocus();
        }
        
        /** 
         * Render passed graphic argument object
         * @param g Graphic object to be rendered
         */
        @Override
	public void paintComponent(Graphics g) {
		game.render(g);
	} // render
}