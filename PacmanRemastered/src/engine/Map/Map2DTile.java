/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.Map;

import engine.Map.Events.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import javax.swing.event.EventListenerList;

/**
 *
 * 
 */
public abstract class Map2DTile implements Iterable<Object>,List<Object>{

    
     /**
     * 
     * @return Path relative path to the image in question, from the asset root location.
     */
    public abstract String getTileImage();
    
    protected abstract boolean canEnterTile(Object entity); //Should actually take an Entity class.
    
    /**
     * Add an Entity to the tile. The actual call to entities.add(Object) must be called in here manually once the entity is prepared.
     * @param entity
     * @return Success of addition operation.
     */
    protected abstract boolean doAddEntitiy(Object entity);
    
    protected abstract boolean doRemoveEntity(Object entity); //Should actually take an Entity class, or at least an index.
    
    public abstract void update();
    
    /**
     * Contains event handlers for map events. Should only be thrown when necessary.
     */
    final private EventListenerList eList;
    
    /**
     * Contains all things present on the map tile, including characters, items, powerups, etc.
     */
    final protected ArrayList<Object> entities;
    
    protected boolean traversing; //Should actually be a semaphore, but that's okay because we don't need this for the most part yet.
    
    protected Map2DTile up;
    protected Map2DTile left;
    protected Map2DTile right;
    protected Map2DTile down;
        
    public Map2DTile getUp() {
        return up;
    }

    public boolean setUp(Map2DTile up) {
        if (!traversing){
            this.up = up;
            raiseMapEvent(new TileNewNeighborEvent(this, "New tile neighbor!", up, "up"));
            return true;
        } else return false;
    }

    public Map2DTile getLeft() {
        return left;
    }

    public boolean setLeft(Map2DTile left) {
        if (!traversing){
            this.left = left;
            raiseMapEvent(new TileNewNeighborEvent(this, "New tile neighbor!", left, "left"));
            return true;
        } else return false;
    }

    public Map2DTile getRight() {
        return right;
    }

    public boolean setRight(Map2DTile right) {
        if (!traversing){
            this.right = right;
            raiseMapEvent(new TileNewNeighborEvent(this, "New tile neighbor!", right, "right"));
            return true;
        } else return false;
    }

    public Map2DTile getDown() {
        return down;
    }

    public boolean setDown(Map2DTile down) {
        if (!traversing){
            this.down = down;
            raiseMapEvent(new TileNewNeighborEvent(this, "New tile neighbor!", down, "down"));
            return true;
        } else return false;
    }
    
    protected void raiseMapEvent(Map2DTileEvent e){
        for (Map2DTileEventListener eL:eList.getListeners(Map2DTileEventListener.class)){
            eL.raiseMapEvent(e);
        }
    }
    
    public void addMapEventListener(Map2DTileEventListener e){
        eList.add(Map2DTileEventListener.class, e);
    }
    
    public void removeMapEventListener(Map2DTileEventListener e){
        eList.remove(Map2DTileEventListener.class, e);
    }    
    
    public boolean doTraverseUp(Object entity){ //Should actually take an Entity class, or at least an index for the array.
        if (up == null ||!entities.contains(entity))
            return false;
        else if (up.canEnterTile(entity)&&up.doAddEntitiy(entity)){
            doRemoveEntity(entity);
            raiseMapEvent(new TileEntityTraverseEvent(this, " has changed moved one tile up.", up, "up"));
            return true;
        }
        else return false;
    }
    
    public boolean doTraverseLeft(Object entity){ //Should actually take an Entity class, or at least an index for the array.
        if (up == null || !entities.contains(entity))
            return false;
        else if (left.canEnterTile(entity)&&left.doAddEntitiy(entity)){
            doRemoveEntity(entity);
            raiseMapEvent(new TileEntityTraverseEvent(this, " has changed moved one tile left.", left, "left"));
            return true;
        }
        else return false;
    }
    
    public boolean doTraverseDown(Object entity){ //Should actually take an Entity class, or at least an index for the array.
        if (up == null || !entities.contains(entity))
            return false;
        else if (down.canEnterTile(entity)&&down.doAddEntitiy(entity)){
            doRemoveEntity(entity);
            raiseMapEvent(new TileEntityTraverseEvent(this, " has changed moved one tile down.", down, "down"));
            return true;
        }
        else return false;
    }
    
    public boolean doTraverseRight(Object entity){ //Should actually take an Entity class, or at least an index for the array.
        if (up == null || !entities.contains(entity))
            return false;
        if (right.canEnterTile(entity)&&right.doAddEntitiy(entity)){
            doRemoveEntity(entity);
            raiseMapEvent(new TileEntityTraverseEvent(this, " has changed moved one tile right.", right, "right"));
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
        traversing = false;
    }  
    
    public Map2DTile(){
        entities = new ArrayList<>();
        eList = new EventListenerList();
        this.up = null;
        this.down = null;
        this.left = null;
        this.right = null;
        traversing = false;        
    }

        @Override
    public Stream<Object> stream() {
        return entities.stream(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Stream<Object> parallelStream() {
        return entities.parallelStream(); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public boolean add(Object e) {
        if (canEnterTile(e) && !entities.contains(e) && doAddEntitiy(e))
        {
            raiseMapEvent(new TileEntityAddedEvent(this, "Entity entered tile.", e));
            return true;
        }
        else return false;
    }

    @Override
    public boolean remove(Object o) {
        boolean retThis = doRemoveEntity(o);
        raiseMapEvent(new TileEntityRemovedEvent(this, "Entity exited tile.", o));
        return retThis;
    }
    
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
        entities.forEach(action);
    }

    @Override
    public Spliterator<Object> spliterator() {
        return entities.spliterator();
    }    
    
    @Override
    public Iterator<Object> iterator() {
        return entities.iterator();
    }
    
    
    
    
    
    @Override
    @Deprecated
    public boolean addAll(Collection<? extends Object> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Deprecated
    public boolean addAll(int index, Collection<? extends Object> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Deprecated
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Deprecated
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Deprecated
    public void replaceAll(UnaryOperator<Object> operator) {
        List.super.replaceAll(operator); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Deprecated
    public void sort(Comparator<? super Object> c) {
        List.super.sort(c); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Deprecated
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Deprecated
    public Object set(int index, Object element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Deprecated
    public void add(int index, Object element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Deprecated
    public Object remove(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Deprecated
    public boolean removeIf(Predicate<? super Object> filter) {
        return List.super.removeIf(filter); //To change body of generated methods, choose Tools | Templates.
    }

}
