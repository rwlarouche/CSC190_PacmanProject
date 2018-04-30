/**
 * Core game engine supporting JavaFX implementation of API and required methods
 * to run a game that is built to be platform independent
 * CSC190 Pacman Term Project
 * @author Ryan LaRouche, Craig, Nick, Jack, Scott
 */

package engine;

import engine.Map.Map2D;
import game.PacmanRemastered.Game;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
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
    // ======= DATA MEMBERS =======
    Game game;                          // Require game as a data member to use its objects
 
    Pane canvas;                        // Main canvas that the game is drawn on
    
    String key = "Right";               // Track key pressed (Supports Left, Right, Up, Down)
    
    ArrayList<Sprite> sprites;          // ArrayList of sprites
    ArrayList<ImageView> sprite_images; // ArrayList of ImageViews assoc. with each sprite
    
    protected int delay;                // Track the update delay
    protected double width;             // Width of game frame/canvas
    protected double height;            // Height of game frame/canvas
    protected String title;             // Name of game
    
    protected boolean over = false; // Track whether the game is over
    protected Timeline timer;
    
    //This needs to be set by the game itself.
    protected Map2D map = null;
    // ============================

    // ====== PRIVATE METHODS =====
    /**
     * Builds new game session using passed Game
     * @param game Game object
     */
    private void build(Game game){
        this.game = game;
        this.map = game.map;        
        this.width = game.getWidth();
        this.height = game.getHeight();
        this.title = game.getTitle();
        this.sprites = game.getSprites();
        this.sprite_images = new ArrayList<ImageView>();
    }
    
    /**
     * Check sprite collisions
     */
    private void collisionDetection(){
        
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
                this.key = "Right";
                break;
            case "Left":
                this.key = "Left";
                break;
            case "Up":
                this.key = "Up";
                break;
            case "Down":
                this.key = "Down";
                break;
        }
        game.setKey(this.key);
    }
    // ============================
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        build(new Game());  // Build new pacman game (set datamembers)
        
        canvas = new Pane();
        
        Scene scene = new Scene(canvas, this.height, this.width, Color.BLACK);

        primaryStage.setTitle(this.title);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent key) -> {
            handleKey(key);
        });

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
        if (index > sprite_images.size() -1) return;
        
        // Add image if not image set
        if (sprite_images.get(index).getImage() == null) try {
            sprite_images.get(index).setImage(new Image(new FileInputStream(picname)));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sprite_images.get(index).setFitWidth(w);       // Set image height
        sprite_images.get(index).setFitHeight(h);      // Set image width
        
        sprite_images.get(index).setTranslateX(x);      // Set Y coord
        sprite_images.get(index).setTranslateY(y);      // Set X coord
    }
        
    @Override
    public void drawSprite(int index, String picname, int x, int y, int w, int h, int fx, int fy){
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
    // ============================
}
