/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game.PacmanRemastered.Map;

import engine.Map.Map2DTile;

/**
 *
 * 
 */
public class BlankPacTile extends Map2DTile{

    public BlankPacTile(Map2DTile up, Map2DTile down, Map2DTile left, Map2DTile right, Object... initEntities) {
        super(up, down, left, right, initEntities);
    }

    @Override
    public String getTileImage() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected boolean canEnterTile(Object entity) {
        return true;//Anything can enter a blank tile, and the only add method other classes can't use already checks if two of the same entity enter the tile.
    }

    @Override
    protected boolean doAddEntitiy(Object entity) {
        return entities.add(entity);
    }

    @Override
    protected boolean doRemoveEntity(Object entity) {
        return entities.remove(entity);
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
