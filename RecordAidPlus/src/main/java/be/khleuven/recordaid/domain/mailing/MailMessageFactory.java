package be.khleuven.recordaid.domain.mailing;

import java.io.IOException;
import java.util.*; 
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.*; 
import org.jdom.input.SAXBuilder;

/**
 * Creates a set of standard MailMessages based on an XML-file.
 * 
 * @author Vincent Ceulemans
 */
public class MailMessageFactory {
    private String filename; 

    public MailMessageFactory() {
        this.filename = getClass().getClassLoader().getResource("/META-INF/default_mail_messages.xml").toString();
    }

    public MailMessageFactory(String filename) {
        this.filename = filename; 
    }
    
    public List<MailMessage> createMailMessages(){
        List<MailMessage> mailMessages = new ArrayList<MailMessage>(); 
        
        try {
            SAXBuilder builder = new SAXBuilder(false); 
            Document doc =builder.build(filename);
            Element rootElement = doc.getRootElement();
            List<Element> children = rootElement.getChildren(); 
            
            for(Element element : children){
                MailMessage message = new MailMessage(
                    element.getChild("subject").getText(), 
                    element.getChild("content").getText(), 
                    element.getChild("description").getText()
                ); 
                mailMessages.add(message);
            }
        } catch (JDOMException ex) {
            Logger.getLogger(MailMessageFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MailMessageFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mailMessages;
    }
}