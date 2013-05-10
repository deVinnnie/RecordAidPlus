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
        assertEquals("Voornaam", gebruiker.getVoornaam());
        assertEquals("De Achternaam", gebruiker.getAchternaam());      
    }
}