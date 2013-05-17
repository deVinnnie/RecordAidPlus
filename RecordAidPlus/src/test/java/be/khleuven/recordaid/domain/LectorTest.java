package be.khleuven.recordaid.domain;

import be.khleuven.recordaid.domain.departement.Lector;
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
}