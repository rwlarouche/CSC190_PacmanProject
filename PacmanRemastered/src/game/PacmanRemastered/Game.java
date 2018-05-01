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
import engine.Map.Map2DBuilder;
import engine.Map.Map2DTile;
import game.PacmanRemastered.Map.PacTileEmpty;
import game.PacmanRemastered.Map.PacTileWall;
import java.util.ArrayList;
import java.util.stream.Stream;

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
        map = null;
        pacman = new Pacman(this);
        sprites = new ArrayList<>();
        sprites.add(this.pacman);
    }// constructor
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
    
    /**
     * This needs to be changed to load different map files later.
     * Configures the game map. Can't be used during loading because the map object requires the game object to be constructed first.
     * @param api 
     */
    public void loadMap(API api){
        Map2DBuilder b = new Map2DBuilder();
        b.rootLevelPath = "";
        b.assetsRoot = "";
        b.tileSizeW = 64;
        b.tileSizeH = 64;
        b.game = this;
        b.mapGrid = PacTileEmpty.makeEmptyTileBoardArray(10, 10);
        b.mapGrid[4][4].add(getPacman());
        b.mapGrid[0] [2].add(new PacDot(this));
        b.mapGrid[6] [3].add(new PacDot(this));
        b.mapGrid[4] [4] = new PacTileWall();
        b.mapGrid[4] [5] = new PacTileWall();
        b.api = api;
        map = b.build();
        //Adds all sprites in the map to the sprite table.
        map.stream().map(Map2DTile::stream).reduce(Stream::concat).get().filter((e) ->{ return !sprites.contains(e);}).forEachOrdered(sprites::add);
    }
}
