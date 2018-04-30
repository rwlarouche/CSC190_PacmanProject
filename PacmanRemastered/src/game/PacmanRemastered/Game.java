/**
 * Core game script to the PacmanRemastered game
 * Runs using the engine package
 * 
 * CSC190 Pacman Term Project
 * @author Ryan LaRouche, Craig, Nick, Jack, Scott
 */
package game.PacmanRemastered;

import engine.*;
import engine.Map.Map2D;
import java.util.ArrayList;

public class Game { 
    // ======= DATA MEMBERS =======
    protected String title;     // Window title
    protected double width;     // Window width
    protected double height;    // Window height
    protected Pacman pacman;    // Pacman character
    protected String key = "Right";     // String of name of last key pressed (Right default)
    protected ArrayList<Sprite> sprites;    // Array of game sprites
    public Map2D map;
    // ============================

    
    // ======== CONSTRUCTOR =======
    public Game() {
        title = "Pacman Remastered";
        width = 640;
        height = 640;
        pacman = new Pacman(this);
        sprites = new ArrayList<>();
        sprites.add(this.pacman);
    } // constructor
    // ============================

    // ====== ACCESSOR METHODS ====
    /**
     * Returns Pacman object/sprite
     * @return Pacman object
     */
    public Pacman getPacman(){
        return this.pacman;
    }
    
    /**
     * Returns game title
     * @return title string
     */
    public String getTitle(){
        return this.title;
    }
    
    /**
     * Returns game screen width
     * @return width double
     */
    public double getWidth(){
        return this.width;
    }
    
    /**
     * Returns game screen height
     * @return height double
     */
    public double getHeight(){
        return this.height;
    }
    
    public ArrayList<Sprite> getSprites() {
        return this.sprites;
    }
    
    /**
     * Returns current direction of pacman determined by KeyEvent handleKey
     */
    public String getKey() {
        return key;
    }
    // ============================
    
    // ====== SETTER METHODS ======
    public void setKey(String key) {
        this.key = key;
    }
    // ============================
}
