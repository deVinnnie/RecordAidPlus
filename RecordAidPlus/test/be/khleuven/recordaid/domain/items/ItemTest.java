package be.khleuven.recordaid.domain.items;

import be.khleuven.recordaid.domain.DomainException;
import be.khleuven.recordaid.domain.gebruiker.Gebruiker;
import be.khleuven.recordaid.domain.gebruiker.Rollen;
import be.khleuven.recordaid.util.TimeSpan;
import java.util.Calendar;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author vincent
 */
public class ItemTest {
    
    private Item item; 
    private Gebruiker gebruiker; 
    
    public ItemTest() {
    }
    
    @Before
    public void before() throws DomainException{
         this.item = new Item("Item"); 
         this.gebruiker = new Gebruiker(Rollen.BUDDY, "dummy@khleuven.be", "Dummy", "Dummer", "wachtwoord"); 
    }

            
            
    @Test
    public void test_Reservatie_toevoegen_is_succesvol() throws DomainException{
        Calendar calendar1 = Calendar.getInstance();         
        calendar1.set(2013, 01, 16,10,00,00);
        
        Calendar calendar2 = Calendar.getInstance();      
        calendar2.set(2013, 01, 16,11,00,00);
        
        Calendar calendar3 = Calendar.getInstance();      
        calendar3.set(2013, 01, 16,14,00,00);
        
        Calendar calendar4 = Calendar.getInstance();      
        calendar4.set(2013, 01, 16,15,00,00);
        
        Reservatie reservatie = new Reservatie(new TimeSpan(calendar1, calendar2), gebruiker); 
        Reservatie reservatie2 = new Reservatie(new TimeSpan(calendar3, calendar4), gebruiker); 
        this.item.addReservatie(reservatie);
        this.item.addReservatie(reservatie2);
    }
}