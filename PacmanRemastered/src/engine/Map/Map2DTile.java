/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.Map;

import engine.Direction;
import engine.Map.Events.*;
import engine.Sprite;
import java.util.ArrayList;
import java.util.Arrays;
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
    

    public abstract boolean canEnterTile(Sprite entity); 
    
    /**
     * Add an Entity to the tile. The actual call to entities.add(Object) must be called in here manually once the entity is prepared.
     * @param entity
     * @return Success of addition operation.
     */
    protected abstract boolean doAddSprite(Sprite entity);
    
    /**
     * Sprite is removed from the tile. Must call sprites.remove from within this method.
     * @param entity
     * @return 
     */
    protected abstract boolean doRemoveSprite(Sprite entity);
    
    public abstract void update();
    
    private Map2D map;

    public Map2D getMap() {
        return map;
    }

    void setMap(Map2D map) {
        this.map = map;
    }
    
    /**
     * Contains event handlers for map events. Should only be thrown when necessary.
     */
    final private ArrayList<Map2DTileEventListener> eList;
    /**
     * Contains all things present on the map tile, including characters, items, powerups, etc.
     */
    final protected ArrayList<Sprite> sprites;
    protected boolean traversing; //Should actually be a semaphore, but that's okay because we don't need this for the most part yet. It might get removed before the end product.
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
            raiseMapEvent(new TileNewNeighborEvent(this, Direction.UP, old, up));
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
            raiseMapEvent(new TileNewNeighborEvent(this, Direction.LEFT, old, left));
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
            raiseMapEvent(new TileNewNeighborEvent(this, Direction.RIGHT, old, right));
            return true;
        } else return false;
    }

    public Map2DTile getDown() { //Insert GoldenEye meme here.
        return down;
    }

    public boolean setDown(Map2DTile down) {
        if (!traversing){
            Map2DTile old = this.down;
            this.down = down;
            raiseMapEvent(new TileNewNeighborEvent(this, Direction.DOWN, old, down));
            return true;
        } else return false;
    }
    
    protected void raiseMapEvent(Map2DTileEvent e){
        new ArrayList<Map2DTileEventListener>(eList).forEach((eL) -> {
            if (eList.contains(eL))//This way, if an event causes a sprite to remove itself, it won't caiuse an exception.
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
    
    public boolean doTraverseUp(Sprite sprite){
        if (up == null ||!sprites.contains(sprite))
            return false;
        else if (up.canEnterTile(sprite)&&up.doAddSprite(sprite)){
            removeMapEventListener(sprite);
            sprite.setMapTile(up);
            doRemoveSprite(sprite);
            up.addMapEventListener(sprite);
            raiseMapEvent(new TileSpriteTraverseEvent(this, " has moved one tile up.", up, Direction.UP, sprite));
            return true;
        }
        else return false;
    }
    
    public boolean doTraverseLeft(Sprite sprite){
        if (left == null || !sprites.contains(sprite))
            return false;
        else if (left.canEnterTile(sprite)&&left.doAddSprite(sprite)){
            removeMapEventListener(sprite);
            sprite.setMapTile(left);
            doRemoveSprite(sprite);
            left.addMapEventListener(sprite);
            raiseMapEvent(new TileSpriteTraverseEvent(this, " has moved one tile left.", left, Direction.LEFT, sprite));
            return true;
        }
        else return false;
    }
    
    public boolean doTraverseDown(Sprite sprite){
        if (down == null || !sprites.contains(sprite))
            return false;
        else if (down.canEnterTile(sprite)&&down.doAddSprite(sprite)){
            removeMapEventListener(sprite);
            sprite.setMapTile(down);
            doRemoveSprite(sprite);
            down.addMapEventListener(sprite);
            raiseMapEvent(new TileSpriteTraverseEvent(this, " has moved one tile down.", down, Direction.DOWN, sprite));
            return true;
        }
        else return false;
    }
    
    public boolean doTraverseRight(Sprite sprite){
        if (right == null || !sprites.contains(sprite))
            return false;
        if (right.canEnterTile(sprite)&&right.doAddSprite(sprite)){
            removeMapEventListener(sprite);
            sprite.setMapTile(right);
            doRemoveSprite(sprite);
            right.addMapEventListener(sprite);
            raiseMapEvent(new TileSpriteTraverseEvent(this, " has moved one tile right.", right, Direction.RIGHT, sprite));
            return true;
        }
        else return false;
    }
    
    public Map2DCoords getBoardIndicies(){
        Map2D.Map2DIterator iterate = (Map2D.Map2DIterator)getMap().iterator();
        int tag = 0;
        while (iterate.hasNext() && iterate.next() != this){tag++;}
        return new Map2DCoords(iterate.getTagColumn(tag), iterate.getTagRow(tag));
    }

    public Map2DCoords getAbsCoordinates(){
        Map2DCoords indicies = getBoardIndicies();
        return new Map2DCoords(getMap().mapRootX + (indicies.x *getMap().tileDrawW), getMap().mapRootY + (indicies.y *getMap().tileDrawH));
    }
    
    public void snapSpritesToTile(){
        Map2DCoords coordinates = getAbsCoordinates();
        this.stream().map((sprite) -> {
            sprite.setX(coordinates.x);
            return sprite;
        }).forEachOrdered((sprite) -> {
            sprite.setY(coordinates.y);
        });
    }
    
    public Map2DTile(Map2DTile up, Map2DTile down, Map2DTile left, Map2DTile right, Sprite... initEntities) {
        sprites = new ArrayList<>();
        sprites.addAll(Arrays.asList(initEntities));
        sprites.forEach((s)->{addMapEventListener(s);});
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
        return sprites.stream();
    }

    @Override
    public Stream<Sprite> parallelStream() {
        return sprites.parallelStream();
    }


    @Override
    public boolean add(Sprite e) {
        if (!sprites.contains(e) && canEnterTile(e) && doAddSprite(e))
        {
            raiseMapEvent(new TileSpriteAddedEvent(this, "Sprite entered tile.", e));
            addMapEventListener(e);
            e.setMapTile(this);
            return true;
        }
        else return false;
    }

    @Override
    public boolean remove(Object o) {
        boolean retThis = doRemoveSprite((Sprite)o);
        if (((Sprite)o).getMapTile() == this){
            ((Sprite)o).setMapTile(null);
        }
        removeMapEventListener((Sprite)o);
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
        throw new UnsupportedOperationException("Not supported yet.");
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