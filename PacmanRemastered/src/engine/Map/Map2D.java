/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.Map;


import engine.API;
import engine.Sprite;
import game.PacmanRemastered.Game;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 *
 * 
 */
public class Map2D implements Iterable<Map2DTile>, Map2DTileEventListener{
    
    /**
     * So, apparently, nested classes can access parent class attributes. Odd choice, Java, but I'll make use of it anyway.
     */
    public class Map2DIterator implements Iterator<Map2DTile>{
        int tagIndex;
        public int getTag(int row, int column){
            return (row*mapTiles.length)+column-((mapTiles.length-mapTiles[0].length)*row);
        }
        public int getTagColumn(int tag){
            return (tag)%mapTiles[0].length;
        }
        public int getTagRow(int tag){
            return (tag-getTagColumn(tag))/mapTiles[0].length;
        }
        public Map2DTile getTile(int tag){
            return mapTiles[getTagRow(tag)][getTagColumn(tag)];
        }
        @Override
        public boolean hasNext() {
            return tagIndex < getTag(getRowCount()-1, getColumnCount()-1);
        }
        @Override
        public Map2DTile next() {
            return getTile(tagIndex++);
        }
        public Map2DIterator(){
            tagIndex = 0;
        }
    }
    
    final private ArrayList<Map2DTileEventListener> eList;
    public void addMapEventListener(Map2DTileEventListener e){
        if (!eList.contains(e)){
            eList.add(e);
        }
    }
    public void removeMapEventListener(Map2DTileEventListener e){
        eList.remove(e);
    }
    @Override
    public void onMapEvent(Map2DTileEvent e) {
        eList.forEach((doIt) -> {
            doIt.onMapEvent(e);
        });
    }
    
    final public String assetsRoot;
    
    final public String levelRootPath;
    
    final public API api;
    
    final public Game game;
    
    protected Map2DTile[][] mapTiles;
    
    public int getRowCount(){
        return mapTiles.length;
    }
    public int getColumnCount(){
        return mapTiles[0].length;
    }
    public Map2DTile getTile(int row, int column){
        return mapTiles[row][column];
    }
    
    /**
     * Prepares map for use and sets the map to it; usually called in constructor, but perhaps it should technically also be called every time a map tile is updated?
     * @param mapTiles
     */
    protected final void prepMapTiles(){
        
        for (int ri = 0; ri < mapTiles.length; ri++){
            for (int ci = 0; ci < mapTiles[ri].length; ci++){
                if (mapTiles[ri][ci] == null)
                        mapTiles[ri][ci] = new NullTile();
                Map2DTile curTile = mapTiles[ri][ci];
                curTile.setMap(this);                
                if (ci - 1 > -1){
                    if (mapTiles[ri][ci-1] == null)
                        mapTiles[ri][ci-1] = new NullTile();
                    curTile.setLeft(mapTiles[ri][ci-1]);
                }
                if (ci + 1 < mapTiles[ri].length){
                    if (mapTiles[ri][ci+1] == null)
                        mapTiles[ri][ci+1] = new NullTile();
                    curTile.setRight(mapTiles[ri][ci+1]);
                }
                if (ri - 1 > -1){
                    if (mapTiles[ri-1][ci] == null)
                        mapTiles[ri-1][ci] = new NullTile();
                    curTile.setUp(mapTiles[ri-1][ci]);
                }
                if (ri + 1 < mapTiles.length){
                    if (mapTiles[ri+1][ci] == null)
                        mapTiles[ri+1][ci] = new NullTile();
                    curTile.setDown(mapTiles[ri+1][ci]);
                }
                curTile.addMapEventListener(this);
            }
        }
    }
    
    public final void snapAllSpritesToAllTiles(){
        for (Map2DTile tile: this){
            tile.snapSpritesToTile();
        }
    }

    /**
     * Location of the top-left corner of the map.
     */
    public final double mapRootX, mapRootY;
    
    /**
     * Tile width and height, in pixels.
     */
    public final int tileDrawW, tileDrawH;
    
    public Stream<Map2DTile> stream(){
        return StreamSupport.stream(spliterator(), false);
    }
    
    @Override
    public Iterator<Map2DTile> iterator() {
        return new Map2DIterator();
    }

    @Override
    public void forEach(Consumer<? super Map2DTile> action) {
        Iterable.super.forEach(action); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Spliterator<Map2DTile> spliterator() {
        return Iterable.super.spliterator(); //To change body of generated methods, choose Tools | Templates.
    }
        
    public Map2D(Map2DBuilder b){
        eList = new ArrayList<>();
        mapRootX = b.topLeftX;
        mapRootY = b.topLeftY;
        tileDrawW = b.tileSizeW;
        tileDrawH = b.tileSizeH;
        game = b.game;
        mapTiles = b.mapGrid;
        prepMapTiles();
        snapAllSpritesToAllTiles();
        levelRootPath = b.rootLevelPath;
        assetsRoot = (b.assetsRoot==null)? b.rootLevelPath: b.assetsRoot;
        api = b.api;
    }
}
