/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.PacmanRemastered.Map;

import engine.API;
import engine.Map.Map2DLoader;
import engine.Map.Map2DTile;
import game.PacmanRemastered.Game;

/**
 * Loads a PacMan map.
 * 
 * 
 * Scott, delete the following note after reading this: this should know exactly what to do when returning based on a single character.
 */
public class PacManMapLoader extends Map2DLoader{

    public final Game game;

    public PacManMapLoader(API api, Game game) {
        super(api);
        this.game = game;
    }

    @Override
    public Map2DTile translateToTile(char symbol, int row, int column) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
