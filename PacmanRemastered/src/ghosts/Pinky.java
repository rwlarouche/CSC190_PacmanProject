/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghosts;

import game.PacmanRemastered.Game;
/**
 *
 * @author csc190
 */
public class Pinky extends Ghost{
    public Pinky(Game game,int x, int y, int s){   
            super(game, x, y, s);
       }
    
    @Override
    public void doAI()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getGhostSpriteLocationX() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
