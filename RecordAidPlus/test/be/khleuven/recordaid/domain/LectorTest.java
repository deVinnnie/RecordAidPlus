/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.khleuven.recordaid.domain;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vincent Ceulemans
 */
public class LectorTest {
    
    public LectorTest() {
    }

    @Test 
    public void test_constructor_naam_wordt_correct_gegeneerd(){
        Lector lector = new Lector("voornaam.middelnaam.achternaam@khleuven.be");
        assertEquals("Voornaam Middelnaam Achternaam", lector.getNaam()); 
    }

    /**
     * Test of equals method, of class Lector.
     */
    @Test
    public void testEquals_zelfde_emailadres_geeft_true() {
        Lector lector = new Lector("voornaam.middelnaam.achternaam@khleuven.be");
        Lector lector2 = new Lector("voornaam.middelnaam.achternaam@khleuven.be");
        assertTrue(lector.equals(lector2)); 
    }
}