/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghosts;

import engine.Direction;
import engine.GameEngine;
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
abstract public class Ghost implements Sprite{
    // Buffered image for the ghost
    Game game;
    int speed = 2; //Ghost speed. Speed is incremented every time the player completes a level
    int frame;
    int dir;
    
    
    
    Map2DTile mapTile3;
    
    private int horizontal, vertical;
    private int size;
    
    boolean alive = true;
    
    public abstract void doAI();
    
    public abstract int getGhostSpriteLocationX();
    
    public Ghost(Game game,int x, int y, int s){            
        this.game = game;
            frame = 0;
            horizontal = x;
            vertical = y;
            size = s;
       }
   
    @Override
    public double getDrawX() {
        return horizontal;
    }

    @Override
    public double getDrawY() {
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
    

    
    /**
     *
     * @param speed
     * @return
     */
    public int levelUp(int speed)
    {
        speed++;
        return speed;
    }
    
    /**
     *
     * @return
     */
    @Override
    public Map2DTile getMapTile() {
        return mapTile3;
    }

    @Override
    public void setMapTile(Map2DTile tile3) {
        this.mapTile3 = tile3;
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw(engine.API api) {
        api.drawSprite(this, "images/GhostSheet.png", getDrawX(), getDrawY(), getMapTile().getMap().tileDrawW, getMapTile().getMap().tileDrawH,
                getGhostSpriteLocationX() * getMapTile().getMap().tileDrawW, dir);
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
    public Direction getDirection() {
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
}
