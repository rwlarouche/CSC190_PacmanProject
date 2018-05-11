/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.PacmanRemastered.Map;

import engine.Direction;
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
        retThis.put(EnumSet.of(WallStats.DOWN, WallStats.LEFT, WallStats.RIGHT, WallStats.UP), autoKeepTrack++);
        retThis.put(EnumSet.noneOf(WallStats.class), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.RIGHT, WallStats.DOWN, WallStats.DOWNRIGHT), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.LEFT, WallStats.UP, WallStats.UPLEFT), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.LEFT, WallStats.DOWN, WallStats.DOWNLEFT), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.RIGHT, WallStats.UP, WallStats.UPRIGHT), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.RIGHT, WallStats.DOWN), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.LEFT, WallStats.UP), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.LEFT, WallStats.DOWN), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.RIGHT, WallStats.UP), autoKeepTrack++);
        retThis.put(EnumSet.complementOf(EnumSet.of(WallStats.DOWNLEFT)), autoKeepTrack++);
        retThis.put(EnumSet.complementOf(EnumSet.of(WallStats.UPRIGHT)), autoKeepTrack++);
        retThis.put(EnumSet.complementOf(EnumSet.of(WallStats.UPLEFT)), autoKeepTrack++);
        retThis.put(EnumSet.complementOf(EnumSet.of(WallStats.DOWNRIGHT)), autoKeepTrack++);
        retThis.put(EnumSet.complementOf(EnumSet.of(WallStats.LEFT, WallStats.DOWNLEFT, WallStats.UPLEFT)), autoKeepTrack++);
        retThis.put(EnumSet.complementOf(EnumSet.of(WallStats.RIGHT, WallStats.UPRIGHT, WallStats.DOWNRIGHT)), autoKeepTrack++);
        retThis.put(EnumSet.complementOf(EnumSet.of(WallStats.UP, WallStats.UPLEFT, WallStats.UPRIGHT)), autoKeepTrack++);
        retThis.put(EnumSet.complementOf(EnumSet.of(WallStats.DOWN, WallStats.DOWNLEFT, WallStats.DOWNRIGHT)), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.RIGHT, WallStats.UP, WallStats.DOWN, WallStats.UPRIGHT), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.LEFT, WallStats.UP, WallStats.DOWN, WallStats.DOWNLEFT), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.LEFT, WallStats.RIGHT, WallStats.DOWN, WallStats.DOWNRIGHT), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.UP, WallStats.LEFT, WallStats.RIGHT, WallStats.UPLEFT), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.DOWN, WallStats.UP, WallStats.RIGHT, WallStats.DOWNRIGHT), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.DOWN, WallStats.UP, WallStats.LEFT, WallStats.UPLEFT), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.DOWN, WallStats.RIGHT, WallStats.LEFT, WallStats.DOWNLEFT), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.UP, WallStats.RIGHT, WallStats.LEFT, WallStats.UPRIGHT), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.UP, WallStats.DOWN, WallStats.RIGHT), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.UP, WallStats.LEFT, WallStats.DOWN), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.RIGHT, WallStats.LEFT, WallStats.DOWN), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.UP, WallStats.LEFT, WallStats.RIGHT), autoKeepTrack++);
        retThis.put(EnumSet.complementOf(EnumSet.of(WallStats.DOWNLEFT, WallStats.UPLEFT, WallStats.UPRIGHT)), autoKeepTrack++);
        retThis.put(EnumSet.complementOf(EnumSet.of(WallStats.DOWNLEFT, WallStats.DOWNRIGHT, WallStats.UPRIGHT)), autoKeepTrack++);
        retThis.put(EnumSet.complementOf(EnumSet.of(WallStats.DOWNRIGHT, WallStats.UPLEFT, WallStats.UPRIGHT)), autoKeepTrack++);
        retThis.put(EnumSet.complementOf(EnumSet.of(WallStats.DOWNRIGHT, WallStats.UPLEFT, WallStats.DOWNLEFT)), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.DOWN), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.UP), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.LEFT), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.RIGHT), autoKeepTrack++);
        retThis.put(EnumSet.complementOf(EnumSet.of(WallStats.UPLEFT, WallStats.DOWNLEFT)), autoKeepTrack++);
        retThis.put(EnumSet.complementOf(EnumSet.of(WallStats.UPRIGHT, WallStats.DOWNRIGHT)), autoKeepTrack++);
        retThis.put(EnumSet.complementOf(EnumSet.of(WallStats.UPRIGHT, WallStats.UPLEFT)), autoKeepTrack++);
        retThis.put(EnumSet.complementOf(EnumSet.of(WallStats.DOWNRIGHT, WallStats.DOWNLEFT)), autoKeepTrack++);
        retThis.put(EnumSet.complementOf(EnumSet.of(WallStats.UPRIGHT, WallStats.DOWNLEFT)), autoKeepTrack++);
        retThis.put(EnumSet.complementOf(EnumSet.of(WallStats.DOWNRIGHT, WallStats.UPLEFT)), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.UP, WallStats.DOWN), autoKeepTrack++);
        retThis.put(EnumSet.of(WallStats.LEFT, WallStats.RIGHT), autoKeepTrack++);
        
        //No condititons beyond this point, please!
        PacTileWall.wallFrames_internal = retThis;
        return retThis;
    }
    
    
    @Override
    public int getTileImageX() {
        EnumSet<WallStats> wallStats = EnumSet.noneOf(WallStats.class);
        if (getUp() == null || !(getUp() instanceof PacTileEmpty))
            wallStats.add(WallStats.UP);
        if (getDown() == null || !(getDown() instanceof PacTileEmpty))
            wallStats.add(WallStats.DOWN);
        if (getLeft() == null || !(getLeft() instanceof PacTileEmpty))
            wallStats.add(WallStats.LEFT);
        if (getRight() == null || !(getRight() instanceof PacTileEmpty)){
            wallStats.add(WallStats.RIGHT);
        }   
        if (wallStats.contains(WallStats.DOWN) && wallStats.contains(WallStats.LEFT)){
            if (getDown().getLeft()== null || !(getDown().getLeft() instanceof PacTileEmpty))
                wallStats.add(WallStats.DOWNLEFT);
            else if (getLeft().getDown()== null || !(getLeft().getDown() instanceof PacTileEmpty))
                wallStats.add(WallStats.UPLEFT);
        }
        if (wallStats.contains(WallStats.DOWN) && wallStats.contains(WallStats.RIGHT)){
            if (getDown().getRight()== null || !(getDown().getRight() instanceof PacTileEmpty))
                wallStats.add(WallStats.DOWNRIGHT);
            else if (getRight().getDown()== null || !(getRight().getDown() instanceof PacTileEmpty))
                wallStats.add(WallStats.DOWNRIGHT);
        }
        if (wallStats.contains(WallStats.UP) && wallStats.contains(WallStats.RIGHT)){
            if (getUp().getRight()== null || !(getUp().getRight() instanceof PacTileEmpty))
                wallStats.add(WallStats.UPRIGHT);
            else if (getRight().getUp()== null || !(getRight().getUp() instanceof PacTileEmpty))
                wallStats.add(WallStats.UPRIGHT);
        }
        if (wallStats.contains(WallStats.UP) && wallStats.contains(WallStats.LEFT)){
            if (getUp().getLeft()== null || !(getUp().getLeft() instanceof PacTileEmpty))
                wallStats.add(WallStats.UPLEFT);
            else if (getLeft().getUp()== null || !(getLeft().getUp() instanceof PacTileEmpty))
                wallStats.add(WallStats.UPLEFT);
        }

        return WallMap().getOrDefault(wallStats,2) * getMap().tileDrawW;
    }

    @Override
    public int getTileImageY() {
        return 0; // Straight image.
    }

    @Override
    public boolean canEnterTile(Sprite entity, Direction direction) {
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
    public void onMapEvent(Map2DTileEvent e) {
        
    }
    
    public PacTileWall(Map2DTile up, Map2DTile down, Map2DTile left, Map2DTile right) {
        super(up, down, left, right);
    }
    
    public PacTileWall(){
        super();
    }
    
}
