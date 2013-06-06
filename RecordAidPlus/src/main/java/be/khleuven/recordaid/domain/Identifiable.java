package be.khleuven.recordaid.domain;

import java.io.Serializable;
import javax.persistence.*; 

/**
 * Definieert een ID voor overervende entiteiten. 
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
     * Constructor met parameters.
     *
     * @param id Het id van het object.
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
     * @param id Het nieuwe id van het object.
     */
    public void setId(long id)
    {
        this.id = id;
    }
}