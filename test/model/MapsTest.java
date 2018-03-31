/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import common.model.Maps;
import common.model.Directions;
import common.model.Board;
import common.model.Element;
import common.model.TypeElement;
import common.exeptions.TypeElementNotFoundException;
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
public class MapsTest {
    
    public MapsTest() {
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
     * Test of addMap method, of class Maps.
     */
    @Test
    public void testAddMap() throws Exception {
        System.out.println("addMap");
        int x = 0;
        int y = 0;
        Directions directions = null;
        TypeElement object = null;
        Maps instance = null;
        instance.addMap(x, y, directions, object);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeMap method, of class Maps.
     */
    @Test
    public void testRemoveMap() throws Exception {
        System.out.println("removeMap");
        int x = 0;
        int y = 0;
        Element elem = null;
        Maps instance = null;
        instance.removeMap(x, y, elem);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getListElement method, of class Maps.
     */
    @Test
    public void testGetListElement() {
        System.out.println("getListElement");
        int x = 0;
        int y = 0;
        Maps instance = null;
        List<Element> expResult = null;
        List<Element> result = instance.getListElement(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSizeX method, of class Maps.
     */
    @Test
    public void testGetSizeX() {
        System.out.println("getSizeX");
        Maps instance = new Maps(5,6);
        int expResult = 5;
        int result = instance.getSizeX();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSizeY method, of class Maps.
     */
    @Test
    public void testGetSizeY() {
        System.out.println("getSizeY");
        Maps instance = new Maps(5,6);
        int expResult = 6;
        int result = instance.getSizeY();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAffichage method, of class Maps.
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
     * Test of getForget method, of class Maps.
     */
    @Test
    public void testGetForget() {
        System.out.println("getForget");
        Maps instance = null;
        List<TypeElement> expResult = null;
        List<TypeElement> result = instance.getForget();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of save method, of class Maps.
     */
    @Test
    public void testSave_0args() throws Exception {
        System.out.println("save");
        Maps instance = null;
        instance.save();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of save method, of class Maps.
     */
    @Test
    public void testSave_String() throws Exception {
        System.out.println("save");
        String fileName = "";
        Maps instance = null;
        instance.save(fileName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
