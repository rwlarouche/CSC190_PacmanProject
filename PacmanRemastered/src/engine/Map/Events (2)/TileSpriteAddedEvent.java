/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.Map.Events;

import engine.Map.Map2DTile;
import engine.Map.Map2DTileEvent;

/**
 *
 * 
 */
public class TileSpriteAddedEvent extends Map2DTileEvent{
    
    public TileSpriteAddedEvent(Map2DTile sender, String event, Object... affected) {
        super(sender, event, affected);
    }
    
}
