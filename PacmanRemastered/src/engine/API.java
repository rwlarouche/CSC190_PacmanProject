/*
 * API.java
 * API interface that acts as a middleman between the GameEngine and individual game
 * being run on engine. Allows for the game to run using the engine without platform
 * specific methods (i.e. JavaFX)
 * @author Ryan laRouche
 */
package engine;

import engine.Map.Map2D;
import engine.Map.Map2DTile;
import java.io.FileNotFoundException;

public interface API {
    /**
     * Draw the image specified by ImageView at coordinates x,y
     * @param sprite
     * @param picname Picture path
     * @param x Picture x coordinate
     * @param y Picture y coordinate
     * @param w Picture width
     * @param h Picture height
     */
    public void drawImage(Sprite sprite, String picname, int x, int y, int w, int h);
    
    /**
     * Draw the image specified by ImageView at coordinates x,y
     * @param sprite
     * @param picname Picture path
     * @param x Picture x coordinate
     * @param y Picture y coordinate
     * @param w Sprite width
     * @param h Sprite height
     * @param fx Sprite frame x
     * @param fy Sprite frame y
     */
    public void drawSprite(Sprite sprite, String picname, double x, double y, int w, int h, int fx, int fy);    
    
    
    /**
     * Draws a map tile into the given area.
     * @param tile Tile to use.
     * @param picname Location of picture to load.
     * @param x Horizontal location on board to place tile.
     * @param y Vertical location on board to place tile.
     * @param w Width of tile.
     * @param h Height of tile.
     * @param fx x location on sprite sheet for tile image.
     * @param fy y location on sprite sheet for tile image.
     */
    public void drawMapTile(Map2DTile tile, String picname, double x, double y, int w, int h, int fx, int fy);
    
    /**
     * Prepares an area for the map and sprites to be generated into. For example, in JavaFX, you would prepare a standard Pane to add it into.
     * @param map Map to draw.
     * @param x Place the top left corner of the map at this horizontal coordinate.
     * @param y Place the top left corner of the map at this vertical coordinate.
     * @param w Make the map area this width.
     * @param h Make the map area this height.
     */
    public void drawMapArea(Map2D map, double x, double y, double w, double h);
}
