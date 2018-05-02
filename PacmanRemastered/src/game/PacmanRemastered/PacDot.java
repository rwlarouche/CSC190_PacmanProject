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

/**
 *
 * @author csc190
 */
public class PacDot implements Sprite{

    Game game;
    double x,y;


    int width, length;
    Map2DTile mapTile1;
    int frame;
    int dir_num;
    
    String picture = "images/DotSprite.png";
    
    public PacDot(Game game)
    {
        x=y=131; // One temporary dot. Want a while loop that adds dots all aound the board based on the tile
        this.game = game;
        //setMapTile(this.game.map.getTile(5,5));
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
       if (sprite instanceof Pacman) game.removeSprite(this);
    }
    
}
