package be.khleuven.recordaid.domain.aanvragen;

import be.khleuven.recordaid.domain.Identifiable;
import be.khleuven.recordaid.domain.departement.Lector;
import be.khleuven.recordaid.opnames.OpnameMethode;
import java.io.Serializable;
import java.util.*; 
import javax.persistence.*; 

/**
 *
 * @author Vincent Ceulemans
 */
@Entity
public class Goedkeuring extends Identifiable implements Serializable{
    @OneToOne
    public Lector lector; 
    
    @OneToMany
    private List<OpnameMethode> mogelijkeOpnameMethodes;

    public Goedkeuring() {}

    public Goedkeuring(Lector lector) {
        this.lector = lector;
    }

    public Goedkeuring(Lector lector, List<OpnameMethode> mogelijkeOpnameMethodes) {
        this.lector = lector;
        this.mogelijkeOpnameMethodes = mogelijkeOpnameMethodes;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public Lector getLector() {
        return lector;
    }

    public void setLector(Lector lector) {
        this.lector = lector;
    }

    public List<OpnameMethode> getMogelijkeOpnameMethodes() {
        return mogelijkeOpnameMethodes;
    }

    public void setMogelijkeOpnameMethodes(List<OpnameMethode> mogelijkeOpnameMethodes) {
        this.mogelijkeOpnameMethodes = mogelijkeOpnameMethodes;
    }
    
    public void addMogelijkeOpnameMethode(OpnameMethode methode){
        this.mogelijkeOpnameMethodes.add(methode);
    }
    //</editor-fold>
}