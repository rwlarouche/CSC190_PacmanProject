/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.PacmanRemastered.Map;

import engine.Map.Map2DTile;
import engine.Map.Map2DTileEvent;
import engine.Sprite;
import java.util.EnumSet;

/**
 *
 * 
 */
public class PacTileWall extends Map2DTile{

    @Override
    public String getTileImagePath() {
        return getMap().assetsRoot + "/images/pactiles.png";
    }
    
    @Override
    public int getTileImageX() {
        EnumSet<PacWallPresentStats> wallStats = EnumSet.noneOf(PacWallPresentStats.class);
        if (getUp() !=null && getUp() instanceof PacTileWall)
            wallStats.add(PacWallPresentStats.wallUp);
        if (getDown() !=null && getDown() instanceof PacTileWall)
            wallStats.add(PacWallPresentStats.wallDown);        
        if (getLeft() !=null && getLeft() instanceof PacTileWall)
            wallStats.add(PacWallPresentStats.wallLeft);
        if (getRight() !=null && getRight() instanceof PacTileWall)
            wallStats.add(PacWallPresentStats.wallRight);
        
        
        
        if (wallStats.containsAll(EnumSet.of(PacWallPresentStats.wallUp, PacWallPresentStats.wallDown, PacWallPresentStats.wallLeft, PacWallPresentStats.wallRight))){
            
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getTileImageY() {
        return 0; // Straight image.
    }

    @Override
    protected boolean canEnterTile(Sprite entity) {
        return false;
    }

    @Override
    protected boolean doAddSprite(Sprite entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected boolean doRemoveSprite(Sprite entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update() {
        
    }

    @Override
    public void onMapEvent(Map2DTileEvent e) {
        
    }
    
}
