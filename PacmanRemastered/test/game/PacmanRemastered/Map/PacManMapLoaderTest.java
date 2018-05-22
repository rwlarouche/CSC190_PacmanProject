/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.PacmanRemastered.Map;

import engine.Map.Map2DBuilder;
import engine.Map.Map2DLoader;
import engine.Map.Map2DLoaderTest;
import engine.Map.Map2DTile;
import game.PacmanRemastered.Game;
import java.io.ByteArrayInputStream;
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
public class PacManMapLoaderTest {
    
    public PacManMapLoaderTest() {
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
     * Test of translateToTile method, of class PacManMapLoader.
     */
    //@Test
    public void testTranslateToTile() {
        System.out.println("translateToTile");
        char symbol = ' ';
        int row = 0;
        int column = 0;
        PacManMapLoader instance = null;
        Map2DTile expResult = null;
        Map2DTile result = instance.translateToTile(symbol, row, column);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testLoadMap_Map2DBuilder_InputStream() throws Exception{
        System.out.println("loadMap");
        Map2DBuilder b = new Map2DBuilder();
        InputStream stream = new ByteArrayInputStream((
"5\n"+
"5\n"+
"wwwww\n" +
"w...w\n" +
"w.P.w\n" +
"w...w\n" +
"wwwww"
                ).getBytes(StandardCharsets.UTF_8));
        Map2DLoader instance = new PacManMapLoader(null, new Game(null));
        
        Map2DTile[][] result = instance.loadMap(stream);
        System.out.println("No time for proper checking; set a breakpoint and inspect the output.");
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}
