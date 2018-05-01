/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.PacmanRemastered.Map;

import engine.Map.Map2D;
import engine.Map.Map2DBuilder;
import engine.Map.Map2DCoords;
import engine.Map.Map2DTile;
import engine.Map.Map2DTileEvent;
import engine.Sprite;
import game.PacmanRemastered.Game;
import game.PacmanRemastered.PacDot;
import game.PacmanRemastered.Pacman;
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
public class PacTileEmptyTest {
    
    public PacTileEmptyTest() {
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
     * Test of onMapEvent method, of class PacTileEmpty.
     */
    //@Test
    public void testOnMapEvent() {
        System.out.println("onMapEvent");
        Map2DTileEvent e = null;
        PacTileEmpty instance = new PacTileEmpty();
        instance.onMapEvent(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTileImagePath method, of class PacTileEmpty.
     */
    //@Test
    public void testGetTileImagePath() {
        System.out.println("getTileImagePath");
        PacTileEmpty instance = new PacTileEmpty();
        String expResult = "";
        String result = instance.getTileImagePath();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of canEnterTile method, of class PacTileEmpty.
     */
    //@Test
    public void testCanEnterTile() {
        System.out.println("canEnterTile");
        Sprite entity = null;
        PacTileEmpty instance = new PacTileEmpty();
        boolean expResult = false;
        boolean result = instance.canEnterTile(entity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of doAddSprite method, of class PacTileEmpty.
     */
    //@Test
    public void testDoAddSprite() {
        System.out.println("doAddSprite");
        Sprite entity = null;
        PacTileEmpty instance = new PacTileEmpty();
        boolean expResult = false;
        boolean result = instance.doAddSprite(entity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of doRemoveSprite method, of class PacTileEmpty.
     */
    //@Test
    public void testDoRemoveSprite() {
        System.out.println("doRemoveSprite");
        Sprite entity = null;
        PacTileEmpty instance = new PacTileEmpty();
        boolean expResult = false;
        boolean result = instance.doRemoveSprite(entity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class PacTileEmpty.
     */
    //@Test
    public void testUpdate() {
        System.out.println("update");
        PacTileEmpty instance = new PacTileEmpty();
        instance.update();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTileImageX method, of class PacTileEmpty.
     */
    //@Test
    public void testGetTileImageX() {
        System.out.println("getTileImageX");
        PacTileEmpty instance = new PacTileEmpty();
        int expResult = 0;
        int result = instance.getTileImageX();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTileImageY method, of class PacTileEmpty.
     */
    //@Test
    public void testGetTileImageY() {
        System.out.println("getTileImageY");
        PacTileEmpty instance = new PacTileEmpty();
        int expResult = 0;
        int result = instance.getTileImageY();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of MakeEmptyMap method, of class PacTileEmpty.
     */
    @Test
    public void testMapGen() {
        System.out.println("MakeEmptyMap");
        int numRows = 5;
        int numColumns = 5;
        Map2D expResult = null;
        Map2DBuilder b = new Map2DBuilder();
        b.rootLevelPath = "";
        b.assetsRoot = "";
        b.tileSizeW = 64;
        b.tileSizeH = 64;
        b.game = new Game();
        b.mapGrid = PacTileEmpty.makeEmptyTileBoardArray(numRows, numColumns);
        b.mapGrid[b.mapGrid.length/2][(b.mapGrid[0].length)/2].add(new Pacman(b.game));
        b.mapGrid[1] [2].add(new PacDot(b.game));
        Map2D result = b.build();
        boolean foundSprite = false;
        for (Map2DTile e: result){
            if (e.getMap() == null || (e.getUp() == null && e.getLeft() == null && e.getDown() == null && e.getRight() == null))
                fail("Map tiles missing associations.");
            for (Sprite s: e){
                Map2DCoords coords = e.getAbsCoordinates();
                if (s.getX() != coords.x || s.getY() != e.getAbsCoordinates().y) {
                    fail("One or more sprites un-centered!");
                }
                foundSprite = true;
            }
        }
        if (!foundSprite)
            fail("There were no sprites in the map!");
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
