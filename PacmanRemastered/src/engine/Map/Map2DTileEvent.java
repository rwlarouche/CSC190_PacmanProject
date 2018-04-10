/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.Map;

/**
 *
 * @author CMM
 */
public abstract class Map2DTileEvent {
    final public Map2DTile sender;
    final public String event;
    final public Object[] affected;
    
    @Override
    public String toString(){
        return event;
    }
    
    public Map2DTileEvent(Map2DTile sender, String event, Object... affected){
        this.sender = sender;
        this.event = event;
        this.affected = affected;
    }
    
}
