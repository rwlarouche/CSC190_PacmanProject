/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.Map;

import engine.API;
import game.PacmanRemastered.Game;

/**
 *
 * 
 */
    public class Map2DBuilder{
        public String name;
        public String rootLevelPath = null;
        public String assetsRoot = null;
        public double topLeftX, topLeftY;
        public int tileSizeW, tileSizeH;
        public Game game;
        public API api;
        /**
         * Array of tiles, stored left to right. Null tiles will be replaced with a default tile class that allows nothing to enter.
         */
        public Map2DTile[][] mapGrid;
        
        public Map2D build(){
            return new Map2D(this);
        }
        
        public Map2DBuilder(){}
    }


