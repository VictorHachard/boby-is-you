/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.sun.javafx.scene.traversal.Direction;
import exeptions.TypeElementNotFoundException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Windows
 */
public class BoardTest {
    
    public BoardTest() {
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
     * Test of getSizeX method, of class Board.
     */
    @Test
    public void testGetSizeX() throws TypeElementNotFoundException {
        System.out.println("getSizeX");
        Board instance = new Board(new Maps(1,2));
        int expResult = 1;
        int result = instance.getSizeX();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSizeY method, of class Board.
     */
    @Test
    public void testGetSizeY() throws TypeElementNotFoundException {
        System.out.println("getSizeY");
        Board instance = new Board(new Maps(1,2));
        int expResult = 2;
        int result = instance.getSizeY();
        assertEquals(expResult, result);
    }

    /**
     * Test of getListGrid method, of class Board.
     */
    @Test
    public void testGetListGrid() throws TypeElementNotFoundException {
        System.out.println("getListGrid");
        Board instance = new Board(new Maps(1,2));
        List<List<Placement>> expResult = null;
        List<List<Placement>> result = instance.getListGrid();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAffichage method, of class Board.
     */
    @Test
    public void testGetAffichage() throws TypeElementNotFoundException {
        System.out.println("getAffichage");
        Board instance = new Board(new Maps(1,2));
        String expResult = "E_WI|\nE_WI|\n";
        String result = instance.getAffichage();
        assertEquals(expResult, result);
    }

    /**
     * Test of editPlacement method, of class Board.
     */
    @Test
    public void testEditPlacement() throws TypeElementNotFoundException {
        System.out.println("editPlacement");
        Position pos = new Position(2,3);
        Directions direction = Directions.LEFT;
        TypeElement element = TypeElement.HOT;
        Board instance = new Board(new Maps(3,6));
        instance.editPlacement(pos, direction, element);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of movePlayer method, of class Board.
     */
    @Test
    public void testMovePlayer() throws TypeElementNotFoundException {
        System.out.println("movePlayer");
        Directions direction = Directions.DOWN;
        Board instance = new Board(new Maps(3,6));
        instance.movePlayer(direction);
    }

    /**
     * Test of save method, of class Board.
     */
    @Test
    public void testSave_0args() throws Exception {
        System.out.println("save");
        Board instance = new Board(new Maps(2,3));
        instance.save();
    }

    /**
     * Test of save method, of class Board.
     */
    @Test
    public void testSave_String() throws Exception {
        System.out.println("save");
        String fileName = "test.txt";
        Board instance = new Board(new Maps(2,3));
        instance.save(fileName);
    }
    
}
