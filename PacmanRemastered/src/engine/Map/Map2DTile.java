/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.Map;

import engine.Map.Events.*;
import engine.Sprite;
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

/**
 *
 * 
 */
public abstract class Map2DTile implements Iterable<Sprite>,List<Sprite>,Map2DTileEventListener{

    
     /**
     * 
     * @return Path relative path to the image in question, from the asset root location.
     */
    public abstract String getTileImagePath();
    
    public abstract int getTileImageX();
    
    public abstract int getTileImageY();
    
    private Map2D map;

    public Map2D getMap() {
        return map;
    }

    void setMap(Map2D map) {
        this.map = map;
    }
    
    protected abstract boolean canEnterTile(Sprite entity); //Should actually take an Entity class.
    
    /**
     * Add an Entity to the tile. The actual call to entities.add(Object) must be called in here manually once the entity is prepared.
     * @param entity
     * @return Success of addition operation.
     */
    protected abstract boolean doAddSprite(Sprite entity);
    
    protected abstract boolean doRemoveSprite(Sprite entity); //Should actually take an Entity class, or at least an index.
    
    public abstract void update();
    
    
    /**
     * Contains event handlers for map events. Should only be thrown when necessary.
     */
    final private ArrayList<Map2DTileEventListener> eList;
    /**
     * Contains all things present on the map tile, including characters, items, powerups, etc.
     */
    final protected ArrayList<Sprite> sprites;
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
            Map2DTile old = this.up;
            this.up = up;
            raiseMapEvent(new TileNewNeighborEvent(this, "up", old, up));
            
            return true;
        } else return false;
    }

    public Map2DTile getLeft() {
        return left;
    }

    public boolean setLeft(Map2DTile left) {
        if (!traversing){
            Map2DTile old = this.left;
            this.left = left;
            raiseMapEvent(new TileNewNeighborEvent(this, "left", old, left));
            return true;
        } else return false;
    }

    public Map2DTile getRight() {
        return right;
    }

    public boolean setRight(Map2DTile right) {
        if (!traversing){
            Map2DTile old = this.right;
            this.right = right;
            raiseMapEvent(new TileNewNeighborEvent(this, "right", old, right));
            return true;
        } else return false;
    }

    public Map2DTile getDown() {
        return down;
    }

    public boolean setDown(Map2DTile down) {
        if (!traversing){
            Map2DTile old = this.down;
            this.down = down;
            raiseMapEvent(new TileNewNeighborEvent(this, "down", old, down));
            return true;
        } else return false;
    }
    
    protected void raiseMapEvent(Map2DTileEvent e){
        eList.forEach((eL) -> {
            eL.onMapEvent(e);
        });
    }
    

    
    public void addMapEventListener(Map2DTileEventListener e){
        if (!eList.contains(e)){
            eList.add(e);
        }
    }
    
    public void removeMapEventListener(Map2DTileEventListener e){
        eList.remove(e);
    }    
    
    public boolean doTraverseUp(Sprite sprite){ //Should actually take an Entity class, or at least an index for the array.
        if (up == null ||!sprites.contains(sprite))
            return false;
        else if (up.canEnterTile(sprite)&&up.doAddSprite(sprite)){
            doRemoveSprite(sprite);
            raiseMapEvent(new TileSpriteTraverseEvent(this, " has changed moved one tile up.", up, "up"));
            return true;
        }
        else return false;
    }
    
    public boolean doTraverseLeft(Sprite sprite){ //Should actually take an Entity class, or at least an index for the array.
        if (left == null || !sprites.contains(sprite))
            return false;
        else if (left.canEnterTile(sprite)&&left.doAddSprite(sprite)){
            doRemoveSprite(sprite);
            raiseMapEvent(new TileSpriteTraverseEvent(this, " has changed moved one tile left.", left, "left"));
            return true;
        }
        else return false;
    }
    
    public boolean doTraverseDown(Sprite sprite){ //Should actually take an Entity class, or at least an index for the array.
        if (down == null || !sprites.contains(sprite))
            return false;
        else if (down.canEnterTile(sprite)&&down.doAddSprite(sprite)){
            doRemoveSprite(sprite);
            raiseMapEvent(new TileSpriteTraverseEvent(this, " has changed moved one tile down.", down, "down"));
            return true;
        }
        else return false;
    }
    
    public boolean doTraverseRight(Sprite sprite){ //Should actually take an Entity class, or at least an index for the array.
        if (right == null || !sprites.contains(sprite))
            return false;
        if (right.canEnterTile(sprite)&&right.doAddSprite(sprite)){
            doRemoveSprite(sprite);
            raiseMapEvent(new TileSpriteTraverseEvent(this, " has changed moved one tile right.", right, "right"));
            return true;
        }
        else return false;
    }
    
    public Map2DTile(Map2DTile up, Map2DTile down, Map2DTile left, Map2DTile right, Sprite... initEntities) { //Need to add array for initial Entities to be added.
        sprites = new ArrayList<>();
        for (Sprite s :initEntities)
            sprites.add(s);
        eList = new ArrayList<>();
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        traversing = false;
    }  
    
    public Map2DTile(){
        sprites = new ArrayList<>();
        eList = new ArrayList<>();
        this.up = null;
        this.down = null;
        this.left = null;
        this.right = null;
        traversing = false;
    }

        @Override
    public Stream<Sprite> stream() {
        return sprites.stream(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Stream<Sprite> parallelStream() {
        return sprites.parallelStream(); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public boolean add(Sprite e) {
        if (canEnterTile(e) && !sprites.contains(e) && doAddSprite(e))
        {
            raiseMapEvent(new TileSpriteAddedEvent(this, "Sprite entered tile.", e));
            return true;
        }
        else return false;
    }

    @Override
    public boolean remove(Object o) {
        boolean retThis = doRemoveSprite((Sprite)o);
        raiseMapEvent(new TileSpriteRemovedEvent(this, "Sprite exited tile.", o));
        return retThis;
    }
    
    @Override
    public int size() {
        return sprites.size();
    }

    @Override
    public boolean isEmpty() {
        return sprites.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return sprites.contains(o);
    }

    @Override
    public Sprite[] toArray() {
        return sprites.toArray(new Sprite[]{});
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return sprites.toArray(a);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return sprites.containsAll(c);
    }

    @Override
    public Sprite get(int index) {
        return sprites.get(index);
    }

    @Override
    public int indexOf(Object o) {
        return sprites.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return sprites.lastIndexOf(o);
    }

    @Override
    public ListIterator<Sprite> listIterator() {
        return sprites.listIterator();
    }

    @Override
    public ListIterator<Sprite> listIterator(int index) {
        return sprites.listIterator(index);
    }

    @Override
    public List<Sprite> subList(int fromIndex, int toIndex) {
        return sprites.subList(fromIndex, toIndex);
    }

    @Override
    public void forEach(Consumer<? super Sprite> action) {
        sprites.forEach(action);
    }

    @Override
    public Spliterator<Sprite> spliterator() {
        return sprites.spliterator();
    }    
    
    @Override
    public Iterator<Sprite> iterator() {
        return sprites.iterator();
    }
    
    
    
    
    
    @Override
    @Deprecated
    public boolean addAll(Collection<? extends Sprite> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Deprecated
    public boolean addAll(int index, Collection<? extends Sprite> c) {
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
    public void replaceAll(UnaryOperator<Sprite> operator) {
        List.super.replaceAll(operator); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Deprecated
    public void sort(Comparator<? super Sprite> c) {
        List.super.sort(c); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Deprecated
    public void clear() {
        sprites.clear();
    }

    @Override
    @Deprecated
    public Sprite set(int index, Sprite element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Deprecated
    public void add(int index, Sprite element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Deprecated
    public Sprite remove(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Deprecated
    public boolean removeIf(Predicate<? super Sprite> filter) {
        return List.super.removeIf(filter); //To change body of generated methods, choose Tools | Templates.
    }

}