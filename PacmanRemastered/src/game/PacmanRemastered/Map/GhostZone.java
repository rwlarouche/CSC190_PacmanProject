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
import ghosts.ghost;
import java.util.Arrays;
import java.util.EnumSet;

/**
 *
 * 
 */
public class GhostZone extends Map2DTile{

    final EnumSet<Direction> canPassTheseWays;
    
    
    //Change this later.
    @Override
    public String getTileImagePath() {
        return ":alpha255:";//getMap().assetsRoot + "images/pactiles.png";
    }

    @Override
    public int getTileImageX() {
        return 2*getMap().tileDrawW;
    }

    @Override
    public int getTileImageY() {
        return 0;
    }

    /**
     * 
     * @param sprite
     * @param fromDir The direction the sprite is coming from (ie. the reverse of the Sprite's direction. Short way to get this is to call getDirection().reverse().
     * @return 
     */
    @Override
    public boolean canEnterTile(Sprite sprite, Direction fromDir) {
        Map2DTile src = getNeighborFromDir(fromDir);
        return 
                sprite instanceof ghost &&
                ((src instanceof GhostZone)?true:canPassTheseWays.contains(fromDir));
    }
    
    @Override
    public boolean canLeaveTile(Sprite sprite, Direction toDir){
        return getNeighborFromDir(toDir) instanceof GhostZone || canPassTheseWays.contains(toDir);
    }

    @Override
    protected boolean doAddSprite(Sprite sprite) {
            return sprites.add(sprite);
    }

    @Override
    protected boolean doRemoveSprite(Sprite sprite) {
        return sprites.remove(sprite);
    }

    @Override
    public void onMapEvent(Map2DTileEvent e) {}
    
    public GhostZone(Direction... passThroughDirs){
        super();
        canPassTheseWays = EnumSet.copyOf(Arrays.asList(passThroughDirs));
    }
    
    public GhostZone(){
        super();
        canPassTheseWays = EnumSet.noneOf(Direction.class);
    }
}
