/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.Map;

import java.util.ArrayList;
import javax.swing.event.EventListenerList;

/**
 *
 * @author jolt1
 */
public abstract class Map2DTile{
    
    private EventListenerList eList;
    
    //Will contain all things present on the map tile, including characters, items, powerups, etc.
    final private ArrayList<Object> entities;
    
    final private Map2DTile up;
    final private Map2DTile left;
    final private Map2DTile right;
    final private Map2DTile down;

    public Map2DTile getUp() {
        return up;
    }

    public Map2DTile getLeft() {
        return left;
    }

    public Map2DTile getRight() {
        return right;
    }

    public Map2DTile getDown() { //Insert Golden Eye meme.
        return down;
    }
    
    protected void raiseMapEvent(Map2DTileEvent e){
        for (Map2DTileEventListener eL:eList.getListeners(Map2DTileEventListener.class)){
            eL.raiseMapEvent(e);
        }
    }
    
    public void addMapEventListener(Map2DTileEventListener e){
        eList.add(Map2DTileEventListener.class, e);
    }
    
    protected abstract boolean canEnterTile(Object entity); //Should actually take an Entity class.
    
    protected abstract boolean onAddEntity(Object entity); //Should actually take an Entity class.
    
    protected abstract void onRemoveEntity(Object entity); //Should actually take an Entity class, or at least an index.
    
    public abstract void update();
    
    public boolean doTraverseUp(Object entity){ //Should actually take an Entity class, or at least an index for the array.
        if (up == null ||!entities.contains(entity))
            return false;
        else if (up.canEnterTile(entity)&&up.onAddEntity(entity)){
            onRemoveEntity(entity);
            return true;
        }
        else return false;
    }
    
    public boolean doTraverseLeft(Object entity){ //Should actually take an Entity class, or at least an index for the array.
        if (up == null || !entities.contains(entity))
            return false;
        else if (left.canEnterTile(entity)&&left.onAddEntity(entity)){
            onRemoveEntity(entity);
            return true;
        }
        else return false;
    }
    
    public boolean doTraverseDown(Object entity){ //Should actually take an Entity class, or at least an index for the array.
        if (up == null || !entities.contains(entity))
            return false;
        else if (down.canEnterTile(entity)&&down.onAddEntity(entity)){
            onRemoveEntity(entity);
            return true;
        }
        else return false;
    }
    
    public boolean doTraverseRight(Object entity){ //Should actually take an Entity class, or at least an index for the array.
        if (up == null || !entities.contains(entity))
            return false;
        else if (right.canEnterTile(entity)&&right.onAddEntity(entity)){
            onRemoveEntity(entity);
            return true;
        }
        else return false;
    }
    
    public Map2DTile(Map2DTile up, Map2DTile down, Map2DTile left, Map2DTile right, Object... initEntities) { //Need to add array for initial Entities to be added.
        entities = new ArrayList<>();
        entities.add(initEntities);
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }
    
}
