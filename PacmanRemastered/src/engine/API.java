/*
 * API.java
 * API interface that acts as a middleman between the GameEngine and individual game
 * being run on engine. Allows for the game to run using the engine without platform
 * specific methods (i.e. JavaFX)
 * @author Ryan laRouche
 */
package engine;

public interface API {
    /**
     * Draw the image specified by ImageView at coordinates x,y
     * @param index Index of picture in GameEngine ImageView ArrayList
     * @param picname Picture path
     * @param x Picture x coordinate
     * @param y Picture y coordinate
     * @param w Picture width
     * @param h Picture height
     */
    public void drawImage(int index, String picname, int x, int y, int w, int h);
    
    /**
     * Draw the image specified by ImageView at coordinates x,y
     * @param index Index of picture in GameEngine ImageView ArrayList
     * @param picname Picture path
     * @param x Picture x coordinate
     * @param y Picture y coordinate
     * @param w Sprite width
     * @param h Sprite height
     * @param fx Sprite frame x
     * @param fy Sprite frame y
     */
    public void drawSprite(int index, String picname, double x, double y, int w, int h, int fx, int fy);    
    
    public void drawMapTile(int index, String picname, double x, double y, int w, int h, int fx, int fy);
}
