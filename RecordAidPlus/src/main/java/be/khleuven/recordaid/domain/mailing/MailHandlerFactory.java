package be.khleuven.recordaid.domain.mailing;

import java.io.IOException;

/**
 * Factory voor het maken van MailHandlers. 
 * 
 * @author Vincent Ceulemans
 */
public class MailHandlerFactory {
    public AbstractMailHandler createMailHandler(String type, String path) throws IOException{
        AbstractMailHandler mailHandler = null; 
        if(type.equals("MailHandlerDummy")){
            mailHandler = new MailHandlerDummy(path); 
        }
        else if(type.equals("MailHandler")){
            mailHandler = new MailHandler(path); 
        }
        return mailHandler; 
    }
}