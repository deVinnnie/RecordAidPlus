package be.khleuven.recordaid.domain.aanvragen;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Vincent Ceulemans
 */
@Entity
public class Opname implements Serializable {
    @Id 
    private Long id;
    
    private String locatie;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}   