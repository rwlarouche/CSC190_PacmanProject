/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghosts;

import engine.Direction;
import engine.GameEngine;
import engine.Map.Map2DTile;
import engine.Map.Map2DTileEvent;
import engine.Sprite;
import game.PacmanRemastered.Game;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
/**
 *
 * @author csc190
 */
public class Clyde extends Ghost{
    
    public Clyde(Game game,int x, int y, int s){   
            super(game, x, y, s);
       }
    
    @Override
    public void doAI()
    {
        // AI code here
    }

    @Override
    public int getGhostSpriteLocationX() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    


}
