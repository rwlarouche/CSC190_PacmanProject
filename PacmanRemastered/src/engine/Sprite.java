/*
 * Sprite.java
 * An interface that can be implemented by all game characters/sprite to implement basic
 * updating/drawing performed by the GameEngine every frame
 * Additionally allows interoperability with the map tile system.
 */
package engine;

import engine.Map.Map2DTile;
import engine.Map.Map2DTileEventListener;

public interface Sprite extends Map2DTileEventListener{
    public void update();
    public void draw(API api);
    public double getDrawX();
    public double getDrawY();
    public void setX(double newX);
    public void setY(double newY);
    public Direction getDirection();
    public Map2DTile getMapTile();
    public void setMapTile(Map2DTile tile);
    public void collide(Sprite sprite);
}
