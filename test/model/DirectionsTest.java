/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import common.model.Directions;
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
public class DirectionsTest {
    
    public DirectionsTest() {
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
     * Test of values method, of class Directions.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        Directions[] expResult = null;
        Directions[] result = Directions.values();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of valueOf method, of class Directions.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String name = "";
        Directions expResult = null;
        Directions result = Directions.valueOf(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fromString method, of class Directions.
     */
    @Test
    public void testFromString() {
        System.out.println("fromString");
        int direction = 0;
        Directions expResult = null;
        Directions result = Directions.fromString(direction);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDir method, of class Directions.
     */
    @Test
    public void testGetDir() {
        System.out.println("getDir");
        Directions instance = null;
        int expResult = 0;
        int result = instance.getDir();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDirVer method, of class Directions.
     */
    @Test
    public void testGetDirVer() {
        System.out.println("getDirVer");
        Directions instance = null;
        int expResult = 0;
        int result = instance.getDirVer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDirHori method, of class Directions.
     */
    @Test
    public void testGetDirHori() {
        System.out.println("getDirHori");
        Directions instance = null;
        int expResult = 0;
        int result = instance.getDirHori();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
