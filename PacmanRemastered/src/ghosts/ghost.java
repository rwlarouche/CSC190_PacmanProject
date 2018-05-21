/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghosts;

/**
 *
 * @author csc190
 */
abstract public class ghost {
    abstract public int getX();
    abstract public int getY();
    abstract public void setX(int x);
    abstract public void setY(int y);
    abstract public boolean move();
    abstract public boolean valid(int x, int y);
    abstract public int levelUp(int speed); 
}
