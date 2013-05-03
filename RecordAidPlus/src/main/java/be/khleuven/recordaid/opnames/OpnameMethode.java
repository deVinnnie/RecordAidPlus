package be.khleuven.recordaid.opnames;

import be.khleuven.recordaid.domain.Identifiable;
import java.io.Serializable;
import javax.persistence.*; 

/**
 *
 * @author Vincent Ceulemans
 */
@Entity
public class OpnameMethode extends Identifiable implements Serializable {
    private String naam; 
    private String beschrijving; 

    public OpnameMethode() {}
    
    public OpnameMethode(String naam, String beschrijving){
        this.beschrijving= beschrijving; 
    }

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public void setBeschrijving(String beschrijving){
        this.beschrijving = beschrijving; 
    }
    
    public String getBeschrijving() {
        return beschrijving;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }
    //</editor-fold>
}