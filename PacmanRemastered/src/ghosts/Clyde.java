/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghosts;

<<<<<<< HEAD:PacmanRemastered/src/ghosts/blinky.java
import engine.Direction;
import engine.GameEngine;
import engine.Map.Map2DTile;
import engine.Map.Map2DTileEvent;
import engine.Sprite;
=======
import engine.Map.Map2DTile;
import game.PacmanRemastered.Game;
>>>>>>> 351ee77f03753001c00b9ae0bedeff249d6beaa2:PacmanRemastered/src/ghosts/Clyde.java
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
/**
 *
 * @author csc190
 */
<<<<<<< HEAD:PacmanRemastered/src/ghosts/blinky.java
public class blinky implements Ghost{
    // Buffered image for the blinky
    
    int speed = 2; //Ghost speed. Speed is incremented every time the player completes a level
=======
public class Clyde extends ghost implements GhostInterface{
    Game game;
    int speed = 2; 
>>>>>>> 351ee77f03753001c00b9ae0bedeff249d6beaa2:PacmanRemastered/src/ghosts/Clyde.java
    int frame;
    int dir;
    Map2DTile mapTile5;
    
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
   
    
    public double getX()
    {
        return horizontal;
    }
    

    public double getY()
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
<<<<<<< HEAD:PacmanRemastered/src/ghosts/blinky.java

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw(GameEngine api) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setX(double newX) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setY(double newY) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map2DTile getMapTile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMapTile(Map2DTile tile) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void collide(Sprite sprite) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onMapEvent(Map2DTileEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Direction getDirection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getDrawX() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getDrawY() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
=======
    
    public Map2DTile getMapTile() {
        return mapTile5;
    }

    private void setMapTile(Map2DTile tile5) {
        this.mapTile5 = tile5;
>>>>>>> 351ee77f03753001c00b9ae0bedeff249d6beaa2:PacmanRemastered/src/ghosts/Clyde.java
    }
}