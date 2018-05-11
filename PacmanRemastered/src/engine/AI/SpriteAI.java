/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.AI;

import engine.Map.Map2DTile;
import engine.Sprite;

/**
 *
 * 
 */
public interface SpriteAI extends Sprite{
   void setTarget(Sprite target);
   void setTarget(Map2DTile target);
   boolean buildPath();
}
