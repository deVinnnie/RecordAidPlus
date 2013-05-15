package be.khleuven.recordaid.domain.departement;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 *
 * @author Vincent Ceulemans
 */
@Entity
public class Departement implements Serializable {
    @Id
    private String naam; 
    
    private boolean actief=false; 
    
    @OneToMany(mappedBy="departement")
    private List<Lokaal> lokalen = new ArrayList<Lokaal>(); 
    
    public Departement(){}
    
    public Departement(String naam, boolean actief){
        this.naam = naam; 
        this.actief = actief; 
    }

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public boolean isActief() {
        return actief;
    }

    public void setActief(boolean actief) {
        this.actief = actief;
    }

    public List<Lokaal> getLokalen() {
        return lokalen;
    }

    public void setLokalen(List<Lokaal> lokalen) {
        this.lokalen = lokalen;
    }

    //</editor-fold>
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.naam != null ? this.naam.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Departement other = (Departement) obj;
        if ((this.naam == null) ? (other.naam != null) : !this.naam.equals(other.naam)) {
            return false;
        }
        return true;
    }
}