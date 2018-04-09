/**
 * Core game script to the PacmanRemastered game
 * Runs using the engine package
 * 
 * CSC190 Pacman Term Project
 * @author Ryan LaRouche
 */
package game.PacmanRemastered;

import engine.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Game extends Engine { 
    // Class properties
    GamePacman pacman;
    
        
    /**
     * Initializes initial game properties
     *  - Title
     *  - Window dimensions (width/height)
     *  - Necessary game components (i.e.Game Characters)
     */
    public Game(){
        title = "Pacman Remastered"; // Game title
        width = height = 500;       // Game window dimensions
        pacman = new GamePacman(this);
    } // constructor
    
    /**
     * Runs the main components of the PacmanRemastered game
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      // Builds a new engine game session for this game
      EngineBuild.build(new Game());
    }
    
    /**
     * Attempts to load BufferedImage asset with File passed as a parameter (i.e.A sprite)
     * @param path File path pointing to asset being loaded
     * @return Buffered imaged that was loaded, or null if unable to load
     */
    public static BufferedImage loadAsset(String path) {
        try {
            BufferedImage asset = ImageIO.read(new File(path));
            return asset;
	} catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public void update() {
        pacman.update();
    }              // Updates canvas
    
    @Override
    public void render(Graphics g) {
        pacman.render(g);
    }    // Draws canvas
}
