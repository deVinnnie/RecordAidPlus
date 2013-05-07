package be.khleuven.recordaid.domain.gebruiker;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vincent Ceulemans
 */
public class GebruikerTest {
    
    public GebruikerTest() {}

    @Test
    public void testConstructor_Enkel_Emailadres() {
        Gebruiker gebruiker = new Gebruiker("voornaam.de.achternaam@student.khleuven.be");
        assertEquals("voornaam", gebruiker.getVoornaam());
        assertEquals("de achternaam", gebruiker.getAchternaam());      
    }
}