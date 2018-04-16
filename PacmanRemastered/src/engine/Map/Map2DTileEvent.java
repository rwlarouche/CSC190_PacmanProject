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
public class Map2DTileEvent {
    final public Map2DTile sender;
    final public String event;
    final public Object[] affected;
    
    @Override
    public String toString(){
        return event;
    }
    
    public Map2DTileEvent(Map2DTile sender, String event, Object... affected){ //Object will most likely be Entity, event may be an enum.
        this.sender = sender;
        this.event = event;
        this.affected = affected;
    }
}
