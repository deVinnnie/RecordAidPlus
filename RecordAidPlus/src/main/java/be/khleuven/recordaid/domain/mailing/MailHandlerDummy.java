package be.khleuven.recordaid.domain.mailing;

/**
 *
 * @author Vincent Ceulemans
 */
public class MailHandlerDummy extends AbstractMailHandler{
    public MailHandlerDummy(){
        super(); 
    }
    
    public MailHandlerDummy(String path){
        super(path);
    }

    @Override
    public boolean sendMessage(MailMessage mailMessage){
        System.out.println("You've got mail!:\n"+
                "Subject:" + mailMessage.renderSubject() +"\n" +
                mailMessage.renderMessage()); 
        return true; 
    }
}