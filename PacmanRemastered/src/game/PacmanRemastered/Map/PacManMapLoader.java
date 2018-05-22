/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.PacmanRemastered.Map;

import engine.API;
import engine.Direction;
import engine.Map.Map2DLoader;
import engine.Map.Map2DTile;
import engine.Map.NullTile;
import game.PacmanRemastered.Game;
import game.PacmanRemastered.PacDot;
import game.PacmanRemastered.PacPill;
import game.PacmanRemastered.Pacman;
import ghosts.*;

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
        switch(symbol){
            case 'w':
                return new PacTileWall();
            case 'P':
                return new PacTileEmpty(null,null,null,null, new Pacman(game));
            case '.':
                return new PacTileEmpty(null,null,null,null, new PacDot(game));
            case 'b':
                return new PacTileEmpty(null, null, null, null, new Blinky(game, 0, 0, 0));
            //Blinky starts the game on an empty tile, always.
            case 'p':
                GhostZone gz = new GhostZone(Direction.UP);
                gz.add(new Pinky(game, 0, 0, 0));
                return gz;
            case 'i':
                gz = new GhostZone(Direction.UP);
                gz.add(new Inky(game, 0, 0, 0));
                return gz;
            case 'c':
                gz = new GhostZone(Direction.UP);
                gz.add(new Clyde(game, 0, 0, 0));
                return gz;
            default: 
                return new NullTile();
        }
        
    }
    
}
