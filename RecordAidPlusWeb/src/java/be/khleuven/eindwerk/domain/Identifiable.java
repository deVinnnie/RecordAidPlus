package be.khleuven.eindwerk.domain;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


/**
 * Klasse waarvan overge-erfd kan worden door klassen die een id willen
 * toevoegen.
 *
 * @author Maxime Van den Kerkhof
 */
@MappedSuperclass
public class Identifiable implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    /**
     * Constructor zonder parameters, nodig voor Java Persistency.
     */
    public Identifiable()
    {
    }


    /**
     * /**
     * Constructor met parameters.
     *
     * @param id long dat het id van het object moet zijn.
     */
    public Identifiable(long id)
    {
        if(id > -1)
        {
            this.id = id;
        }
    }


    /**
     * Geeft het id van het object terug.
     *
     * @return long die het id van het object is.
     */
    public long getId()
    {
        return id;
    }


    /**
     * Setter om het id van het object te setten.
     *
     * @param id long dat het id van het object moet zijn.
     */
    public void setId(long id)
    {
        this.id = id;
    }
}


