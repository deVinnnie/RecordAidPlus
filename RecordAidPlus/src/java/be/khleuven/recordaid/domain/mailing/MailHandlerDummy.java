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
    public boolean sendMessage(MailMessage mailMessage) {
        return true; 
    }
}