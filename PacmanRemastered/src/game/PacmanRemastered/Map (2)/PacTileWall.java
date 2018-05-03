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
import java.util.HashMap;

/**
 *
 * 
 */
public class PacTileWall extends Map2DTile{

    @Override
    public String getTileImagePath() {
        return getMap().assetsRoot + "images/pactiles.png";
    }
    
    private static HashMap<EnumSet<WallStats>,Integer> wallFrames_internal;
    static HashMap<EnumSet<WallStats>,Integer> WallMap(){
        if (wallFrames_internal != null)
            return wallFrames_internal;
        HashMap<EnumSet<WallStats>,Integer> retThis = new HashMap<>();
        int autoKeepTrack = 0;
        
        //Conditions will be listed in order of appearance on the sheet so they can automatically be given an index.
        
        retThis.put(EnumSet.allOf(WallStats.class), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.down, WallStats.left, WallStats.right, WallStats.up), autoKeepTrack++);
        retThis.put(EnumSet.noneOf(WallStats.class), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.right, WallStats.down, WallStats.downRight), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.left, WallStats.up, WallStats.upLeft), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.left, WallStats.down, WallStats.downLeft), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.right, WallStats.up, WallStats.upRight), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.right, WallStats.down), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.left, WallStats.up), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.left, WallStats.down), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.right, WallStats.up), autoKeepTrack++);
        retThis.put(EnumSet.complementOf(EnumSet.of(WallStats.downLeft)), autoKeepTrack++);
        retThis.put(EnumSet.complementOf(EnumSet.of(WallStats.upRight)), autoKeepTrack++);
        retThis.put(EnumSet.complementOf(EnumSet.of(WallStats.upLeft)), autoKeepTrack++);
        retThis.put(EnumSet.complementOf(EnumSet.of(WallStats.downRight)), autoKeepTrack++);
        retThis.put(EnumSet.complementOf(EnumSet.of(WallStats.left, WallStats.downLeft, WallStats.upLeft)), autoKeepTrack++);
        retThis.put(EnumSet.complementOf(EnumSet.of(WallStats.right, WallStats.upRight, WallStats.downRight)), autoKeepTrack++);
        retThis.put(EnumSet.complementOf(EnumSet.of(WallStats.up, WallStats.upLeft, WallStats.upRight)), autoKeepTrack++);
        retThis.put(EnumSet.complementOf(EnumSet.of(WallStats.down, WallStats.downLeft, WallStats.downRight)), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.right, WallStats.up, WallStats.down, WallStats.upRight), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.left, WallStats.up, WallStats.down, WallStats.downLeft), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.left, WallStats.right, WallStats.down, WallStats.downRight), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.up, WallStats.left, WallStats.right, WallStats.upLeft), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.down, WallStats.up, WallStats.right, WallStats.downRight), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.down, WallStats.up, WallStats.left, WallStats.upLeft), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.down, WallStats.right, WallStats.left, WallStats.downLeft), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.up, WallStats.right, WallStats.left, WallStats.upRight), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.up, WallStats.down, WallStats.right), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.up, WallStats.left, WallStats.down), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.right, WallStats.left, WallStats.down), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.up, WallStats.left, WallStats.right), autoKeepTrack++);
        retThis.put(EnumSet.complementOf(EnumSet.of(WallStats.downLeft, WallStats.upLeft, WallStats.upRight)), autoKeepTrack++);
        retThis.put(EnumSet.complementOf(EnumSet.of(WallStats.downLeft, WallStats.downRight, WallStats.upRight)), autoKeepTrack++);
        retThis.put(EnumSet.complementOf(EnumSet.of(WallStats.downRight, WallStats.upLeft, WallStats.upRight)), autoKeepTrack++);
        retThis.put(EnumSet.complementOf(EnumSet.of(WallStats.downRight, WallStats.upLeft, WallStats.downLeft)), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.down), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.up), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.left), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.right), autoKeepTrack++);
        retThis.put(EnumSet.complementOf(EnumSet.of(WallStats.upLeft, WallStats.downLeft)), autoKeepTrack++);
        retThis.put(EnumSet.complementOf(EnumSet.of(WallStats.upRight, WallStats.downRight)), autoKeepTrack++);
        retThis.put(EnumSet.complementOf(EnumSet.of(WallStats.upRight, WallStats.upLeft)), autoKeepTrack++);
        retThis.put(EnumSet.complementOf(EnumSet.of(WallStats.downRight, WallStats.downLeft)), autoKeepTrack++);
        retThis.put(EnumSet.complementOf(EnumSet.of(WallStats.upRight, WallStats.downLeft)), autoKeepTrack++);
        retThis.put(EnumSet.complementOf(EnumSet.of(WallStats.downRight, WallStats.upLeft)), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.up, WallStats.down), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.left, WallStats.right), autoKeepTrack++);
        
        //No condititons beyond this point, please!
        PacTileWall.wallFrames_internal = retThis;
        return retThis;
    }
    
    
    @Override
    public int getTileImageX() {
        EnumSet<WallStats> wallStats = EnumSet.noneOf(WallStats.class);
        if (getUp() == null || !(getUp() instanceof PacTileEmpty))
            wallStats.add(WallStats.up);
        if (getDown() == null || !(getDown() instanceof PacTileEmpty))
            wallStats.add(WallStats.down);
        if (getLeft() == null || !(getLeft() instanceof PacTileEmpty))
            wallStats.add(WallStats.left);
        if (getRight() == null || !(getRight() instanceof PacTileEmpty)){
            wallStats.add(WallStats.right);
        }   
        if (wallStats.contains(WallStats.down) && wallStats.contains(WallStats.left)){
            if (getDown().getLeft()== null || !(getDown().getLeft() instanceof PacTileEmpty))
                wallStats.add(WallStats.downLeft);
            else if (getLeft().getDown()== null || !(getLeft().getDown() instanceof PacTileEmpty))
                wallStats.add(WallStats.upLeft);
        }
        if (wallStats.contains(WallStats.down) && wallStats.contains(WallStats.right)){
            if (getDown().getRight()== null || !(getDown().getRight() instanceof PacTileEmpty))
                wallStats.add(WallStats.downRight);
            else if (getRight().getDown()== null || !(getRight().getDown() instanceof PacTileEmpty))
                wallStats.add(WallStats.downRight);
        }
        if (wallStats.contains(WallStats.up) && wallStats.contains(WallStats.right)){
            if (getUp().getRight()== null || !(getUp().getRight() instanceof PacTileEmpty))
                wallStats.add(WallStats.upRight);
            else if (getRight().getUp()== null || !(getRight().getUp() instanceof PacTileEmpty))
                wallStats.add(WallStats.upRight);
        }
        if (wallStats.contains(WallStats.up) && wallStats.contains(WallStats.left)){
            if (getUp().getLeft()== null || !(getUp().getLeft() instanceof PacTileEmpty))
                wallStats.add(WallStats.upLeft);
            else if (getLeft().getUp()== null || !(getLeft().getUp() instanceof PacTileEmpty))
                wallStats.add(WallStats.upLeft);
        }

        return WallMap().getOrDefault(wallStats,2) * getMap().tileDrawW;
    }

    @Override
    public int getTileImageY() {
        return 0; // Straight image.
    }

    @Override
    public boolean canEnterTile(Sprite entity) {
        return false;
    }

    @Override
    protected boolean doAddSprite(Sprite entity) {
        throw new UnsupportedOperationException("No WallHax Plz."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected boolean doRemoveSprite(Sprite entity) {
        throw new UnsupportedOperationException("No WallHax Plz."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update() {
        
    }

    @Override
    public void onMapEvent(Map2DTileEvent e) {
        
    }
    
    public PacTileWall(Map2DTile up, Map2DTile down, Map2DTile left, Map2DTile right, Sprite... initEntities) {
        super(up, down, left, right);
    }
    
    public PacTileWall(){
        super(null,null,null,null);
    }
    
}
