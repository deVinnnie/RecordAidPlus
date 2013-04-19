package be.khleuven.recordaid.domain.aanvragen;

import java.io.Serializable;
import javax.persistence.*; 

/**
 *
 * @author Vincent Ceulemans
 */
@Entity
public class OpnameMethode implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String beschrijving; 

    public OpnameMethode() {
    }
    
    public OpnameMethode(String beschrijving){
        this.beschrijving= beschrijving; 
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}