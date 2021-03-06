/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game.PacmanRemastered.Map;

import engine.Direction;
import engine.Map.Map2DTile;
import engine.Map.Map2DTileEvent;
import engine.Sprite;
import game.PacmanRemastered.Game;

/**
 * 
 */
public class PacTileEmpty extends Map2DTile{
    public PacTileEmpty(Map2DTile up, Map2DTile down, Map2DTile left, Map2DTile right, Sprite... initEntities) {
        super(up, down, left, right, initEntities);
    }
    
    public PacTileEmpty(){
        super();
    }

    @Override
    public void onMapEvent(Map2DTileEvent e){
        
    }
    
    @Override
    public String getTileImagePath() {
        return getMap().assetsRoot + "images/pactiles.png";
    }

    @Override
    public boolean canEnterTile(Sprite entity, Direction dir) {
        return true;//Anything can enter a blank tile, and the only add method other classes can't use already checks if two of the same entity enter the tile.
    }
    
    @Override
    public boolean canEnterTile(Sprite entity) {
        return true;//Anything can enter a blank tile, and the only add method other classes can't use already checks if two of the same entity enter the tile.
    }

    @Override
    protected boolean doAddSprite(Sprite entity) {
        return sprites.add(entity);
    }

    @Override
    protected boolean doRemoveSprite(Sprite entity) {
        return sprites.remove(entity);
    }

    @Override
    public int getTileImageX() {
        return 0; //The current tile image has this as the first one.
    }

    @Override
    public int getTileImageY() {
        return 0; //The tile image is horizontal.
    }
    
    public static Map2DTile[][] makeEmptyTileBoardArray(Game game, int numRows, int numColumns){
        Map2DTile[][] tileMaker = new Map2DTile[numRows][numColumns];
        for (int row = 0; row < numRows; row++){
            for (int col = 0; col < numColumns; col++ ){
                PacTileEmpty tile = new PacTileEmpty();
                //tile.add(new PacDot(game));
                tileMaker[row][col] = tile;
            }
        }
        return tileMaker;
    }
}
