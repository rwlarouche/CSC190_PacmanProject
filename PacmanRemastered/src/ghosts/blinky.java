/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghosts;

import engine.Direction;
import engine.GameEngine;
import engine.Map.Events.TileSpriteTraverseEvent;
import engine.Map.Map2DTile;
import engine.Map.Map2DTileEvent;
import engine.Sprite;
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
public class blinky implements Ghost{
    // Buffered image for the blinky
    Game game;

    //final double STEP = 8;     // Movement speed of pacman	
    int frame;              // Track frame within sprite-sheets

    Direction dir;             // Corresponds to direction pacman is moving
    Direction oldDir;
    int dir_num;            // Corresponds to sprite frame direction

    double x, y;               // Tracks x,y location/coordinates of Pacman

    String pic = "images/GhostSheet.png";
    double width;
    double height;
    Map2DTile mapTile;
    int tileW = 64;
    int tileH = 64;
    int speed = 2; //Ghost speed. Speed is incremented every time the player completes a level
    //int frame;
    //int dir;
    
    private int horizontal, vertical;
    private int size;
    
    boolean alive = true;
    
    public blinky(int x, int y, int s){
            frame = 0;
            horizontal = x;
            vertical = y;
            size = s;
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
    
    private boolean valid(int x, int y)
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

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw(GameEngine api) {
        // Frame is used by draw to parse through sprite-sheet
        frame += 1;
        if (frame > 2) {
            frame = 0;
        }
        api.drawSprite(this, pic, x, y, 3, 4, frame, dir_num);
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setX(double newX) {
        this.horizontal = horizontal;
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setY(double newY) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map2DTile getMapTile() {
        return mapTile;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMapTile(Map2DTile tile) {
       
        this.mapTile = tile;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void collide(Sprite sprite) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onMapEvent(Map2DTileEvent e) {
        if (e instanceof TileSpriteTraverseEvent) {
           // System.out.println("Testing");
        }
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Direction getDirection() {
        return dir;
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getDrawX() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getDrawY() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
