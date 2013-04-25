package be.khleuven.recordaid.domain.mailing;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Vincent Ceulemans
 */
@Entity
public class SubjectPrefix implements Serializable{
    private String subject_prefix = "[RecordAid]";  
    
    @Id
    private long id;
    
    public SubjectPrefix(){
    }

    public String getSubject_prefix() {
        return subject_prefix;
    }

    public void setSubject_prefix(String subject_prefix) {
        this.subject_prefix = subject_prefix;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof SubjectPrefix){
            return true; 
        }
        return false; 
    }
}