/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.PacmanRemastered;

import engine.GameEngine;
import engine.Map.Events.TileSpriteTraverseEvent;
import engine.Map.Map2DTile;
import engine.Map.Map2DTileEvent;
import engine.Sprite;
//import javax.swing.Timer;

/**
 *
 * @author csc190
 */
public class SpawnFruit implements Sprite{
    
    Game game;
    double x,y;
  //  protected Timer timer;
    
    int width, length;
    Map2DTile mapTile1;
    int frame;
    int dir_num;
    
    String picture = "images/DotSprite.png"; // use for now until we have a fruit sprite 
    // Images of fruit. Every fruit should be declared to a variable in an array
    public SpawnFruit(Game game){
    
    //timer = new timer(40,this);
    
    x=y=150;
    this.game = game;
    width = 64;
    length = 64;
    }
    
    @Override
    public double getX() {
        return x;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }
    
    public int getLength(){
        return length;
    }
    
    public int getWidth(){
        return width;
    }
    
    @Override
    public void update() {

    }

    @Override
    public void draw(GameEngine api) {
         api.drawSprite(this, picture, x, y, 1, 1, frame, dir_num);
    }

    @Override
    public Map2DTile getMapTile() {
        return mapTile1;
    }

    @Override
    public void setMapTile(Map2DTile tile1) {
        this.mapTile1=tile1;
    }

    @Override
    public void onMapEvent(Map2DTileEvent e) {

        if(e instanceof TileSpriteTraverseEvent)
        {
            if (((TileSpriteTraverseEvent)e).sprite()==game.getPacman()){
                mapTile1.remove(this);
                game.getSprites().remove(this);
            }
        }
    }
        
    @Override
    public void collide(Sprite sprite) {
       //if (sprite instanceof Pacman) game.removeSprite(this);
    }
        // Use the timer implemented in java - fruit should spawn every 40 seconds and disappear after 10 seconds

    public void changeGraphics()
    {
           // Start off with the first fruit
           // Every time a level is finished, increment the value of fruit by one for the next fruit
           // Not sure how many fruits we'll have, but for the very last one, don't change the value}
    }

    private void checkSprites() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}