/*
 * Direction.java
 * Used to track Sprite/Key direction
 */
package engine;

/**
 *
 * @author csc190
 */
public enum Direction {UP,DOWN,LEFT,RIGHT;
    public Direction reverse(){
        switch (this){
        case UP:
            return DOWN;
        case DOWN:
            return UP;
        case LEFT:
            return RIGHT;
        case RIGHT:
            return LEFT;
        default:
            throw new AssertionError(this.name());
}
    }
}