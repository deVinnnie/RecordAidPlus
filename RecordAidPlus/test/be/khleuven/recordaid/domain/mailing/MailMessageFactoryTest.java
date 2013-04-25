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
        assertEquals(3, messages.size()); 
        assertEquals("Validatie RecordAid account", messages.get(0).getSubject()); 
    }
    
    @Test
    public void test_whitespace_in_messages(){
        MailMessageFactory factory = new MailMessageFactory(); 
        List<MailMessage> messages = factory.createMailMessages(); 
        System.out.println(messages.get(0).getMessage()); 
    }
}