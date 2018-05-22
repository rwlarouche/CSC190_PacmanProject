/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.Map;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
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
public class Map2DLoaderTest {
    
    public Map2DLoaderTest() {
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
     * Test of translateToTile method, of class Map2DLoader.
     */
    @Test
    public void testTranslateToTile() {
        System.out.println("translateToTile");
        char symbol = ' ';
        int row = 0;
        int column = 0;
        Map2DLoader instance = null;
        Map2DTile expResult = null;
        Map2DTile result = instance.translateToTile(symbol, row, column);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadMap method, of class Map2DLoader.
     */
    @Test
    public void testLoadMap_Map2DBuilder_InputStream() throws Exception{
        System.out.println("loadMap");
        Map2DBuilder b = new Map2DBuilder();
        InputStream stream = new ByteArrayInputStream((
"5\n"+
"5\n"+
"wwwww\n" +
"w...w\n" +
"w.p.w\n" +
"w...w\n" +
"wwwww"
                ).getBytes(StandardCharsets.UTF_8));
        Map2DLoader instance = new Map2DLoaderImpl();
        Map2DTile[][] expResult = null;
        Map2DTile[][] result = instance.loadMap(stream);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadMap method, of class Map2DLoader.
     */
    @Test
    public void testLoadMap_Map2DBuilder() throws Exception {
        System.out.println("loadMap");
        Map2DBuilder b = new Map2DBuilder();
        Map2DLoader instance = new Map2DLoaderImpl();
        Map2DTile[][] expResult = null;
        Map2DTile[][] result = instance.loadMap();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class Map2DLoaderImpl extends Map2DLoader {

        public Map2DLoaderImpl() {
            super(null);
        }

        @Override
        public Map2DTile translateToTile(char symbol, int row, int column) {
            return new NullTile();
        }
    }
    
}
