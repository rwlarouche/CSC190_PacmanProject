/*
 * Sprite.java
 * An interface that can be implemented by all game characters/sprite to implement basic
 * updating/drawing performed by the GameEngine every frame
 */
package engine;

public interface Sprite {
    public void update();
    public void draw(int index, GameEngine api);
}
