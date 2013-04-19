package be.khleuven.recordaid.domain.mailing;

import java.io.Serializable;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.stringtemplate.v4.ST;

/**
 *
 * @author Vincent Ceulemans
 */
@Entity
public class MailMessage implements Serializable {
    @Id
    private Long id;
    
    /*ST = StringTemplate: Library for templating with Strings.*/
    private String message; 
    private String subject; 
    
    private String recipient; 
    private String sender; 
    
    public MailMessage(){}
    
    public MailMessage(String subject, String message){
        this.subject = subject; 
        this.message = message; 
    }
    
    public void setContext(Map<String, String> context){
        ST subjectTemplate = new ST(subject);
        ST messageTemplate = new ST(message); 
        for(Map.Entry<String, String> entry : context.entrySet()){
            messageTemplate.add(entry.getKey(), entry.getValue()); 
            subjectTemplate.add(entry.getKey(), entry.getValue()); 
        }
        this.message = messageTemplate.render(); 
        this.subject = subjectTemplate.render(); 
    }
    
    public void setMessage(String message){
        this.message = message; 
    }
    
    public String getMessage(){
        return message; 
    }
    
    public void setSubject(String subject){
        this.subject = subject; 
    }
    
    public String getSubject(){
        return subject; 
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}