package be.khleuven.recordaid.domain.gebruiker;

import be.khleuven.recordaid.domain.Identifiable;
import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * This class holds a series of events. 
 * 
 * @author Vincent Ceulemans
 */
@Entity
public class Geschiedenis extends Identifiable implements Serializable{
    @OneToMany
    private List<Gebeurtenis> gebeurtenissen = new ArrayList<Gebeurtenis>(); 
    //Use stack for a last in, first out list. Because the history is in reverse chronological order. 
    
    public Geschiedenis() {}
    
    public void addGebeurtenis(Gebeurtenis gebeurtenis){
        this.gebeurtenissen.add(gebeurtenis); 
    }        
    
    public Gebeurtenis getGebeurtenis(int i){
        return this.gebeurtenissen.get(i);
    }
    
    public List<Gebeurtenis> getGebeurtenissen(){
        return this.gebeurtenissen; 
    }
}