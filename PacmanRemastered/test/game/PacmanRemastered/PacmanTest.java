/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.PacmanRemastered;

import engine.GameEngine;
import engine.Map.Map2DTile;
import engine.Map.Map2DTileEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author "[ ]"
 */
public class PacmanTest {
    
    public PacmanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getLocationX method, of class Pacman.
     */
    @org.junit.Test
    public void testGetLocationX() {
        System.out.println("getLocationX");
        Pacman instance = null;
        double expResult = 0.0;
        double result = instance.getLocationX();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLocationY method, of class Pacman.
     */
    @org.junit.Test
    public void testGetLocationY() {
        System.out.println("getLocationY");
        Pacman instance = null;
        double expResult = 0.0;
        double result = instance.getLocationY();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class Pacman.
     */
    @org.junit.Test
    public void testUpdate() {
        System.out.println("update");
        Pacman instance = null;
        instance.update();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of draw method, of class Pacman.
     */
    @org.junit.Test
    public void testDraw() {
        System.out.println("draw");
        int index = 0;
        GameEngine api = null;
        Pacman instance = null;
        instance.draw(index, api);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMapTile method, of class Pacman.
     */
    @org.junit.Test
    public void testGetMapTile() {
        System.out.println("getMapTile");
        Pacman instance = null;
        Map2DTile expResult = null;
        Map2DTile result = instance.getMapTile();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMapTile method, of class Pacman.
     */
    @org.junit.Test
    public void testSetMapTile() {
        System.out.println("setMapTile");
        Map2DTile tile = null;
        Pacman instance = null;
        instance.setMapTile(tile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of onMapEvent method, of class Pacman.
     */
    @org.junit.Test
    public void testOnMapEvent() {
        System.out.println("onMapEvent");
        Map2DTileEvent e = null;
        Pacman instance = null;
        instance.onMapEvent(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
