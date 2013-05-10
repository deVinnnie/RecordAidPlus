package be.khleuven.recordaid.util;

import be.khleuven.recordaid.domain.DomainException;
import java.util.Calendar;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vincent Ceulemans
 */
public class TimeSpanTest {
    
    public TimeSpanTest() {
    }
    
    @Test
    public void testIsOverlappin_Non_overlapping_timespans_return_false() throws DomainException {
        Calendar calendar1 = Calendar.getInstance();         
        calendar1.set(2013, 01, 16,10,00,00);
        
        Calendar calendar2 = Calendar.getInstance();      
        calendar2.set(2013, 01, 16,11,00,00);
        
        Calendar calendar3 = Calendar.getInstance();      
        calendar3.set(2013, 01, 16,14,00,00);
        
        Calendar calendar4 = Calendar.getInstance();      
        calendar4.set(2013, 01, 16,15,00,00);
        
        TimeSpan timeSpan1 = new TimeSpan(calendar1, calendar2); 
        TimeSpan timeSpan2 = new TimeSpan(calendar3, calendar4); 
        
        boolean isOverlapping;  
        
        isOverlapping = timeSpan1.isOverlapping(timeSpan2);
        assertFalse(isOverlapping); 
        
        isOverlapping = timeSpan2.isOverlapping(timeSpan1);
        assertFalse(isOverlapping); 
    }
    
    @Test
    public void testIsOverlappin_Overlapping_timespans_return_true() throws DomainException {
        Calendar calendar1 = Calendar.getInstance();         
        calendar1.set(2013, 01, 16,10,00,00);
        
        Calendar calendar2 = Calendar.getInstance();      
        calendar2.set(2013, 01, 16,11,00,00);
        
        Calendar calendar3 = Calendar.getInstance();      
        calendar3.set(2013, 01, 16,10,30,00);
        
        Calendar calendar4 = Calendar.getInstance();      
        calendar4.set(2013, 01, 16,11,30,00);
        
        TimeSpan timeSpan1 = new TimeSpan(calendar1, calendar2); 
        TimeSpan timeSpan2 = new TimeSpan(calendar3, calendar4); 
        
        boolean isOverlapping;  
        
        isOverlapping = timeSpan1.isOverlapping(timeSpan2);
        assertTrue(isOverlapping); 
        
        isOverlapping = timeSpan2.isOverlapping(timeSpan1);
        assertTrue(isOverlapping); 
    }
    
    @Test
    public void testIsOverlappin_Adjecent_begin_and_end_return_false() throws DomainException {
        /*Calendar calendar1 = Calendar.getInstance();         
        calendar1.set(2013, 01, 16,10,0,0);
        
        Calendar calendar2 = Calendar.getInstance();      
        calendar2.set(2013, 01, 16,11,0,0);
        
        Calendar calendar3 = Calendar.getInstance();      
        calendar3.set(2013, 01, 16,9,0,0);
        
        Calendar calendar4 = Calendar.getInstance();      
        calendar4.set(2013, 01, 16,10,0,0);
        
        TimeSpan timeSpan1 = new TimeSpan(calendar1, calendar2); 
        TimeSpan timeSpan2 = new TimeSpan(calendar3, calendar4); 
        
        boolean isOverlapping;  
        
        isOverlapping = timeSpan1.isOverlapping(timeSpan2);
        assertFalse(isOverlapping); 
        
        isOverlapping = timeSpan2.isOverlapping(timeSpan1);
        assertFalse(isOverlapping); */
    }
    
    @Test
    public void testIsOverlappin_Adjecent_end_and_begin_return_false() throws DomainException {
        Calendar calendar1 = Calendar.getInstance();         
        calendar1.set(2013, 01, 16,10,0,0);
        
        Calendar calendar2 = Calendar.getInstance();      
        calendar2.set(2013, 01, 16,11,0,0);
        
        Calendar calendar3 = Calendar.getInstance();      
        calendar3.set(2013, 01, 16,11,0,0);
        
        Calendar calendar4 = Calendar.getInstance();      
        calendar4.set(2013, 01, 16,12,0,0);
        
        TimeSpan timeSpan1 = new TimeSpan(calendar1, calendar2); 
        TimeSpan timeSpan2 = new TimeSpan(calendar3, calendar4); 
        
        boolean isOverlapping;  
        
        isOverlapping = timeSpan1.isOverlapping(timeSpan2);
        assertFalse(isOverlapping); 
        
        isOverlapping = timeSpan2.isOverlapping(timeSpan1);
        assertFalse(isOverlapping); 
    }
    
    @Test
    public void testIsOverlappin_otherTimeSpan_contained_in_current_returns_true() throws DomainException {
        Calendar calendar1 = Calendar.getInstance();         
        calendar1.set(2013, 01, 16,10,0,0);
        
        Calendar calendar2 = Calendar.getInstance();      
        calendar2.set(2013, 01, 16,14,0,0);
        
        Calendar calendar3 = Calendar.getInstance();      
        calendar3.set(2013, 01, 16,11,0,0);
        
        Calendar calendar4 = Calendar.getInstance();      
        calendar4.set(2013, 01, 16,12,0,0);
        
        TimeSpan timeSpan1 = new TimeSpan(calendar1, calendar2); 
        TimeSpan timeSpan2 = new TimeSpan(calendar3, calendar4); 
        
        boolean isOverlapping;  
        
        isOverlapping = timeSpan1.isOverlapping(timeSpan2);
        assertTrue(isOverlapping); 
        
        isOverlapping = timeSpan2.isOverlapping(timeSpan1);
        assertTrue(isOverlapping); 
    }
}