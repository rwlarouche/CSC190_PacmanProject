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
import java.util.HashMap;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class GameEngine extends Application implements API {
    Game game;                          // Require game as a data member to use its objects
 
    Pane parentPane;
    
    Pane mapPane;                        // Main canvas that the game is drawn on
    
    Direction key = Direction.RIGHT;               // Track key pressed (Supports Left, Right, Up, Down)
    
    // Hashtable tracking all sprites and their corresponding ImageViews
    HashMap<Sprite,ImageView> sprites = new HashMap<>();
    // Hashtable tracking all textboxes
    HashMap<UIElement, TextField> textboxes = new HashMap<>();
    // Hashtable tracking all buttons
    HashMap<UIElement, Button> buttons = new HashMap<>();
    
//    ArrayList<ImageView> sprite_images; // ArrayList of ImageViews assoc. with each sprite
    HashMap<Map2DTile,ImageView> mapTiles = new HashMap<>();
    ArrayList<ImageView> tile_images;  //This also needs conversion to a hashtable.
    
    ArrayList<ImageView> other_images; 
    
    protected int delay;                // Track the update delay
    protected double width;             // Width of game frame/canvas
    protected double height;            // Height of game frame/canvas
    protected String title;             // Name of game
    
    protected boolean playing = true;
    protected Timeline timer;
    
    //This needs to be set by the game itself.
    //protected Map2D map = null;
    // ============================

    // ====== PUBLIC METHODS ======
    /**
     * Adds a sprite to the ArrayList of game sprites
     * @param sprite The sprite object to be added
     */
    public void addSprite(Sprite sprite) {
        sprites.put(sprite, new ImageView());
        mapPane.getChildren().add(sprites.get(sprite));
    }
    
    /**
     * Removes a sprite from the ArrayList of game sprites (i.e. on death)
     * Also deletes the ImageView of the object
     * @param sprite The sprite to be removed
     */
    public void removeSprite(Sprite sprite) {
        ImageView image = sprites.get(sprite);
               
        mapPane.getChildren().remove(image);
        image.setImage(null);
        sprites.remove(sprite);
    }
    
    /**
     * Loads and returns FileInputStream object of a passed file path OR throws
     * exception if path not found
     * @param file
     * @return FileInputStream of loaded file
     * @throws FileNotFoundException 
     */
    public FileInputStream chooseFile(String file) throws FileNotFoundException {
        return new FileInputStream(file);
    }
    
    @Override
    public void addTextBox(UIElement textbox) {
        TextField tf = new TextField();
        tf.setText(textbox.getText());
        tf.setTranslateX(textbox.getX());
        tf.setTranslateY(textbox.getY());
        tf.setMinSize(textbox.getWidth(), textbox.getHeight());
        tf.setMaxSize(textbox.getWidth(), textbox.getHeight());
        
        textboxes.put(textbox, tf);
        parentPane.getChildren().add(textboxes.get(textbox));
    }
    
    @Override
    public void updateTextBox(UIElement textbox) {
        if (textboxes.containsKey(textbox)) {
           TextField tf = textboxes.get(textbox);
           tf.setText(textbox.getText());
        }
    }
    
    @Override
    public void removeTextBox(UIElement textbox) {
        TextField tf = textboxes.get(textbox);
               
        parentPane.getChildren().remove(tf);
        tf.clear();
        textboxes.remove(textbox);
    }
    
    @Override
    public void addButton(UIElement button) {
        Button b = new Button();
        b.setText(button.getText());
        b.setTranslateX(button.getX());
        b.setTranslateY(button.getY());
        b.setMinSize(button.getWidth(), button.getHeight());
        b.setMaxSize(button.getWidth(), button.getHeight());

        b.setOnAction((event) -> { button.doAction(); });
        
        buttons.put(button, b);
        parentPane.getChildren().add(buttons.get(button));
    }
    
    @Override
    public void removeButton(UIElement button) {
        Button b = buttons.get(button);
               
        parentPane.getChildren().remove(b);
        b.disarm();
        buttons.remove(button);    
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
        this.width = game.getWidth()*game.getMap().tileDrawW;
        this.height = game.getHeight()*game.getMap().tileDrawH;
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
        gameSprites.stream().filter((sprite1) -> (this.sprites.get(sprite1) == null)).forEachOrdered((sprite1) -> {
            addSprite(sprite1);
        });
        
        // Remove sprites that are not in Game but are in engine
        ArrayList<Sprite> spriteList = new ArrayList(sprites.keySet());
        spriteList.stream().filter((sprite2) -> (!gameSprites.contains(sprite2))).forEachOrdered((sprite2) -> {
            removeSprite(sprite2);
        });
    }
    
    /**
     * Check sprite collisions
     */
    private void collisionDetection(){
        Set<Sprite> spriteList = sprites.keySet();
        spriteList.forEach((sprite1) -> {
            Map2DTile t1 = sprite1.getMapTile();
            spriteList.stream().filter((sprite2) -> (sprite1 != sprite2 && t1.contains(sprite2))).map((sprite2) -> {
                sprite1.collide(sprite2);
                return sprite2;
            }).forEachOrdered((sprite2) -> {
                sprite2.collide(sprite1);
            });
        });
    }
    
    /**
     * Update any sprites by calling their update methods
     */
    private void update(){
        checkSprites();
        sprites.keySet().forEach((sprite) -> {
            sprite.update();
        });
        
    }
    
    /**
     * Redraw sprite animations
     */
    private void drawAll() {
        Set<Sprite> spriteList = sprites.keySet();
        spriteList.forEach((sprite) -> {
            sprite.draw(this);
        });
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
        build(new Game(this));  // Build new pacman game (set datamembers)
        parentPane = new Pane();

        
        Scene scene = new Scene(parentPane, this.height, this.width, Color.ANTIQUEWHITE);

        primaryStage.setTitle(this.title);
        primaryStage.setScene(scene);
        primaryStage.show();

        game.getMap().drawMap();
        
        scene.addEventFilter(KeyEvent.KEY_PRESSED, this::handleKey);
        
        timer = new Timeline(                
                new KeyFrame(Duration.millis(100), e -> {if (playing) drawAll();}), // Update drawing
                //new KeyFrame(Duration.millis(10), e -> collisionDetection()),    // Check collisions
                new KeyFrame(Duration.millis(10), e -> {if (playing) update();})   // Update sprites
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
    public void drawMapTile(Map2DTile tile, String picname, double x, double y, int w, int h, int fx, int fy){
        if (picname.equals(":alpha255:")) return; //For NullTile objects, or whenever nothing should be drawn.
        ImageView tileView = null;
        try {
            if (!mapTiles.containsKey(tile)){
                tileView = new ImageView(new Image(new FileInputStream(picname)));
                mapTiles.put(tile, tileView);
                mapPane.getChildren().add(tileView);
                
            }
            else tileView = mapTiles.get(tile);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        if (tileView == null) return;
        double fw = tileView.getImage().getWidth();
        double fh = tileView.getImage().getHeight();

        // Determine sprite frame
        Rectangle2D frame = new Rectangle2D(fx, fy, fx+w, fy+h);


        tileView.setTranslateX(x);      // Set Y coord
        tileView.setTranslateY(y);      // Set X coord
        tileView.setViewport(frame);    // Set sprite frame
    }
    
    @Override
    public void drawMapArea(Map2D map, double x, double y, double w, double h) {
        if (mapPane!=null)
            parentPane.getChildren().remove(mapPane);
        mapPane = new Pane();
        parentPane.getChildren().add(mapPane);
        mapPane.resizeRelocate(x, y, w, h);
    }
    
    @Override
    public void togglePlaying() {
        playing = !playing;
        //if (playing) timer.pause();
        //else timer.play();
    }
    // ============================
}