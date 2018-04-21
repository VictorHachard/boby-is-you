/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.model;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Glaskani
 */
public class BoardTest {

    /**
     * Test of getInstance method, of class Board.
     */
    @Test
    public void testGetInstance_Maps() {
        System.out.println("getInstance");
        Maps map = null;
        Board expResult = null;
        Board result = Board.getInstance(map);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
