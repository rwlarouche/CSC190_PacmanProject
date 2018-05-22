/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.PacmanRemastered;

import engine.Direction;
import engine.GameEngine;
import engine.Map.Events.TileSpriteTraverseEvent;
import engine.Map.Map2DTile;
import engine.Map.Map2DTileEvent;
import engine.Sprite;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author csc190
 */
public class SpawnFruit implements Sprite{
    
    Game game;
    double x,y;
    static Timer spawnItem = new Timer();
    static int clock = 0;
    
    int width, length;
    Map2DTile mapTile2;
    int frame;
    
    String picture1 = "images/DotSprite.png";
    
    private static String fruitArray(){
        ArrayList<String> fruits = new ArrayList<String>();
        fruits.add("images/DotSprite.png");
        return null;
    }
    
    public SpawnFruit(Game game)
    {
        x=y=141;
        this.game = game;
        width = 64;
        length = 64;
    }
    
    public void changeGraphics()
    {
           // Start off with the first fruit
           // Every time a level is finished, increment the value of fruit by one for the next fruit
           // Not sure how many fruits we'll have, but for the very last one, don't change the value
    }
    
    public void spawn()
    {
        // Use the timer implemented in java - fruit should spawn every 40 seconds and disappear after 10 seconds
    }

    @Override
    public void update() {
        
    }
    
    public static void create(){
    TimerTask task;
    
    task = new TimerTask(){
        @Override
        public void run() {
            while(clock<10){
                clock++;
            }
            if(clock==10)
            {
             //   api.draw(this);
            }
            while(clock>10 && clock<25)
            {
                clock++;
            }
            if(clock==25)
            {
            //   mapTile2.remove(this);
            //    game.removeSprite(this);
                clock=0;
            }
        }
    };
        
    }
   

    @Override
    public double getDrawX() {
        return x;
    }

    @Override
    public double getDrawY() {
        return y;
    }

    @Override
    public void setX(double X) {
        this.x=x;
    }

    @Override
    public void setY(double Y) {
        this.y=y;
    }

    @Override
    public Direction getDirection() {
        return Direction.UP;
    }

    @Override
    public Map2DTile getMapTile() {
        return mapTile2;
    }

    @Override
    public void setMapTile(Map2DTile tile2) {
        this.mapTile2=tile2;
    }

    @Override
    public void collide(Sprite sprite) {
         
    }

    @Override
    public void onMapEvent(Map2DTileEvent e) {
        if(e instanceof TileSpriteTraverseEvent)
        {
            if (((TileSpriteTraverseEvent)e).sprite()==game.getPacman()){
                mapTile2.remove(this);
                game.removeSprite(this);
            }
        }
    }

    @Override
    public void draw(engine.API api) {
       api.drawSprite(this, picture1, x, y, 1, 1, frame, 0);
      }
}
