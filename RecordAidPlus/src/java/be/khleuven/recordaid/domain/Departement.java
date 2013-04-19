package be.khleuven.recordaid.domain;

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
}