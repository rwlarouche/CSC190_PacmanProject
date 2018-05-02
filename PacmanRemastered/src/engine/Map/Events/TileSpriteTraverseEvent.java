/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.Map.Events;

import engine.Direction;
import engine.Map.Map2DTile;
import engine.Map.Map2DTileEvent;

/**
 *
 */
public class TileSpriteTraverseEvent extends Map2DTileEvent {
    
    public Map2DTile movedTo() { return (Map2DTile)affected[0];}
    
    public Direction direction() {return (Direction)affected[1];}
    
    public TileSpriteTraverseEvent(Map2DTile sender, String event, Object... affected) {
        super(sender, event, affected);
    }
    
}
