/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.Map;

/**
 *
 * 
 */
    public class Map2DBuilder{
        String name;
        String rootPath;
        int tileSizeX, tileSizeY;
        /**
         * Array of tiles, stored left to right. Null tiles will be replaced with a default tile class that allows nothing to enter.
         */
        Map2DTile[][] mapGrid;
        
        public Map2D build(){
            return new Map2D(this);
        }
    }
