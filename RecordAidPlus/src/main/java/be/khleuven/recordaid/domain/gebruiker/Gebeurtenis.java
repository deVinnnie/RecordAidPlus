package be.khleuven.recordaid.domain.gebruiker;

import be.khleuven.recordaid.domain.Identifiable;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.*; 

/**
 *
 * @author Vincent Ceulemans
 */
@Entity
public class Gebeurtenis extends Identifiable implements Serializable {
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar tijdstip = Calendar.getInstance(); 
    
    @OneToOne
    private Gebruiker betrokkenGebruiker; 
    
    private String message; 

    public Gebeurtenis(){}
    
    public Gebeurtenis(String message, Gebruiker betrokkenGebruiker){
        this.betrokkenGebruiker = betrokkenGebruiker; 
        this.message = message; 
    }

    public Calendar getTijdstip() {
        return tijdstip;
    }

    public void setTijdstip(Calendar tijdstip) {
        this.tijdstip = tijdstip;
    }

    public Gebruiker getBetrokkenGebruiker() {
        return betrokkenGebruiker;
    }

    public void setBetrokkenGebruiker(Gebruiker betrokkenGebruiker) {
        this.betrokkenGebruiker = betrokkenGebruiker;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}