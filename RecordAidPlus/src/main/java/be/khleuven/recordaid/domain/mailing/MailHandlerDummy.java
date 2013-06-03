package be.khleuven.recordaid.domain.mailing;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Dummy implemenatie bedoeld voor testing. 
 * Print mails uit naar de console. 
 * 
 * @author Vincent Ceulemans
 */
public class MailHandlerDummy extends AbstractMailHandler{
    public MailHandlerDummy(){
        super(); 
    }
    
    public MailHandlerDummy(String path) throws IOException{
        super(path);
    }

    @Override
    public boolean sendMessage(MailMessage mailMessage){
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss"); 
        mailMessage.setSender("recordaidkhl@gmail.com");
        System.out.println("You've got mail!:\n"+
                "Time:" + dateFormatter.format(Calendar.getInstance().getTime()) +"\n"+ 
                "From:" + mailMessage.getSender() + "\n" +
                "To:" + mailMessage.getRecipient() + "\n" +
                "Subject:" + mailMessage.renderSubject() +"\n" +
                mailMessage.renderMessage()); 
        return true; 
    }
}