/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.Map;

import engine.Sprite;

/**
 * A tile that does not allow anything to enter it. Used for padding a map that was loaded with uneven rows and columns.
 * 
 */
public class NullTile extends Map2DTile{

    /**
     * 
     * @return 
     */
    @Override
    public String getTileImagePath() {
        return ":alpha255:";
    }

    @Override
    public boolean canEnterTile(Sprite entity) {
        return false;
    }

    @Override
    protected boolean doAddSprite(Sprite entity) {
        throw new UnsupportedOperationException("Not supported; null tile."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected boolean doRemoveSprite(Sprite entity) {
        throw new UnsupportedOperationException("Not supported; null tile."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update() {
        
    }

    @Override
    public void onMapEvent(Map2DTileEvent e) {
        
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
