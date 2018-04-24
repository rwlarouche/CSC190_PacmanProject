/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game.PacmanRemastered.Map;

import engine.Map.Map2DTile;
import engine.Map.Map2DTileEvent;
import engine.Sprite;

/**
 *
 * 
 */
public class PacTileEmpty extends Map2DTile{

    public PacTileEmpty(Map2DTile up, Map2DTile down, Map2DTile left, Map2DTile right, Sprite... initEntities) {
        super(up, down, left, right, initEntities);
    }

    @Override
    public void onMapEvent(Map2DTileEvent e){
        
    }
    
    @Override
    public String getTileImagePath() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected boolean canEnterTile(Sprite entity) {
        return true;//Anything can enter a blank tile, and the only add method other classes can't use already checks if two of the same entity enter the tile.
    }

    @Override
    protected boolean doAddSprite(Sprite entity) {
        return sprites.add(entity);
    }

    @Override
    protected boolean doRemoveSprite(Sprite entity) {
        return sprites.remove(entity);
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getTileImageX() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getTileImageY() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
