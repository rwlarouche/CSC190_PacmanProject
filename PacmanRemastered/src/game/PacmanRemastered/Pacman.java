package game.PacmanRemastered;

import engine.GameEngine;
import engine.Sprite;

public class Pacman implements Sprite{
    // ======= DATA MEMBERS =======
    Game game;
        
    final int STEP = 8;     // Movement speed of pacman	
    int frame;              // Track frame within sprite-sheets
    
    String dir;             // Corresponds to direction pacman is moving
    int dir_num;            // Corresponds to sprite frame direction
    
    int x, y;               // Tracks x,y location/coordinates of Pacman
    String pic = "images/packman.png";
                
    int width;
    int height;
    // ============================
    
    // ======= CONSTRUCTOR ========
    /**
     * Constructor for Pacman character
     * @param game Instance of game
     */
    public Pacman(Game game) {
        frame = 0;                  // Initial frame
        x = y = 128;
        dir = "Right";    // Initial control direction
        dir_num = 39;       // Used by sprite to determine animation direction
        
        this.game = game;
        
        width = 500;
        height = 500;
    } // constructor
    // ============================
    
    // ===== ACCESSOR METHODS =====
    /**
     * Returns the x coordinate of the Pacman character
     * @return An int x coordinate
     */
    public int getLocationX() {
        return x;
    }
        
    /**
     * Returns the y coordinate of the Pacman character
     * @return An int y coordinate
     */
    public int getLocationY() {
        return y;
    }
    // ============================
    
    // ====== SPRITE METHODS ======
    /** 
     * Updates Pacman position based on movement direction
     */
    @Override
    public void update() {
        dir = game.getKey();
        switch(dir) {
            case "Left":
                x -= STEP;
                dir_num = 0;
                break;
            case "Up":
                y -= STEP;
                dir_num = 1;
                break;
            case "Right":
                x += STEP;
                dir_num = 2;
                break;
            case "Down":
                y += STEP;
                dir_num = 3;
                break;
        }
        
        // Limit x bounds from moving out of frame
        if (x < 0) {
            x = 0;
        } else if (x > width - 28 - STEP) {
            x = width - 28 - STEP;
        }
        
        // Limit y bounds from moving out of frame
        if (y < 0) {
            y = 0;
        } else if (y > height - 28 - STEP) {
            y = width - 28 - STEP;
        }
    } // update
            
    @Override
    public void draw(int index, GameEngine api) {
        // Frame is used by draw to parse through sprite-sheet
        frame += 1;
        if (frame > 2) {
            frame = 0;
        }

        api.drawSprite(index, pic, x, y, 3, 4, frame, dir_num);
    }
    // ============================
    
    // g.drawImage(pacman.getSubimage((frame/2)*30, (dir-37)*30, 28, 28), x, y, null);
}
