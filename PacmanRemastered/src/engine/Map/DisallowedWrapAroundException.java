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
    /**
     * Unsure if I'll use this, but I was thinking that maybe we might want to make sure that tiles may only wrap around the edges where specifically allowed to.
     */
    public class DisallowedWrapAroundException extends Exception{
        public DisallowedWrapAroundException(String message){
            super(message);
        }
    }
