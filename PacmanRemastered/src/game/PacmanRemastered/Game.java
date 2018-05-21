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
import game.PacmanRemastered.Map.GhostZone;
import game.PacmanRemastered.Map.PacTileEmpty;
import game.PacmanRemastered.Map.PacTileWall;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class Game { 
    // ======= DATA MEMBERS =======
    protected String title;     // Window title
    protected double width;     // Window width
    protected double height;    // Window height
    protected int pelletCount;
    protected API api;
    protected Pacman pacman;    // Pacman character
    protected Direction key = Direction.RIGHT;     // String of name of last key pressed (Right default)
    protected ArrayList<Sprite> sprites;    // Array of game sprites
    protected Map2D map;
    // ============================

    
    // ======== CONSTRUCTOR =======
    public Game(API api) {
        this.api = api;
        title = "Pacman Remastered";
        width = 10;        // Number of tiles X
        height = 10;       // Number of tiles Y
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
    
    public Map2D getMap(){
        return map;
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
     * @return key Returns Direction enum {UP,DOWN,LEFT,RIGHT}
     */
    public Direction getKey() {
        return key;
    }
    // ============================
    
    // ====== SETTER METHODS ======
    public void setKey(Direction key) {
        this.key = key;
    }
    
    // Add new sprite into game
    public void addSprite(Sprite sprite){
        if (!sprites.contains(sprite)) sprites.add(sprite);
        if (sprite instanceof Pacman){
            if (pacman != null) removeSprite(pacman);
            pacman = (Pacman)sprite;
        }
            
        if (sprite instanceof PacDot || sprite instanceof PacPill)
            pelletCount++;
    }
    
    // Remove sprite from game
    public void removeSprite(Sprite sprite){
        if (sprites.contains(sprite)) sprites.remove(sprite); else return;
        if (sprite instanceof Pacman)
            pacman = null;
        else if (sprite instanceof PacDot || sprite instanceof PacPill){
            pelletCount--;
            if (pelletCount == 0)
                levelClear();
        }
    }
    
    public void levelClear(){
        api.togglePlaying();
        new ArrayList<>(sprites).forEach(this::removeSprite);
        loadMap(api);
        map.drawMap();
        setKey(Direction.RIGHT);
        api.togglePlaying();
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
        b.mapGrid = PacTileEmpty.makeEmptyTileBoardArray(this, 10, 10);

        String theString = "";

        File file = new File("map.txt");
        Scanner sc;
        
        try {
            sc = new Scanner(file);
            
            if(sc != null){
            theString = sc.nextLine();
        
            while (sc.hasNextLine()) {
            theString = theString + "\n" + sc.nextLine();
            

            char[] charArray = theString.toCharArray();
            
            for(int x = 0; x < charArray.length; x++){
                
                if(charArray[x] == 'w'){
                    
                    b.mapGrid[2][2] = new PacTileWall(); 
                    
               }
                else if(charArray[x] == 'p'){
                    b.mapGrid[0][0].add(new Pacman(this));
                }
                else if(charArray[x] == '.'){
                    b.mapGrid[0][2].add(new PacPill(this));
                }
            }}}
            else
                System.err.println("No Input.");
            
            
        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        //Map2DBuilder b = new Map2DBuilder();
        //b.rootLevelPath = "";
        //b.assetsRoot = "";
        //b.tileSizeW = 64;
        //b.tileSizeH = 64;
        //b.game = this;
        //b.mapGrid = PacTileEmpty.makeEmptyTileBoardArray(this, 10, 10);
        //b.mapGrid[0][0].add(new Pacman(this));
        //b.mapGrid[0] [2].add(new PacPill(this));
        //b.mapGrid[6] [3].add(new PacPill(this));
       // b.mapGrid[4] [3] = new PacTileWall();
       // b.mapGrid[4] [4] = new PacTileWall();
       // b.mapGrid[4] [5] = new PacTileWall();
       // b.mapGrid[5] [3] = new PacTileWall();
       // b.mapGrid[5] [5] = new PacTileWall();
        b.mapGrid[1][6] = new GhostZone(Direction.UP);
        b.mapGrid[1][7] = new GhostZone(Direction.UP);
        b.api = api;
        map = b.build();
        //Adds all sprites in the map to the sprite table.
        map.stream().map(Map2DTile::stream).reduce(Stream::concat).get().filter((e) ->{ return !sprites.contains(e);}).forEachOrdered(this::addSprite);
    }
}
