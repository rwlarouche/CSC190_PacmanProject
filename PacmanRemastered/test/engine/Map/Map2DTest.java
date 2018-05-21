/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.Map;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * 
 */
public class Map2DTest {
    
    public Map2DTest() {
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
     * Test of addMapEventListener method, of class Map2D.
     */
    @Test
    public void testAddMapEventListener() {
        System.out.println("addMapEventListener");
        Map2DTileEventListener e = null;
        Map2D instance = null;
        instance.addMapEventListener(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeMapEventListener method, of class Map2D.
     */
    @Test
    public void testRemoveMapEventListener() {
        System.out.println("removeMapEventListener");
        Map2DTileEventListener e = null;
        Map2D instance = null;
        instance.removeMapEventListener(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of onMapEvent method, of class Map2D.
     */
    @Test
    public void testOnMapEvent() {
        System.out.println("onMapEvent");
        Map2DTileEvent e = null;
        Map2D instance = null;
        instance.onMapEvent(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRowCount method, of class Map2D.
     */
    @Test
    public void testGetRowCount() {
        System.out.println("getRowCount");
        Map2D instance = null;
        int expResult = 0;
        int result = instance.getRowCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColumnCount method, of class Map2D.
     */
    @Test
    public void testGetColumnCount() {
        System.out.println("getColumnCount");
        Map2D instance = null;
        int expResult = 0;
        int result = instance.getColumnCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTile method, of class Map2D.
     */
    @Test
    public void testGetTile() {
        System.out.println("getTile");
        int row = 0;
        int column = 0;
        Map2D instance = null;
        Map2DTile expResult = null;
        Map2DTile result = instance.getTile(row, column);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of prepMapTiles method, of class Map2D.
     */
    @Test
    public void testPrepMapTiles() {
        System.out.println("prepMapTiles");
        Map2D instance = null;
        instance.prepMapTiles();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of snapAllSpritesToAllTiles method, of class Map2D.
     */
    @Test
    public void testSnapAllSpritesToAllTiles() {
        System.out.println("snapAllSpritesToAllTiles");
        Map2D instance = null;
        instance.snapAllSpritesToAllTiles();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stream method, of class Map2D.
     */
    @Test
    public void testStream() {
        System.out.println("stream");
        Map2D instance = null;
        Stream<Map2DTile> expResult = null;
        Stream<Map2DTile> result = instance.stream();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iterator method, of class Map2D.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        Map2D instance = null;
        Iterator<Map2DTile> expResult = null;
        Iterator<Map2DTile> result = instance.iterator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of forEach method, of class Map2D.
     */
    @Test
    public void testForEach() {
        System.out.println("forEach");
        Consumer<? super Map2DTile> action = null;
        Map2D instance = null;
        instance.forEach(action);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of spliterator method, of class Map2D.
     */
    @Test
    public void testSpliterator() {
        System.out.println("spliterator");
        Map2D instance = null;
        Spliterator<Map2DTile> expResult = null;
        Spliterator<Map2DTile> result = instance.spliterator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of drawMap method, of class Map2D.
     */
    @Test
    public void testDrawMap() {
        System.out.println("drawMap");
        Map2D instance = null;
        instance.drawMap();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
