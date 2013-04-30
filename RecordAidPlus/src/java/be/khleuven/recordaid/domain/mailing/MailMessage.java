package be.khleuven.recordaid.domain.mailing;

import be.khleuven.recordaid.domain.Identifiable;
import java.io.Serializable;
import java.util.Map;
import javax.persistence.*; 
import org.stringtemplate.v4.ST;

/**
 *
 * @author Vincent Ceulemans
 */
@Entity
public class MailMessage extends Identifiable implements Serializable {
    /*ST = StringTemplate: Library for templating with Strings.*/
    @Column(columnDefinition = "CLOB")
    private String message; 
    
    private String subject; 
    
    @Column(unique=true)
    private String description; 
    
    @OneToOne
    private SubjectPrefix subjectPrefix; 
    
    //@Transient = Variable is not part of persitence 
    @Transient
    private String recipient; 
    @Transient
    private String sender; 
    @Transient
    private Map<String, String> context; 
    
    public MailMessage(){}
    
    public MailMessage(String subject, String message, String description){
        this.subject = subject; 
        this.message = message; 
        this.description = description; 
    }
    
    public void setContext(Map<String, String> context){
        this.context = context; 
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
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

    public SubjectPrefix getSubjectPrefix() {
        return subjectPrefix;
    }

    public void setSubjectPrefix(SubjectPrefix subjectPrefix) {
        this.subjectPrefix = subjectPrefix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //</editor-fold>
    
    public String renderSubject(){
        ST subjectTemplate = new ST("$subject_prefix$ " +subject, '$', '$');
        for(Map.Entry<String, String> entry : this.context.entrySet()){
             subjectTemplate.add(entry.getKey(), entry.getValue()); 
        }
        subjectTemplate.add("subject_prefix", subjectPrefix.getSubject_prefix());
        return subjectTemplate.render(); 
    }
    
    public String renderMessage(){
        ST messageTemplate = new ST(message, '$', '$'); 
        for(Map.Entry<String, String> entry : context.entrySet()){
            messageTemplate.add(entry.getKey(), entry.getValue()); 
        }
        return messageTemplate.render(); 
    }
}