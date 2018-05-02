package game.PacmanRemastered;

import engine.Direction;
import engine.GameEngine;
import engine.Map.Events.TileSpriteTraverseEvent;
import engine.Map.Map2DTile;
import engine.Map.Map2DTileEvent;
import engine.Sprite;

public class Pacman implements Sprite {

    // ======= DATA MEMBERS =======
    Game game;

    final double STEP = 8;     // Movement speed of pacman	
    int frame;              // Track frame within sprite-sheets

    Direction dir;             // Corresponds to direction pacman is moving
    int dir_num;            // Corresponds to sprite frame direction

    double x, y;               // Tracks x,y location/coordinates of Pacman

    String pic = "images/PacmanSprite.png";

    double width;
    double height;
    Map2DTile mapTile;
    int tileW = 64;
    int tileH = 64;
    // ============================

    // ======= CONSTRUCTOR ========
    /**
     * Constructor for Pacman character
     *
     * @param game Instance of game
     */
    public Pacman(Game game) {
        frame = 0;                  // Initial frame
        x = y = 128;
        dir = Direction.RIGHT;    // Initial control direction
        dir_num = 39;       // Used by sprite to determine animation direction

        this.game = game;
        //setMapTile(this.game.map.getTile(10,10));
        width = game.width;
        height = game.height;
    } // constructor
    // ============================

    // ===== ACCESSOR METHODS =====
    /**
     * Returns the x coordinate of the Pacman character
     *
     * @return An int x coordinate
     */
    @Override
    public double getX() {
        return x;
    }

    /**
     * Returns the y coordinate of the Pacman character
     *
     * @return An int y coordinate
     */
    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }
    // ============================

    // ====== SPRITE METHODS ======
    /**
     * Updates Pacman position based on movement direction
     */
    @Override
    public void update() {
        if (game.map != null) {
            tileW = game.map.tileDrawW;
            tileH = game.map.tileDrawH;
        }

        // Get current key
        dir = game.getKey();

        // Check is key is a valid movement direction (pacman is fixed along a 64x64 grid)
        if (x % tileW == 0 && y % tileH == 0) {
            switch (dir) {
                case LEFT:
                    dir_num = 0;
                    break;
                case UP:
                    dir_num = 1;
                    break;
                case RIGHT:
                    dir_num = 2;
                    break;
                case DOWN:
                    dir_num = 3;
                    break;
            }
        } else if (x % tileW != 0) {
            switch (dir) {
                case LEFT:
                    dir_num = 0;
                    break;
                case RIGHT:
                    dir_num = 2;
                    break;
            }
        } else {
            switch (dir) {
                case UP:
                    dir_num = 1;
                    break;
                case DOWN:
                    dir_num = 3;
                    break;
            }
        }

        // Handle movement in same direction if pacman could not turn in new dir
        // Reset key in game to prev key becuase new key is invalid
        switch (dir_num) {
            case 0:
                x -= STEP;
                break;
            case 1:
                y -= STEP;
                break;
            case 2:
                x += STEP;
                break;
            case 3:
                y += STEP;
                break;
        }

        /*        
        // Limit x bounds from moving out of frame
        if (x < 0) {
            x = 0;
        } else if (x > width - 64 - STEP) {
            x = width - 64 - STEP;
        }
        
        // Limit y bounds from moving out of frame
        if (y < 0) {
            y = 0;
        } else if (y > height - 64 - STEP) {
            y = width - 64 - STEP;
        }
         */
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

    @Override
    public Map2DTile getMapTile() {
        return mapTile;
    }

    @Override
    public void setMapTile(Map2DTile tile) {
        this.mapTile = tile;
    }

    @Override
    public void onMapEvent(Map2DTileEvent e) {
        if (e instanceof TileSpriteTraverseEvent) {
            System.out.println("Testing");
        }
    }

    @Override
    public void collide(Sprite sprite) {
        System.out.print("Pacman hit");
    }
}
