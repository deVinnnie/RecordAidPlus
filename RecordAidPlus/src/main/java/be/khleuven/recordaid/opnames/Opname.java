package be.khleuven.recordaid.opnames;

import be.khleuven.recordaid.domain.Identifiable;
import java.io.Serializable;
import javax.persistence.*; 

/**
 *
 * @author Vincent Ceulemans
 */
@Entity
public class Opname extends Identifiable implements Serializable {
    @OneToOne
    private Locatie locatie = new Locatie();
    
    @Enumerated
    private OpnameStatus status; 
    
    @OneToOne
    private OpnameMethode methode; 

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public Locatie getLocatie() {
        return locatie;
    }

    public void setLocatie(Locatie locatie) {
        this.locatie = locatie;
    }

    public OpnameStatus getStatus() {
        return status;
    }

    public void setStatus(OpnameStatus status) {
        this.status = status;
    }
    
    public OpnameMethode getMethode() {
        return methode;
    }

    public void setMethode(OpnameMethode methode) {
        this.methode = methode;
    }
    //</editor-fold>
}   