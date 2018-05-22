/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Pacmanremastered;

import engine.API;
import game.PacmanRemastered.Game;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author csc190
 */
public class Scoreboard extends Frame implements ActionListener, API{
    
    private Label lblCount;
    private TextField tfCount;
    private Button BtnCount;
    private int score = 0;
    
    public Scoreboard(API api){
        setLayout(new FlowLayout());
        lblCount = new Label("Score");
        add(lblCount);
        
        tfCount = new TextField(score);
        tfCount.setEditable(false);
        add(tfCount);
        
        setVisible(true);
    }
    
    public void drawboard(Graphics2D g)
    {
       int i;
       String s;
       Font scoreFont = new Font("Helvetica", Font.BOLD, 14);
       g.setFont(scoreFont);
       g.setColor(new Color(96, 128, 255));
       s = "Score: " + score;
       g.drawString(s, 1, 1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        score=score+100;
        tfCount.setText(score + "");
    }

    @Override
    public void drawImage(int index, String picname, int x, int y, int w, int h) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawSprite(int index, String picname, double x, double y, int w, int h, int fx, int fy) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
