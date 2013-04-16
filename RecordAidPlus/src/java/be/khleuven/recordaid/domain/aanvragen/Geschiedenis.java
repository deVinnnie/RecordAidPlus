package be.khleuven.recordaid.domain.aanvragen;

import java.io.Serializable;
import java.util.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * This class holds a series of events. 
 * 
 * @author Vincent Ceulemans
 */
@Entity
public class Geschiedenis implements Serializable{
    @ManyToMany
    private List<Gebeurtenis> gebeurtenissen = new ArrayList<Gebeurtenis>(); 
    
    @Id
    private Long id;

    public Geschiedenis() {
    }
    
    public void addGebeurtenis(Gebeurtenis gebeurtenis){
        this.gebeurtenissen.add(gebeurtenis);
    }        
    
    public Gebeurtenis getGebeurtenis(int i){
        return this.gebeurtenissen.get(i);
    }
    
    public List<Gebeurtenis> getGebeurtenises(){
        return this.gebeurtenissen; 
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}