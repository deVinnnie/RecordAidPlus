package be.khleuven.recordaid.domain.aanvragen;

import be.khleuven.recordaid.domain.Gebruiker;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Vincent Ceulemans
 */
@Entity
public class Gebeurtenis implements Serializable {
    @Id
    private Long id;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar tijdstip = Calendar.getInstance(); 
    
    @OneToOne
    private Gebruiker betrokkenGebruiker; 
    
    private String message; 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Gebeurtenis(){}
    
    public Gebeurtenis(String message, Gebruiker betrokkenGebruiker){
        this.betrokkenGebruiker = betrokkenGebruiker; 
        this.message = message; 
    }
}