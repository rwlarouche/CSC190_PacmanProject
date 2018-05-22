/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghosts;

import engine.Map.Map2DTile;
import game.PacmanRemastered.Game;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
/**
 *
 * @author csc190
 */
public class Clyde extends ghost implements GhostInterface{
    Game game;
    int speed = 2; 
    int frame;
    int dir;
    Map2DTile mapTile5;
    
    String pic = "images/PacmanSprite.png";
    
    private int horizontal, vertical;
    private int size;
    
    boolean alive = true;
    
    public Clyde(Game game,int x, int y, int s){
            super(game, x, y, s);
            frame = 0;
            horizontal = x;
            vertical = y;
            size = s;
            setMapTile(this.game.map.getTile(5,5));
       }
   
    public int getX()
    {
        return horizontal;
    }
    
    public int getY()
    {
        return vertical;
    }
    
    public void setX(int x)
    {
        horizontal = x;
    }
    
    public void setY(int y)
    {
        vertical = y;
    }
    
    public boolean valid(int x, int y)
    {
        boolean check = true;
        if(x==0 || x>size-1 || y==0 || y>size-1)
        {
            check = false;
        }
        return check;
    }
    
    public boolean move()
    {
        boolean canMove = true;
        while(alive==true){
        int movement = (int)(Math.random() * 4);
        if(movement==0) // Also add this condition to every direction: if the next space in the grid is not a wall
        {
            if(valid(horizontal-1, vertical)==false) // North
            {
                canMove=false;
            }
            else
            {
                horizontal=horizontal-1;
            }
        }
        if(movement==1) // South
        {
            if(valid(horizontal+1, vertical)==false)
            {
                canMove=false;
            }
            else
            {
                horizontal=horizontal+1;
            }
        }
        if(movement==2) // East 
        {
            if(valid(horizontal, vertical-1)==false)
            {
                canMove=false;
            }
            else
            {
                vertical=vertical-1;
            }
        }
        if(movement==3) //West
        {
            if(valid(horizontal, vertical+1)==false)
            {
                canMove=false;
            }
            else
            {
                vertical=vertical+1;
            }
        }
        }
        return canMove;
    }
    
    public void chase(int speed)
    {
        
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
    
    public Map2DTile getMapTile() {
        return mapTile5;
    }

    private void setMapTile(Map2DTile tile5) {
        this.mapTile5 = tile5;
    }
}
