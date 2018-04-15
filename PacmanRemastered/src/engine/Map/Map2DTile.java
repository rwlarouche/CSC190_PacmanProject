/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.Map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import javax.swing.event.EventListenerList;

/**
 *
 * @author jolt1
 */
public abstract class Map2DTile implements Iterable<Object>,List<Object>{//Would iterate over Entity instead.
    
    final private EventListenerList eList;
    
    //Will contain all things present on the map tile, including characters, items, powerups, etc.
    final private ArrayList<Object> entities;
    
    final private Map2DTile up;
    final private Map2DTile left;
    final private Map2DTile right;
    final private Map2DTile down;

    @Override
    public int size() {
        return entities.size();
    }

    @Override
    public boolean isEmpty() {
        return entities.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return entities.contains(o);
    }

    @Override
    public Object[] toArray() {
        return entities.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return entities.toArray(a);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return entities.containsAll(c);
    }

    @Override
    public Object get(int index) {
        return entities.get(index);
    }

    @Override
    public int indexOf(Object o) {
        return entities.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return entities.lastIndexOf(o);
    }

    @Override
    public ListIterator<Object> listIterator() {
        return entities.listIterator();
    }

    @Override
    public ListIterator<Object> listIterator(int index) {
        return entities.listIterator(index);
    }

    @Override
    public List<Object> subList(int fromIndex, int toIndex) {
        return entities.subList(fromIndex, toIndex);
    }

    @Override
    public void forEach(Consumer<? super Object> action) {
        List.super.forEach(action);
    }

    @Override
    public Spliterator<Object> spliterator() {
        return List.super.spliterator();
    }    
        
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
        eList = new EventListenerList();
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }
    
    @Override
    public Iterator<Object> iterator() {
        return entities.iterator();
    }
  

}
