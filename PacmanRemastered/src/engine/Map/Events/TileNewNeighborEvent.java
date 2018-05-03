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
 *
 */
public class TileNewNeighborEvent extends Map2DTileEvent {
    
    public Direction direction;
    public Map2DTile oldNeighbor;
    public Map2DTile newNeighbor;
    
    public TileNewNeighborEvent(Map2DTile sender, Direction direction, Map2DTile oldNeighbor, Map2DTile newNeighbor) {
        super(sender, "Tile's neighbor has changed!", oldNeighbor, direction, newNeighbor);
        this.direction=direction;
        this.oldNeighbor=oldNeighbor;
        this.newNeighbor=newNeighbor;
    }
    
}
