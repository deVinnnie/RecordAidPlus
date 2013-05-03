package be.khleuven.recordaid.domain.departement;

import be.khleuven.recordaid.domain.Identifiable;
import java.io.Serializable;
import javax.persistence.*; 

/**
 *
 * @author Vincent Ceulemans
 */
@Entity
public class Lokaal extends Identifiable implements Serializable {
    private String naam; 
    
    @ManyToOne
    private Departement departement; 
   
    public Lokaal(){}
    
    public Lokaal(String naam, Departement departement) {
        this.naam = naam;
        this.departement = departement;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    //</editor-fold>
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.naam != null ? this.naam.hashCode() : 0);
        hash = 23 * hash + (this.departement != null ? this.departement.hashCode() : 0);
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
        final Lokaal other = (Lokaal) obj;
        if ((this.naam == null) ? (other.naam != null) : !this.naam.equals(other.naam)) {
            return false;
        }
        if (this.departement != other.departement && (this.departement == null || !this.departement.equals(other.departement))) {
            return false;
        }
        return true;
    }
}