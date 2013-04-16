package be.khleuven.recordaid.domain.aanvragen;

import be.khleuven.recordaid.domain.Gebruiker;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Vincent Ceulemans
 */
@Entity
public class Dossier implements Serializable { 
    @OneToOne
    @Id
    private Gebruiker gebruiker; 
    
    @OneToOne
    private Geschiedenis geschiedenis; 

}