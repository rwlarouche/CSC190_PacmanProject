/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghosts;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author csc190
 */
public class blinky implements GhostInterface{
    // Buffered image for the blinky
    
    int speed = 2; //Ghost speed. Speed is incremented every time the player completes a level
    int frame;
    int dir;
    
    int x,y;
    int width;
    int height;
    
    boolean alive = true;
    
    public blinky(Game game){
            frame = 0;
            dir=KeyEvent.VK_UP;
       }
    
    public void move()
    {
        while(alive==true){
        int movement = (int)(Math.random() * 4);
        if(movement==0) // Also add this condition to every direction: if the next space in the grid is not a wall
        {
            dir=KeyEvent.VK_UP;
        }
        if(movement==1)
        {
            dir=KeyEvent.VK_DOWN;
        }
        if(movement==2) // Also add this condition to every direction: if the next space in the grid is not a wall
        {
            dir=KeyEvent.VK_LEFT;
        }
        if(movement==3)
        {
            dir=KeyEvent.VK_RIGHT;
        }
        }
    }
    
    public void chase(int speed)
    {
        /* Compare distance (or, #of tiles, between pacman and the ghost.
        If the distance between pacman and the ghost are close, theh ghost chases after pacman,
        */
    }
    
    public void goBack(int speed)
    {
        if(alive==false)
        {
            // Go back to the spawn point, should probably use dikjstra's algorithm
            // Also change buffer image
        }
    }
    
    public int levelUp(int speed)
    {
        // If the player completes a level, the speed of the ghost increases
        speed++;
        return speed;
    }
}
