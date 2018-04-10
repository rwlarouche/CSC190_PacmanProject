/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.Map;

import java.util.EventListener;

/**
 *
 * @author CMM
 */
public interface Map2DTileEventListener extends EventListener{
    public void raiseMapEvent(Map2DTileEvent e);
}
