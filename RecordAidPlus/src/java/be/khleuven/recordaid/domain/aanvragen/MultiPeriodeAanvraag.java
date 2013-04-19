package be.khleuven.recordaid.domain.aanvragen;

import be.khleuven.recordaid.domain.gebruiker.Gebruiker;
import be.khleuven.recordaid.domain.Lector;
import be.khleuven.recordaid.util.TimeSpan;
import javax.persistence.*; 
import java.util.*; 

/**
 *
 * @author Vincent Ceulemans
 */
@Entity
public class MultiPeriodeAanvraag extends AbstractAanvraag{
    @OneToOne
    private TimeSpan periode; 
    
    @OneToOne
    private Gebruiker begeleider; 
    
    @OneToMany
    private List<Lector> lectoren; 
}