/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.pacman.map;


/**
 * Base class for every tile.  This also represents "empty" tiles.
 * TODO: Add event to capture passing through.
 * @author CMetomorrow
 */
public class pacTile {
    protected boolean passed;
    public final pacTile up;
    public final pacTile down;
    public final pacTile left;
    public final pacTile right;
    
    public boolean canEnter(){ //In case behavior is needed.
        return true;
    }
    
    public boolean PassThrough(){
       boolean didpass = passed;
       passed = true;
       return didpass;
    }
    
    public pacTile(pacTile inup, pacTile indown, pacTile inleft, pacTile inright){
        up=inup;
        down=indown;
        left=inleft;
        right=inright;
    }
}
