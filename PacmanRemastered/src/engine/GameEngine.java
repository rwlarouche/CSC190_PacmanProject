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
    // ======= DATA MEMBERS =======
    Game game;                          // Require game as a data member to use its objects
 
    Pane canvas;                        // Main canvas that the game is drawn on
    
    Direction key = Direction.RIGHT;               // Track key pressed (Supports Left, Right, Up, Down)
    
    ArrayList<Sprite> sprites;          // ArrayList of sprites
    ArrayList<ImageView> sprite_images; // ArrayList of ImageViews assoc. with each sprite
    ArrayList<ImageView> tile_images; 
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
        sprites.add(sprite);
    }
    
    /**
     * Removes a sprite from the ArrayList of game sprites (i.e. on death)
     * Also deletes the ImageView of the object
     * @param sprite The sprite to be removed
     */
    public void removeSprite(Sprite sprite) {
        int index = sprites.indexOf(sprite);
        sprites.remove(sprite);
        
        ImageView image = sprite_images.get(index);
        if (image != null) {
            canvas.getChildren().remove(image);
            image.imageProperty().set(null);
            sprite_images.remove(index);
        }
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
        this.sprites = game.getSprites();
        this.sprite_images = new ArrayList<>();
        this.tile_images = new ArrayList<>();
        this.other_images = new ArrayList<>();
    }
    
    /**
     * Check sprite collisions
     */
    private void collisionDetection(){
        for (int i = 0; i<sprites.size(); i++) {
                Map2DTile t1 = sprites.get(i).getMapTile();
            for (int j = 0; j<sprites.size(); j++) {
                if (i != j && t1.contains(sprites.get(j))) {
                    sprites.get(i).collide(sprites.get(j));
                    sprites.get(j).collide(sprites.get(j));
                }
            }
        }
    }
    
    /**
     * Update any sprites by calling their update methods
     */
    private void update(){
        for (int i = 0; i<sprites.size(); i++){
            sprites.get(i).update();
        }
        
    }
    
    /**
     * Redraw sprite animations
     */
    private void drawAll() {
        for (int i = 0; i<sprites.size(); i++){
            if ((i+1) > sprite_images.size()) {
                sprite_images.add(new ImageView());
                canvas.getChildren().add(sprite_images.get(i));
            }
            sprites.get(i).draw(i, this);
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
                new KeyFrame(Duration.millis(10), e -> update()),   // Update sprites
                new KeyFrame(Duration.millis(10), e -> collisionDetection())    // Check collisions
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
    public void drawImage(int index, String picname, int x, int y, int w, int h){
        // Return if invalid index
        if (index > other_images.size() -1) return;
        
        // Add image if not image set
        if (other_images.get(index).getImage() == null) try {
            other_images.get(index).setImage(new Image(new FileInputStream(picname)));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        other_images.get(index).setFitWidth(w);       // Set image height
        other_images.get(index).setFitHeight(h);      // Set image width
        
        other_images.get(index).setTranslateX(x);      // Set Y coord
        other_images.get(index).setTranslateY(y);      // Set X coord
    }
        
    @Override
    public void drawSprite(int index, String picname, double x, double y, int w, int h, int fx, int fy){
        // Return if invalid index
        if (index > sprite_images.size() -1) return;
        
        try {
            Image img = new Image(new FileInputStream(picname));
            
             // Add image if not image set
            if (sprite_images.get(index).getImage() == null) sprite_images.get(index).setImage(new Image(new FileInputStream(picname)));
            
            double fw = img.getWidth();
            double fh = img.getHeight();
        
            // Determine sprite frame
            Rectangle2D frame = new Rectangle2D(fx*(fw/w), fy*(fh/h), fw/w, fh/h);
            
            sprite_images.get(index).setTranslateX(x);      // Set Y coord
            sprite_images.get(index).setTranslateY(y);      // Set X coord
            sprite_images.get(index).setViewport(frame);    // Set sprite frame
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
