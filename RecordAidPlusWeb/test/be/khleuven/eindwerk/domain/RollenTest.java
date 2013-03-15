package be.khleuven.eindwerk.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vincent Ceulemans
 */
public class RollenTest {
    
    public RollenTest() {
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
     * Test of valueOf method, of class Rollen.
     */
    @Test(expected=IllegalArgumentException.class)
    public void testValueOf_Value_does_not_exist() {
        String name = "DitIsGeenBesetaandeRol";
        Rollen expResult = null;
        Rollen result = Rollen.valueOf(name);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of valueOf method, of class Rollen.
     */
    @Test
    public void testValueOf_Value_exists(){
        String name = "Student";
        Rollen expResult = Rollen.STUDENT;
        Rollen result = Rollen.valueOf(name.toUpperCase());
        assertEquals(expResult, result); 
    }
}