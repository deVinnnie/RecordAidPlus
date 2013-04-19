package be.khleuven.recordaid.domain.mailing;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*; 

/**
 *
 * @author vincent
 */
public class MailMessageFactoryTest {
    public MailMessageFactoryTest() {
    }
    
    @Test
    public void test_createMailMessages(){
        MailMessageFactory factory = new MailMessageFactory(); 
        List<MailMessage> messages = factory.createMailMessages(); 
        assertEquals(2, messages.size()); 
        assertEquals("Validatie RecordAid account", messages.get(0).getSubject()); 
        System.out.println(messages.get(0).getMessage()); 
    }
}