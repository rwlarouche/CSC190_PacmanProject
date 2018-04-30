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
public class Map2DTileEvent {
    final public Map2DTile sender;
    final public String event;
    final public Object[] affected;
    final int thisObjectID;
    
    @Override
    public int hashCode(){
        return thisObjectID;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Map2DTileEvent other = (Map2DTileEvent) obj;
        return this.thisObjectID == other.thisObjectID;
    }
    
    @Override
    public String toString(){
        return event;
    }
    
    public Map2DTileEvent(Map2DTile sender, String event, Object... affected){ //Object will most likely be Entity, event may be an enum.
        this.sender = sender;
        this.event = event;
        this.affected = affected;
        thisObjectID = super.hashCode();//Make sure that if a copy is generated it can still register as the same event.
    }
}
