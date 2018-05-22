/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.PacmanRemastered;

import engine.API;
import engine.Map.Events.TileSpriteTraverseEvent;
import java.awt.Color;
import java.awt.Font;
import static java.awt.Font.BOLD;
import java.awt.Graphics2D;
import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;
import static javafx.scene.text.FontWeight.BOLD;
import engine.Map.Map2DTile;
import engine.Map.Map2DTileEvent;
import javax.swing.JLabel;
import engine.GameEngine;
import engine.Map.Events.TileSpriteTraverseEvent;
import engine.Sprite;

/**
 *
 * @author csc190
 */
public class Scoreboard implements API{
    
    Game game;
    public int score = 0;
    double x,y, width, height;
    Font displayFont = new Font("Helvetica", Font.BOLD, 14);
    
    public Scoreboard(API api){
        x=y=0;
        this.game = game;
        width = game.width;
        height = game.height;
    }
    
    JLabel scoreboard = new JLabel("Score: " +score);

    public void showScore(Graphics2D g)
    {
        int i;
        String s;
        g.setFont(displayFont);
        g.setColor(new Color(96, 128, 255));
        s="Score: "+score;
        g.drawString(s, 1, 1);
    }
  
    public void updateScore(Map2DTileEvent e){
      if(e instanceof TileSpriteTraverseEvent)
        {
                score=score+100;            
        }
    }

    @Override
    public void drawImage(int index, String picname, int x, int y, int w, int h) {
        
    }

    @Override
    public void drawSprite(int index, String picname, double x, double y, int w, int h, int fx, int fy) {
        
    }
}
