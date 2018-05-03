/**
 * Core game engine supporting JavaFX implementation of API and required methods
 * to run a game that is built to be platform independent
 * CSC190 Pacman Term Project
 * @author Ryan LaRouche, Craig, Nick, Jack, Scott
 */

package engine;

import engine.Map.Map2D;
import engine.Map.Map2DTile;
import game.PacmanRemastered.Game;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyEvent;

public class GameEngine extends Application implements API {
    Game game;                          // Require game as a data member to use its objects
 
    Pane canvas;                        // Main canvas that the game is drawn on
    
    Direction key = Direction.RIGHT;               // Track key pressed (Supports Left, Right, Up, Down)
    
//    ArrayList<Sprite> sprites;          // ArrayList of sprites
    Hashtable<Sprite,ImageView> sprites = new Hashtable<Sprite,ImageView>();
//    ArrayList<ImageView> sprite_images; // ArrayList of ImageViews assoc. with each sprite
    ArrayList<ImageView> tile_images;  //This also needs conversion to a hashtable.
    ArrayList<ImageView> other_images; 
    
    protected int delay;                // Track the update delay
    protected double width;             // Width of game frame/canvas
    protected double height;            // Height of game frame/canvas
    protected String title;             // Name of game
    
    protected boolean over = false; // Track whether the game is over
    protected Timeline timer;
    
    //This needs to be set by the game itself.
    protected Map2D map = null;
    // ============================

    // ====== PUBLIC METHODS ======
    /**
     * Adds a sprite to the ArrayList of game sprites
     * @param sprite The sprite object to be added
     */
    public void addSprite(Sprite sprite) {
        sprites.put(sprite, new ImageView());
        canvas.getChildren().add(sprites.get(sprite));
    }
    
    /**
     * Removes a sprite from the ArrayList of game sprites (i.e. on death)
     * Also deletes the ImageView of the object
     * @param sprite The sprite to be removed
     */
    public void removeSprite(Sprite sprite) {
        ImageView image = sprites.get(sprite);
               
        canvas.getChildren().remove(image);
        image.setImage(null);
        sprites.remove(sprite);
    }
    // ============================
    
    // ====== PRIVATE METHODS =====
    /**
     * Builds new game session using passed Game
     * @param game Game object
     */
    private void build(Game game){
        this.game = game;
        game.loadMap(this);
        this.map = game.map;        
        this.width = game.getWidth()*game.map.tileDrawW;
        this.height = game.getHeight()*game.map.tileDrawH;
        this.title = game.getTitle();
        this.tile_images = new ArrayList<>();
        this.other_images = new ArrayList<>();
    }
    
    /**
     * Checks if the Game classes' sprites match the Game Engine's sprites
     * Resolves differences (remove sprites that are not in game class but are in engine
     * , add sprites that are but are not in engine)
     */
    private void checkSprites() {
        ArrayList<Sprite> gameSprites = game.getSprites();
        
        // Add new sprites that are in Game but not engine
        for (Sprite sprite1 : gameSprites) {
            if (this.sprites.get(sprite1) == null) addSprite(sprite1);
        }
        
        // Remove sprites that are not in Game but are in engine
        Set<Sprite> spriteList = this.sprites.keySet();
        for (Sprite sprite2 : new ArrayList<Sprite>(spriteList)) {
            if (!gameSprites.contains(sprite2)) removeSprite(sprite2);
        }
    }
    
    /**
     * Check sprite collisions
     */
    private void collisionDetection(){
        Set<Sprite> spriteList = sprites.keySet();
        for (Sprite sprite1 : spriteList) {
                Map2DTile t1 = sprite1.getMapTile();
            for (Sprite sprite2 : spriteList) {
                if (sprite1 != sprite2 && t1.contains(sprite2)) {
                    sprite1.collide(sprite2);
                    sprite2.collide(sprite1);
                }
            }
        }
    }
    
    /**
     * Update any sprites by calling their update methods
     */
    private void update(){
        checkSprites();
        Set<Sprite> spriteList = sprites.keySet();
        for (Sprite sprite : spriteList){
            sprite.update();
        }
        
    }
    
    /**
     * Redraw sprite animations
     */
    private void drawAll() {
        Set<Sprite> spriteList = sprites.keySet();
        for (Sprite sprite : spriteList){
            sprite.draw(this);
        }
    }
    
    private void handleKey(KeyEvent event){
        String key = event.getCode().getName();
        switch(key) {
            case "Right":
                this.key = Direction.RIGHT;
                break;
            case "Left":
                this.key = Direction.LEFT;
                break;
            case "Up":
                this.key = Direction.UP;
                break;
            case "Down":
                this.key = Direction.DOWN;
                break;
        }
        game.setKey(this.key);
    }
    // ============================
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {        
        build(new Game());  // Build new pacman game (set datamembers)
        canvas = new Pane();
        
        Scene scene = new Scene(canvas, this.height, this.width, Color.ANTIQUEWHITE);

        primaryStage.setTitle(this.title);
        primaryStage.setScene(scene);
        primaryStage.show();

        map.drawMap();
        
        scene.addEventFilter(KeyEvent.KEY_PRESSED, this::handleKey);
        
        timer = new Timeline(                
                new KeyFrame(Duration.millis(100), e -> drawAll()), // Update drawing
                //new KeyFrame(Duration.millis(10), e -> collisionDetection()),    // Check collisions
                new KeyFrame(Duration.millis(10), e -> update())   // Update sprites
        );
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    // =========== API ============
    // Override all implemented API methods
    @Override
    public void drawImage(Sprite sprite, String picname, int x, int y, int w, int h){
        if (sprites.get(sprite) == null) {
            addSprite(sprite);
        }
        
        ImageView image = sprites.get(sprite);
        
        
        // Add image if not image set
        if (image.getImage() == null) try {
            image.setImage(new Image(new FileInputStream(picname)));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        image.setFitWidth(w);       // Set image height
        image.setFitHeight(h);      // Set image width
        
        image.setTranslateX(x);      // Set Y coord
        image.setTranslateY(y);      // Set X coord
    }
        
    @Override
    public void drawSprite(Sprite sprite, String picname, double x, double y, int w, int h, int fx, int fy){
        if (sprites.get(sprite) == null) {
            addSprite(sprite);
        }
        
        ImageView image = sprites.get(sprite);
        
        try {
            Image img = new Image(new FileInputStream(picname));
            
             // Add image if not image set
            if (image.getImage() == null) image.setImage(new Image(new FileInputStream(picname)));
            
            double fw = img.getWidth();
            double fh = img.getHeight();
        
            // Determine sprite frame
            Rectangle2D frame = new Rectangle2D(fx*(fw/w), fy*(fh/h), fw/w, fh/h);
            
            image.setTranslateX(x);      // Set Y coord
            image.setTranslateY(y);      // Set X coord
            image.setViewport(frame);    // Set sprite frame
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void drawMapTile(int index, String picname, double x, double y, int w, int h, int fx, int fy){
        // Return if invalid index
        if (index > tile_images.size() -1) {
            tile_images.add(new ImageView());
            canvas.getChildren().add(tile_images.get(index));
        }
        
        try {
            Image img = new Image(new FileInputStream(picname));
            
             // Add image if not image set
            if (tile_images.get(index).getImage() == null) tile_images.get(index).setImage(new Image(new FileInputStream(picname)));
            
            double fw = img.getWidth();
            double fh = img.getHeight();
        
            // Determine sprite frame
            Rectangle2D frame = new Rectangle2D(fx, fy, fx+w, fy+h);
            
            
            tile_images.get(index).setTranslateX(x);      // Set Y coord
            tile_images.get(index).setTranslateY(y);      // Set X coord
            tile_images.get(index).setViewport(frame);    // Set sprite frame
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }
    // ============================
}
